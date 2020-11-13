package Actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport {
    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String execute() throws Exception {
        if(action == null)
            return "home";
        String returnText = "";
        switch (action) {
            case "login":
                if(ActionContext.getContext().getSession().get("userStatus")!=null) {
                    if(ActionContext.getContext().getSession().get("type").toString().equals("1"))
                        returnText = "admin";
                    else {
                        returnText = "student";
                    }
                }
                else {
                    returnText = "login";
                }
                break;
            case "logout":
                ((org.apache.struts2.dispatcher.SessionMap)ActionContext.getContext().getSession()).invalidate();
                returnText = "home";
                break;
            case "home":
                returnText = "home";
                break;
            case "register":
                returnText = "register";
                break;
            case "resetPassword":
                returnText = "resetPassword";
                break;
            case "badRequest":
                returnText = "badRequest";
                break;
            default:
                return "home";
        }
        return returnText;
    }


}
