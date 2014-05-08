package org.ccck8ptsa.web.forms;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by &quot;The Collective&quot; on 5/6/14.
 */
public class EventForm implements Serializable{
    private String id;
    private Timestamp eventTimestamp;
    private String description;
    private String title;
    private List<SponsorForm> sponsorForms = new ArrayList<SponsorForm>(0);
    private List<NewsEventForm> newsEventForms = new ArrayList<NewsEventForm>(0);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(Timestamp eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SponsorForm> getSponsorForms() {
        return sponsorForms;
    }

    public void setSponsorForms(List<SponsorForm> sponsorForms) {
        this.sponsorForms = sponsorForms;
    }

    public List<NewsEventForm> getNewsEventForms() {
        return newsEventForms;
    }

    public void setNewsEventForms(List<NewsEventForm> newsEventForms) {
        this.newsEventForms = newsEventForms;
    }
}
