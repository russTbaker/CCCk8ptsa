<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript"
        src="<c:url value='/resources/js/jquery-2.0.3.min.js'/>"></script>


<script type="text/javascript">
    var cssmenuids = ["cssmenu1"] //Enter id(s) of CSS Horizontal UL menus, separated by commas
    var csssubmenuoffset = -1 //Offset of submenus from main menu. Default is 0 pixels.

    function createcssmenu2() {
        for (var i = 0; i < cssmenuids.length; i++) {
            var ultags = document.getElementById(cssmenuids[i]).getElementsByTagName("ul")
            for (var t = 0; t < ultags.length; t++) {
                ultags[t].style.top = ultags[t].parentNode.offsetHeight + csssubmenuoffset + "px"
                var spanref = document.createElement("span")
                spanref.className = "arrowdiv"
                spanref.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;"
                ultags[t].parentNode.getElementsByTagName("a")[0].appendChild(spanref)
                ultags[t].parentNode.onmouseover = function () {
                    this.style.zIndex = 100
                    this.getElementsByTagName("ul")[0].style.visibility = "visible"
                    this.getElementsByTagName("ul")[0].style.zIndex = 0
                }
                ultags[t].parentNode.onmouseout = function () {
                    this.style.zIndex = 0
                    this.getElementsByTagName("ul")[0].style.visibility = "hidden"
                    this.getElementsByTagName("ul")[0].style.zIndex = 100
                }
            }
        }
    }

    if (window.addEventListener)
        window.addEventListener("load", createcssmenu2, false)
    else if (window.attachEvent)
        window.attachEvent("onload", createcssmenu2)

    /***********************************************

     * CSS Horizontal List Menu- by JavaScript Kit (www.javascriptkit.com)
     * Menu interface credits: http://www.dynamicdrive.com/style/csslibrary/item/glossy-vertical-menu/
     * This notice must stay intact for usage
     * Visit JavaScript Kit at http://www.javascriptkit.com/ for this script and 100s more

     ***********************************************/

</script>
<!-- END JavaScript -->
<link rel="stylesheet" href="<c:url value='/resources/css/csshorizontalmenu.css'/>">

<div class="horizontalcssmenu">
    <ul id="cssmenu1">
        <li><a href="#">Home</a></li>
        <li><a href="#">Programs & Events</a>
            <ul>
                <li><a href="#">After School Programs</a></li>
                <li><a href="#">In School Enrichment</a></li>
                <li><a href="#">Health & Wellness</a></li>
                <li><a href="#">Reflections</a></li>
            </ul>
        </li>
        <li><a href="#">Parent Resources</a>
            <ul>
                <li><a href="#">Curriculum Support</a></li>
                <li><a href="#">PTA</a></li>
                <li><a href="#">Jeffco Schools</a></li>
                <li><a href="#">Legislative</a></li>
                <li><a href="#">Just for Kids</a></li>
            </ul>
        </li>
        <li><a href="#">Volunteer</a>
            <ul>
                <li><a href="#">In Class Volunteering</a></li>
                <li><a href="#">Offsite Volunteering</a></li>
                <li><a href="#">Help Wanted</a></li>
                <li><a href="#">Job Descriptions</a></li>
                <li><a href="#">Volunteer Handbooks</a>
                    <ul>
                        <li><a href="<c:url value='/master.htm?view=enrichmentcoordinatorhandbook'/>">Enrichment
                            Coordinator Handbook</a></li>
                        <li>
                            <a href="<c:url value='/master.htm?view=thirdandfourthgradevolunteercoordinatorhandbook'/>">Third
                                And Fourth Grade Volunteer Coordinator Handbook</a></li>
                        <li>
                            <a href="<c:url value='/master.htm?view=thirdandfourthgraderoomparenthandbook'/>">Third
                                And Fourth Grades Room Parent Handbook</a>
                        </li>
                        <li>
                            <a href="<c:url value='/master.htm?view=middlelevelenrichmentcoordinatorhandbook'/>">Middle
                                level Enrichment coordinator Handbook</a>
                        </li>

                        <li>
                            <a href="<c:url value='/master.htm?view=middlelevelclassroomvolunteercoordinatorhandbook'/>">Middle
                                Level Classroom Volunteer Coordinator Handbook</a>
                        </li>
                        <li>
                            <a href="<c:url value='/master.htm?view=firstandsecondgradeclassroomvolunteercoordinatorhandbook'/>">First
                                and Second Grade Classrom Volunteer Coordinator Handbook</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </li>
        <li><a href="#">Fundraising</a>
            <ul>
                <li><a href="#">Donate / Special Giving</a></li>
                <li><a href="#">Box Tops</a></li>
                <li><a href="#">King Soopers Cards</a></li>
                <li><a href="#">Fundraising Events</a></li>
                <li><a href="#">Outdoor Lab</a></li>
                <li><a href="#">DC Trip </a></li>
            </ul>
        </li>
        <li><a href="#">Join, Donate</a>
            <ul>
                <li><a href="#">Join PTSA</a></li>
                <li><a href="#">Pay PTSA</a></li>
                <li><a href="#">Donate to PTSA</a></li>
            </ul>
        </li>
        <li><a href="#">General Information</a>
            <ul>
                <li><a href="#">About Us</a></li>
                <li><a href="#">Meeting Minutes & Agendas</a></li>
                <li><a href="#">Forms</a></li>
                <li><a href="#">C3AC</a></li>
            </ul>
        </li>
    </ul>
    <br style="clear: left;"/>
</div>