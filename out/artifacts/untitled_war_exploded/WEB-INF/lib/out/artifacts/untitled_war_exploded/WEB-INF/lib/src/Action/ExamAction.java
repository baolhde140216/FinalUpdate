package Action;

import com.opensymphony.xwork2.ActionContext;
import myPackage.DatabaseClass;

import java.sql.SQLException;

public class ExamAction {
    private String coursename;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String execute() {
        System.out.println("Hello execute method");
        return "success";
    }

    public String TakeExam() throws SQLException, ClassNotFoundException {

        int userId=Integer.parseInt(ActionContext.getContext().getSession().get("userId").toString());

        int examId=new DatabaseClass().startExam(coursename,userId);
        ActionContext.getContext().getSession().put("examId",examId);
        ActionContext.getContext().getSession().put("examStarted","1");
        url = "std-page.jsp?pgprt=1&coursename="+coursename;
        return "redirect";
    }
}
