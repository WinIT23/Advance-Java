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
import model.MyConnection;

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
            
            try {
                
                String dBUrl = getServletContext().getInitParameter("db_url");
                String dBUame = getServletContext().getInitParameter("db_name");
                String dBPass = getServletContext().getInitParameter("db_pass");
                
                MyConnection myCon = new MyConnection(dBUrl, dBUame, dBPass, uname, pass);
                
                PreparedStatement myPst = myCon.getConnection().prepareStatement("SELECT * FROM tab WHERE uname=? and passwd=?");

                myPst.setString(1, uname);
                myPst.setString(2, pass);

                ResultSet rs;
                rs = myPst.executeQuery();
                
                boolean hasUser = false;
                while (rs.next()) {
                    myCon.getUserInstance().setDBData(rs.getString("uname"), rs.getString("passwd"));
                    
                    if (myCon.getUserInstance().passCheck()) {
                        // Creating Session...
                        HttpSession s = request.getSession();
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
                myCon.closeConnction();
            } catch (IOException | SQLException | ServletException ex) {
                out.println("<p id=\"error\">");
                ex.printStackTrace(new java.io.PrintWriter(out));
                out.println("</div>");
            } catch (ClassNotFoundException ex) {
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
