<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript" src="<c:url value='/resources/media/js/jquery.dataTables.js'/>"></script>


<script>
    $(document).ready(function() {
        $("#users").dataTable({
            "aaData":[
                <c:forEach items="${users}" var="user" varStatus="status">
                ["<c:out value='${user.actingParty.firstName}'/>","<c:out value='${user.actingParty.lastName}'/>","<c:out value='${user.role}'/>","<c:out value='${user.partyId}'/>",  <c:choose><c:when test="${user.partyState == 'ACTIVE'}">"<c:out value='${user.partyId}'/>"</c:when><c:otherwise>""</c:otherwise></c:choose>]
                <c:if test="${!status.last}">,
                </c:if>
                </c:forEach>
            ],
            "aoColumnDefs":[
                {
                    "aTargets": [ 3 ]
                    , "bSortable": false
                    , "mRender": function (edit, type, full) {

                    return  '<a href="<%=request.getContextPath()%>/admin/user.htm?userId=' + edit + '" data-method="PUT" >X</a>';

                }
                },
                {
                    "aTargets": [ 4 ]
                    , "bSortable": false
                    , "mRender": function (del, type, full) {
                    if ("" == del) {
                        return 'X';
                    } else {
//                        var userInfo = del.split(':');
                        var userFirstName = full[0];
                        var userLastName = full[1];
                        var userId = full[4];
                        return  '<a href="<%=request.getContextPath()%>/admin/user.htm?theMethod=delete&userId=' + userId + '&firstName='+userFirstName+'&lastName='+userLastName+'" data-method="DELETE" class="confirm">X</a>';
                    }
                }
                }
            ]
        }); // end users dataTable


        $("#customers").dataTable({
            "aaData":[
                <c:forEach items="${customers}" var="customer" varStatus="status">
                ["<c:out value='${customer.firstName}'/>","<c:out value='${customer.lastName}'/>","<c:out value='${customer.phone}'/>","<c:out value='${customer.provider}'/>","<c:out value='${customer.partyId}'/>","<c:out value='${customer.partyId}'/>"]
                <c:if test="${!status.last}">,
                </c:if>
                </c:forEach>
            ],
            "aoColumnDefs":[
                {
                    "aTargets": [ 4 ]
                    , "bSortable": false
                    , "mRender": function (edit) {
                    return  '<a href="<%=request.getContextPath()%>/admin/customer.htm?customerId=' + edit + '" data-method="PUT">X</a>';
                }
                },
                {
                    "aTargets": [ 5 ]
                    , "bSortable": false
                    , "mRender": function (del, type, full) {
                    var customerFirstName = full[0];
                    var customerLastName = full[1];
                    var customerId = full[4];
                    return  '<a href="<%=request.getContextPath()%>/admin/customer.htm?theMethod=delete&customerId=' + customerId + '&firstName=' + customerFirstName + '&lastName=' + customerLastName + '" data-method="DELETE" class="confirm">X</a>';
                }
                }
            ]
        }); // end users dataTable

        //select all anchors with a class of 'confirm'
        jQuery('a.confirm').click(function(event) {

            //prevent the default action of opening the link
            event.preventDefault()

            //grab the url of the current link
            var url = $(this).attr('href');
            var partyName = url.split('&');
            var partyFirstName = partyName[2].substring(partyName[2].lastIndexOf('=')+1,partyName[2].length);
            var partyLastName = partyName[3].substring(partyName[3].lastIndexOf('=')+1,partyName[3].length);


            //set the confirmation message
            var confirm_box = confirm('Are you sure you want to delete '+partyFirstName+' '+partyLastName);

            //if the confirmation is true (user clicks ok) direct the browser to the link's url
            if (confirm_box) {
                window.location = url;
                //uncomment below and remove above if you want the link to open in a new window
                //window.open(url,'_blank');
            }
        });
    });


</script>
<table id="outtertable" width="100%" class="tableReport">
    <c:choose>
        <c:when test="${empty messages}">

        </c:when>
        <c:otherwise>
            <tr align="center">
                <c:forEach items="${messages}" var="message">
                    <td>
                        <table class="messageImportant">
                            <tr>
                                <td><c:out value="${message}"/></td>
                            </tr>
                        </table>
                    </td>
                </c:forEach>
            </tr>
        </c:otherwise>
    </c:choose>
    <tr>
        <td><h1>Current Users</h1></td>
    </tr>
    <tr>
        <td>
            <table id="users" cellpadding="0" cellspacing="0" border="1" width="100%" class="tableReport">
                <thead>
                <tr class="site_name">
                    <th class="tableReport-th">First Name</th>
                    <th class="tableReport-th">Last Name</th>
                    <th class="tableReport-th">Role</th>
                    <th class="tableReport-th">Edit</th>
                    <th class="tableReport-th">Delete</th>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
        </td>
    </tr>
    <tr>
        <td>
            <hr/>
        </td>
    </tr>
    <tr>
        <td><a href="user.htm">Add User</a></td>
    </tr>

    <tr>
        <td>
            <hr/>
        </td>
    </tr>
    <tr>
        <td><h1>Current Customers</h1></td>
    </tr>
    <tr>
        <td>
            <table id="customers" cellpadding="0" cellspacing="0" border="1" width="100%" class="tableReport">
                <thead>
                <tr class="site_name2">
                    <th class="tableReport-th">First Name</th>
                    <th class="tableReport-th">Last Name</th>
                    <th class="tableReport-th">Phone Number</th>
                    <th class="tableReport-th">Provider</th>
                    <th class="tableReport-th">Edit</th>
                    <th class="tableReport-th">Delete</th>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
        </td>
    </tr>
    <tr>
        <td>
            <hr/>
        </td>
    </tr>
    <tr>
        <td><a href="customer.htm">Add Customer</a></td>
    </tr>

</table>





