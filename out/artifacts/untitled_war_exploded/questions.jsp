<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList" %>
<%@ page import="Models.classes.Courses" %>
<%@ page import="Models.DatabaseClass" %>

    <div class="panel form-style-6" style="min-width: 300px;max-width: 390px">
        <form action="paging.action" method="get">
            <div class="title">Show All Questions for</div>
            <br><br>
            <label>Select Course</label>
            <input type="hidden" name="action" value="question">
            <select name="coursename" class="text">
                <%
                    ArrayList<Courses> list1 = new DatabaseClass().getAllCourses();
                    session.setAttribute("courses",list1);
                %>
                <c:forEach var="item" items="${sessionScope.courses}">
                    <option value=${item.cCode}>${item.cName}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Show" class="form-button">
        </form>
    </div>

    <div class="panel form-style-6" style="max-width: 700px!important;">
        <form action="question.action" method="post">
            <div class="title">Add New Question</div>
            <table>
                <tr>
                    <td><label>Course Name</label></td>
                    <td colspan="3">
                        <select name="coursename" class="text">
                            <c:forEach var="item" items="${sessionScope.courses}">
                                <option value=${item.cCode}>${item.cName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label>Your Question:</label></td>
                    <td colspan="4"><input type="text" name="question" class="text"
                                           placeholder="Type your question here" style="width: 420px;"></td>
                    <br>
                </tr>
                <tr>
                    <td><label>Options</label></td>
                    <td><input type="text" name="opt1" class="text" placeholder="First Option" style="width: 130px;"><br>
                    </td>
                    <td><input type="text" name="opt2" class="text" placeholder="Second Option" style="width: 130px;"><br>
                    </td>
                    <td><input type="text" name="opt3" class="text" placeholder="Third Option" style="width: 130px;"><br>
                    </td>
                    <td><input type="text" name="opt4" class="text" placeholder="Fourth Option" style="width: 130px;">
                    </td>
                </tr>
                <tr>
                    <td><label>Correct Answer</label></td>
                    <td colspan="4">
                        <center><input type="text" name="correct" class="text" placeholder="Correct Answer"
                                       style="width: 130px;"></center>
                    </td>
                <tr>


                <input type="hidden" name="action" value="add">
                </tr>
            </table>
            <input type="submit" class="form-button" value="Add" name="submit">
        </form>


    </div>
