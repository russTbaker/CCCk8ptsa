<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link href="<c:url value='/resources/css/main.css' />" rel="stylesheet">
    <link href="<c:url value='/resources/css/home.css' />" rel="stylesheet">
    <title><tiles:insertAttribute name="title" ignore="true"/></title>
</head>
<body>
<div id="everythingContainer">
    <table border="0" cellpadding="0" cellspacing="0" align="left" width="100%" class="tableReport">
        <tr>
            <td class="table-th"><tiles:insertAttribute name="header"/></td>
        </tr>
        <tr align="left">
            <td class="table-th"><tiles:insertAttribute name="body"/></td>
        </tr>
        <tr>
            <td class="table-th"><tiles:insertAttribute name="footer"/></td>
        </tr>
    </table>
</div>
</body>
</html>