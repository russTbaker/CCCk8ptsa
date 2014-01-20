/*
 * Copyright (c) 2013, ReminderPortal, Inc.
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

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * DonorRelationships.java
 *
 * @author: Russ
 * @since Jan 20, 2014:12:30:04 PM
 */
@Entity
@Table(name = "DONOR_RELATIONSHIPS")
@XmlRootElement(name = "DonorRelationships")
@XmlAccessorType(XmlAccessType.FIELD)
public class DonorRelationships {
    @Id
    @GeneratedValue
    private String donorRelationshipId;

    @JoinColumn(updatable = false)
    private Organization organization;

    @JoinColumn
    private Organization ptsa;

    public enum DonorRelationshipTypes{
        YEARLY,
        MONTHLY,
        NO_SCHEDULE
    }

    public String getDonorRelationshipId() {
        return donorRelationshipId;
    }

    public void setDonorRelationshipId(String donorRelationshipId) {
        this.donorRelationshipId = donorRelationshipId;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Organization getPtsa() {
        return ptsa;
    }

    public void setPtsa(Organization ptsa) {
        this.ptsa = ptsa;
    }
}
