<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<form:form modelAttribute="eventForm" action="events/addevent.htm" method="POST">
    <form:errors path="*" cssClass="errorblock" element="div"/><form:hidden path="id"/>
    <table width="100%" cellpadding="0" cellspacing="0">
        <tr><td>&NonBreakingSpace;</td></tr>
        <tr>
            <th class="tops-rounded">Enter Event</th>
        </tr>
        <tr>
            <td bgcolor="#6699ff"><label for="eventDate">Event Date: </label><form:errors path="eventTimestamp" cssClass="error"
                                                                        id="eventDate"/>
                <form:input path="eventTimestamp"/></td>
        </tr>
        <tr>
            <td bgcolor="#6699ff"><label for="eventTitle">Event Title: </label><form:errors path="title" cssClass="error"/><form:input
                    id="eventTitle"
                    path="title"/>
            </td>
        </tr>
        <tr>
            <td bgcolor="#6699ff"><label for="eventDescription">Event Description: </label><form:errors path="description"
                                                                                      cssClass="error"/><form:textarea
                    path="description" id="eventDescription"/></td>
        </tr>
        <tr>
            <th class="bottoms-rounded">&NonBreakingSpace;</th>
        </tr>
        <tr><td>&NonBreakingSpace;</td></tr>
        <tr>
            <th class="tops-rounded">Sponsor Information</th>
        </tr>
        <tr>
            <td bgcolor="#6699ff"><label for="sponsorName">Sponsor Name: </label><form:errors path="sponsorForms[0].name"
                                                                            cssClass="error"/><form:input
                    path="sponsorForms[0].name" id="sponsorName"/></td>
        </tr>
        <tr>
            <td bgcolor="#6699ff"><label for="sponsorImageLocation">Sponsor Image Location: </label><form:errors
                    path="sponsorForms[0].imageLocation" cssClass="error"/><form:input
                    path="sponsorForms[0].imageLocation" id="sponsorImageLocation"/></td>
        </tr>
        <tr>
            <td bgcolor="#6699ff"><label for="sponsorWebsite">Sponsor Website: </label><form:errors path="sponsorForms[0].websiteUrl"
                                                                                  cssClass="error"/><form:input
                    path="sponsorForms[0].websiteUrl" id="sponsorWebsite"/></td>
        </tr>
        <tr>
            <td bgcolor="#6699ff"><label for="sponsorPhone">Sponsor Phone: </label><form:errors path="sponsorForms[0].sponsorPhone"
                                                                              cssClass="error"/><form:input
                    path="sponsorForms[0].sponsorPhone" id="sponsorPhone"/></td>
        </tr>
        <tr>
            <td bgcolor="#6699ff"><label for="sponsorEmail">Sponsor Email: </label><form:errors path="sponsorForms[0].email"
                                                                              cssClass="error"/><form:input
                    path="sponsorForms[0].email" id="sponsorEmail"/></td>
        </tr>
        <tr>
            <th class="bottoms-rounded">&NonBreakingSpace;</th>
        </tr>
        <tr><td>&NonBreakingSpace;</td></tr>
        <tr>
            <th class="tops-rounded">News Information</th>
        </tr>


        <tr>
            <td bgcolor="#6699ff"><label for="news">News: </label><form:errors path="newsEventForms[0].body"
                                                             cssClass="error"/><form:textarea
                    path="newsEventForms[0].body" id="news"/></td>
        </tr>
        <tr>
            <td bgcolor="#6699ff"><input type="submit" value="Submit"></td>
        </tr>
        <tr>
            <th class="bottoms-rounded">&NonBreakingSpace;</th>
        </tr>
    </table>
</form:form>