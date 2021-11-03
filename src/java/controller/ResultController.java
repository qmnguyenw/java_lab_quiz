
package controller;

import entity.Account;
import entity.Question;
import entity.Quiz;
import entity.TypeAccount;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ResultController.<br>
 *
 * <pre>
 * Class Hiện kết quả của bài quiz
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
@WebServlet(name = "resultController", urlPatterns = {"/result"})
public class ResultController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * <pre>
     * -Lấy xuống bài quiz vừa làm , và kiểm tra đáp án
     * </pre>
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("account");
            if (account == null) {
                response.sendRedirect("login");
            } else {
                if (account.getTypeAccount().getId() != TypeAccount.STUDENT) {
                    request.setAttribute("error", "You are not authorized to enter this page");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
                Quiz quiz = (Quiz) session.getAttribute("Quiz");
                if (quiz == null) {
                    request.setAttribute("error", "You haven't done any quiz yet");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                } else {

                    int currentQuestion = (int) session.getAttribute("current");

                    String[] yourAnswer = request.getParameterValues("option");
                    if (yourAnswer != null) {
                        String resultAnswer = "";
                        for (int i = 0; i < yourAnswer.length; i++) {
                            if (yourAnswer[i] != null) {
                                resultAnswer = resultAnswer + yourAnswer[i] + "|";
                            }
                        }
                        resultAnswer = resultAnswer.substring(0, resultAnswer.length() - 1);
                        quiz.getListQuestion().get(currentQuestion).setYourAnswer(resultAnswer);
                    }

                    int count = 0;
                    for (Question q : quiz.getListQuestion()) {
                        if (q.getAnswerCorrect().equals(q.getYourAnswer())) {
                            count++;
                        }
                    }
                    double marks = (double) count / quiz.getListQuestion().size() * 10;
                    marks = Math.round(marks * 100);
                    request.setAttribute("marks", marks / 100);
                    request.getRequestDispatcher("resultQuiz.jsp").forward(request, response);
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
