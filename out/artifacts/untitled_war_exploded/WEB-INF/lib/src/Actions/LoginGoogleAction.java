package Actions;

import LoginWithSocial.Google.common.GooglePojo;
import LoginWithSocial.Google.common.GoogleUtils;
import Models.DatabaseClass;
import Models.classes.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginGoogleAction extends ActionSupport {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String execute() throws Exception {
        String returnResult = "success";
        if (code == null || code.isEmpty()) {
            return "login";
        } else {
            try {
                DatabaseClass db = new DatabaseClass();
                String accessToken = GoogleUtils.getToken(code);
                GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
                if(db.isGmailExist(googlePojo.getEmail())) {
                    User user = db.isAccessWithGmail(googlePojo.getEmail());
                    if(user!=null) {
                        ActionContext.getContext().getSession().put("type", "0");
                        ActionContext.getContext().getSession().put("userStatus", "1");
                        ActionContext.getContext().getSession().put("userId",db.getUserId(googlePojo.getEmail()));
                    }
                    else {
                        returnResult = "login";
                    }
                }
                else {
                    db.addNewGmailLogin("GmailAccess","None",googlePojo.getEmail());
                    ActionContext.getContext().getSession().put("type", "0");
                    ActionContext.getContext().getSession().put("userStatus", "1");
                    ActionContext.getContext().getSession().put("userId",db.getUserId(googlePojo.getEmail()));
                    ActionContext.getContext().getSession().put("name",googlePojo.getEmail());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return returnResult;
    }
}
