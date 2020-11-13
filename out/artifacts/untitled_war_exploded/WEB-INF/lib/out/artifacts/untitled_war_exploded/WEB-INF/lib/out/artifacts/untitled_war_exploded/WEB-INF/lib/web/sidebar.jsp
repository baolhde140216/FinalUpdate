<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Responsive Sidebar Dashboard Template</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="Common/Manual/style-backend.css">
    <link rel="stylesheet" href="/Common/login/css/bootstrap.min.css" type="text/css">
</head>
<body>

<input type="checkbox" id="check">
<!--header area start-->
<header>
    <label for="check">
        <i class="fas fa-bars" id="sidebar_btn"></i>
    </label>
    <div class="left_area">
        <h3>Coding <span>Snow</span></h3>
    </div>
    <div class="right_area">
        <a href="#" class="logout_btn">Logout</a>
    </div>
</header>
<!--header area end-->
<!--mobile navigation bar start-->
<div class="mobile_nav">
    <div class="nav_bar">
        <i class="fa fa-bars nav_btn"></i>
    </div>
    <div class="mobile_nav_items">
        <a href="#"><i class="fas fa-desktop"></i><span>Dashboard</span></a>
        <a href="#"><i class="fas fa-cogs"></i><span>Components</span></a>
        <a href="#"><i class="fas fa-table"></i><span>Tables</span></a>
        <a href="#"><i class="fas fa-th"></i><span>Forms</span></a>
        <a href="#"><i class="fas fa-info-circle"></i><span>About</span></a>
        <a href="#"><i class="fas fa-sliders-h"></i><span>Settings</span></a>
    </div>
</div>
<!--mobile navigation bar end-->
<!--sidebar start-->
<div class="sidebar">
    <div class="profile_info">
        <h4>Jessica</h4>
    </div>
    <a href="#"><i class="fas fa-desktop"></i><span>Dashboard</span></a>
    <a href="#"><i class="fas fa-cogs"></i><span>Components</span></a>
    <a href="#"><i class="fas fa-table"></i><span>Tables</span></a>
    <a href="#"><i class="fas fa-th"></i><span>Forms</span></a>
    <a href="#"><i class="fas fa-info-circle"></i><span>About</span></a>
    <a href="#"><i class="fas fa-sliders-h"></i><span>Settings</span></a>
</div>
<!--sidebar end-->

<div class="content">
    <%
        if (session.getAttribute("userStatus") != null) {
            if (session.getAttribute("userStatus").equals("1") && session.getAttribute("type").equals("1")) {

    %>


    <%
        if (request.getParameter("pgprt").equals("1")) {

    %>

    <jsp:include page="accounts.jsp"/>

    <%

    } else if (request.getParameter("pgprt").equals("2")) {

    %>

    <jsp:include page="courses.jsp"/>

    <%

    } else if (request.getParameter("pgprt").equals("3")) {
    %>

    <jsp:include page="questions.jsp"/>
    <%
    } else if (request.getParameter("pgprt").equals("4")) {
        session.setAttribute("courseName",request.getParameter("coursename"));
    %>

    <jsp:include page="showall.jsp"/>
    <%
    } else {
    %>

    <jsp:include page="profile.jsp"/>

    <%
        }
    %>

    <%
            } else {

                response.sendRedirect("std-page.jsp?pgprt=0");
            }
        } else response.sendRedirect("login.jsp");
    %>
</div>

<script type="text/javascript">
    $(document).ready(function(){
        $('.nav_btn').click(function(){
            $('.mobile_nav_items').toggleClass('.active');
        });
    });
</script>

</body>
</html>
