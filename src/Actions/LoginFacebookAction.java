package Actions;

import LoginWithSocial.Facebook.common.RestFB;
import Models.DatabaseClass;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.restfb.types.User;

import javax.servlet.RequestDispatcher;
import java.sql.SQLException;

public class LoginFacebookAction extends ActionSupport {
    private String code;
    private DatabaseClass db = new DatabaseClass();

    public LoginFacebookAction() throws SQLException, ClassNotFoundException {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String execute() throws Exception {

        if (code == null || code.isEmpty()) {
            return "login";
        } else {
            String accessToken = RestFB.getToken(code);
            User user = RestFB.getUserInfo(accessToken);
            ActionContext.getContext().getSession().put("type", "0");
            ActionContext.getContext().getSession().put("userStatus", "1");
            ActionContext.getContext().getSession().put("userId",db.getUserIdFromIdFacebook(user.getId().trim()));
            ActionContext.getContext().getSession().put("name",user.getName());
            int lastSpace = user.getName().lastIndexOf(" ");
            try {
                if(!db.isIdFacebookExist(user.getId())) {
                    db.addNewFacebookLogin(user.getName().substring(0,lastSpace-1).trim(),
                            user.getName().substring(lastSpace).trim(),"none",user.getId());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return "success";
        }
    }
}
