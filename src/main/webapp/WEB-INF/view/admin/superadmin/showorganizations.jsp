<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript" src="<c:url value='/resources/media/js/jquery.dataTables.js'/>"></script>


<script>
    $(document).ready(function() {
        $("#organizations").dataTable({
            "aaData":[
                <c:forEach items="${organizations}" var="org" varStatus="status">
                ["<c:out value='${org.organizationName}'/>","<c:out value='${org.contact.firstName}'/>","<c:out value='${org.contact.lastName}'/>","<c:out value='${org.contact.email}'/>","<c:out value='${org.mailsenderHost}'/>","<c:out value='${org.mailsenderUsername}'/>","<c:out value='${org.partyId}'/>","<c:out value='${org.organizationName}'/>:<c:out value='${org.partyId}'/>"]
                <c:if test="${!status.last}">,
                </c:if>
                </c:forEach>
            ],
            "aoColumnDefs":[
                {
                    "aTargets": [ 6 ]
                    , "bSortable": false
                    , "mRender": function (edit) {

                    return  '<a href="<%=request.getContextPath()%>/admin/superadmin/organization.htm?organizationId=' + edit + '" data-method="PUT" >X</a>';

                }
                },
                {
                    "aTargets": [ 7 ]
                    , "bSortable": false
                    , "mRender": function (del) {
                    if ("" == del) {
                        return 'X';
                    } else {
                        var companyInfo = del.split(':');
                        var companyName = companyInfo[0];
                        var companyId = companyInfo[1];
                        return  '<a href="<%=request.getContextPath()%>/admin/superadmin/organization.htm?theMethod=delete&organizationId=' + companyId + '&organizationName='+companyName+'" data-method="DELETE" class="confirm">X</a>';
                    }
                }
                }
            ]


        }); // end organizations dataTable

        //select all anchors with a class of 'confirm'
        jQuery('a.confirm').click(function(event) {

            //prevent the default action of opening the link
            event.preventDefault()

            //grab the url of the current link
            var url = $(this).attr('href');

            var organizationName = url.substring(url.lastIndexOf('=')+1,url.length);
            //set the confirmation message
            var confirm_box = confirm('Are you sure you want to delete '+organizationName+'?');

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
        <c:when test="${empty messages}"/>
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
        <td><h1>Current Reminderportal Customers</h1></td>
    </tr>
    <tr>
        <td>
            <table id="organizations" cellpadding="0" cellspacing="0" border="1" width="100%" class="tableReport">
                <thead>
                <tr class="site_name">
                    <th class="tableReport-th">Organization Name</th>
                    <th class="tableReport-th">Contact First Name</th>
                    <th class="tableReport-th">Contact last Name</th>
                    <th class="tableReport-th">Contact Email</th>
                    <th class="tableReport-th">Contact Email Host</th>
                    <th class="tableReport-th">Contact Email Username</th>
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
        <td><a href="organization.htm">Add Organization</a></td>
    </tr>

    <tr>
        <td>
            <hr/>
        </td>
    </tr>
</table>