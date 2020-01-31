package com.example.web;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import com.example.model.MyConnection;

/**
 * Web application lifecycle listener.
 *
 * @author Win_It
 */
@WebListener()
public class StartupContextListener implements ServletContextListener {

    private MyConnection myConn;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        String dBUrl = sce.getServletContext().getInitParameter("db_url");
        String dBUname = sce.getServletContext().getInitParameter("db_name");
        String dBPass = sce.getServletContext().getInitParameter("db_pass");
        String tabName = sce.getServletContext().getInitParameter("tab_name");

        myConn = new MyConnection(dBUrl, dBUname, dBPass, tabName);
        sce.getServletContext().setAttribute("my_con", myConn);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            myConn.closeConnction();
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(StartupContextListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
