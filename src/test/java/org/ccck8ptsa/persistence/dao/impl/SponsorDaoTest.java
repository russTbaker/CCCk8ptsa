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
package org.ccck8ptsa.persistence.dao.impl;

import org.ccck8ptsa.persistence.dao.api.SponsorDao;
import org.ccck8ptsa.persistence.entity.Event;
import org.ccck8ptsa.persistence.entity.Sponsor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * SponsorDaoTest.java
 *
 * @author: Russ
 * @since May 5, 2014:10:21:47 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test-config.xml"})
@TransactionConfiguration(transactionManager = "jpaTestTransactionManager", defaultRollback = true)
@Transactional
public class SponsorDaoTest extends BaseDaoTest<Sponsor, SponsorDao> {

    public static final String IMAGE_LOCATION = "/images/logo.jpg";
    public static final String UPDATED_EMAIL = "UPDATED EMAIL";
    @Autowired
    @Qualifier(value = "sponsorDaoJpaImpl")
    private SponsorDao sponsorDao;
    private static final String SPONSOR_EMAIL = "sponsor@sponsor.com";

    private static final String SPONSOR_NAME = "SPONSOR_NAME";
    private static final String SPONSOR_URL = "http://someurl.com";
    private static final String SPONSOR_PHONE = "(303)555-1212";

    @Test
    public void whenInserted_ExpectFetch() {
        createFindSponsor();
    }



    @Test
    public void whenUpdated_ExpectUpdated(){
        Sponsor insertedSponsor = createFindSponsor();
        insertedSponsor.setEmail(UPDATED_EMAIL);
        sponsorDao.update(insertedSponsor);
        Sponsor result = sponsorDao.find(insertedSponsor.getId());
        assertNotNull("Sponsor not found",result);
        assertEquals("Sponsors not equal",insertedSponsor,result);
    }

    @Test
    public void delete(){
        super.doDelete(sponsorDao);
    }


    @Override
    protected Sponsor doInsert() {
        Sponsor sponsor = new Sponsor();
        sponsor.setEmail(SPONSOR_EMAIL);
        try {
            sponsor.setImageLocation(IMAGE_LOCATION);
        } catch (Exception e) {
            fail(e.getLocalizedMessage());
        }
        sponsor.setName(SPONSOR_NAME);
        sponsor.setWebsiteUrl(SPONSOR_URL);
        sponsor.setSponsorPhone(SPONSOR_PHONE);
        Sponsor returnedValue = sponsorDao.create(sponsor);
        assertNotNull("Sponsor not created",sponsor);
        return returnedValue;
    }

    private Sponsor createFindSponsor() {
        Sponsor sponsor = doInsert();
        Sponsor fetched = sponsorDao.find(sponsor.getId());
        assertNotNull("Sponsor not inserted",fetched);
        assertEquals("Image locaton wrong",IMAGE_LOCATION,fetched.getImageLocation());
        return fetched;
    }

}
