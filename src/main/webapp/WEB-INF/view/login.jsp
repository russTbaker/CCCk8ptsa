<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="login">
    <form:form modelAttribute="loginForm" action='/ccck8ptsa/login.htm' method="POST">
        <form:errors path="*" cssClass="errorblock" element="div"/>
    <table>
        <tr>
            <td class="tableReport-tr0">Username</td>
            <td class="tableReport-tr0">Password</td>
        </tr>
        <tr>
            <td class="tableReport-tr0"><form:input path="username"/><form:errors path="username" cssClass="error"/></td>
            <td class="tableReport-tr0"><form:password path="password"/><form:errors path="password" cssClass="error"/></td>
        </tr>
    </table>
    </form:form>
</div>