package Actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import Models.DatabaseClass;
import Models.classes.User;

import java.sql.SQLException;

public class LoginAction extends ActionSupport {

    private String username,password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String execute() throws Exception {
        return "success";
    }



    public String loginPost() {
        DatabaseClass pDAO = null;
        try {
            pDAO = new DatabaseClass();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            User user = pDAO.loginValidate(username, password);
            if(user!=null){
                ActionContext.getContext().getSession().put("type", user.getType().equals("admin")? "1":"0");
                ActionContext.getContext().getSession().put("userStatus", "1");
                ActionContext.getContext().getSession().put("userId",pDAO.getUserId(username));
                ActionContext.getContext().getSession().put("name",username);
                return "checkLogin";
            }else{
                ActionContext.getContext().getSession().put("userStatus", "-1");
                return "login";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "success";
    }

    public String loginGet() {
        DatabaseClass pDAO = null;
        try {
            pDAO = new DatabaseClass();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (ActionContext.getContext().getSession().get("userStatus") != null) {
            String loginConfirm = ActionContext.getContext().getSession().get("userStatus").toString();
            if (loginConfirm.equals("1")) {
                if (pDAO.getUserType(ActionContext.getContext().getSession().get("userId").toString()).equals("admin")) {
//                    response.sendRedirect("adm-page.jsp?pgprt=0");
                    return "admin";
                } else if (pDAO.getUserType(ActionContext.getContext().getSession().get("userId").toString()).equals("student")) {
//                    response.sendRedirect("std-page.jsp?pgprt=0");
                    return "student";
                }
            } else if (!loginConfirm.equals("1")) {
//                response.sendRedirect("HomeController?action=login");
                return "login";
            }
        }
        return "login";
    }
}
