<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Models.classes.Exams" %>
<%@page import="Models.classes.Questions" %>
<%@page import="java.util.ArrayList" %>
<%@ page import="Models.classes.Courses" %>
<%@ page import="Models.DatabaseClass" %>
<jsp:useBean id="pDAO" class="Models.DatabaseClass" scope="page"/>

<style>
    .content-area {
        display: block !important;
        margin-top: 4rem;
        margin-left: 20rem;
        padding: 20px;
        padding-left: 30px;
    }

    .question-label {
        height: 65px !important;
    }
</style>

<div class="sidebar" style="background-image: url(Common/Manual/sidebar-1.jpg)">
    <div class="sidebar-background">
        <h2 class="logo-text">
            Online Examination System
        </h2>
        <div class="left-menu">
            <a href="std-page.jsp?pgprt=0"><h2>Profile</h2></a>
            <a class="active" href="std-page.jsp?pgprt=1"><h2>Exams</h2></a>
            <a href="paging?action=result"><h2>Results</h2></a>
        </div>
    </div>

</div>

<!-- CONTENT AREA -->
<div class="content-area">
    <% if (session.getAttribute("examStarted") != null) { %>

    <% }%>


    <%
        if (session.getAttribute("examStarted") != null) {
            if (session.getAttribute("examStarted").equals("1")) {
    %>
    <span id="remainingTime"
          style="position: fixed;top:90px;left: 300px;font-size: 23px;background: rgba(255,0,77,0.38);border-radius: 5px;padding: 10px;box-shadow: 2px -2px 6px 0px;">
        </span>
    <%
        int time = new DatabaseClass().getRemainingTime(Integer.parseInt(session.getAttribute("examId").toString()));
    %>
    <script>
        let time,sec;
        if(window.localStorage.getItem("time")==null || localStorage.getItem("examId")==null
        || localStorage.getItem("examId")!=<%=session.getAttribute("examId").toString()%>) {
            time = <%=time%>;
            sec = 0;
            window.localStorage.setItem("time",time);
            window.localStorage.setItem("sec",sec);
            localStorage.setItem("examId",<%=session.getAttribute("examId").toString()%>)
        }


        else {
            time = window.localStorage.getItem("time");
            sec = window.localStorage.getItem("sec");
        }



        document.getElementById("remainingTime").innerHTML = time + " : " + sec;
        let x = window.setInterval(timerFunction, 1000);

        function timerFunction() {
            sec--;
            window.localStorage.setItem("sec",sec);
            if (time < 0) {
                clearInterval(x);
                document.getElementById("remainingTime").innerHTML = "00 : 00";
                document.getElementById("myform").submit();
            }
            if (sec < 0) {
                sec = 59;
                time--;
                window.localStorage.setItem("time",time);
            }
            document.getElementById("remainingTime").innerHTML = time + " : " + sec;
        }
        <c:if test="${sessionScope.examStarted!=null}">
            window.onbeforeunload = function(e) {
                <%session.setAttribute("examStarted",null);%>
                return "Your test wil be failed";
            }

        </c:if>
            function validate() {
                if(!confirm("Do you really want to do this?")) {
                    return false;
                }
                <%session.setAttribute("examStarted",null);%>
                this.form.submit();
            }
    </script>

    <form id="myform" onsubmit="return validate(this)" name="abc" action="exam.action" method="post">
        <%
            ArrayList<Questions> list = pDAO.getQuestions(request.getParameter("coursename"));
            Questions question;
        %>
        <input type="hidden" name="size" value="<%=list.size()%>">
        <input type="hidden" name="totalmarks"
               value="<%=pDAO.getTotalMarksByCode(request.getParameter("coursename"))%>">
        <%
            for (int i = 0; i < list.size(); i++) {
                question = list.get(i);
        %>
        <center>
            <div class="question-panel">
                <div class="question">
                    <label class="question-label"><%=i + 1 %>
                    </label>
                    <%=question.getQuestion() %>
                </div>
                <div class="answer">
                    <input type="radio" id="c1<%=i%>" name="ans<%=i%>" value="<%=question.getOpt1()%>"/>
                    <label for="c1<%=i%>"><%=question.getOpt1()%>
                    </label>
                    <input type="radio" id="c2<%=i%>" name="ans<%=i%>" value="<%=question.getOpt2()%>"/>
                    <label for="c2<%=i%>"><%=question.getOpt2()%>
                    </label>
                    <input type="radio" id="c3<%=i%>" name="ans<%=i%>" value="<%=question.getOpt3()%>"/>
                    <label for="c3<%=i%>"><%=question.getOpt3()%>
                    </label>
                    <input type="radio" id="c4<%=i%>" name="ans<%=i%>" value="<%=question.getOpt4()%>"/>
                    <label for="c4<%=i%>"><%=question.getOpt4()%>
                    </label>

                </div>
            </div>
            <input type="hidden" name="question<%=i%>" value="<%=question.getQuestion()%>">
            <input type="hidden" name="qid<%=i%>" value="<%=question.getQuestionId()%>">
                <%
                       }
                       
                       %>
            <input type="hidden" name="action" value="submitted">
            <input type="submit" class="add-btn" value="Finished"/>
    </form>



    <%
        }
    } else if (request.getParameter("showresult") != null) {
        if (request.getParameter("showresult").equals("1")) {
            Exams result = new DatabaseClass().getResultByExamId(Integer.parseInt(request.getParameter("eId")));
    %>
    <div class="panel" style="float: left;max-width: 900px">
        <div class="title">Result of Recent Exam</div>
        <div class="profile">
            <span class="tag">Exam Date</span><span class="val"><%=result.getDate() %></span><br/>
            <span class="tag">Start Time</span><span class="val"><%=result.getStartTime() %></span><br/>
            <span class="tag">End Time</span><span class="val"><%=result.getEndTime() %></span><br/>
            <span class="tag">Course Name</span><span class="val"><%=result.getcName() %></span><br/>
            <span class="tag">Obt Marks</span><span class="val"><%=result.getObtMarks() %></span><br/>
            <span class="tag">Total Marks</span><span class="val"><%=result.gettMarks() %></span><br/>
            <span class="tag">Result</span><span class="val"><%=result.getStatus() %></span></h2>
        </div>
        <p></p>
        <p></p>
        <p></p>
        <p></p>
        <p></p>
    </div>
    <%
        }
    } else if (session.getAttribute("examStarted") == null) {
    %>
    <div class="panel form-style-6" style="float: left;max-width: 900px; padding-top: 40px;">
        <div class="title" style="margin-top: -60px;">Select Course to Take Exam</div>
        <br/>
        <form action="exam.action" method="post">
            <br/><br>
            <label>Select Course</label>
            <input type="hidden" name="action" value="startExam">
            <select name="coursename" class="text">
                <%
                    ArrayList<Courses> list1 = pDAO.getAllCourses();
                    for (int i = 0; i < list1.size(); i++) {
                %>
                <option value="<%=list1.get(i).getcCode()%>"><%=list1.get(i).getcName()%>
                </option>
                <%
                    }
                %>
            </select>
            <input type="submit" value="Take Exam" class="form-button">
        </form>
    </div>
    <% }%>
</div>
       