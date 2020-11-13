package Controllers;

import myPackage.DatabaseClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/CourseController")
public class CourseController extends HttpServlet {
    private DatabaseClass db = null;

    public CourseController() throws SQLException, ClassNotFoundException {
        db = new DatabaseClass();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "add":
                add(request,response);
                break;
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        db.addNewCourse(request.getParameter("coursename"),Integer.parseInt(request.getParameter("totalmarks")),
                request.getParameter("time"),request.getParameter("courseCode"));
        response.sendRedirect("adm-page.jsp?pgprt=2");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "delete":
                db.delCourse(request.getParameter("cCode"));
                response.sendRedirect("adm-page.jsp?pgprt=2");
                break;
        }
    }
}
