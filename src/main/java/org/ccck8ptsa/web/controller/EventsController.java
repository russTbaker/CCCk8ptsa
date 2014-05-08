/*
 * Copyright (c) 2014, RBC, LLC.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the RBC, LLC. nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL RBC, LLC. BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.ccck8ptsa.web.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.ccck8ptsa.persistence.entity.Event;
import org.ccck8ptsa.persistence.entity.NewsEvent;
import org.ccck8ptsa.persistence.entity.Sponsor;
import org.ccck8ptsa.service.api.EventService;
import org.ccck8ptsa.web.forms.EventForm;
import org.ccck8ptsa.web.forms.NewsEventForm;
import org.ccck8ptsa.web.forms.SponsorForm;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * EventsController.java
 *
 * @author: Russ
 * @since Jan 20, 2014:11:09:53 AM
 */
@Controller(value = "eventsController")
@RequestMapping("/events")
public class EventsController {

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/monthlyevents", method = RequestMethod.GET)
    public String getThisMonthsEvents(ModelMap model, HttpServletRequest request) {
        List<Event> thisMonthsEvents = getThisMonthsEvents();
        List<Event> upcomingEvents = getThisWeeksEvents();
        model.addAttribute("thisMonthsEvents", thisMonthsEvents);
        model.addAttribute("upcomingEvents", upcomingEvents);
        return "welcome";
    }

    @RequestMapping( method = RequestMethod.GET)
    public String getAddEventForm(ModelMap model, HttpServletRequest request){
        EventForm eventForm = new EventForm();
        SponsorForm sponsorForm = new SponsorForm();
        eventForm.getSponsorForms().add(sponsorForm);
        NewsEventForm newsEventForm = new NewsEventForm();
        eventForm.getNewsEventForms().add(newsEventForm);
        model.addAttribute("eventForm",eventForm);
        return "events";
    }

    @RequestMapping(value="/addevent",method = RequestMethod.POST)
    public String createEvent(ModelMap model, @Valid EventForm eventForm) {
        createEvent(eventForm);
        model.addAttribute("eventForm", eventForm);
        return "eventAdded";
    }

    private List<Event> getThisWeeksEvents() {
        int oneWeek = 7;
        Date sevenDaysOut = new LocalDate().plusDays(oneWeek).toDate();
        Timestamp sevenDaysOutTimestamp = new Timestamp(sevenDaysOut.getTime());

        Timestamp nowTimestamp = new Timestamp(new Date().getTime());
        return eventService.getEventsByDateRange(nowTimestamp, sevenDaysOutTimestamp, oneWeek);
    }

    private List<Event> getThisMonthsEvents() {
        Date endOfMonth = new LocalDate().dayOfMonth().withMaximumValue().toDate();
        Date startOfMonth = new LocalDate().withDayOfMonth(1).toDate();
        Timestamp startMonthTimestamp = new Timestamp(startOfMonth.getTime());
        Timestamp endMonthTimestamp = new Timestamp(endOfMonth.getTime());

        // Get the full months TODO: figure out how to find how many days left in month
        return eventService.getEventsByDateRange(startMonthTimestamp,
                endMonthTimestamp, 31);
    }


    protected Event createEvent(final EventForm eventForm) {
        return eventService.addEvent(populateEventFromEventForm(eventForm));
    }

    protected Event updateEvent(final EventForm eventForm) {
        return eventService.updateEvent(populateEventFromEventForm(eventForm));
    }

    protected void deleteEvent(final EventForm eventForm){
        eventService.deleteEvent(eventForm.getId());
    }

    private Event populateEventFromEventForm(final EventForm eventForm) {
        Event event = new Event();
        List<NewsEventForm> newsEventForm = eventForm.getNewsEventForms();
        List<SponsorForm> sponsorForm = eventForm.getSponsorForms();
        try {
            BeanUtils.copyProperties(event, eventForm);
            if (newsEventForm != null) {
                addNewsEvent(event, newsEventForm);
            }
            if (sponsorForm != null) {
                addSponsor(event, sponsorForm);
            }
        } catch (Exception e) {
            throw new EventNotCreatedException(e);
        }
        return event;
    }

    private void addSponsor(Event event, List<SponsorForm> sponsorForms) throws IllegalAccessException, InvocationTargetException {
        for (SponsorForm sponsorForm : sponsorForms) {
            Sponsor sponsor = new Sponsor();
            BeanUtils.copyProperties(sponsor, sponsorForm);
            event.getSponsors().add(sponsor);
        }
    }

    private void addNewsEvent(Event event, List<NewsEventForm> newsEventForms) throws IllegalAccessException, InvocationTargetException {
        for (NewsEventForm newsEventForm:newsEventForms) {
            NewsEvent newsEvent = new NewsEvent();
            BeanUtils.copyProperties(newsEvent, newsEventForm);
            event.getNewsEvents().add(newsEvent);
        }
    }


    public static class EventNotCreatedException extends RuntimeException {
        public EventNotCreatedException(String message) {
            super(message);
        }

        public EventNotCreatedException(String message, Throwable cause) {
            super(message, cause);
        }

        public EventNotCreatedException(Throwable cause) {
            super(cause);
        }

        public EventNotCreatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }

        public EventNotCreatedException() {
        }
    }
}
