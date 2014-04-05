<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link href="<c:url value='/css/main.css' />" rel="stylesheet">
    <link href="<c:url value='/css/home.css' />" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/media/css/jquery.dataTables.css'/>">

    <title><tiles:insertAttribute name="title" ignore="true"/></title>
    <style>
        #mainlayout tr {
            background-color: #336600;
            padding: 15px;
        }

        #innertable tr {
            background-color: #ffffff;
        }

        div.even {
            /*background: #cccccc;*/

        }

        div.uneven {
            background: #fcf;
            border: 1px solid;
            padding: 5px;
            margin-top: 20px;
            margin-right: 10px;
            margin-bottom: 40px;
            margin-left: 80px;
        }
    </style>

</head>
<body margin-left:0>
<%--<div align="center">--%>
    <table id="mainlayout" border="0" cellpadding="0" cellspacing="0">
        <col width="10%">
        <col width="80%">
        <col width="10%">
        <tr>
            <td>&nbsp;</td>
            <td>
                <table id="innertable" cellpadding="0" cellspacing="0" align="center" width="100%">
                    <tr>
                        <td align="center"><tiles:insertAttribute name="header"/></td>
                    </tr>
                    <tr align="center" style="background-color:#272b76">
                        <td align="center" class="table-th"><tiles:insertAttribute name="topnav"/></td>
                    </tr>
                    <tr>
                         <td><tiles:insertAttribute name="body"/></td>
                    <tr>
                        <td class="table-th"><tiles:insertAttribute name="footer"/></td>
                    </tr>
                </table>
            </td>
            <td>&nbsp;</td>
        </tr>
    </table>

<%--</div>--%>
</body>
</html>