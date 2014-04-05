package org.ccck8ptsa.persistence.dao.api;

import org.ccck8ptsa.persistence.entity.Event;

import java.sql.Timestamp;
import java.util.List;

/**
 * EventDao.java
 *
 * @author: Russ
 * @since Jan 20, 2014:3:52:12 PM
 */
public interface EventDao extends BaseDao<Event, String> {
    List<Event> findByDateRange(Timestamp startDate, Timestamp endDate);
}
