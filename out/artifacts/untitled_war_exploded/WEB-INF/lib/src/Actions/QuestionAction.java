package Actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import Models.DatabaseClass;

import java.sql.SQLException;

public class QuestionAction extends ActionSupport {
    private String action,coursename,question,opt1,opt2,opt3,opt4,correct,qid;

    public String getOpt1() {
        return opt1;
    }

    public void setOpt1(String opt1) {
        this.opt1 = opt1;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    private DatabaseClass db = new DatabaseClass();

    public QuestionAction() throws SQLException, ClassNotFoundException {
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOp1() {
        return opt1;
    }

    public void setOp1(String op1) {
        this.opt1 = op1;
    }

    public String getOpt2() {
        return opt2;
    }

    public void setOpt2(String opt2) {
        this.opt2 = opt2;
    }

    public String getOpt3() {
        return opt3;
    }

    public void setOpt3(String opt3) {
        this.opt3 = opt3;
    }

    public String getOpt4() {
        return opt4;
    }

    public void setOpt4(String opt4) {
        this.opt4 = opt4;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    @Override
    public String execute() throws Exception {
        if(action == null)
            return "questionPage";
        switch (action) {
            case "add":
                add();
                break;
            case "delete":
                String courseName = ActionContext.getContext().getSession().get("courseName").toString();
                coursename = courseName;
                delete();
                break;
        }
        return "questionPage";
    }

    private void delete() {
        db.delQuestion(Integer.parseInt(qid));
    }

    private void add() {
        db.addQuestion(coursename, question, opt1, opt2,opt3,
                opt4, correct);
    }
}
