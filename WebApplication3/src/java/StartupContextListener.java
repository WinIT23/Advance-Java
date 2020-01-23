/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import model.MyConnection;


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
        
        myConn = new MyConnection(dBUrl, dBUname, dBPass); 
        sce.getServletContext().setAttribute("my_con", myConn);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            myConn.closeConnction(); //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            Logger.getLogger(StartupContextListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
