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
import org.ccck8ptsa.service.api.EventService;
import org.ccck8ptsa.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.List;

/**
 * EventServiceImpl.java
 *
 * @author: Russ
 * @since Apr 4, 2014:6:11:59 PM
 */
@Service(value = "eventService")
public class EventServiceImpl extends BaseServiceImpl implements EventService {

    @Autowired
    @Qualifier(value = "eventDaoJpa")
    private EventDao eventDao;


    @Override
    public Event addEvent(Event event) {
        validate(event, eventDao);
        return eventDao.create(event);
    }

    @Override
    public Event updateEvent(Event event) {
        validate(event, eventDao);
        return eventDao.update(event);
    }

    @Override
    public void deleteEvent(String id) {
        try {
            eventDao.delete(id);
        } catch (EntityNotFoundException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Event getEvent(String eventId) {
        try {
            return eventDao.find(eventId);
        } catch (EntityNotFoundException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Event> getEventsByDateRange(Timestamp startDate, Timestamp endDate, Integer limit) {
        return eventDao.findByDateRange(startDate,endDate);
    }

}
