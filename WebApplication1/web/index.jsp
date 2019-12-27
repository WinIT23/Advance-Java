<%-- 
    Document   : index
    Created on : 23 Dec, 2019, 10:13:43 AM
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
        <form method="POST" action="LoginServlet">
            Username <input type="text" name="username">
            Password <input type="password" name="password">
            <input type="submit">
        </form>
    </body>
</html>
