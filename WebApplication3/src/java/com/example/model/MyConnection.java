/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Win_It
 */
public class MyConnection { 
    private final String dBUrl;
    private final String dBUname;
    private final String dBPass;
    private final String tabName;
    private final User user;
    private Connection myConnection;
 
    //get data from servlet by using constructor..
    public MyConnection(String dBUrl, String dBUname, String dBPass, String tabName) {
        this.user = new User();
        this.dBUrl = dBUrl;
        this.dBUname = dBUname;
        this.dBPass = dBPass;
        this.tabName = tabName;
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
    
    public void setStatus(String status) throws SQLException {
        // username whrer name = nameInForm
        PreparedStatement pStm = myConnection.prepareStatement("SELECT status FROM " + this.tabName + " WHERE uname = ?");
        PreparedStatement dbChange = myConnection.prepareStatement("UPDATE " + this.tabName + " SET status = ? WHERE uname = ?");
        pStm.setString(1, user.getName());
        
        ResultSet rs =  pStm.executeQuery();
        rs.next();
        String dbStatus = rs.getString("status");
        if(!dbStatus.equals(status)) {
            // set in database 
            dbChange.setString(1, status);
            dbChange.setString(2, user.getName());
            
            int i = dbChange.executeUpdate();
            if(i > 0)
                this.user.setStatus(status.equals("A"));
        }
    }
    
    public boolean getStatus() {
        return user.getStatus();
    }
    
    public void closeConnction() throws SQLException, NullPointerException {
            this.myConnection.close();
    }
    
    public User getUserInstance() {
        return this.user;
    }
}
