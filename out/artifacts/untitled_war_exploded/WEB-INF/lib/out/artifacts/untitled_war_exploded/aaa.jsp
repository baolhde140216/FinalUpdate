<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="Models.classes.Answers" %>
<%@page import="java.util.ArrayList" %>
<%@ page import="Models.DatabaseClass" %>
<jsp:useBean id="pDAO" class="Models.DatabaseClass" scope="page"/>
<%
    int totalPage = new DatabaseClass().totalPageResult();
    session.setAttribute("totalPageResult",totalPage);
%>
<style>
    .panel {
        margin-left: 260px!important;
    }
</style>
<div class="sidebar" style="background-image: url(Common/Manual/sidebar-1.jpg)">
    <div class="sidebar-background">
        <h2 class="logo-text">
            Online Examination System
        </h2>
        <div class="left-menu">
            <a href="std-page.jsp?pgprt=0"><h2>Profile</h2></a>
            <a href="std-page.jsp?pgprt=1"><h2>Exams</h2></a>
            <a class="active" href="paging?action=result"><h2>Results</h2></a>
        </div>
    </div>

</div>
<!-- CONTENT AREA -->
<div class="content-area">
    <div class="panel" style="float: left;max-width: 900px">
        <%
            if (request.getParameter("eId") == null) {
        %>
        <div class="title">All Results</div>
        <table id="rounded-corner">
            <thead>
            <tr>
                <th scope="col" class="rounded-company">Date</th>
                <th scope="col" class="rounded-q1">Course</th>
                <th scope="col" class="rounded-q2">Time</th>
                <th scope="col" class="rounded-q3">Status</th>
                <th scope="col" class="rounded-q4">Action</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${sessionScope.pagingItems}" var="item">
                <tr>
                    <td>${item.date}</td>
                    <td>${item.cName}</td>
                    <td>${item.startTime} - ${item.endTime}</td>
                    <c:if test="${item.status.equals('Pass')}">
                        <td style="background: #00cc33;color:white">${item.status}</td>
                    </c:if>
                    <c:if test="${item.status.equals('Fail')}">
                        <td style="background: #ff3333;color:white">${item.status}</td>
                    </c:if>
                    <c:if test="${item.status == null}">
                        <td style="background: bisque ;">Terminated</td>
                    </c:if>
                    <td><a href="std-page.jsp?pgprt=2&eId=${item.examId}">Details</a></td>--%>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <nav aria-label="Page navigation example" style="margin-left: 2rem">
            <ul class="pagination">
                <c:forEach begin="1" end="${sessionScope.totalPageResult}" var="i">
                    <c:if test="${sessionScope.index.equals(i)}">
                        <li class="page-item active"><a class="page-link" href="paging.action?action=result&index=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${!sessionScope.index.equals(i)}">
                        <li class="page-item"><a class="page-link" href="paging.action?action=result&index=${i}">${i}</a></li>
                    </c:if>
                </c:forEach>
            </ul>
        </nav>

        <%
        } else {
        %>
        <div class="title">Result Details</div>
        <table id="gradient-style">

            <%
                ArrayList list = pDAO.getAllAnswersByExamId(Integer.parseInt(request.getParameter("eId")));
                for (int i = 0; i < list.size(); i++) {
                    Answers a = (Answers) list.get(i);

            %>
            <tr>
                <td rowspan="2"><%=i + 1 %>)</td>
                <td colspan="2"><%=a.getQuestion()%>
                </td>
                <td rowspan="2"><%=a.getStatus() %>
                </td>
            </tr>
            <tr>
                <td><%="Your Ans: " + a.getAnswer()%>
                </td>
                <td><%="Correct Ans: " + a.getCorrectAns() %>
                </td>
            </tr>
            <tr>
                <td colspan="3" style="background: white"></td>
                    <%
                }
                %>
        </table>
        <%
            }
        %>
    </div>
</div>