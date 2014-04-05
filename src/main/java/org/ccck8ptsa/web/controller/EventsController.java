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

import org.ccck8ptsa.persistence.entity.Event;
import org.ccck8ptsa.service.api.EventService;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value="/monthlyevents",method = RequestMethod.GET)
    public String getThisMonthsEvents(ModelMap model, HttpServletRequest request) {
        List<Event> thisMonthsEvents = getThisMonthsEvents();
        List<Event> upcomingEvents = getThisWeeksEvents();
        model.addAttribute("thisMonthsEvents",thisMonthsEvents);
        model.addAttribute("upcomingEvents",upcomingEvents);
        return "welcome";
    }

    private List<Event> getThisWeeksEvents() {
        int oneWeek = 7;
        Date sevenDaysOut = new LocalDate().plusDays(oneWeek).toDate();
        Timestamp sevenDaysOutTimestamp = new Timestamp(sevenDaysOut.getTime());

        Timestamp nowTimestamp = new Timestamp(new Date().getTime());
        List<Event> upcomingEvents = eventService.getEventsByDateRange(nowTimestamp,sevenDaysOutTimestamp, oneWeek);
        return upcomingEvents;
    }

    private List<Event> getThisMonthsEvents() {
        Date endOfMonth = new LocalDate().dayOfMonth().withMaximumValue().toDate();
        Date startOfMonth = new LocalDate().withDayOfMonth(1).toDate();
        Timestamp startMonthTimestamp = new Timestamp(startOfMonth.getTime());
        Timestamp endMonthTimestamp = new Timestamp(endOfMonth.getTime());

        // Get the full months TODO: figure out how to find how many days left in month
        List<Event> thisMonthsEvents = eventService.getEventsByDateRange(startMonthTimestamp,
                endMonthTimestamp, 31);
        return thisMonthsEvents;
    }

}
