<%-- 
    Document   : welcome
    Created on : Dec 31, 2019, 11:13:52 AM
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
        <h1>Hello, 
            <%
                String uname = (String) request.getAttribute("user_name");
                out.println(uname);
            %></h1>
    </body>
</html>
