<%-- 
    Document   : welcome
    Created on : 01-Mar-2020, 11:49:46
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
        <jsp:useBean id="person" class="com.javabeans.model.Person" scope="request"/> 
        <jsp:setProperty name="person" property="*"/>
        <h1>Hello <jsp:getProperty name="person" property="name"/></h1>
        <p style="font-size: 24px; font-family: Sans ">
            There's a nice Doggo you have. <br>
            Isn't it's name ${person.doggo.dname} ? <br>
        </p>
    </body>
</html>
