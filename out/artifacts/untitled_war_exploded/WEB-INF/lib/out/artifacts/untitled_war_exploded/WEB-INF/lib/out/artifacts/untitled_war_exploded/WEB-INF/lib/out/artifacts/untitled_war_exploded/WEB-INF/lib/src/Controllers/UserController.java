package Controllers;

import myPackage.DatabaseClass;
import myPackage.MailConfig;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
    private DatabaseClass pDAO = new DatabaseClass();

    public UserController() throws SQLException, ClassNotFoundException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "register":
                register(request,response);
                request.getRequestDispatcher("login.jsp").forward(request,response);
                break;
            case "reset":
                reset(request,response);
                break;
            case "sendMailReset":
                int code = new Random().nextInt(9999);
                request.getSession().setAttribute("code",code);
                request.getSession().setMaxInactiveInterval(120);
                String content = "Code to reset password: "+code;
                MailConfig.sendMail(content,request.getParameter("email"));
                response.sendRedirect("UserController?action=reset&email="+request.getParameter("email"));
                break;
        }
    }

    private void reset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("code")!=null) {
            String code = request.getParameter("code");
            String newPass = request.getParameter("password");
            String email = request.getSession().getAttribute("email").toString();
            if(code.equals(request.getSession().getAttribute("code").toString())) {
                pDAO.updatePassword(email,newPass);
                request.getSession().setAttribute("code",null);
                request.getSession().setAttribute("email",null);
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }
            else {
                request.getRequestDispatcher("resetPassword.jsp").forward(request,response);
            }
        }
        else {
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "reset":
                request.getSession().setAttribute("email",request.getParameter("email"));
                request.getRequestDispatcher("resetPassword.jsp").forward(request,response);
                break;
//            case "test":
//                request.s
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fName =request.getParameter("fname");
        String lName =request.getParameter("lname");
        String uName=request.getParameter("uname");
        String email=request.getParameter("email");
        String pass=request.getParameter("pass");
        pDAO.addNewStudent(fName,lName,uName,email,pass);
    }

}
