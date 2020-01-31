/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.web;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web application lifecycle listener.
 *
 * @author Win_It
 */
@WebListener()
public class LoginCountListner implements HttpSessionListener {

    private int mCount;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        mCount++;
        se.getSession().getServletContext().log("" + mCount);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        if (session.getAttribute("login") != null) {
            mCount--;
            session.getServletContext().log("" + mCount);
        }//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
