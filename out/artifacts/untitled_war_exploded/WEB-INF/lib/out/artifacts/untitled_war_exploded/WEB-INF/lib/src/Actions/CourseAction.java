package Actions;

import com.opensymphony.xwork2.ActionSupport;
import myPackage.DatabaseClass;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CourseAction extends ActionSupport {
    private String action,coursename, totalmarks, time,courseCode;

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getTotalmarks() {
        return totalmarks;
    }

    public void setTotalmarks(String totalmarks) {
        this.totalmarks = totalmarks;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    DatabaseClass db = new DatabaseClass();


    public CourseAction() throws SQLException, ClassNotFoundException {
    }


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String execute() throws Exception {
        String returnAction = "";
        if(action==null) {
            return "coursePage";
        }

        switch (action) {
            case "delete":
                delete();
                returnAction = "coursePage";
                break;
            case "add":
                add();
                returnAction = "coursePage";
                break;
        }

        return returnAction;
    }

    private void add() {
        db.addNewCourse(coursename,Integer.parseInt(totalmarks),
                time,courseCode);
    }

    private void delete() {
        db.delCourse(courseCode);
    }
}
