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

import junit.framework.Assert;
import org.ccck8ptsa.persistence.dao.impl.BaseDaoImpl;
import org.ccck8ptsa.persistence.entity.User;
import org.ccck8ptsa.service.api.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.Assert.*;

/**
 * UserServiceImplTest.java
 *
 * @author: Russ
 * @since May 3, 2014:1:44:44 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test-config.xml"})
@TransactionConfiguration(transactionManager = "jpaTestTransactionManager", defaultRollback = true)
@Transactional
public class UserServiceImplTest {

    private static final String FIRST_NAME = "FIRST NAME";
    private static final String LAST_NAME = "LAST NAME";
    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";

    @Autowired
    @Qualifier(value = "userServiceJpaImpl")
    private UserService service;
    private static final String UPDATED_LAST_NAME = "UPDATED LAST NAME";


    @Test(expected = IllegalArgumentException.class)
    public void whenPTSAVolunteerIsNull_expectNothingCreated() {
        // Assemble
        User ptsaVolunteer = null;

        // Act
        service.createPTSAVolunteer(ptsaVolunteer);
    }

    @Test
    public void whenCreatePTSAVolunteer_ExpectValidPTSAVolunteer() {
        // Assemble/Act
        User result = createPTSAVolunteer();

        // Assert
        assertNotNull("ptsaVolunteer not created.", result);
        assertTrue("Wrong role", result.getRoles().contains(User.Role.PTSA_VOLUNTEER));
    }

    @Test
    public void whenCreatePTSAVolunteer_ExpectFindValidPTSAVolunteer() {
        User result = createPTSAVolunteer();
        assertNotNull("Cannot find user", service.findUser(result.getId()));
    }


    @Test(expected = IllegalArgumentException.class)
    public void whenPTSAAdmministratorNull_ExpectIllegalArgumentException() {
        // Assemble
        User ptsaAdministrator = null;

        // Act
        service.createPTSAAdministrator(ptsaAdministrator);
    }

    @Test
    public void whenValidPTSAAdministrator_expectFindPTSAAdministrator() {
        // Assemble
        User ptsaAdministrator = new User();

        // Act
        User result = service.createPTSAAdministrator(ptsaAdministrator);
        assertNotNull(result);
        User findResult = service.findUser(result.getId());

        // Assert
        assertNotNull(findResult);
        assertTrue(findResult.getRoles().contains(User.Role.PTSA_ADMINISTRATOR));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSchoolEmployeeNull_ExpectIllegalArgumentException() {
        User teacher = null;
        service.createSchoolEmployee(teacher);
    }

    @Test
    public void whenValidSchoolEmployee_expectFindSchoolEmployee() {
        // Assemble
        User ptsaAdministrator = new User();

        // Act
        User result = service.createSchoolEmployee(ptsaAdministrator);
        assertNotNull(result);
        User findResult = service.findUser(result.getId());

        // Assert
        assertNotNull(findResult);
        assertTrue(findResult.getRoles().contains(User.Role.SCHOOL_EMPLOYEE));
    }


    @Test(expected = IllegalArgumentException.class)
    public void whenFindWithNull_ExpectIllegalArgumentException() {
        String blank = null;
        service.findUser(blank);
    }

    @Test
    public void whenUpdateUser_ExpectValidUser() {
        User volunteer = createPTSAVolunteer();
        volunteer.setLastame(UPDATED_LAST_NAME);
        User updatedResult = service.update(volunteer);
        assertEquals(volunteer, updatedResult);
    }

    @Test(expected = BaseDaoImpl.NotFoundException.class)
    public void whenDeleteUser_ExpectUserDeleted(){
        User volunteer = createPTSAVolunteer();
        service.delete(volunteer);
        service.findUser(volunteer.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenDeleteWithNoId_ExpectIllegalArgumentException(){
        service.delete(new User());
    }

    @Test
    public void whenPromoted_ExpectAdminRole(){
        User volunteer = createPTSAVolunteer();
        User administrator = service.promotePTSAVolunteerToAdmin(volunteer);
        assertTrue(administrator.getRoles().contains(User.Role.PTSA_ADMINISTRATOR));
        assertTrue(administrator.getRoles().contains(User.Role.PTSA_VOLUNTEER));
    }
    //== Private

    private User createPTSAVolunteer() {
        User user = new User();
        user.setFirstName(FIRST_NAME);
        user.setLastame(LAST_NAME);
        user.setUserName(USERNAME);
        user.setPassword(PASSWORD);
        return service.createPTSAVolunteer(user);
    }


}
