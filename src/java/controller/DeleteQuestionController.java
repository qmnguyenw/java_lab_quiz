
package controller;

import dao.QuestionDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * DeleteQuestionController.<br>
 * 
 * <pre>
 * Class sử Dụng QuestionDao để xóa 1 câu hỏi với id truyền vào
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
@WebServlet(name = "DeleteQuestionController", urlPatterns = {"/delete-question"})
public class DeleteQuestionController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * - Nhận về questionID và tiến hành xóa
     * - Nếu Xóa thành công chuyển sang trang view-quiz
     * - Nếu xóa thất bại in ra xóa thất bại
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
            QuestionDao questionDao = new QuestionDao();
            String questionId = request.getParameter("questionId");
            
            String quizId = request.getParameter("quizId");
            boolean checkDeleteQuestion = questionDao.removeQuestionByQuestionId(Integer.parseInt(questionId));
            if(!checkDeleteQuestion){
                out.println("Delete Question failed");
            }else{
                request.setAttribute("quizId", quizId);
                request.getRequestDispatcher("view-quiz").forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * Gọi processRequest.
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
     * Gọi processRequest.
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
