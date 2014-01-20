<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link href="<c:url value='/resources/css/main.css' />" rel="stylesheet">
    <link href="<c:url value='/resources/css/home.css' />" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/media/css/jquery.dataTables.css'/>">

    <title><tiles:insertAttribute name="title" ignore="true"/></title>
</head>
<body>
<div align="center">
    <table border="1" cellpadding="0" cellspacing="0" align="center" width="100%" class="tableReport">
        <tr>
            <td align="center"><tiles:insertAttribute name="header"/></td>
        </tr>
        <tr align="center">
            <td class="table-th"><tiles:insertAttribute name="topnav"/></td>
        </tr>
        <tr>
            <td class="table-th">
                <table width="100%">
                    <tr>
                        <td align="center"><tiles:insertAttribute name="body"/></td>
                        <td class="table-th" align="right"><tiles:insertAttribute name="sponsors"/></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td class="table-th"><tiles:insertAttribute name="footer"/></td>
        </tr>
    </table>
</div>
</body>
</html>