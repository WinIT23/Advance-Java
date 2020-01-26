package com.example.web;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.example.model.MyConnection;
import javax.servlet.http.Cookie;

/**
 *
 * @author Win_It
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String uname = request.getParameter("username");
            String pass = request.getParameter("password");
            String rMe = request.getParameter("remember"); // for check box of remember me...
            // if checked response.addCookie( for uname and pass);
            
            if(rMe != null) {
                // Username Cookie
                Cookie cName = new Cookie("c_uname", uname);
                cName.setMaxAge(Integer.MAX_VALUE);
                response.addCookie(cName);
                
                // Password Cookie
                Cookie cPass = new Cookie("c_pass", pass);
                cPass.setMaxAge(Integer.MAX_VALUE);
                response.addCookie(cPass);
            }
            
            try {    
                HttpSession s = request.getSession();
                s.setAttribute("login", Boolean.FALSE);
                
                MyConnection myCon = (MyConnection) getServletContext().getAttribute("my_con");
                myCon.getUserInstance().setFrmData(uname, pass);
                PreparedStatement myPst = myCon.getConnection().prepareStatement("SELECT * FROM tab WHERE uname=? and passwd=?");
                
                myPst.setString(1, uname);
                myPst.setString(2, pass);

                ResultSet rs;
                rs = myPst.executeQuery();
                
                boolean hasUser = false;
                while (rs.next()) {
                    myCon.getUserInstance().setDBData(rs.getString("uname"), rs.getString("passwd"));
                    
                    if (myCon.getUserInstance().passCheck()) {
                        s.setAttribute("user_name", uname);
                        request.getRequestDispatcher("welcome.jsp").forward(request, response);
                        hasUser = true;
                    } 
                }
                if(!hasUser){
                        String err = "Invalid Username or Password";
                        getServletContext().setAttribute("passwd_msg", err);
                        response.sendRedirect("index.jsp");
                        //request.getRequestDispatcher("index.jsp").include(request, response);
                    }
            } catch (IOException | SQLException | ServletException | ClassNotFoundException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
