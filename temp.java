import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

class MyClass {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "");

            Statement stm = conn.createStatement();

            // for(int i = 0; i < 10; i++) {
            //     String myQuery = "INSERT INTO tab VALUES("+ i + ", Vinit" + i + " )";
            //     stm.executeQuery(myQuery);
            // }   

            ResultSet rs = stm.executeQuery("select * from tab");

            while(rs.next()) {
                int n = rs.getInt("id");
                String str = rs.getString("name");
                System.out.println("id : " + n + " name : " + str);
            }   

            conn.close();
            
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}

// java -cp "d:\Don't Delete\mysql-connector-java-8.0.18.jar;" MyClass