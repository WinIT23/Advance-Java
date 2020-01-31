/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.model;


/**
 *
 * @author Win_It
 */
public class User {

    private String mFormUsername;
    private String mFormPasswd;
    private String mDBUsername;
    private String mDBPasswd;
    private boolean mStatus;

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

    public void setDBData(String uname, String pass, String status) {
        this.mDBUsername = uname;
        this.mDBPasswd = pass;
        this.mStatus = status.equals("A");
    }

    public String getName() {
        return this.mDBUsername;
    }
    
    public void setStatus(boolean status) {
        this.mStatus = status;
    }
    
    public boolean getStatus() {
        return this.mStatus;
    }
    
    public void setFrmData(String uname, String pass) {
        this.mFormUsername = uname;
        this.mFormPasswd = pass;
    }
    
    
}
