/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Win_It
 */
public class LoginServlet extends HttpServlet {
    
    String dbUser = "admin";
    String dbPass = "admin";
    
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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        
        String uname = request.getParameter("uname");
        String pass = request.getParameter("pass");
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // autoReconnect = true and useSSL = false is used to remove error specific to netbeans while connecting to database.
            Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdata?autoReconnect=true&useSSL=false", "root", "12345");
            
            PreparedStatement myPst = myCon.prepareStatement("SELECT * FROM data WHERE uname=?");
            
            myPst.setString(1, uname);
            ResultSet rs = myPst.executeQuery();
            
            rs.next();
            dbUser = rs.getString("uname");
            dbPass = rs.getString("passwd");
            
            // Check for invalid usernames and password and prompt ..
            // and also ask to sign in 
            
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        if(uname.equals(dbUser) && pass.equals(dbPass)) {
            RequestDispatcher rd = (RequestDispatcher) request.getRequestDispatcher("welcome.html");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = (RequestDispatcher) request.getRequestDispatcher("index.html");
            rd.forward(request, response);
        }
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
