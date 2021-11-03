
package controller;

import dao.QuizDao;
import entity.Account;
import entity.Quiz;
import entity.TypeAccount;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * TakeQuizController.<br>
 *
 * <pre>
 * Class tạo bài quiz ứng với id và số lượng câu hỏi nhận về
 *
 * Trong class này sẽ tiến hành các xử lí dưới đây.
 *
 *. ProcessRequest.
 *. DoGet.
 *. DoPost.
 *
 *
 * </pre>
 *
 */
@WebServlet(name = "takeQuizController", urlPatterns = {"/takeQuiz"})
public class TakeQuizController extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *  <pre>
     *      - Chuyển sang trang numberOfQuestion.jsp để nhập vào số lượng câu hỏi mong muốn
     * </pre>
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("current");
        session.removeAttribute("Quiz");
        Account account = (Account) session.getAttribute("account");
        session.setAttribute("url", "takeQuiz");
        if (account == null) {
            response.sendRedirect("login");
        } else {
            if (account.getTypeAccount().getId() != TypeAccount.STUDENT) {
                request.setAttribute("error", "You are not authorized to enter this page");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("numberOfQuestion.jsp").forward(request, response);
            }
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * <pre>
     *      -Lấy 1 bài Quiz với số lượng câu hỏi thỏa mãn
     *      -Cho phép làm Bài Quiz đó
     *      -Khi hoàn thành thì chuyển sang trang result để hiện kết quả
     * </pre>
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        QuizDao quizDao = new QuizDao();
        HttpSession session = request.getSession();
        //Lấy xuống bài Quiz từ sesson
        Quiz quiz = (Quiz) session.getAttribute("Quiz");
        if (quiz == null) { //nếu trên sesson chưa có bài quiz , thì tạo mới bài quiz thỏa mãn
            int num = Integer.parseInt(request.getParameter("numOfQuestion"));
            String quizId = request.getParameter("quiz_id");
            quiz = quizDao.getQuiz(num, quizId);
            if(quiz==null){//Nếu bài bài ko tồn tại trong database, in ra Quiz id không tồn tại
                request.setAttribute("error", "Quiz id not exist.");
                request.getRequestDispatcher("numberOfQuestion.jsp").forward(request, response);
            }else if(quiz.getListQuestion().size()<num){
                //Nếu số lượng nhập vào lớn hơn số lượng câu hỏi của bài quiz đó trong database
                request.setAttribute("error", "Not enough number of questions");
                request.getRequestDispatcher("numberOfQuestion.jsp").forward(request, response);
            }else{//Tạo mới bài quiz, chuyển sang trang take quiz
                session.setAttribute("Quiz", quiz);
                session.setAttribute("current", 0);
                String answer[] = quiz.getListQuestion().get(0).getAnswer().split("\\|");
                request.setAttribute("answer", answer);
                request.setAttribute("clear", true);
                int time = quiz.getListQuestion().size() * 5;
                request.setAttribute("time", time);
                request.getRequestDispatcher("takeQuiz.jsp").forward(request, response);
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
