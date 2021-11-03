<%-- 
    Document   : newjsp
    Created on : Jun 2, 2021, 10:32:54 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link rel="stylesheet" href="css/main.css">
    </head>
    <body>
        <div class="container">
            <%@include file="menu.jsp" %>
            <div class="main">
                <h3><span>Welcome </span> ${sessionScope.account.username}</h3>
            </div>

        </div>
    </body>
</html>
