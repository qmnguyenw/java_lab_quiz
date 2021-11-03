<%-- 
    Document   : newjsp
    Created on : Jun 2, 2021, 10:40:23 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result Quiz Page</title>
        <link rel="stylesheet" href="css/main.css">
    </head>
    <body>
        <div class="container">
            <%@include file="menu.jsp" %>
            <div class="main">
                <c:if test="${marks > 4}">
                <h3 style="color: blue">
                    <span>Your score</span> ${marks} (<fmt:formatNumber type="percent" maxIntegerDigits="4" value="${marks/10}"/>) - 
                    Passed
                </h3>
                </c:if>
                <c:if test="${marks <= 4}">
                <h3 style="color: red">
                    <span>Your score</span> ${marks} (<fmt:formatNumber type="percent" maxIntegerDigits="4" value="${marks/10}"/>) - 
                    Not Passed
                </h3>
                </c:if>
                <form action="takeQuiz" method="GET">
                    <div>
                        Take another test
                        <input type="submit" value="Start">
                    </div>

                </form>
            </div>

        </div>
    </body>
</html>
