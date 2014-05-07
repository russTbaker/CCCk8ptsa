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
package org.ccck8ptsa.persistence.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Sponsor.java
 *
 * @author: Russ
 * @since May 5, 2014:10:09:24 PM
 */
@Entity
@Table(name = "SPONSOR", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Sponsor extends BaseEntity {

    @Column(name = "SPONSOR_NAME")
    private String name;

    @Column(name = "LOGO")
    private String imageLocation;

    @Column(name = "SPONSOR_WEBSITE")
    @URL
    private String websiteUrl;

    @Column(name = "SPONSOR_PHONE")
    private String sponsorPhone;

    @Column(name = "SPONSOR_EMAIL")
    @Email
    private String email;

    @ManyToMany
    private List<Event> events = new ArrayList<Event>();

    @Override
    public String getClassName() {
        return getClass().getSimpleName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String image) {
        this.imageLocation = image;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getSponsorPhone() {
        return sponsorPhone;
    }

    public void setSponsorPhone(String sponsorPhone) {
        this.sponsorPhone = sponsorPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sponsor sponsor = (Sponsor) o;

        if (email != null ? !email.equals(sponsor.email) : sponsor.email != null) return false;
        if (events != null ? !events.equals(sponsor.events) : sponsor.events != null) return false;
        if (imageLocation != null ? !imageLocation.equals(sponsor.imageLocation) : sponsor.imageLocation != null)
            return false;
        if (name != null ? !name.equals(sponsor.name) : sponsor.name != null) return false;
        if (sponsorPhone != null ? !sponsorPhone.equals(sponsor.sponsorPhone) : sponsor.sponsorPhone != null)
            return false;
        if (websiteUrl != null ? !websiteUrl.equals(sponsor.websiteUrl) : sponsor.websiteUrl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (imageLocation != null ? imageLocation.hashCode() : 0);
        result = 31 * result + (websiteUrl != null ? websiteUrl.hashCode() : 0);
        result = 31 * result + (sponsorPhone != null ? sponsorPhone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (events != null ? events.hashCode() : 0);
        return result;
    }
}
