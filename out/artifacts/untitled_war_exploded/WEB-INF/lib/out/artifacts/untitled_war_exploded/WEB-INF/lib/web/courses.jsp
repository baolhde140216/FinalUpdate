<%@page import="java.util.ArrayList" %>
<%@ page import="Models.classes.Courses" %>
<jsp:useBean id="pDAO" class="Models.DatabaseClass" scope="page"/>



    <div class="panel" style="max-width: 420px;float:right">
        <div class="title">
            All Courses
        </div>
        <table id="one-column-emphasis" style="min-width: 400px;margin: 5px; margin-top: 35px">
            <colgroup>
                <col class="oce-first"/>
            </colgroup>
            <thead>
            <tr>
                <th scope="col">Course Code</th>
                <th scope="col">Courses</th>
                <th scope="col">T.Marks</th>
                <th scope="col">Action</th>

            </tr>
            </thead>
            <%
                ArrayList<Courses> list = pDAO.getAllCourses();
                for (int i = 0; i < list.size(); i++) {
            %>
            <tr>
                <td><%=list.get(i).getcCode()%>
                </td>
                <td><%=list.get(i).getcName()%>
                </td>
                <td><%=list.get(i).gettMarks()%>
                </td>
                <td><a href="course?action=delete&courseCode=<%=list.get(i).getcCode()%>"
                       onclick="return confirm('Are you sure you want to delete this ?');" class="del">
                    <div class="delete-btn" style="max-width: 40px;font-size: 17px; padding: 3px">X</div>
                </a></td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
    <div class="panel form-style-6" style="max-width: 450px">
        <div class="title">
            Add New Course
        </div>
        <center>
            <form action="course.action" method="post">
                <br>
                <table>
                    <tr>
                        <td><label>Course Code</label></td>
                        <td><input type="text" name="courseCode" class="text" placeholder="Course Name"
                                   style="width: 230px;"></td>
                    </tr>
                    <tr>
                        <td><label>Course Name</label></td>
                        <td><input type="text" name="coursename" class="text" placeholder="Course Name"
                                   style="width: 230px;"></td>
                    </tr>
                    <tr>
                        <td><label>Total Marks</label></td>
                        <td><input type="text" name="totalmarks" class="text" placeholder="Total Marks"
                                   style="width: 230px;"></td>
                    </tr>
                    <tr>
                        <td><label>Exam Time</label></td>
                        <td>
                            <input type="text" name="time" class="text" placeholder="MM" style="width: 230px;">
                        </td>

                    </tr>
                    <tr>
                        <td colspan="2"><input type="hidden" name="page" value="courses">
                            <input type="hidden" name="action" value="add">
                            <center><input type="submit" class="form-button" value="Add" name="submit"></center>
                        </td>
                    </tr>
                </table>

            </form>
        </center>

    </div>

                       