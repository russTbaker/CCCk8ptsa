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
package org.ccck8ptsa.persistence.initializer;

import org.ccck8ptsa.persistence.entity.Event;
import org.joda.time.LocalDate;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.Date;

/**
 * ApplicationContextListener.java
 *
 * @author: Russ
 * @since Jan 25, 2014:10:27:46 AM
 */
@Component
public class ApplicationContextListener implements ApplicationListener<ContextRefreshedEvent> {

    @PersistenceContext
    EntityManager em;


    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void onApplicationEvent(ContextRefreshedEvent ctxEvent) {
        if(ctxEvent.getApplicationContext().getParent() == null && "test".equals(System.getProperty("env"))){
            Timestamp timestamp;
            Date theDate= new LocalDate().toDate();
            for (int i = 0; i < 31; i++) {
                if (i % 2 == 0) {
                    theDate =  new LocalDate().plusDays(i).toDate();
                }
                timestamp = new Timestamp(theDate.getTime());
                createEvent(timestamp);
            }
        }
    }

    private Event createEvent(Timestamp timestamp) {
        Event event = new Event();
        Timestamp ts = timestamp;
        long currentTime = System.currentTimeMillis();
        event.setDescription("Description for: " + currentTime);
        event.setEventDate(ts);
        event.setTitle("Title for: " + currentTime);
        event.setLink("http://www.volunteerspot.com");
        em.persist(event);
        em.flush();
        return event;
    }
}
