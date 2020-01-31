/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.web;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Win_It
 */
public class TestFilter implements Filter {
    
    private FilterConfig fc;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.fc = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        fc.getServletContext().log("Filter is Called...");
        
        // ------------------TO-DO----------------------
        // check login if Session is there than no worries else redirect to hackerman or index
        
        //-------------------------------------
        boolean authorize = true; //false;
        
        if(request instanceof HttpServletRequest) {
            HttpSession session = ((HttpServletRequest)request).getSession(false);
            if(session != null) {
                fc.getServletContext().log("Debug : " + session.toString());
                Object isLogin = session.getAttribute("login");
                if(isLogin == null) {
                    fc.getServletContext().log("Debug : " + isLogin);
                    authorize = (boolean)isLogin;
                }
            }
            
            //change states
            //MyConnection myConn = (MyConnection)fc.getServletContext().getAttribute("my_con");
            
            if(authorize) {
                fc.getServletContext().log("Username : " + fc.getServletContext().getAttribute("user_name").toString());
                chain.doFilter(request, response);
            } else if (fc != null) {
                fc.getServletContext().log("Redirection the HACKERMAN.... : authorize : " + authorize);
                // try redirecting on index... its more useful that way.
                fc.getServletContext().getRequestDispatcher("/hackerman.jsp").forward(request, response);
            }
        }
        
        // Old approach--------
        /*HttpSession session = req.getSession(false);
        
        if(session != null) {
            // use login data from db...and check it here
            if((boolean)session.getAttribute("login") != false ) {
                fc.getServletContext().log("Username : " + session.getAttribute("user_name"));
                chain.doFilter(request, response);
            } else {
                HttpServletResponse resp = (HttpServletResponse) response;
                resp.sendRedirect("hackerman.jsp");
                fc.getServletContext().log("Redirection the HACKERMAN....");
            }
        }*/
        // fatch ip 
    }

    @Override
    public void destroy() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
