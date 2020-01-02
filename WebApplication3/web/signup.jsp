<%-- 
    Document   : signup
    Created on : Dec 31, 2019, 3:07:15 PM
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
        <h1>Sign up Bitch!</h1>
        <form method="POST" action="SignupServlet">
            Enter name : <input type="text" name="uname"> <br><br>
            Enter Password : <input type="password" name="pass"> <br><br>
            Re-Enter Password : <input type="password" name="cpass"> <br><br>
            <input type="submit">
        </form>
    </body>
</html>
