package Controllers;

import Models.DatabaseClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;

@WebServlet("/ExamController")
public class ExamController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "startExam":
                try {
                    startExam(request,response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "submitted":
                submitted(request,response);
                break;
        }
    }

    private void submitted(HttpServletRequest request, HttpServletResponse response) {
        try{
            String time= LocalTime.now().toString();
            int size=Integer.parseInt(request.getParameter("size"));
            int eId=Integer.parseInt(request.getSession().getAttribute("examId").toString());
            int tMarks=Integer.parseInt(request.getParameter("totalmarks"));
            request.getSession().removeAttribute("examId");
            request.getSession().removeAttribute("examStarted");
            for(int i=0;i<size;i++){
                String question=request.getParameter("question"+i);
                String ans=request.getParameter("ans"+i);

                int qid=Integer.parseInt(request.getParameter("qid"+i));

                new DatabaseClass().insertAnswer(eId,qid,question,ans);
            }
            System.out.println(tMarks+" conn\t Size: "+size);
            new DatabaseClass().calculateResult(eId,tMarks,time,size);
            response.sendRedirect("std-page.jsp?pgprt=1&eid="+eId+"&showresult=1");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void startExam(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException {
        String cName=request.getParameter("coursename");
        int userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());

        int examId=new DatabaseClass().startExam(cName,userId);
        request.getSession().setAttribute("examId",examId);
        request.getSession().setAttribute("examStarted","1");
        response.sendRedirect("std-page.jsp?pgprt=1&coursename="+cName);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
