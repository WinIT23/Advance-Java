<%-- 
    Document   : index
    Created on : Dec 31, 2019, 10:43:50 AM
    Author     : Win_It
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>

        <%
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = getServletContext().getInitParameter("db_url");
            String name = getServletContext().getInitParameter("db_name");
            String pass = getServletContext().getInitParameter("db_pass");
            java.sql.Connection myCon = java.sql.DriverManager.getConnection(url, name, pass);
            getServletContext().setAttribute("dBConnection", myCon);
        %>

        <form action="NewServlet" method="GET"> 
            Username : <input type="text" name="username">
            Password : <input type="password" name="password"><br>
            <%
                String err = (String) request.getAttribute("passwd_msg");
                if(err != null)
                    out.println(err + "<br>");
            %> 
            <input type="submit">
        </form>
        <a href="signup.jsp">Sign Up</a>
    </body>
</html>
