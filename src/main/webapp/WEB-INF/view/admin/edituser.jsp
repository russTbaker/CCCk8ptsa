<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<form:form modelAttribute="adduser" action="user.htm" method="PUT">
    <input type="hidden" name="userId" value="<%=request.getParameter("userId")%>"/>
    <form:errors path="*" cssClass="errorblock" element="div"/>
    <input type="hidden" name="theAction" value="edit"/>
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
                <form:select path="role" id="roleInput"><form:errors path="role" cssClass="error"/>
                    <c:forEach var="item" items="${adduser.roles}">
                        <form:option value="${item}"><c:out value="${item}"/></form:option>
                    </c:forEach>
                </form:select>
            </td>
        </tr>
        <tr>
            <c:choose>
                <c:when test="${adduser.activate == 'suspend'}">
                    <td>
                        <label for="reactivateInput">Re-Activate User: </label>
                        <form:radiobutton path="activate" value='reactivate' id="reactivateInput"/>
                    </td>

                </c:when>
                <c:otherwise>
                    <td>
                        <label for="activateInput">Activate User: </label>
                        <form:radiobutton path="activate" value='active' id="activateInput"/>
                    </td>
                </c:otherwise>
            </c:choose>
            <td>
                <label for="deactivateInput">Suspend User: </label>
                <form:radiobutton path="activate" value='suspend' id="deactivateInput"/>
            </td>
        </tr>

        <tr>
            <td colspan="2"><input type="submit" value="Submit"></td>
        </tr>
    </table>
</form:form>