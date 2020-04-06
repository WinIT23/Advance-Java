/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javabeans.model;

/**
 *
 * @author Win_It
 */
public class Dog {

    private String dname;
    private boolean confuse;

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public void setConfuse(boolean confuse) {
        this.confuse = confuse;
    }

    public boolean isConfuse() {
        return confuse;
    }

}
