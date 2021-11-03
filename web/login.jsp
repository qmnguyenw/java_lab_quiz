<%-- 
    Document   : newjsp
    Created on : Jun 2, 2021, 10:34:45 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="css/main.css">
    </head>
    
    <body>
        <div class="container">
            <%@include file="menu.jsp" %>
            <div class="main">
                <h3>Login form</h3>
                <form action="login" method="POST">
                    <span>User name: </span><input class="text" type="text" name="username"></br>
                    <span style="margin-right: 9px;">Password: </span><input class="text" type="password" name="password"></br>
                    <input class="sign-in" type="submit" value="Sign in">
                    <a href="register" class="register">Register</a>
                </form>
                <h4>${error}</h4>
            </div>

        </div>
    </body>
</html>
