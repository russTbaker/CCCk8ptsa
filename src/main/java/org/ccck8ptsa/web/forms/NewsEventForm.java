package org.ccck8ptsa.web.forms;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by &quot;The Collective&quot; on 5/6/14.
 */
public class NewsEventForm implements Serializable{
    private Timestamp createDate = new Timestamp(new Date().getTime());

    private Timestamp modifyDate;

    private EventForm eventForm;

    private String body;

    private String title;

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
    }

    public EventForm getEventForm() {
        return eventForm;
    }

    public void setEventForm(EventForm eventForm) {
        this.eventForm = eventForm;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
