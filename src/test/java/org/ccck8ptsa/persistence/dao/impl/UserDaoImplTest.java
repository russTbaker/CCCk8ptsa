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

import org.ccck8ptsa.persistence.dao.api.UserDao;
import org.ccck8ptsa.persistence.entity.BaseEntity;
import org.ccck8ptsa.persistence.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.Assert.*;

/**
 * UserDaoImplTest.java
 *
 * @author: Russ
 * @since May 3, 2014:8:31:50 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test-config.xml"})
@TransactionConfiguration(transactionManager = "jpaTestTransactionManager", defaultRollback = true)
@Transactional
public class UserDaoImplTest extends BaseDaoTest{

    @Autowired
    private UserDao userDao;
    private User user;
    private static final String FIRST_NAME = "FIRST NAME";
    private static final String LAST_NAME = "LAST NAME";
    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";

    @Before
    public void createUser() {
        user = new User();
        user.setFirstName(FIRST_NAME);
        user.setLastame(LAST_NAME);
        user.setUserName(USERNAME);
        user.setPassword(PASSWORD);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateNull_expectIllegalArgumentException() {
        User nullUser = null;
        assertNull(userDao.create(nullUser));
    }


    @Test
    public void whenCreateSchoolUser_expectFindUser() {
        assertSchoolEmployeeCreated(user);
        assertFindUser(user);
    }

    @Test
    public void whenCreatePTSAVolunteer_expectFindUser() {
        assertFindPTSAVolunteer(user);
    }


    @Test
    public void whenCreatePTSAAdministrator_expectFindUser() {
        assertNotNull(userDao.createPTSAAdministrator(user));
        assertTrue(user.getRoles().contains(User.Role.PTSA_ADMINISTRATOR));
        assertFindUser(user);
        assertTrue(user.getRoles().contains(User.Role.PTSA_VOLUNTEER));
    }

    @Test
    public void whenCreatePTSAAdministrator_expectPTSAVolunteerAlso() {
        assertNotNull(userDao.createPTSAAdministrator(user));
        assertTrue(user.getRoles().contains(User.Role.PTSA_ADMINISTRATOR));
        assertFindPTSAVolunteer(user);
    }

    //== Updates

    @Test
    public void whenAddingAdminRole_expectRoleAdded() {
        userDao.createPTSAVolunteer(user);
        User adminUser = userDao.find(user.getId());
        userDao.promotePTSAVolunteerToAdmin(adminUser);

        User updatedUser = userDao.find(user.getId());
        assertTrue(updatedUser.getRoles().contains(User.Role.PTSA_ADMINISTRATOR));
    }

    @Test
    public void whenDeleted_expectRecordDeleted(){
        doDelete(userDao);
    }


    //==== Private methods

    private void assertSchoolEmployeeCreated(User user) {
        assertNotNull(userDao.createSchoolUser(user));
        assertTrue(user.getRoles().contains(User.Role.SCHOOL_EMPLOYEE));
    }

    private void assertFindPTSAVolunteer(User user) {
        assertPTSAVolunteerCreated(user);
        assertFindUser(user);
    }

    private void assertFindUser(User user) {
        assertNotNull(userDao.find(user.getId()));
    }

    private void assertPTSAVolunteerCreated(User user) {
        assertNotNull(userDao.createPTSAVolunteer(user));
        assertTrue(user.getRoles().contains(User.Role.PTSA_VOLUNTEER));
    }

    @Override
    protected BaseEntity doInsert() {
        return userDao.create(user);
    }
}
