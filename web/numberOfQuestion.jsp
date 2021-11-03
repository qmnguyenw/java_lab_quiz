<%-- 
    Document   : newjsp
    Created on : Jun 2, 2021, 10:37:36 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Number of Question Page</title>
        <link rel="stylesheet" href="css/main.css">
    </head>
    <body>
        <div class="container">
            <%@include file="menu.jsp" %>
            <div class="main">
                <h3><span>Welcome </span> ${sessionScope.account.username}</h3>
                <form action="takeQuiz" method="POST">
                    Enter id of Quiz:</br>
                    <input class="no-question" type="text" name="quiz_id" required></br>
                    Enter number of question:</br>
                    <input class="no-question" type="number" min="1" name="numOfQuestion" required></br>
                    <h4>${error}</h4>
                    <div class="start">
                        <input type="submit" value="Start">
                    </div>

                </form>
            </div>

        </div>
    </body>
</html>
