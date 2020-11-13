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
                ActionContext.getContext().getSession().

        }
        return returnText;
    }


}
