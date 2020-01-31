<%-- 
    Document   : logout
    Created on : Jan 10, 2020, 6:14:57 PM
    Author     : Win_It
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.example.model.MyConnection"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>We will miss you,  
            <%
                HttpSession s = request.getSession(false);
                out.println("<p>" + s.getId() + "</p>");
                String uname = (String) s.getAttribute("user_name");
                s.setAttribute("login", Boolean.FALSE);
                ((MyConnection) getServletContext().getAttribute("my_con")).setStatus("D");
                s.invalidate();
                out.print(" " + uname);
                out.println("<p>" + s.getId() + "</p>");

            %>


        </h1>
    </body>
</html>
