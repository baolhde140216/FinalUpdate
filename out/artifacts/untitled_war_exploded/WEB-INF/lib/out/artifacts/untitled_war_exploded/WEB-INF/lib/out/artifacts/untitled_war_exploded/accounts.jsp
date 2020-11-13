<%@page import="java.util.ArrayList" %>
<%@ page import="Models.classes.User" %>
<%@ page import="Models.DatabaseClass" %>
<%@ page import="java.util.stream.Collectors" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%

    User user = new DatabaseClass().getUserDetails(session.getAttribute("userId").toString());
    if (user.getType().endsWith("admin")) {
%>
<!-- SIDEBAR -->

<link rel="stylesheet" href="Common/test/css/styleAccount.css">
<link rel="stylesheet" href="Common/test/fonts/font-awesome-4.7.0/css/font-awesome.css">
<style>


    /* The Modal (background) */
    .modal {
        display: none; /* Hidden by default */
        position: fixed; /* Stay in place */
        z-index: 1; /* Sit on top */
        padding-top: 100px; /* Location of the box */
        left: 163px;
        top: 0;
        width: 100%; /* Full width */
        height: 100%; /* Full height */
        overflow: auto; /* Enable scroll if needed */
        background-color: rgb(0, 0, 0); /* Fallback color */
        background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
    }

    /* Modal Content */
    .modal-content {
        background-color: #fefefe;
        margin: auto;
        padding: 20px;
        border: 1px solid #888;
        width: 50%;
    }

    /* The Close Button */
    .close {
        color: #aaaaaa;

        font-size: 28px;
        float: right;
        font-weight: bold;
        position: relative;
        left: 688px;
        top: -10px;
    }

    .close:hover,
    .close:focus {
        color: #000;
        text-decoration: none;
        cursor: pointer;
    }
</style>
<%
    int totalPageResult = new DatabaseClass().totalPageAccount(session.getAttribute("query").toString());
    session.setAttribute("totalPageResult",totalPageResult);
%>
<div class="inner" style="margin-top: 50px;background-color: whitesmoke!important;width: 1078px">
    <div class="title" style="margin-top: -30px; height: 60px!important;">List of All Registered Persons</div>
    <a href="Register.jsp" class="button" style="text-decoration: none!important;"><span class="add-btn" style="margin-left: 80px;">Add New Person</span></a>
    <form method="get" action="paging" class="container" style="margin-top: 1rem">
        <input type="hidden" name="action" value="account">
        <div class="row justify-content-center">
            <div class="form-group col-4 ">
                <input name="query" type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
                       placeholder="Search by username">
            </div>
        </div>

    </form>
    <br>

    <table id="one-column-emphasis" style="margin-top: 10px">
        <colgroup>
            <col class="oce-first"/>
        </colgroup>
        <thead>
        <tr>
            <th scope="col">User Name</th>
            <th scope="col">First Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">Email</th>
            <th scope="col">Roles</th>
            <th scope="col">Update</th>
            <th scope="col">Delete</th>

        </tr>
        </thead>
        <tbody>


        <c:forEach var="item" items="${sessionScope.pagingItems}">
            <tr>
                <td style="padding: 12px 15px;">${item.userName}</td>
                <td style="padding: 12px 15px;">${item.firstName}</td>
                <td style="padding: 12px 15px;">${item.lastName}</td>
                <td style="padding: 12px 15px;">${item.email}</td>
                <td style="padding: 12px 15px;">${item.type}</td>
                <td style="padding: 20px 15px;">
                    <a href="updateUser?action=updateGet&userId=${item.userId}"
                       type="submit" class="btn btn-primary" id="myBtn"><i class="fas fa-edit"></i>
                    </a>
                </td>
                <td style="padding: 12px 15px;">
                    <a type="submit" class="btn btn-danger" href="user?action=delete&userId=${item.userId}"
                       onclick="return confirm('Are you sure you want to delete this ?');">
                        <i class="fas fa-trash-alt"></i>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <nav aria-label="Page navigation example" style="margin:1rem 45%">
        <ul class="pagination" style="padding: 1rem">
            <c:forEach begin="1" end="${sessionScope.totalPageResult}" var="i">
                <c:if test="${sessionScope.index.equals(i)}">
                    <li class="page-item active"><a class="page-link" href="paging.action?action=account&index=${i}">${i}</a></li>
                </c:if>
                <c:if test="${!sessionScope.index.equals(i)}">
                    <li class="page-item"><a class="page-link" href="paging.action?action=account&index=${i}">${i}</a></li>
                </c:if>
            </c:forEach>
        </ul>
    </nav>
        <div id="myModal" class="modal">
            <!-- Modal content -->
            <div class="modal-content">
                <span class="close">&times;</span>
                <form action="updateUser.action" method="POST">
                    <input type="hidden" name="userName" value="${sessionScope.userUpdate.userName}">
                    <div class="modal-body">
                        <h2>Update Info</h2>
                        <input type="hidden" name="userId" id="edit_id" value="${sessionScope.userUpdate.userId}">
                        <div class="form-group">
                            <label>First Name</label>
                            <input type="text" name="firstName" id="edit_fname" class="form-control"
                                   value="${sessionScope.userUpdate==null ? "":sessionScope.userUpdate.firstName}">
                        </div>
                        <div class="form-group">
                            <label>Last Name</label>
                            <input type="text" name="lastName" id="edit_lname" class="form-control"
                                   value="${sessionScope.userUpdate==null ? "":sessionScope.userUpdate.lastName}">
                        </div>
                        <div class="form-group">
                            <s:fielderror fieldName="emailValidation" style="color:red"></s:fielderror>
                            <label>Email</label>
                            <input type="text" name="email" id="edit_class" class="form-control"
                                   value="${sessionScope.userUpdate==null ? "":sessionScope.userUpdate.email}">
                        </div>
                        <div class="form-group">
                            <label>Pass</label>
                            <input type="password" name="password" id="edit_section" class="form-control"
                                   value="${sessionScope.userUpdate==null ? "":sessionScope.userUpdate.password}">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeForm()">
                            Close
                        </button>
                        <input type="hidden" name="action" value="update">
                        <input type="submit" name="update_student" class="btn btn-primary" value="Update">
                    </div>
                </form>
            </div>

        </div>

        <%
            if (session.getAttribute("isUpdate") != null) {
                out.println("<script>var modal = document.getElementById(\"myModal\");" +
                        "modal.style.display = \"block\";</script>");
            }
        %>
        <script>


            // Get the modal
            var modal = document.getElementById("myModal");

            // Get the button that opens the modal
            var btn = document.getElementById("myBtn");

            // Get the <span> element that closes the modal
            var span = document.getElementsByClassName("close")[0];

            // When the user clicks the button, open the modal
            btn.onclick = function () {
                modal.style.display = "block";
            }

            // When the user clicks on <span> (x), close the modal
            span.onclick = function () {
                modal.style.display = "none";
            }

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }

            function closeForm() {
                modal.style.display = "none";
                <%
                    session.removeAttribute("isUpdate");
//                        session.removeAttribute("userUpdate");
                %>
            }
        </script>


        <script src="Common/test/js/ShowpassAndHide.js"></script>


        <!-- and so on... -->
        </tbody>
    </table>

</div>


<%
    }

%>

</div>
<%--</div>--%>