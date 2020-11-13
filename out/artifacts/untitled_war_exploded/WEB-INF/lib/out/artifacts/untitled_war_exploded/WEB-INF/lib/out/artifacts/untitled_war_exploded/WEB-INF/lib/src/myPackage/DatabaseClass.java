 /*
  * To change this license header, choose License Headers in Project Properties.
  * To change this template file, choose Tools | Templates
  * and open the template in the editor.
  */
 package myPackage;

 import java.sql.*;
 import java.time.Duration;
 import java.time.LocalDate;
 import java.time.LocalTime;
 import java.time.format.DateTimeFormatter;
 import java.util.ArrayList;
 import java.util.logging.Level;
 import java.util.logging.Logger;

 import myPackage.classes.*;

 public class DatabaseClass {
     private Connection conn;

     public DatabaseClass() throws ClassNotFoundException, SQLException {
         establishConnection();
     }

     private void establishConnection() throws ClassNotFoundException, SQLException {
         String dbURL = "jdbc:sqlserver://localhost:1433;"
                 + "databaseName=ExaminationOnline;";
         String userName = "sa";
         String password = "hoaibao0806";

         try {
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             conn = DriverManager.getConnection(dbURL, userName, password);
         } catch (Exception ex) {
             System.out.println("connect failure!");
             ex.printStackTrace();
         }
     }

     public boolean updatePassword(String email, String newPassword) {
         try {
             String sql = "Update users"
                     + " set password=? "
                     + " where email=?";

             PreparedStatement pstm = conn.prepareStatement(sql);
             pstm.setString(1, newPassword);
             pstm.setString(2, email);
             return pstm.executeUpdate() > 0;
         } catch (SQLException ex) {
             Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
             return false;
         }
     }

     public User isAccessWithGmail(String email) throws SQLException {
         String sql = "Select * from Users where email=? and socialLogin=1";
         PreparedStatement preparedStatement = conn.prepareStatement(sql);
         preparedStatement.setString(1,email);
         ResultSet resultSet = preparedStatement.executeQuery();
         if(resultSet.next()) {
            return new User(resultSet.getString("user_name"),resultSet.getString("email"));
         }
         return null;
     }

     public boolean isGmailExist(String email) throws SQLException {
         String sql = "Select * from Users where email=?";
         PreparedStatement preparedStatement = conn.prepareStatement(sql);
         preparedStatement.setString(1,email);
         return preparedStatement.executeQuery().next() ? true : false;
     }

     public boolean isIdFacebookExist(String id) throws SQLException {
         String sql = "Select * from Users where idFacebook=?";
         PreparedStatement preparedStatement = conn.prepareStatement(sql);
         preparedStatement.setString(1,id);
         return preparedStatement.executeQuery().next() ? true : false;
     }

     public ArrayList<User> getAllUsers() {
         ArrayList<User> list = new ArrayList<>();
         User user = null;
         PreparedStatement pstm;
         try {
             pstm = conn.prepareStatement("Select * from users");
             ResultSet rs = pstm.executeQuery();
             while (rs.next()) {
                 user = new User(rs.getInt(1), rs.getString(2),
                         rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
                 list.add(user);
             }
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());

         }
         return list;
     }

     public String getUserType(String userId) {
         String str = "";
         PreparedStatement pstm;
         try {
             pstm = conn.prepareStatement("Select * from users where user_id=?");
             pstm.setInt(1, Integer.parseInt(userId));
             ResultSet rs = pstm.executeQuery();
             while (rs.next()) {
                 str = rs.getString("user_type");
             }
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
             str = "error";
         }
         return str;
     }

     public  ArrayList<User> getStudent() throws SQLException {
         ArrayList<User> courses = new ArrayList<User>();
         String sql = "Select * from users where user_type = 'student'";
         Statement statement = conn.createStatement();
         ResultSet result = statement.executeQuery(sql);
         while(result.next()) {
             int userId = result.getInt(1);
             String firstName = result.getString(2);
             String lastName = result.getString(3);
             String username = result.getString(4);
             String email  = result.getString(5);
             String password  = result.getString(6);
             String type  = result.getString(7);
             String contact  = result.getString(8);
             String city  = result.getString(9);
             String address  = result.getString(10);
             String isGmail  = result.getString(11);
             courses.add(new User(userId,firstName,lastName,username,email,password,type,contact,city,address,isGmail));
         }
         return courses;
     }

     public int getUserId(String userName) {
         int str = 0;
         PreparedStatement pstm;
         try {
             pstm = conn.prepareStatement("Select * from users where user_name=?");
             pstm.setString(1, userName);
             ResultSet rs = pstm.executeQuery();
             while (rs.next()) {
                 str = rs.getInt("user_id");
             }
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());

         }
         return str;
     }

     public int getUserIdFromIdFacebook(String id) {
         int str = 0;
         PreparedStatement pstm;
         try {
             pstm = conn.prepareStatement("Select * from users where idFacebook=?");
             pstm.setString(1, id);
             ResultSet rs = pstm.executeQuery();
             while (rs.next()) {
                 str = rs.getInt("user_id");
             }
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());

         }
         return str;
     }

     public User getUserDetails(String userId) {
         User userDetails = null;

         try {
             String sql = "SELECT * from users where user_id=?";
             PreparedStatement pstm = conn.prepareStatement(sql);
             pstm.setString(1, userId);
             ResultSet rs = pstm.executeQuery();
             while (rs.next()) {
                 userDetails = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)
                         , rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)
                         , rs.getString(9), rs.getString(10));
             }
             pstm.close();
         } catch (SQLException ex) {
             Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
         }
         return userDetails;
     }

     public void addNewGmailLogin(String fName, String lName, String email) {
         try {
             String sql = "INSERT into users (first_name,last_name,user_name,email,password,user_type,isGmailLogin)"
                     + "Values(?,?,?,?,?,?,?)";

             PreparedStatement pstm = conn.prepareStatement(sql);
             pstm.setString(1, fName);
             pstm.setString(2, lName);
             pstm.setString(3, email);
             pstm.setString(4, email);
             pstm.setString(5,email);
             pstm.setString(6, "student");
             pstm.setInt(7,1);
             pstm.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
         }
     }

     public void addNewFacebookLogin(String fName, String lName, String email,String fbId) {
         try {
             String sql = "INSERT into users (first_name,last_name,user_name,email,password,user_type,socialLogin" +
                     ",idFacebook)"
                     + "Values(?,?,?,?,?,?,?,?)";

             PreparedStatement pstm = conn.prepareStatement(sql);
             pstm.setString(1, fName);
             pstm.setString(2, lName);
             pstm.setString(3, email);
             pstm.setString(4, email);
             pstm.setString(5,email);
             pstm.setString(6, "student");
             pstm.setInt(7,1);
             pstm.setString(8,fbId);
             pstm.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
         }
     }

     public void addNewStudent(String fName, String lName, String uName, String email, String pass) {
         try {
             String sql = "INSERT into users(first_name,last_name,user_name,email,password,user_type,isGmailLogin) "
                     + "Values(?,?,?,?,?,?,?)";

             PreparedStatement pstm = conn.prepareStatement(sql);
             pstm.setString(1, fName);
             pstm.setString(2, lName);
             pstm.setString(3, uName);
             pstm.setString(4, email);
             pstm.setString(5, pass);
             pstm.setString(6, "student");
             pstm.setInt(7, 0);
             pstm.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
         }
     }

     public void updateStudent(int uId, String fName, String lName, String email, String pass) {
         try {
             String sql = "Update users"
                     + " set first_name=?, last_name=? , email=? , password=?"
                     + " where user_id=?";

             PreparedStatement pstm = conn.prepareStatement(sql);
             pstm.setString(1, fName);
             pstm.setString(2, lName);
             pstm.setString(3, email);
             pstm.setString(4, pass);
             pstm.setInt(5, uId);
             pstm.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
         }
     }

     public User loginValidate(String userName, String userPass) throws SQLException {
         String sql = "SELECT * FROM users\n"
                 + "WHERE user_name=? ";
         PreparedStatement pstm = conn.prepareStatement(sql);
         pstm.setString(1, userName);
         ResultSet rs = pstm.executeQuery();
         String pass;
         while (rs.next()) {
             pass = rs.getString("password");
             String userHash = EncryptPassword.generateHash(userPass);
             if (pass.equals(userHash)) {
                 return new User(rs.getString("user_type"));
             }
         }
         return null;
     }

     public ArrayList<Courses> getAllCourses() {
         ArrayList<Courses> list = new ArrayList<>();
         try {
             String sql = "SELECT * from courses";
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery();
             while (rs.next()) {
                 list.add(new Courses(rs.getString(4)
                         ,rs.getString(1),rs.getInt(2),rs.getString(3)));
             }
             pstm.close();
         } catch (SQLException ex) {
             Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
         }
         return list;
     }

     public void addNewCourse(String courseName, int tMarks, String time,String courseId) {
         try {
             String sql = "INSERT into courses Values(?,?,?,?)";
             PreparedStatement pstm = conn.prepareStatement(sql);
             pstm.setString(1, courseName);
             pstm.setInt(2, tMarks);
             pstm.setString(3, time);
             pstm.setString(4, courseId);
             pstm.executeUpdate();
             pstm.close();
         } catch (SQLException ex) {
             Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
         }
     }

     public void delCourse(String cCode) {
         try {
             System.out.println(cCode);
             String sql = "DELETE from courses where course_id=?";
             PreparedStatement pstm = conn.prepareStatement(sql);
             pstm.setString(1, cCode);
             pstm.executeUpdate();
             pstm.close();
         } catch (SQLException ex) {
             Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
         }
     }

     public void delQuestion(int qId) {
         try {
             String sql = "DELETE from questions where question_id=?";
             PreparedStatement pstm = conn.prepareStatement(sql);
             pstm.setInt(1, qId);
             pstm.executeUpdate();
             pstm.close();
         } catch (SQLException ex) {
             Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
         }
     }

     public void delUser(int uid) {
         try {
             String sql = "DELETE from users where user_id=?";
             PreparedStatement pstm = conn.prepareStatement(sql);
             pstm.setInt(1, uid);
             pstm.executeUpdate();
             pstm.close();
         } catch (SQLException ex) {
             Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
         }
     }

     public void addQuestion(String cName, String question, String opt1, String opt2, String opt3
             , String opt4, String correct) {

         try {
             String sql = "INSERT into questions"
                     + " VALUES (?,?,?,?,?,?,?)";
             PreparedStatement pstm = conn.prepareStatement(sql);
             pstm.setString(1, cName);
             pstm.setString(2, question);
             pstm.setString(3, opt1);
             pstm.setString(4, opt2);
             pstm.setString(5, opt3);
             pstm.setString(6, opt4);
             pstm.setString(7, correct);
             pstm.executeUpdate();
             pstm.close();
         } catch (SQLException ex) {
             Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
         }
     }

     public ArrayList getQuestions(String courseName) {
         System.out.println(courseName);
         ArrayList list = new ArrayList();
         try {

             String sql = "Select * from questions where course_id=?";
             PreparedStatement pstm = conn.prepareStatement(sql);
             pstm.setString(1, courseName);
             ResultSet rs = pstm.executeQuery();
             Questions question;
             while (rs.next()) {
                 question = new Questions(
                         rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5),
                         rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(2)
                 );
                 list.add(question);
             }
             pstm.close();
         } catch (SQLException ex) {
             Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
         }
         return list;
     }

     public int startExam(String cName, int sId) {
         int examId = 0;
         try {
             String sql = "INSERT into exams(course_id,date,start_time,exam_time,user_id,total_Marks) "
                     + "VALUES(?,?,?,?,?,?)";
             PreparedStatement pstm = conn.prepareStatement(sql);
             pstm.setString(1, cName);
             pstm.setString(2, getFormatedDate(LocalDate.now().toString()));
             pstm.setString(3, LocalTime.now().toString());
             pstm.setString(4, getCourseTimeByCode(cName));
             pstm.setInt(5, sId);
             pstm.setInt(6, getTotalMarksByCode(cName));
             pstm.executeUpdate();
             pstm.close();
             examId = getLastExamId();
         } catch (SQLException ex) {
             Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
         }
         return examId;
     }

     public int getLastExamId() {
         int id = 0;
         try {

             String sql = "Select * from exams";
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery();

             while (rs.next()) {
                 id = rs.getInt(1);
             }
             pstm.close();
         } catch (SQLException ex) {
             Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
         }
         return id;
     }

     public String getStartTime(int examId) {
         String time = "";
         try {

             String sql = "Select start_time from exams where exam_id=?";
             PreparedStatement pstm = conn.prepareStatement(sql);
             pstm.setInt(1, examId);
             ResultSet rs = pstm.executeQuery();

             while (rs.next()) {
                 time = rs.getString(1);
             }
             pstm.close();
         } catch (SQLException ex) {
             Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
         }
         return time;
     }

     public String getCourseTimeByCode(String code) {
         String c = null;
         try {
             PreparedStatement pstm = conn.prepareStatement("Select time from courses where course_id=?");
             pstm.setString(1, code);
             ResultSet rs = pstm.executeQuery();
             while (rs.next()) {
                 c = rs.getString(1);
             }
             pstm.close();
         } catch (Exception e) {
             Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, e);
         }

         return c;
     }

     public int getTotalMarksByCode(String code) {
         int marks = 0;
         try {
             PreparedStatement pstm = conn.prepareStatement("Select total_marks from courses where course_id=?");
             pstm.setString(1, code);
             ResultSet rs = pstm.executeQuery();
             while (rs.next()) {
                 marks = rs.getInt(1);
                 System.out.println(rs.getInt(1));
             }
             pstm.close();
         } catch (Exception e) {
             e.printStackTrace();
         }

         return marks;
     }

     public ArrayList getAllQuestions(String courseCode) {
         ArrayList<Questions> list = new ArrayList<>();
         try {

             String sql = "Select * from questions where course_id=?";
             PreparedStatement pstm = conn.prepareStatement(sql);
             pstm.setString(1, courseCode);
             ResultSet rs = pstm.executeQuery();
             Questions question;
             while (rs.next()) {
                 question = new Questions(
                         rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5),
                         rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(2)
                 );
                 list.add(question);
             }
             pstm.close();
         } catch (SQLException ex) {
             Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
         }
         return list;
     }

     public ArrayList getAllAnswersByExamId(int examId) {
         ArrayList list = new ArrayList();
         try {

             String sql = "Select * from answers where exam_id=?";
             PreparedStatement pstm = conn.prepareStatement(sql);
             pstm.setInt(1, examId);
             ResultSet rs = pstm.executeQuery();
             Answers a;
             while (rs.next()) {
                 a = new Answers(
                         rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)
                 );
                 list.add(a);
             }
             pstm.close();
         } catch (SQLException ex) {
             Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
         }
         return list;
     }

     private String getFormatedDate(String date) {
         LocalDate localDate = LocalDate.parse(date);
         return localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
     }

     private String getNormalDate(String date) {
         String[] d = date.split("-");
         return d[2] + "-" + d[1] + "-" + d[0];
     }

     private String getFormatedTime(String time) {
         if (time != null) {
             LocalTime localTime = LocalTime.parse(time);
             return localTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
         } else {

             return "-";
         }
     }

     public int getRemainingTime(int examId) {
         int time = 0;
         try {

             String sql = "Select start_time,exam_time from exams where exam_id=?";
             PreparedStatement pstm = conn.prepareStatement(sql);
             pstm.setInt(1, examId);
             ResultSet rs = pstm.executeQuery();

             while (rs.next()) {
                 //totalTime-(Math.abs(currentTime-examStartTime))
                 //Duration.between(first,sec) returns difference between 2 dates or 2 times
                 time = Integer.parseInt(rs.getString(2)) - (int) Math.abs((Duration.between(LocalTime.now(), LocalTime.parse(rs.getString(1))).getSeconds() / 60));
             }
             pstm.close();
         } catch (SQLException ex) {
             Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
         }
         System.out.println(time);
         return time;
     }

     public void insertAnswer(int eId, int qid, String question, String ans) {
         try {
             PreparedStatement pstm = conn.prepareStatement("insert into answers(exam_id,question,answer,correct_answer,status) "
                     + "Values(?,?,?,?,?)");
             pstm.setInt(1, eId);
             pstm.setString(2, question);
             pstm.setString(3, ans);
             String correct = getCorrectAnswer(qid);
             pstm.setString(4, correct);
             pstm.setString(5, getAnswerStatus(ans, correct));
             pstm.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
         }
     }

     private String getCorrectAnswer(int qid) {
         String ans = "";

         try {
             PreparedStatement pstm = conn.prepareStatement("Select correct from questions where question_id=?");
             pstm.setInt(1, qid);
             ResultSet rs = pstm.executeQuery();
             while (rs.next()) {
                 ans = rs.getString(1);
             }
         } catch (SQLException ex) {
             Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
         }


         return ans;
     }

     private String getAnswerStatus(String ans, String correct) {
         String resp = "";
         if (ans.equals(correct)) {
             resp = "correct";
         } else {
             resp = "incorrect";
         }
         return resp;

     }

     public ArrayList getAllResultsFromExams(int stdId) {
         ArrayList list = new ArrayList();
         Exams exam = null;
         try {
             PreparedStatement pstm = conn.prepareStatement("select * from exams where user_id=? order by date desc");
             pstm.setInt(1, stdId);
             ResultSet rs = pstm.executeQuery();
             while (rs.next()) {
                 exam = new Exams(rs.getInt(1), rs.getString(2),
                         rs.getString(3), rs.getString(4), rs.getString(5),
                         getFormatedTime(rs.getString(6)),getFormatedTime(rs.getString(7)),
                         rs.getString(8),rs.getString(9),rs.getInt(10));
                 list.add(exam);
             }
         } catch (SQLException ex) {
         }
         return list;

     }

     public void calculateResult(int eid, int tMarks, String endTime, int size) {

         try {
             String sql = "update exams "
                     + "set obt_marks=?, end_time=?,status=? "
                     + "where exam_id=?";
             PreparedStatement pstm = conn.prepareStatement(sql);
             int obt = getObtMarks(eid, tMarks, size);
             pstm.setInt(1, obt);
             pstm.setString(2, endTime);
             float percent = ((obt * 100) / tMarks);
             if (percent >= 45.0) {
                 pstm.setString(3, "Pass");
             } else {
                 pstm.setString(3, "Fail");
             }
             pstm.setInt(4, eid);
             pstm.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
         }
     }

     private int getObtMarks(int examId, int tMarks, int size) {
         int m = 0;

         try {
             PreparedStatement pstm = conn.prepareStatement("select count(answer_id) from answers "
                     + "where exam_id=? and status='correct'");
             pstm.setInt(1, examId);
             ResultSet rs = pstm.executeQuery();
             while (rs.next()) {
                 m = rs.getInt(1);
             }
         } catch (SQLException ex) {
             Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
         }
         float rat = (float) tMarks / size;
         System.out.println(rat);
         rat = m * rat;
         System.out.println(rat);
         return m = (int) rat;
     }

     public Exams getResultByExamId(int examId) {
         Exams exam = null;
         try {
             PreparedStatement pstm = conn.prepareStatement("select * from exams where exam_id=?");
             pstm.setInt(1, examId);
             ResultSet rs = pstm.executeQuery();
             while (rs.next()) {
                 exam = new Exams(rs.getInt(1), rs.getString(2),
                         rs.getString(3), rs.getString(4), rs.getString(5),
                         getFormatedTime(rs.getString(6)),getFormatedTime(rs.getString(7)),
                         rs.getString(8),rs.getString(9),rs.getInt(10));
             }
         } catch (SQLException ex) {
             Logger.getLogger(DatabaseClass.class.getName()).log(Level.SEVERE, null, ex);
         }
         return exam;

     }

     public void finalize() {
         try {
             if (conn != null)
                 conn.close();
         } catch (Exception e) {
             e.printStackTrace();
         }
     }

 }