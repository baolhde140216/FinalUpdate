package Controllers;

import myPackage.DatabaseClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/QuestionController")
public class QuestionController extends HttpServlet {
    DatabaseClass pDAO = new DatabaseClass();

    public QuestionController() throws SQLException, ClassNotFoundException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "add":
                add(request,response);
                break;
            case "delete":
                pDAO.delQuestion(Integer.parseInt(request.getParameter("qid")));
                response.sendRedirect("adm-page.jsp?pgprt=3");
                break;
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        pDAO.addQuestion(request.getParameter("coursename"), request.getParameter("question"),
        request.getParameter("opt1"), request.getParameter("opt2"), request.getParameter("opt3"),
        request.getParameter("opt4"), request.getParameter("correct"));

        //admin Controller
        response.sendRedirect("adm-page.jsp?pgprt=3");
//        } else if (request.getParameter("operation").equals("del")) {
//            pDAO.delCourse(request.getParameter("cname").toString());
//            response.sendRedirect("adm-page.jsp?pgprt=3");
//        } else if (request.getParameter("operation").equals("delQuestion")) {
//            pDAO.delQuestion(Integer.parseInt(request.getParameter("qid")));
//            response.sendRedirect("adm-page.jsp?pgprt=3");
//
//        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "delete":
                pDAO.delQuestion(Integer.parseInt(request.getParameter("qid")));
                response.sendRedirect("adm-page.jsp?pgprt=3");
                break;
        }
    }
}
