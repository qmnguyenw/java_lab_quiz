
package controller;

import entity.Account;
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
 * NextProcess.<br>
 * 
 * <pre>
 * Class quay lại câu hỏi sau đó
 * Trong class này sẽ tiến hành các xử lí dưới đây.
 *
 * . ProcessRequest.
 * . DoGet.
 * . DoPost.
 * 
 *
 * </pre>
 * 
 */
@WebServlet(name = "NextQuizController", urlPatterns = {"/next"})
public class NextProcess extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *  <pre>
     *      -Nhận về câu hỏi hiện tại
     *      -Lấy ra câu hỏi sau đó.
     *  </pre>
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
                    request.setAttribute("error", "Not found any quiz");
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
                    session.setAttribute("Quiz", quiz);
                    if (currentQuestion < quiz.getListQuestion().size() - 1) {
                        currentQuestion++;
                    } else {
                        currentQuestion = 0;
                    }
                    session.setAttribute("current", currentQuestion);
                    String answer[] = quiz.getListQuestion().get(currentQuestion).getAnswer().split("\\|");
                    request.setAttribute("answer", answer);
                    request.getRequestDispatcher("takeQuiz.jsp").forward(request, response);
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
