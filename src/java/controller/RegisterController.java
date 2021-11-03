
package controller;

import dao.AccountDao;
import dao.TypeAccountDao;
import entity.Account;
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
 * RegisterController.<br>
 *
 * <pre>
 * Đăng kí 1 tài khoản mới
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
@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *  <pre>
     * - Lấy ra tất cả các loại account
     * - Chuyển sang trang register.jsp
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
        session.removeAttribute("account");
        TypeAccountDao typeDao = new TypeAccountDao();
        List<TypeAccount> listType = typeDao.getAll();
        request.setAttribute("listType", listType);
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * 
     *  <pre>
     * - Kiểm tra xem username , pasword đã tồn tại chưa
     * - Nếu tồn tại in ra là đã tồn tại
     * - Nếu không tồn tại thì tạo tài khoản mới
     * </pre>
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDao accountDao = new AccountDao();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int typeId = Integer.parseInt(request.getParameter("type"));
        String email = request.getParameter("email");
        
        Account account = accountDao.getAccount(username);
        if(account!=null){
            request.setAttribute("error", "User name is exist.");
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("typeid", typeId);
            request.setAttribute("email", email);
            TypeAccountDao typeDao = new TypeAccountDao();
            List<TypeAccount> listType = typeDao.getAll();
            request.setAttribute("listType", listType);
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }else{
            boolean checkAddAccount = accountDao.addAccount(username, password, typeId, email);
            if(!checkAddAccount){
                response.getWriter().println("Add account faild!");
            }else{
                request.getSession().setAttribute("addSuccess", "Add account successfully!");
                response.sendRedirect("login");
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
