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
    <form:form modelAttribute="adduser" action="user.htm" method="POST">
        <form:errors path="*" cssClass="errorblock" element="div"/>
        <input type="hidden" name="theAction" value="add"/>
        <table id="formTable">
            <tr>
                <td>
                    <label for="firstNameInput">First Name: </label>
                    <form:input path="firstName" id="firstNameInput"/><form:errors path="firstName" cssClass="error"/>
                </td>
                <td>
                    <label for="lastNameInput">Last Name: </label>
                    <form:input path="lastName" id="lastNameInput"/><form:errors path="lastName" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="emailInput">Email Address: </label>
                    <form:input path="email" id="emailInput"/><form:errors path="email" cssClass="error"/>
                </td>
                <td>
                    <label for="roleInput">Role: </label>
                    <form:select path="role" id="roleInput">
                        <c:forEach var="item" items="${adduser.roles}">
                            <form:option value="${item}"><c:out value="${item}"/></form:option>
                        </c:forEach>
                    </form:select><form:errors path="role" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="passwordInput">Password: </label>
                    <form:password  path="password" id="passwordInput"/><form:errors path="password" cssClass="error"/>
                </td>
                <td>
                    <label for="passwordconfirmedInput">Confirm Password: </label>
                    <form:password  path="passwordConfirmed" id="passwordconfirmedInput"/><form:errors path="passwordConfirmed" cssClass="error"/>
                </td>
            </tr>
                    <tr>
                        <td colspan="2">
                            <label for="activateInput">Activate User: </label>
                            <form:checkbox path="activate" value="active" id="activateInput"/>
                        </td>
                    </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Submit"></td>
            </tr>
        </table>
    </form:form>
</body>