<%-- 
    Document   : newjsp
    Created on : Jun 2, 2021, 10:43:28 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Take Quiz Page</title>
        <link rel="stylesheet" href="css/main.css">
        <style>
            .question-no span{
                color: blue;
            }
        </style>
        <script>
            function goto(action, method) {
                var form = document.getElementById("frm");
                form.action = action;
                form.method = method;
                form.submit();
            }
            

            
        </script>
    </head>
    <body>
        <div class="container">
            <%@include file="menu.jsp" %>
            <div class="main">
                <h3><span>Welcome </span> ${sessionScope.account.username}</h3>
                <div class="time">Time remaining <span id="timer">${time}</span></div>
                <form id="frm" method="POST">
                    <div class="question-no">Question NO:<span>${current+1} / ${Quiz.getListQuestion().size()}</span></div></br>
                    <div class="question-quiz">
                        ${Quiz.getListQuestion().get(current).question}</br>
                        <c:forEach items="${answer}" var="a">
                            <input type="checkbox" name="option" value="${a}"/>${a}</br>
                        </c:forEach>
                        <input id="listAnswer" type="hidden" value="${Quiz.getListQuestion().get(current).yourAnswer}"
                               <div style="text-align: center">
                        </div>
                    </div>

                </form>
                
                <button class="sign-in" onclick="goto('back', 'POST');">Back</button>
                <button class="sign-in" onclick="goto('next', 'POST');">Next</button>
                <button class="sign-in" onclick="goto('result', 'POST');">Finish</button>
            </div>
            
        </div>

    </div>
    <script>

        var yourAnswer = document.getElementById("listAnswer").value.split('|');
        var input = document.getElementsByName("option");
        for(let a of yourAnswer){
            for(let i =0;i<input.length;i++){
                if(input[i].value===a){
                    input[i].checked=true;
                }
            }
        }
        
        var time = document.getElementById("timer");
        if (${clear eq true}) {
            sessionStorage.setItem('time',${time+1});
        }
        time.innerHTML = sessionStorage.getItem('time');
        function timer() {
            time.innerHTML = parseInt(sessionStorage.getItem('time')) - 1;
            sessionStorage.setItem('time', time.innerHTML);
            if (time.innerHTML == 0) {
                var form = document.getElementById("frm");
                form.action = "result";
                form.submit();
                clearTimeout(id);
            }
            var id = setTimeout(timer, 1000)
        }
        timer();






//            var option[]=document.getElementsByName("option");
//            for (var i = 0, i<option.length;i++) {
//                if(opene)
//            }
    </script>
</body>
</html>
