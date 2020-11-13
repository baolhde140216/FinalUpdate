package Controllers;

import Models.DatabaseClass;
import Models.classes.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DatabaseClass pDAO = null;
        try {
            pDAO = new DatabaseClass();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            User user = pDAO.loginValidate(request.getParameter("username"), request.getParameter("password"));
            if(user!=null){
                request.getSession().setAttribute("type", user.getType().equals("admin")? "1":"0");
                request.getSession().setAttribute("userStatus", "1");
                request.getSession().setAttribute("userId",pDAO.getUserId(request.getParameter("username")));
                response.sendRedirect("LoginController");
            }else{
                request.getSession().setAttribute("userStatus", "-1");
                response.sendRedirect("login.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DatabaseClass pDAO = null;
        try {
            pDAO = new DatabaseClass();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (request.getSession().getAttribute("userStatus") != null) {
            String loginConfirm = request.getSession().getAttribute("userStatus").toString();
            if (loginConfirm.equals("1")) {
                if (pDAO.getUserType(request.getSession().getAttribute("userId").toString()).equals("admin")) {
                    response.sendRedirect("adm-page.jsp?pgprt=0");
                } else if (pDAO.getUserType(request.getSession().getAttribute("userId").toString()).equals("student")) {
                    response.sendRedirect("std-page.jsp?pgprt=0");
                }
            } else if (!loginConfirm.equals("1")) {
                response.sendRedirect("HomeController?action=login");
            }
        } else response.sendRedirect("HomeController?action=login");
    }
}
