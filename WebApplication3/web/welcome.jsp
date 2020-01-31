<%-- 
    Document   : welcome
    Created on : Dec 31, 2019, 11:13:52 AM
    Author     : Win_It
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    HttpSession mySession = request.getSession(false);
    if(mySession.isNew() || (mySession.getAttribute("login") == null)){
            response.sendRedirect("hackerman.jsp");
    } else {
        out.println("<p>" + mySession.getId() + "</p>");
        out.println("<p>" + mySession.getAttribute("login") + "</p>");
    }
    
//    HttpSession s = request.getSession();
//    if(s.isNew()) {
//        response.sendRedirect("hackerman.jsp");
//    }    
%>
<html>
    <head>
        <link rel="stylesheet" href="css/style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello, 
            <%
                if(getServletContext().getAttribute("user_name") != null){
                    String uname = (String)getServletContext().getAttribute("user_name");
                    out.println(uname);
                } else {
            // <---------- I think (Most Probably) it is "Paatiya Method" ------------->
                    //response.sendRedirect("hackerman.jsp");
                }
            %></h1>
            <a href="logout.jsp">Log Out</a>
    </body>
</html>
