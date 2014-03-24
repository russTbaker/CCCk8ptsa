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
import org.ccck8ptsa.persistence.entity.BaseEntity;
import org.ccck8ptsa.persistence.entity.VolunteerSpotProxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

import static junit.framework.Assert.assertNotNull;

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
public class EventDaoTest extends BaseDaoTest<VolunteerSpotProxy, EventDao>{

    @Autowired
    @Qualifier(value = "eventDaoJpa")
    private EventDao eventDao;

    @Test
    public void insert(){
        doInsert();
    }

    @Override
    protected BaseEntity doInsert() {
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DATE,1);
        VolunteerSpotProxy event = new VolunteerSpotProxy();
//        event.setStartTime(new Date());
//        event.setEndTime(tomorrow.getTime());
//        event.setLocation("Here");
        VolunteerSpotProxy result = eventDao.create(event);
        assertNotNull("VolunteerSpotProxy not created",event);
        return result;
    }
}
