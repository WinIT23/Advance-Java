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
        <title>Sign up Bitch!</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <h1>Please Sign up... I need to pay rent... sed lyf :'(</h1>
        <form method="POST" action="SignupServlet" onsubmit="return passCheck(this)">
            <p>Enter name : <input type="text" name="uname"> </p>
            <p>Enter Password : <input type="password" name="pass"> </p>
            <p>Re-Enter Password : <input type="password" name="cpass"> </p>
            <p id="err"></p>
            <button type="submit">Sign Up</button>
        </form>
        <script>
        function passCheck(form) {
            var passwd = form.pass.value;
            var cpasswd = form.cpass.value;
            
            if (passwd !== cpasswd) {
                err.innerHTML = "Password Dosen't match";
                passwd.value = "";
                cpasswd.value = "";
                return false;
            } else if(passwd === "" || form.uname.value === ""){
                err.innerHTML = "Enter some values \"Dumbass\"";
                return false;
            } else {
                return true;
            }
        }
        </script>
    </body>
</html>
