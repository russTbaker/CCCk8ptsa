<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="background-color: #cccccc;vertical-align:top;width:100%">
    <h1 class="artistSeries">Welcome to the Coal Creek K8 PTSA Website!</h1>

    <div id="leftCell" style="display: table-cell;margin-right:25px;padding-right:250px;width:60%">
        <c:forEach items="${thisMonthsEvents}" var="event">
            <div class="thisWeeksEvents">
                <table>
                    <tr>
                        <td bgcolor="#ffffff"><c:out value="${event.eventDate}"/></td>
                        <td><a href="#"><c:out value="${event.title}"/></a></td>
                    </tr>
                    <tr>
                        <td colspan="2"><c:out value="${event.description}"/></td>
                    </tr>

                    <tr>
                        <td colspan="2"><a href="<c:out value="${event.link}"/>"><c:out value="${event.link}"/></a></td>
                    </tr>
                </table>
            </div>
        </c:forEach>
    </div>
    <div id="rightCell" style="display:table-cell;padding-top:0px;margint-top:0;width:40%">
        <table align="center" id="upcomingEventsTable">
            <tr>
                <th class="tops-rounded">Upcoming Events</th>
            </tr>
            <tr>
                <td>

                    <c:forEach items="${thisMonthsEvents}" var="event">
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
                    </c:forEach>

                </td>
            </tr>
            <tr>
                <th class="bottoms-rounded">&nbsp;</th>
            </tr>
        </table>
    </div>
</div>


