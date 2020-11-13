package LoginWithSocial.Google.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import LoginWithSocial.Google.common.GooglePojo;
import LoginWithSocial.Google.common.GoogleUtils;
import myPackage.DatabaseClass;
import myPackage.classes.User;

@WebServlet("/login-google")
public class LoginGoogleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginGoogleServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");

		if (code == null || code.isEmpty()) {
			response.sendRedirect("HomeController?action=login");
		} else {
			try {
				DatabaseClass db = new DatabaseClass();
				String accessToken = GoogleUtils.getToken(code);
				GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
				if(db.isGmailExist(googlePojo.getEmail())) {
					User user = db.isAccessWithGmail(googlePojo.getEmail());
					if(user!=null) {
						request.getSession().setAttribute("type", "0");
						request.getSession().setAttribute("userStatus", "1");
						request.getSession().setAttribute("userId",db.getUserId(googlePojo.getEmail()));
						request.getRequestDispatcher("LoginController").forward(request,response);
					}
					else {
						response.sendRedirect("HomeController?action=login");
					}
					return;
				}
				else {
					db.addNewGmailLogin("GmailAccess","None",googlePojo.getEmail());
					request.getSession().setAttribute("type", "0");
					request.getSession().setAttribute("userStatus", "1");
					request.getSession().setAttribute("userId",db.getUserId(googlePojo.getEmail()));
					response.sendRedirect("LoginController");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

//			request.setAttribute("id", googlePojo.getId());
//			request.setAttribute("name", googlePojo.getName());
//			request.setAttribute("email", googlePojo.getEmail());
//			RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
//			dis.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
