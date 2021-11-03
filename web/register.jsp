<%-- 
    Document   : newjsp
    Created on : Jun 2, 2021, 10:35:11 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
        <link rel="stylesheet" href="css/main.css">
    </head>

    <body>
        <div class="container">
            <%@include file="menu.jsp" %>
            <div class="main">
                <h3>Registration form</h3>
                <form action="register" method="POST">
                    <span>User name: </span><input class="text" type="text" name="username" required="" value="${username}"></br>
                    <span style="margin-right: 9px;">Password: </span><input class="text" type="password" name="password" required=""></br>
                    UserType: 
                    <select style="margin-left: 16px; margin-top: 5px" name="type">
                        <c:forEach items="${listType}" var="type">
                        <option value="${type.id}" ${typeid == type.id ? "selected" : ""}>${type.type}</option>
                        </c:forEach>
                    </select></br>
                    Email: <input style="margin-left: 44px;margin-top: 5px" type="email" name="email" value="${email}"></br></br>
                    <input class="sign-in" type="submit" value="Register">
                </form>
                <h4>${error}</h4>
            </div>

        </div>
    </body>
</html>
