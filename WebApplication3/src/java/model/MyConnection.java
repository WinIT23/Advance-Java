/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author Win_It
 */
public class MyConnection { 
    private final String dBUrl;
    private final String dBUname;
    private final String dBPass;
    private final User user;
    private Connection myConnection;
 
    //get data from servlet by using constructor..
    public MyConnection(String dBUrl, String dBUname, String dBPass, String myUname, String myPass) {
        this.user = new User();
        this.user.setFrmData(myUname, myPass);
        this.dBUrl = dBUrl;
        this.dBUname = dBUname;
        this.dBPass = dBPass;
    }
    //check if url uname and passwd is pointing to null 
    // if so than read from personal config file...
    
    
    private Connection makeConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.myConnection = java.sql.DriverManager.getConnection(this.dBUrl,this.dBUname, this.dBPass);
        return this.myConnection;
    } 
    
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        if(this.myConnection == null) {
            this.myConnection = this.makeConnection();
        }
        return this.myConnection;
    }
    
    public void closeConnction() throws SQLException {
        this.myConnection.close();
    }
    
    public User getUserInstance() {
        return this.user;
    }
}
