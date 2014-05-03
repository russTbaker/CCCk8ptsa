package org.ccck8ptsa.persistence.dao.api;

import org.ccck8ptsa.persistence.entity.User;

import javax.management.relation.RelationServiceMBean;

/**
 * UserDao.java
 *
 * @author: Russ
 * @since May 3, 2014:8:27:23 AM
 */
public interface UserDao extends BaseDao<User,String>{
    User createSchoolUser(User user);

    User createPTSAVolunteer(User user);

    User createPTSAAdministrator(User user);

    User promotePTSAVolunteerToAdmin(User user);
}
