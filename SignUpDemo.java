import java.sql.*;

public class SignUpDemo {
    public static void main(String[] args) {
        User obj = new User("VSCode", "Chauhna", "asdf@gmail.com", "1212413", "k123a", 21, "m");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // make connection..
            Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdata", "root", "12345");
            // create statement...
            
            Statement myStat = myCon.createStatement();
            
            ResultSet rs = myStat.executeQuery("SELECT * FROM udata");
            // execute query...
            int i = myStat.executeUpdate(
                    "INSERT INTO udata " + "VALUES(\""
                            + obj.mFirstName + "\", \"" + obj.mLastName + "\", \"" + obj.mEmail + "\" ,\"" + obj.mPhNo
                            + "\" , \"" + obj.mPassword + "\" , \"" + obj.mGender + "\" , " + obj.mAge + ");" );
           
            System.out.println("i : " + i);

            if(i == 1) {
                throw new Exception(){
                    String stm = "My Exception ";
                    
                    @Override
                    public String toString() {
                        return stm;
                    }
                };
            }

            myCon.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

class User {

    protected String mFirstName;
    protected String mLastName;
    protected String mEmail;
    protected String mPassword;
    protected String mPhNo;
    protected int mAge;
    protected String mGender;

    User(String firstName, String lastName, String email, String phno, String password, int age, String gender) {
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mEmail = email;
        this.mPhNo = phno;
        this.mPassword = password;
        this.mAge = age;
        this.mGender = gender;
    }
}