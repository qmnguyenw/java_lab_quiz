<%-- 
    Document   : newjsp
    Created on : Jun 2, 2021, 10:47:54 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Quiz Page</title>
        <link rel="stylesheet" href="css/main.css">
    </head>
    <body>
        <div class="container">
            <%@include file="menu.jsp" %>
            <div class="main">
                <h3>Number of questions: ${listQuestion.size()}</h3>
                <table>
                    <tr>
                        <th>Question Id</th>
                        <th>Question</th>
                        <th>Delete</th>
                    </tr>
                    <c:forEach items="${listQuestion}" var="q">
                        <tr>
                            <td>${q.id}</td>
                            <td>${q.question}</td>
                            <td><a onclick="submitForm('${quizId}', '${q.id}', 'delete-question')"><button>Delete</button></a></td>
                        </tr>
                    </c:forEach>
                </table>
                <form id="frm" method="POST">
                    <input type="hidden" id="quizId" name="quizId"/>
                    <input type="hidden" id="questionId" name="questionId"/>
                </form>
            </div>

        </div>
        <script>
            function submitForm(quizId, questionId, action) {
                var clickOK = confirm('Are you sure you want to delete this question?');
                if (clickOK) {
                    var form = document.getElementById('frm');
                    var quiz = document.getElementById('quizId');
                    var question = document.getElementById('questionId');
                    form.action = action;
                    quiz.value = quizId;
                    question.value = questionId;
                    form.submit();
                }
            }

        </script>
    </body>
</html>
