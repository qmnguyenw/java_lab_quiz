
package controller;

import dao.AccountDao;
import entity.Account;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * LoginController.<br>
 * 
 * <pre>
 * Class lấy dữ liệu từ database Thông qua class AccountDao lấy dữ liệu và kiểm tra đăng nhập
 * Trong class này sẽ tiến hành các xử lí dưới đây.
 *
 * . DoGet.
 * . DoPost.
 * 
 * 
 * </pre>
 * 
 */
@WebServlet(name = "loginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     * Kiểm tra sesson
     * Nếu account bằng null chuyển sang trang login.jsp
     * Nếu account khác null chuyển sang trang url click trước đó
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("addSuccess") != null){
            session.removeAttribute("addSuccess");
            request.setAttribute("error", "Register Successfully");
        }
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            if (session.getAttribute("url") == null) {
                response.sendRedirect("home");
            } else {
                response.sendRedirect(session.getAttribute("url").toString());
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * Kiểm tra username và password nhận về đã tồn tại chưa
     * Nếu Tồn tại cho phép đăng nhập
     * Nếu không tồn tại báo username hoặc password không đúng.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        AccountDao accountDao = new AccountDao();
        Account account = accountDao.getAccount(userName, password);
        if (account != null) {
            request.getSession().setAttribute("account", account);
            if (request.getSession().getAttribute("url") == null) {
                response.sendRedirect("home");
            } else {
                response.sendRedirect(request.getSession().getAttribute("url").toString());
            }
        } else {
            request.setAttribute("error", "Username or password incorrect.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
