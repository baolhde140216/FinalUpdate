
package Models.classes;


public class User {
   private int userId;
   private String firstName,lastName,userName,email,password,type,isGmail;

   public User(){
       
   }

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public User(int userId, String firstName, String lastName, String userName, String email, String password, String type) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public User(int userId, String firstName, String lastName, String userName, String email, String password, String type,String isGmail) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.type = type;
        this.isGmail = isGmail;
    }

    public User(String type) {
        this.type = type;
    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public String getIsGmail() {
        return isGmail;
    }

    public void setIsGmail(String isGmail) {
        this.isGmail = isGmail;
    }
}
