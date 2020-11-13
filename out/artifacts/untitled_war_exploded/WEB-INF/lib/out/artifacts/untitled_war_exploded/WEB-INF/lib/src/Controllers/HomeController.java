package Controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/HomeController")
public class HomeController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "login":
                if(request.getSession().getAttribute("userStatus")!=null) {
                    if(request.getSession().getAttribute("type").toString().equals("1"))
                        response.sendRedirect("adm-page.jsp?pgprt=0");
                    else {
                        response.sendRedirect("std-page.jsp?pgprt=0");
                    }
                }
                else {
                    request.getRequestDispatcher("login.jsp").forward(request,response);
                }

                break;
            case "logout":
                request.getSession().invalidate();
                response.sendRedirect("HomeController?action=home");
                break;
            case "home":
                request.getRequestDispatcher("index.jsp").forward(request,response);
                break;
            case "register":
                request.getRequestDispatcher("Register.jsp").forward(request,response);
                break;
            case "resetPassword":
                request.getRequestDispatcher("EmailResetpass.jsp").forward(request,response);
                break;
        }
    }
}
