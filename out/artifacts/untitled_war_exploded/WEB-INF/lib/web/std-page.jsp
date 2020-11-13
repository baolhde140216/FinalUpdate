<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="style-backend.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
<script>

</script>
<div class="top-area">
    <center><h2>Student Panel</h2></center>
    <a href="home?action=logout" class="button" style="float: right;background:crimson;color:white">Logout</a>
</div>

<%
    if (session.getAttribute("userStatus") != null) {
        if (session.getAttribute("userStatus").equals("1") && session.getAttribute("type").equals("0")) {
%>

<div class="menu">
    <a href="std-page.jsp?pgprt=0" class="button">profile</a>
    <a class="button" href="std-page.jsp?pgprt=1">exams</a>
    <a class="button" href="paging?action=result">results</a>
    <a href="HomeController?action=logout" class="button" style="float: right;background:crimson;color:white">Logout</a>
</div>
<%
    if (request.getParameter("pgprt").equals("1")) {

%>

<jsp:include page="exam.jsp"/>

<%

} else if (request.getParameter("pgprt").equals("2")) {

%>

<jsp:include page="results.jsp"/>

<%

} else {
%>
<jsp:include page="profile.jsp"/>
<%
    }
%>
<%
        } else response.sendRedirect("adm-page.jsp?pgprt=0");
    } else response.sendRedirect("login.jsp");
%>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>

