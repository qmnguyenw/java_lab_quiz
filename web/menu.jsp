<%-- 
    Document   : newjsp
    Created on : Jun 2, 2021, 10:31:18 AM
    Author     : user
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="menu">
    <ul>
        <li><a href="home">Home</a></li>
        <li><a href="takeQuiz">Take Quiz</a></li>
        <li><a href="makeQuiz">Make Quiz</a></li>
        <li><a href="manageQuiz">Manage Quiz</a></li>
        <c:if test="${sessionScope.account ne null}"><li><a href="logout">Log out</a></li></c:if>
    </ul>
</div>
