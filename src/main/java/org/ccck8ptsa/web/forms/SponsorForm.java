package org.ccck8ptsa.web.forms;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Lob;
import java.io.Serializable;

/**
 * Created by &quot;The Collective&quot; on 5/6/14.
 */
public class SponsorForm implements Serializable {

    private String name;

    @Lob
    private byte[] image;

    @URL
    private String websiteUrl;

    private String sponsorPhone;

    @Email
    private String email;

}
