package LoginWithSocial.Facebook.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import LoginWithSocial.Facebook.common.RestFB;
import com.restfb.types.User;
import myPackage.DatabaseClass;


@WebServlet("/login-facebook")
public class LoginFacebookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseClass db = new DatabaseClass();

	public LoginFacebookServlet() throws SQLException, ClassNotFoundException {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		
		if (code == null || code.isEmpty()) {
			RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
			dis.forward(request, response);
		} else {
			String accessToken = RestFB.getToken(code);
			User user = RestFB.getUserInfo(accessToken);
			System.out.println(user.getName());
			request.getSession().setAttribute("type", "0");
			request.getSession().setAttribute("userStatus", "1");
			request.getSession().setAttribute("userId",db.getUserIdFromIdFacebook(user.getId().trim()));
			try {
				if(!db.isIdFacebookExist(user.getId())) {
					db.addNewFacebookLogin(user.getName(),user.getName(),"none",user.getId());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("LoginController").forward(request,response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
