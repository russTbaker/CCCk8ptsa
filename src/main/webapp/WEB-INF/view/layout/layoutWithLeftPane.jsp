<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link href="<c:url value='/css/main.css' />" rel="stylesheet">
    <link href="<c:url value='/css/home.css' />" rel="stylesheet">
    <title><tiles:insertAttribute name="title" ignore="true"/></title>
</head>
<body>
<div id="everythingContainer">
    <table border="0" cellpadding="0" cellspacing="0" align="left" width="100%" class="tableReport">
        <tr class="table-th">
            <td height="20%" colspan="2" width="100%" ><tiles:insertAttribute name="header"/></td>
        </tr>
        <tr align="center" class="table-th">
            <td height="70%" width="10%" class="table-th"><tiles:insertAttribute name="navigation"/></td>
            <td height="70%" width="70%" class="table-th"><tiles:insertAttribute name="body"/></td>
        </tr>
        <tr class="table-th">
            <td height="10%" colspan="2" width="100%"><tiles:insertAttribute name="footer"/></td>
        </tr>
    </table>
</div>
</body>
</html>