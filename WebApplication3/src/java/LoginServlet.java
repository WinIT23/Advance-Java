/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

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
            String s2 = request.getParameter("password");
            User user = new User();
            user.setFrmData(uname, s2);
            
            try {
                Connection myCon = (Connection)getServletContext().getAttribute("dBConnection");
                
                PreparedStatement myPst = myCon.prepareStatement("SELECT * FROM tab WHERE uname=?");

                myPst.setString(1, uname);

                ResultSet rs;
                rs = myPst.executeQuery();
                
                request.setAttribute("user_name", uname);
                
                boolean hasUser = false;
                while (rs.next()) {
                    user.setDBData(rs.getString("uname"), rs.getString("passwd"));
                    
                    if (user.passCheck()) {
                        request.getRequestDispatcher("welcome.jsp").forward(request, response);
                        hasUser = true;
                    } 
                }
                if(!hasUser){
                        
                        String err = "Invalid Password";
                        request.setAttribute("passwd_msg", err);
                    
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }

            } catch (IOException | SQLException | ServletException ex) {
                out.println("<p id=\"error\">");
                ex.printStackTrace(new java.io.PrintWriter(out));
                out.println("</div>");
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
