<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="Models.classes.User" %>
<%@page import="java.util.ArrayList" %>
<jsp:useBean id="pDAO" class="Models.DatabaseClass" scope="page"/>

<%
    User user = pDAO.getUserDetails(session.getAttribute("userId").toString());
    if (user.getType().endsWith("admin")) {
%>
<div class="content-area">
    <div class="panel" style="float: left;max-width: 600px">


            <%
           }else{
               %>

        <!-- SIDEBAR -->
        <div class="sidebar" style="background-image: url(Common/Manual/sidebar-1.jpg)">
            <div class="sidebar-background">
                <h2 class="logo-text">
                    Online Examination System
                </h2>
                <div class="left-menu">
                    <a class="active" href="std-page.jsp?pgprt=0"><h2>Profile</h2></a>
                    <a href="std-page.jsp?pgprt=1"><h2>Exams</h2></a>
                    <a href="paging?action=result"><h2>Results</h2></a>
                </div>
            </div>

        </div>
        <!-- CONTENT AREA -->
        <div class="content-area">
            <div class="panel" style="float: left;max-width: 600px">
                <%
                    }
                    if (request.getParameter("pedt") == null) {
                %>
                <div class="title">Profile</div>
                <h2 style="padding-left: 131px">Your information</h2>
                <div class="profile " >

                    <span class="tag">Your Name</span>
                        <span style="background-color: floralwhite"
                            class="val"><%=user.getFirstName() + " " %><%=user.getLastName()%></span><br/>
                        <span class="tag">Email</span><span style="background-color: floralwhite" class="val"><%=user.getEmail()%></span><br/>
                        <span class="tag">Username</span><span style="background-color: floralwhite" class="val"><%=user.getUserName()%></span><br/>
                </div>
                <%
                    if (user.getType().equals("admin")) {
                %>
                <br/>
                <a href="adm-page.jsp?pgprt=0&pedt=1"><span class="add-btn">Edit Profile</span></a>
                <%
                } else {
                %><br/>
                <a href="std-page.jsp?pgprt=0&pedt=1"><span class="add-btn">Edit Profile</span></a>
                <%
                    }
                %>

                <%
                } else {
                %>

                <div class="title">Edit Profile</div>
                <div class="central-div form-style-6" style="position:inherit;margin-top: 70px;">
                    <form action=<%=session.getAttribute("type").equals("1") ? "updateProfileAdmin.action" :
                    "updateProfileStudent.action"%> method="post">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="userId" value="<%=user.getUserId()%>">
                        <input type="hidden" name="userName" value="<%=user.getUserName()%>">
                        <table>
                            <tr>
                                <td>
                                    <label>First Name</label>
                                </td>
                                <td>
                                    <input type="text" name="firstName" value="<%=user.getFirstName() %>" class="text"
                                           placeholder="First Name">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label>Last Name</label>
                                </td>
                                <td>
                                    <input type="text" name="lastName" value="<%=user.getLastName() %>" class="text"
                                           placeholder="Last Name">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label>Email</label>
                                </td>
                                <td>
                                    <s:fielderror fieldName="emailValidation" style="color:red"></s:fielderror>
                                    <input type="email" name="email" value="<%=user.getEmail() %>" class="text"
                                           placeholder="Email">
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label>Password</label>
                                </td>
                                <td>
                                    <input type="password" value="<%=user.getPassword() %>" name="password" class="text"
                                           placeholder="Password">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td>
                                    <center>

                                        <input type="submit" value="Done" class="button">
                                    </center>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>


                <%
                    }
                %>

            </div>
        </div>