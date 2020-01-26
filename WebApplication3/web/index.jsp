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
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <h1>Hello World!</h1>

        <%
            String username = "";
            String password = "";

            Cookie[] c = request.getCookies();
            if (c != null) {
                for (Cookie c1 : c) {
                    if (c1.getName().equals("c_uname")) {
                        username = c1.getValue();
                    }
                    if (c1.getName().equals("c_pass")) {
                        password = c1.getValue();
                    }
                }
            }
        %>

        <form action="LoginServlet" method="POST"> 
            <p>Username : <input type="text" name="username" value="<%=username%>">
                Password : <input type="password" name="password" value="<%=password%>"><br></p>
            <p id="err"><%
                String err = (String) getServletContext().getAttribute("passwd_msg");
                if (err != null) {
                    out.println(err + "<br>");
                }
                getServletContext().setAttribute("passwd_msg", null);

                %></p> 
            <p><input type = "checkbox" name = "remember" value="checked"/> Remember Me!!! </p> 
            <button type="submit">Login</button> <a href="signup.jsp">Sign Up</a>
        </form>
    </body>
</html>
