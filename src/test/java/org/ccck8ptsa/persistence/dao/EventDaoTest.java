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
package org.ccck8ptsa.persistence.dao;

import org.ccck8ptsa.persistence.dao.api.EventDao;
import org.ccck8ptsa.persistence.entity.Event;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * EventDaoTest.java
 *
 * @author: Russ
 * @since Jan 20, 2014:4:11:28 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test-config.xml"})
@TransactionConfiguration(transactionManager = "jpaTestTransactionManager", defaultRollback = true)
@Transactional
public class EventDaoTest extends BaseDaoTest<Event, EventDao> {

    @Autowired
    @Qualifier(value = "eventDaoJpa")
    private EventDao eventDao;
    private String EVENT_DESCRIPTION = "TEST";
    private String EVENT_TITLE = "TEST_TITLE";
    private String EVENT_LINK = "http://www.yahoo.com";
    private final Timestamp EVENT_DATE = new Timestamp(new Date().getTime());
    private static final Calendar YESTERDAY = Calendar.getInstance();

    @BeforeClass
    public static void setUp() {
        YESTERDAY.add(Calendar.DATE, -1);
    }

    @Test
    public void insert() {
        doInsert();
    }

    @Test
    public void update() {
        Event initial = doInsert();
        String id = initial.getId();
        String description = "NEW DESCRIPTION";
        initial.setDescription(description);
        eventDao.update(initial);
        Event result = eventDao.find(id);
        assertEquals("Events are different", initial, result);

    }

    @Test
    public void delete() {
        super.doDelete(eventDao);
    }

    @Test
    public void validEntity_expectNoErrors() {
        assertTrue("Violations found!", CollectionUtils.isEmpty(super.validate(eventDao, createEvent())));
    }

    @Test
    public void invalidEntry_expectErrors() {
        Set<ConstraintViolation<Event>> constraintViolationSet = super.validate(eventDao, new Event());
        assertTrue("No violations found!", !CollectionUtils.isEmpty(constraintViolationSet));
        assertEquals("Wrong number of violations", 4, constraintViolationSet.size());
    }

    @Test
    public void invalidUrl_expectErrors() {
        Event event = createEvent();
        event.setLink("totallybogus");
        Set<ConstraintViolation<Event>> constraintViolationSet = super.validate(eventDao, event);
        assertTrue("No violations found!", !CollectionUtils.isEmpty(constraintViolationSet));
        assertEquals("Wrong number of violations", 1, constraintViolationSet.size());
        assertEquals("Wrong type of violation", "must be a valid URL",
                constraintViolationSet.iterator().next().getMessage());
    }

    @Test
    public void getByDate() {
        Event test = doInsert();
        int oneMonth = 31;
        List<Event> events = eventDao.findByDateRange(new Timestamp(YESTERDAY.getTime().getTime()), EVENT_DATE);
        assertTrue("No events returned", !CollectionUtils.isEmpty(events));
        assertTrue("Wrong event returned", events.contains(test));
    }

    @Test
    public void getByDate_expectEmptyList() {
        int oneWeek = 7;
        List<Event> events = eventDao.findByDateRange(new Timestamp(YESTERDAY.getTime().getTime()), EVENT_DATE);
        assertNotNull(events);
        assertTrue(CollectionUtils.isEmpty(events));
    }

    @Test(expected = PersistenceException.class)
    public void nonNullableFields_expectSqlError(){
        Event event = new  Event();
        eventDao.create(event);
    }

    @Override
    protected Event doInsert() {
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DATE, 1);
        Event event = createEvent();
        Event result = eventDao.create(event);
        assertNotNull("VolunteerSpotProxy not created", event);
        return result;
    }

    private Event createEvent() {
        Event event = new Event();
        event.setEventDate(EVENT_DATE);
        event.setDescription(EVENT_DESCRIPTION);
        event.setTitle(EVENT_TITLE);
        event.setLink(EVENT_LINK);
        return event;
    }


}
