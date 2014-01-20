<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <table width="100%">
        <tr>
            <td align="left">Company: <b><c:out value="${company}"/></b></td>
            <td align="right">Logged in as <b><c:out value="${user}"/></b> | <a href="<%=request.getContextPath()%>/logout.htm">Logout</a></td>
        </tr>
    </table>
</div>