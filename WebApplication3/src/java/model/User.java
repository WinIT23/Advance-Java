/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


/**
 *
 * @author Win_It
 */
public class User {

    private String mFormUsername;
    private String mFormPasswd;
    private String mDBUsername;
    private String mDBPasswd;

    /**
     *
     */
    public User() {
    }

    /**
     *
     * @param formUsername Username form form
     * @param formPasswd Password from form
     * @param dBUsername Username form database
     * @param dBPasswd Password from database
     */
    public User(String formUsername, String formPasswd, String dBUsername, String dBPasswd) {

        this.mFormUsername = formUsername;
        this.mFormPasswd = formPasswd;
        this.mDBUsername = dBUsername;
        this.mDBPasswd = dBPasswd;
    }

    public boolean passCheck() {
        return mDBPasswd.equals(mFormPasswd);
    }

    public boolean unameCheck() {
        return mDBUsername.equals(mFormUsername);
    }

//    public Connection getConnction(String url, String name, String pass) throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection myCon = DriverManager.getConnection(url, name, pass);
//        return myCon;
//    }
    
    public void setDBData(String uname, String pass) {
        this.mDBUsername = uname;
        this.mDBPasswd = pass;
    }

    public void setFrmData(String uname, String pass) {
        this.mFormUsername = uname;
        this.mFormPasswd = pass;
    }
    
    
}
