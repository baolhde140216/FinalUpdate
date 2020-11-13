/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myPackage.classes;


public class Courses {
    private String cCode;
   private String cName;
   private int tMarks;
   private String time;

    public Courses(String cCode, String cName, int tMarks, String time) {
        this.cCode = cCode;
        this.cName = cName;
        this.tMarks = tMarks;
        this.time = time;
    }

    public String getcCode() {
        return cCode;
    }

    public void setcCode(String cCode) {
        this.cCode = cCode;
    }

    public Courses(String cName, int tMarks) {
        this.cName = cName;
        this.tMarks = tMarks;
    }

    public Courses() {
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public int gettMarks() {
        return tMarks;
    }

    public void settMarks(int tMarks) {
        this.tMarks = tMarks;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    

   
    
}
