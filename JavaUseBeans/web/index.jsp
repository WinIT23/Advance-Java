<%-- 
    Document   : index
    Created on : 26-Feb-2020, 11:20:46
    Author     : Win_It
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Person and Doggo</title>
    </head>
    <body>
        <h1>Hello Man with Doggo!!</h1>
        <form action="LoginServlet" method="POST">
            Name : <input type="text" name="name">
            Dog's Name : <input type="text" name= "dname">
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
