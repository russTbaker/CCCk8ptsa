<%--
  Created by IntelliJ IDEA.
  User: Russ
  Date: Dec 17, 2013
  Time: 4:59:21 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<form:form modelAttribute="organization" action="organization.htm" method="PUT">
    <form:errors path="*" cssClass="errorblock" element="div"/>
    <input type="hidden" name="organizationId" value="<c:out value="${organization.organizationId}"/>">
    <table id="formTable">
        <tr>
            <td colspan="2">
                <label for="organizationNameInput">Organization Name: </label>
                <form:input path="organizationName" id="organizationNameInput"/><form:errors path="organizationName" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td>
                <label for="contactFirstNameInput">Contact First Name: </label>
                <form:input path="contactFirstName" id="contactFirstNameInput"/><form:errors path="contactFirstName" cssClass="error"/>
            </td>
            <td>
                <label for="contactLastNameInput">Contact Last Name: </label>
                <form:input path="contactLastName" id="contactLastNameInput"/><form:errors path="contactLastName" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td>
                <label for="contactEmailInput">Contact Email: </label>
                <form:input path="contactEmail" id="contactEmailInput"/><form:errors path="contactEmail" cssClass="error"/>
            </td>
            <td>
                <label for="mailSenderHostInput">Contact Email Host: </label>
                <form:input path="mailSenderHost" id="mailSenderHostInput"/><form:errors path="mailSenderHost" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <label for="mailSenderUsernameInput">Contact Email Username (email address): </label>
                <form:input path="mailSenderUsername" id="mailSenderUsernameInput"/><form:errors path="mailSenderUsername" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td>
                <label for="mailSenderPasswordInput">Contact Email password: </label>
                <form:password path="mailSenderPassword" id="mailSenderPasswordInput" showPassword="true"/><form:errors
                    path="mailSenderPassword" cssClass="error"/>
            </td>
            <td>
                <label for="confirmMailSenderPasswordInput">Confirm Contact Email password: </label>
                <form:password path="confirmMailSenderPassword" id="confirmMailSenderPasswordInput" showPassword="true"/><form:errors
                    path="confirmMailSenderPassword" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Submit"></td>
        </tr>
    </table>
</form:form>
</body>