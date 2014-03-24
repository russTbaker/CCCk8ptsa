<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript"
        src="<c:url value='/resources/js/jquery-2.0.3.min.js'/>"></script>
<script type="text/javascript">
    $(document).ready(function() {

        // Function for navigating different coloured bars, only needed in the demo
        $(".navWrap a").click(function() {
            var activeTab = $(this).attr("href");
            $(activeTab).show().addClass('activeMenu');
            $(activeTab).siblings('.wrapper').hide().removeClass('activeMenu');
            return false;
        });

        // Requried: Navigation bar drop-down
        $("nav ul li").hover(function() {
            $(this).addClass("active");
            $(this).find("ul").show().animate({opacity: 1}, 400);
        }, function() {
            $(this).find("ul").hide().animate({opacity: 0}, 200);
            $(this).removeClass("active");
        });

        // Requried: Addtional styling elements
        $('nav ul li ul li:first-child').prepend('<li class="arrow"></li>');
        $('nav ul li:first-child').addClass('first');
        $('nav ul li:last-child').addClass('last');
        $('nav ul li ul').parent().append('<span class="dropdown"></span>').addClass('drop');

    });
</script>
<!-- END JavaScript -->
<link rel="stylesheet" href="<c:url value='/resources/jquery-powered-html5-navigation-menu/Blue/css/navbar.css'/>">

<div align="center" class="wrapper activeMenu" id="dark">
    <!-- BEGIN Dark navigation bar -->
    <nav class="blue">
        <ul class="clear">
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
    </nav>
    <!-- END Dark navigation bar -->
</div>