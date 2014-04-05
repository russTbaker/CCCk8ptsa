<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="background-color: #ccccff">
<h1 class="artistSeries">Welcome to the CCC K8 PTSA Website!</h1>

<table width="100%" id="homepageContainer">
    <tr>
        <td align="left" width="70%">
            <c:forEach items="${thisMonthsEvents}" var="event">
                <%--<div class="rounded-tops">--%>
                    <table id="thisWeeksEvents">
                        <tr>
                            <td><c:out value="${event.eventDate}"/></td>
                            <td><a href="#"><c:out value="${event.title}"/></a></td>
                        </tr>
                        <tr>
                            <td colspan="2"><c:out value="${event.description}"/></td>
                        </tr>

                        <tr>
                            <td colspan="2"><a href="<c:out value="${event.link}"/>"><c:out value="${event.link}"/></a></td>
                        </tr>
                    </table>
                <%--</div>--%>
            </c:forEach>
        </td>
        <td valign="top" width="30%">
            <table align="center" id="upcomingEventsTable">
                <tr>
                    <th class="tops-rounded">Upcoming Events</th>
                </tr>
                <tr>
                    <td>
                        <c:forEach items="${thisMonthsEvents}" var="event">
                            <div class="top-row-rounded">
                                <table id="upcomingEvents">
                                    <tr>
                                        <td><c:out value="${event.eventDate}"/></td>
                                    </tr>
                                    <tr>
                                        <td><c:out value="${event.description}"/></td>
                                    </tr>
                                    <tr>
                                        <td><a href="<c:out value="${event.link}"/>"><c:out value="${event.link}"/></a>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </c:forEach>
                    </td>
                </tr>

            </table>
        </td>
    </tr>
</table>



