
package controller;

import dao.QuestionDao;
import dao.QuizDao;
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
 * SaveQuizController.<br>
 *
 * <pre>
 * Class Tiến hành lưu bài Quiz , và các câu hỏi ứng với bài quiz đó vào database
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
@WebServlet(name = "saveQuizController", urlPatterns = {"/save-quiz"})
public class SaveQuizController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * <pre>
     *  -Sử dụng lớp QuizDao và QuestionDao để tạo bài quiz và các câu hỏi của nó
     *  -Nếu thêm thất bài, rollback và in ra không thể thêm.
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
            QuizDao quizDao = new QuizDao();
            QuestionDao questionDao = new QuestionDao();
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("account");
            if (account == null) {
                response.sendRedirect("login");
            } else {
                if (account.getTypeAccount().getId() != TypeAccount.TEACHER) {
                    request.setAttribute("error", "You are not authorized to enter this page");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
                Quiz newQuiz = (Quiz) session.getAttribute("newQuiz");
                boolean checkAddQuiz = quizDao.AddQuiz(newQuiz.getId(), newQuiz.getName(), newQuiz.getCreator());
                if (!checkAddQuiz) {
                    out.println("Add Quiz Failed!");
                }
                boolean checkAddQuestion = questionDao.AddListQuestion(newQuiz.getListQuestion());
                if (!checkAddQuestion) {
                    out.println("Add Question Failed!");
                    quizDao.removeQuiz(newQuiz.getId());
                } else {
                    response.sendRedirect("manageQuiz");
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
