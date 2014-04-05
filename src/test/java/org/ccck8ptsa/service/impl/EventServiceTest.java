/*
 * Copyright (c) 2014, RBC, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the <organization> nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.ccck8ptsa.service.impl;

import org.ccck8ptsa.persistence.dao.api.EventDao;
import org.ccck8ptsa.persistence.entity.Event;
import org.ccck8ptsa.service.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static junit.framework.Assert.*;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * EventServiceTest.java
 *
 * @author: Russ
 * @since Apr 5, 2014:9:09:00 AM
 */
@RunWith(PowerMockRunner.class)
public class EventServiceTest {
    @InjectMocks
    private EventServiceImpl eventService = new EventServiceImpl();

    @Mock
    private EventDao mockEventDao;

    @Mock
    private Event mockEvent;

    @Mock
    private Validator mockValidator;

    @Mock
    private ValidatorFactory mockValidatorFactory;

    @Mock
    private Set<ConstraintViolation<Event>> mockConstraintViolations;


    @Mock
    private Iterator<ConstraintViolation<Event>> mockIterator;

    @Mock
    private ConstraintViolation<Event> mockConstraintViolation;

    @Mock
    private List<Event> mockEvents;


    @Mock
    private Timestamp mockTimestamp;
    private String EVENT_TITLE = "TITLE";
    private String EVENT_DESCRIPTION = "DESCRIPTION";
    private String EVENT_LINK = "http://www.yahoo.com";

    @Test
    public void testAddEvent() throws Exception {
        // Assemble
        // - Entity first
        assembleEvent();

        // - Dao
        when(mockEventDao.create(mockEvent)).thenReturn(mockEvent);

        // Act
        Event result = eventService.addEvent(mockEvent);

        // Assert
        assertEquals("Event Date different", mockTimestamp, result.getEventDate());
        assertEquals("Event tittle different", EVENT_TITLE, result.getTitle());
        assertEquals("Event tittle different", EVENT_DESCRIPTION, result.getDescription());
        assertEquals("Event tittle different", EVENT_LINK, result.getLink());
    }


    @Test(expected = ServiceException.class)
    public void addEventWithErrors_expectValidationErrors() throws Exception {
        // Assemble
        String constraintViolationField = "INVALID FIELD";
        String constraintViolationValue = "The message";
        assembleConstraintViolations(constraintViolationField, constraintViolationValue);

        // - Dao
        when(mockEventDao.create(mockEvent)).thenReturn(mockEvent);

        // Act
        eventService.addEvent(mockEvent);
    }


    @Test(expected = ServiceException.class)
    public void updateEventWithErrors_expectValidationErrors() throws Exception {
        // Assemble
        String constraintViolationField = "INVALID FIELD";
        String constraintViolationValue = "The message";
        assembleConstraintViolations(constraintViolationField, constraintViolationValue);


        String bogusDescription = "bogus description";
        assembleEvent();
        Event initial = eventService.addEvent(mockEvent);
        assertNotNull("No event created", initial);
        initial.setDescription(bogusDescription);
        eventService.updateEvent(initial);
        Event result = eventService.getEvent(initial.getId());
        assertEquals("Event not updated", bogusDescription, result.getDescription());

    }

    @Test
    public void testDeleteEvent() throws Exception {
    }

    @Test
    public void testGetEvent() throws Exception {
    }

    @Test
    public void getEvent_throwNotFoundException() {

    }

    @Test
    public void testGetEventsByDateRange() throws Exception {
        // Assemble
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DATE, -1);
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DATE, 1);
        Timestamp yesterdayTimestamp = new Timestamp(yesterday.getTime().getTime());
        Timestamp tomorrowTimestamp = new Timestamp(yesterday.getTime().getTime());
        int oneMonth = 31;
        when(mockEventDao.findByDateRange(yesterdayTimestamp,tomorrowTimestamp)).thenReturn(mockEvents);

        // Act

        List<Event> events = eventService.getEventsByDateRange(yesterdayTimestamp,
                tomorrowTimestamp, oneMonth);

        // Assert
        assertTrue("No events returned", !CollectionUtils.isEmpty(events));
    }

    private void assembleEvent() {
        when(mockEvent.getEventDate()).thenReturn(mockTimestamp);
        when(mockEvent.getTitle()).thenReturn(EVENT_TITLE);
        when(mockEvent.getDescription()).thenReturn(EVENT_DESCRIPTION);
        when(mockEvent.getLink()).thenReturn(EVENT_LINK);
    }

    private void assembleConstraintViolations(String constraintViolationField, String constraintViolationValue) {
        when(mockEventDao.validate(mockEvent)).thenReturn(mockConstraintViolations);
        when(mockConstraintViolations.size()).thenReturn(1);
        when(mockConstraintViolations.iterator()).thenReturn(mockIterator);
        when(mockIterator.hasNext()).thenReturn(true, false);
        when(mockIterator.next()).thenReturn(mockConstraintViolation);
        when(mockConstraintViolation.getInvalidValue()).thenReturn(constraintViolationField);
        when(mockConstraintViolation.getMessage()).thenReturn(constraintViolationValue);
    }
}
