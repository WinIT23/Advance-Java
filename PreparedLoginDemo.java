import java.sql.*;

public class PreparedLoginDemo {
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            String s1 = "admin";

            Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "12345");
            
            PreparedStatement myPst = myCon.prepareStatement("SELECT * FROM tab WHERE uname=?");
            
            myPst.setString(1, s1);
            
            ResultSet rs;
            rs = myPst.executeQuery();
            
            rs.next();
            
            System.out.println("Username : " + rs.getString("uname") + "\nPassword : " + rs.getString("passwd"));

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}