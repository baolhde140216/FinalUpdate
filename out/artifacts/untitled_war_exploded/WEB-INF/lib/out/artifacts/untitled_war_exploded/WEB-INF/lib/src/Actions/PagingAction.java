package Actions;

import Models.DatabaseClass;
import Models.classes.Exams;
import Models.classes.Questions;
import Models.classes.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class PagingAction extends ActionSupport {
    private String action,index,coursename,query="";
    private ArrayList list;
    private DatabaseClass db = new DatabaseClass();

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public PagingAction() throws SQLException, ClassNotFoundException {
    }

    public ArrayList getList() {
        return list;
    }

    public void setList(ArrayList list) {
        this.list = list;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    @Override
    public String execute() throws Exception {
        String returnPage = "badRequest";
        int index;

        if(action == null)
            return returnPage;
        if(this.index == null || this.index.equals("")) {
            index = 1;
        }
        else {
            index = Integer.parseInt(this.index);
        }
        switch (action) {
            case "result":
                list = db.pagingResult(index);
                ActionContext.getContext().getSession().put("pagingItems",(ArrayList<Exams>)list);
                returnPage = "result";
                break;
            case "account":
                list = db.pagingAccount(index,query);
                ActionContext.getContext().getSession().put("pagingItems",(ArrayList<User>)list);
                returnPage = "account";
                break;
            case "question":
                if(coursename==null) {
                    return returnPage;
                }
                list = db.pagingQuestion(index,coursename);
                ActionContext.getContext().getSession().put("pagingItems",(ArrayList<Questions>)list);
                returnPage = "question";
                break;
            default:
                return returnPage;
        }
        ActionContext.getContext().getSession().put("index",index);
        ActionContext.getContext().getSession().put("query",query);
        return returnPage;
    }
}
