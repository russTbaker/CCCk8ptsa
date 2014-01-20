<%--
  User: Russ
  Date: Dec 17, 2013
  Time: 4:59:21 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <form:form modelAttribute="addcustomer" action="addcustomer.htm" method="POST">
        <form:errors path="*" cssClass="errorblock" element="div"/>
        <table id="formTable">
            <tr>
                <td>
                    <label for="firstNameInput">First Name: </label>
                    <form:input path="firstName" id="firstNameInput"/><form:errors path="firstName" cssClass="error"/>"
                </td>
                <td>
                    <label for="lastNameInput">Last Name: </label>
                    <form:input path="lastName" id="lastNameInput"/><form:errors path="lastName" cssClass="error"/>"
                </td>
            </tr>
            <tr>
                <td>
                    <label for="phoneInput">Phone Number: </label>
                    <form:input path="phoneNumber" id="phoneInput"/><form:errors path="phoneNumber" cssClass="error"/>"
                </td>
                <td>
                    <label for="providerInput">Provider: </label>
                    <form:select path="provider" id="providerInput"><form:errors path="provider" cssClass="error"/>"
                        <option selected="true">--SELECT--</option>
                        <c:forEach var="item" items="${addcustomer.providers}">
                            <form:option value="${item}"><c:out value="${item}"/></form:option>
                        </c:forEach>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Submit"></td>
            </tr>
        </table>
    </form:form>
</body>