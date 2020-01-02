/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Win_It
 */
@WebServlet(urlPatterns = {"/SignupServlet"})
public class SignupServlet extends HttpServlet {

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
            out.println("<title>Servlet SignupServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignupServlet at " + request.getContextPath() + "</h1>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);

        // Make object of the form data...            
        User obj = new User(request.getParameter("fname"),
                request.getParameter("lname"), request.getParameter("email"),
                request.getParameter("pass"), request.getParameter("phno"),
                Integer.parseInt(request.getParameter("age")),
                request.getParameter("gender"));

        if (!request.getParameter("pass").equals(request.getParameter("cpass")) || obj.hasNulls()) {
            request.getRequestDispatcher("signup.html").forward(request, response);
        } else {
            
            // Add to data to database
            try {
                Class.forName("com.mysql.jdbc.Driver");
                
                // make connection..
                Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdata?autoReconnect=true&useSSL=false", "root", "12345");
                
                // create statement...
                Statement myStat = myCon.createStatement();
                
                // execute query...
                int i = myStat.executeUpdate(
                        "INSERT INTO udata " + "VALUES(\""
                                + obj.mFirstName + "\", \"" + obj.mLastName + "\", \"" + obj.mEmail + "\" ,\"" + obj.mPhNo
                                + "\" , \"" + obj.mPassword + "\" , \"" + obj.mGender + "\" , " + obj.mAge + ");");

                if(i == 1) {
                    request.getRequestDispatcher("index.html").forward(request, response);
                } else {
                    request.getRequestDispatcher("signup.html").forward(request, response);
                }
                    
                
            } catch (IOException | ClassNotFoundException | SQLException | ServletException ex) {
                //request.getRequestDispatcher("signup.html").forward(request, response);
                PrintWriter out = response.getWriter();
                out.println("<p id=\"error\">");
                ex.printStackTrace(new java.io.PrintWriter(out));
                out.println("</div>");
            }
            //go to login page 
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

class User {

    protected String mFirstName;
    protected String mLastName;
    protected String mEmail;
    protected String mPassword;
    protected String mPhNo;
    protected int mAge;
    protected String mGender;

    User(String firstName, String lastName, String email, String phno, String password, int age, String gender) {
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mEmail = email;
        this.mPhNo = phno;
        this.mPassword = password;
        this.mAge = age;
        this.mGender = gender;
    }
    
    public boolean hasNulls() {
        return ("".equals(this.mFirstName) || "".equals(this.mLastName) 
                || "".equals(this.mEmail) || "".equals(this.mPassword)
                || "".equals(this.mAge) || "".equals(this.mPhNo) || "".equals(this.mGender));
    }
}
