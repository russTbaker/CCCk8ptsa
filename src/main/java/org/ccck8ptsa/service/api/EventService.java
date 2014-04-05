package org.ccck8ptsa.service.api;

import org.ccck8ptsa.persistence.entity.Event;

import java.sql.Timestamp;
import java.util.List;

/**
 * EventService.java
 *
 * @author: Russ
 * @since Apr 4, 2014:6:09:32 PM
 */
public interface EventService extends BaseService {
    Event addEvent(Event event);
    Event updateEvent(Event event);
    void deleteEvent(String id);
    Event getEvent(String eventId);
    List<Event> getEventsByDateRange(Timestamp startDate, Timestamp endDate, Integer limit);
}
