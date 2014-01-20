<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Russ
  Date: Dec 18, 2013
  Time: 12:10:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
    body {
        margin: 0px;
        padding: 0px;
    }
</style>

<%--<script src='<c:url value='/resources/jquery-ui-1.10.3.custom/js/jquery-1.9.1.js'/>' type='text/javascript'></script>--%>


<script src='<c:url value='/resources/js/colourful/jquery-pack-colourful.js'/>' type='text/javascript'></script>
<script src='<c:url value='/resources/js/colourful/jcarousel-colourful.js'/>' type='text/javascript'></script>
<script src='<c:url value='/resources/js/colourful/mt-colourful.js'/>' type='text/javascript'></script>
<link href="<c:url value='/resources/css/colourfultabs.css'/>" rel="stylesheet">
<!--START OF COLOURFUL TABS BY MBT-->

<div class='MBT-Nav-container'>
    <ul id='nav'>

        <li class='non-vertical-link top-link' id='link-home'><a class='open' href='<%=request.getContextPath()%>/welcome.htm' onclick="highlightTab('home')">Home</a></li>

        <li class='top-link' id='link-resources'><a href='<%=request.getContextPath()%>/resources.htm' onclick="highlightTab('resources')">Parent Resurces</a>
            <ul class='sub-nav' style="display:none">
            <li><a href='#'>SUB TAB 1.1</a></li>
            <li><a href='#'>SUB TAB 1.2</a></li>
            <li><a href='#'>SUB TAB 1.3</a></li>
            <li><a href='#'>SUB TAB 1.4</a></li>
            </ul>
        </li>

        <li class='top-link' id='link-sign-up'><a href='<%=request.getContextPath()%>/signup.htm' onclick="highlightTab('sign-up')">Volunteer Sign up</a>
            <ul class='sub-nav' style='display: none;'>
                <li><a href='#'>SUB TAB 2.1</a></li>
                <li><a href='#'>SUB TAB 2.2</a></li>
                <li><a href='#'>SUB TAB 2.3</a></li>
                <li><a href='#'>SUB TAB 2.4</a></li>
                <li><a href='#'>SUB TAB 2.5</a></li>
            </ul>
        </li>

        <li class='top-link' id='link-opportunities'><a href='<%=request.getContextPath()%>/opportunities.htm' onclick="highlightTab('opportunities')">Volunteer Opportunities</a>
            <ul class='sub-nav' style='display: none;'>
                <li><a href='#'>SUB TAB 3.1</a></li>
                <li><a href='#'>SUB TAB 3.2</a></li>
                <li><a href='#'>SUB TAB 3.3</a></li>
                <li><a href='#'>SUB TAB 3.4</a></li>
            </ul>
        </li>

        <li class='top-link' id='link-programs'><a href='<%=request.getContextPath()%>/events.htm' onclick="highlightTab('programs')">Programs &amp; Events</a>
            <ul class='sub-nav' style='display: none;'>
                <li><a href='#'>SUB TAB 4.1</a></li>
                <li><a href='#'>SUB TAB 4.2</a></li>
                <li><a href='#'>SUB TAB 4.3</a></li>
                <li><a href='#'>SUB TAB 4.4</a></li>
                <li><a href='#'>SUB TAB 4.5</a></li>
                <li><a href='#'>SUB TAB 4.6</a></li>
                <li><a href='#'>SUB TAB 4.7</a></li>
            </ul>
        </li>

        <li class='top-link' id='link-fund-raising'><a href='<%=request.getContextPath()%>/fundraising.htm' onclick="highlightTab('fund-raising')">Fundraising</a>
            <ul class='sub-nav' style='display: none;'>
                <li><a href='#'>SUB TAB 5.1</a></li>
                <li><a href='#'>SUB TAB 5.2</a></li>
                <li><a href='#'>SUB TAB 5.3</a></li>
                <li><a href='#'>SUB TAB 5.4</a></li>
                <li><a href='#'>SUB TAB 5.5</a></li>
            </ul>
        </li>


        <li style='clear: both;'/>
    </ul>
</div>
<!--END OF COLOURFUL TABS BY MBT-->