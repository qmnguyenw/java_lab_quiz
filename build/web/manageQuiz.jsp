<%-- 
    Document   : newjsp
    Created on : Jun 2, 2021, 10:50:49 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Quiz Page</title>
        <link rel="stylesheet" href="css/main.css">

    </head>
    <body>
        <div class="container">
            <%@include file="menu.jsp" %>
            <div class="main">
                <h3>Number of questions: ${listQuiz.size()}</h3>
                <table>
                    <tr>
                        <th>Quiz Id</th>
                        <th>Quiz Name</th>
                        <th>Creator</th>
                        <th>DateCreated</th>
                        <th>View</th>
                        <th>Delete</th>
                    </tr>
                    <c:forEach items="${listQuiz}" var="q">
                        <tr>
                            <td>${q.id}</td>
                            <td>${q.name}</td>
                            <td>${q.creator}</td>
                            <td><fmt:formatDate pattern = "yyyy-MMM-dd" value = "${q.createdDate}" /></td>
                            <td><a onclick="submitForm('${q.id}', 'view-quiz')"><button>View</button></a></td>
                            <td><a onclick="if (confirm('Are you sure you want to delete this quiz?'))
                                        submitForm('${q.id}', 'delete-quiz')"><button>Delete</button></a></td>
                        </tr>
                    </c:forEach>
                </table>
                <form id="frm" method="POST">
                    <input type="hidden" id="text" name="quizId"/>
                </form>
            </div>

        </div>
        <script>
            function submitForm(quizId, action) {
                var form = document.getElementById('frm');
                var text = document.getElementById('text');
                form.action = action;
                text.value = quizId;
                form.submit();
            }

        </script>
    </body>
</html>
