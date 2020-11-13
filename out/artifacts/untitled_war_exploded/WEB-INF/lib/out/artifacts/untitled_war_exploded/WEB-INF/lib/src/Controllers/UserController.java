package Controllers;

import Models.DatabaseClass;
import Models.EncryptPassword;
import Models.MailConfig;
import Models.classes.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
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
                try {
                    register(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
            case "update":
                int userId = Integer.parseInt(request.getParameter("userId"));
                String fistName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                pDAO.updateStudent(userId,fistName,lastName,email,password);
                request.getSession().removeAttribute("isUpdate");
                request.getSession().removeAttribute("userUpdate");
                response.sendRedirect("adm-page.jsp?pgprt=1");
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
            case "update":
                User user = pDAO.getUserDetails(request.getParameter("userId"));
                request.getSession().setAttribute("isUpdate",1);
                request.getSession().setAttribute("userUpdate",user);
                response.sendRedirect("adm-page.jsp?pgprt=1");
                break;
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String fName =request.getParameter("fname");
        String lName =request.getParameter("lname");
        String uName=request.getParameter("uname");
        String email=request.getParameter("email");
        String pass= EncryptPassword.generateHash(request.getParameter("pass"));
        if(pDAO.isGmailExist(email)) {
            request.getRequestDispatcher("failed.jsp").forward(request,response);
            return;
        }
        pDAO.addNewStudent(fName,lName,uName,email,pass);
        request.getRequestDispatcher("login.jsp").forward(request,response);
    }

}
