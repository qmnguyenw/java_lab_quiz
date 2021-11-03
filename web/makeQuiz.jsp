<%-- 
    Document   : newjsp
    Created on : Jun 2, 2021, 10:44:09 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Make Quiz Page</title>
        <link rel="stylesheet" href="css/main.css">
    </head>
    <body>
        <div class="container">
            <%@include file="menu.jsp" %>
            <div class="main">
                <div class="question">
                    <form action="makeQuiz" method="POST" onsubmit="clickSave(event)">
                        <div class="text-area">Number of Question:<span style="color: blue">${questionNo}</span>
                        </div>
                        </br>
                        <div class="text-area">Question:
                            <textarea name="question" cols="60" rows="10" required></textarea></br>
                        </div>
                        
                        <div class="text-area">Option 1:
                            <textarea class="option" name="answer" id="" cols="60" rows="5" required></textarea></br>
                        </div>
                        <div class="text-area">Option 2:
                            <textarea class="option" name="answer" id="" cols="60" rows="5" required></textarea></br>
                        </div>
                        <div class="text-area">Option 3:
                            <textarea class="option" name="answer" id="" cols="60" rows="5" required></textarea></br>
                        </div>
                        <div class="text-area">Option 4:
                            <textarea class="option" name="answer" id="" cols="60" rows="5" required></textarea></br>
                        </div>
                        <div class="answer">
                            Answer(s):
                            <input type="checkbox" name="option" value="0"> Option 1
                            <input type="checkbox" name="option" value="1"> Option 2
                            <input type="checkbox" name="option" value="2"> Option 3
                            <input type="checkbox" name="option" value="3"> Option 4
                        </div>
                        <div class="save-question">
                            <input type="submit" value="Save">
                        </div>
                    </form>
                </div>
            </div>

        </div>
            <script>
                function clickSave(){
                    var answers = document.getElementsByName("option");
                    for (var i = 0; i < answers.length; i++){
                        if (answers[i].checked){
                            return;
                        }
                    }
                    event.preventDefault();
                    alert("Choose at least one answer!");
                }
            </script>
    </body>
</html>
