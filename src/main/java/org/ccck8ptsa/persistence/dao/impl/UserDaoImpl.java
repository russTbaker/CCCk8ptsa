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
import org.ccck8ptsa.persistence.entity.User;
import org.springframework.stereotype.Repository;

/**
 * UserDaoImpl.java
 *
 * @author: Russ
 * @since May 3, 2014:8:28:49 AM
 */
@Repository(value = "userDaoJpa")
public class UserDaoImpl extends BaseDaoImpl<User, String> implements UserDao {
    @Override
    public User createSchoolUser(User user) {
        user.getRoles().add(User.Role.SCHOOL_EMPLOYEE);
        return create(user);
    }

    @Override
    public User createPTSAVolunteer(User user) {
        user.getRoles().add(User.Role.PTSA_VOLUNTEER);
        return create(user);
    }

    @Override
    public User createPTSAAdministrator(User user) {
        user.getRoles().add(User.Role.PTSA_ADMINISTRATOR);
        user.getRoles().add(User.Role.PTSA_VOLUNTEER);
        return create(user);
    }

    @Override
    public User promotePTSAVolunteerToAdmin(User user) {
        user.getRoles().add(User.Role.PTSA_ADMINISTRATOR);
        return update(user);
    }
}
