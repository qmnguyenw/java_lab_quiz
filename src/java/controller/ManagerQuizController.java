
package controller;

import dao.QuizDao;
import entity.Account;
import entity.Quiz;
import entity.TypeAccount;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ManagerQuizController.<br>
 *
 * <pre>
 * Class thao tác Quản lý các bài Quiz của người dùng hiện tại nếu là giáo viên
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
@WebServlet(name = "manageQuizController", urlPatterns = {"/manageQuiz"})
public class ManagerQuizController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * <pre>
     * - Kiểm tra nếu người đăng nhập là giáo viên thì mới cho sử dụng chức năng này
     * - Ngược lại in ra bạn không có quyền truy cập vào trang này
     * 
     * -Thực hiện load tất cả bài quiz mà giáo viên đó đã tạo
     * </pre>
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
//            if (account == null) {
//                response.sendRedirect("login");
//            } else {
//                if (account.getTypeAccount().getId() != TypeAccount.TEACHER) {
//                    request.setAttribute("error", "You are not authorized to enter this page");
//                    request.getRequestDispatcher("error.jsp").forward(request, response);
//                }
                QuizDao quizDao = new QuizDao();
                List<Quiz> listQuiz = quizDao.getAllQuizByCreator(account.getUsername());
                request.setAttribute("listQuiz", listQuiz);
                request.getRequestDispatcher("manageQuiz.jsp").forward(request, response);
//            }
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
