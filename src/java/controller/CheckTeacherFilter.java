
package controller;

import entity.Account;
import entity.TypeAccount;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * CheckTeacherFilter.<br>
 * 
 * <pre>
 * Class quay lại câu hỏi trước đó
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
@WebFilter(filterName = "CheckTeacherFilter", urlPatterns = {"/delete-question","/delete-quiz","/makeQuiz","/manageQuiz","/save-quiz","/view-quiz"})
public class CheckTeacherFilter implements Filter {
    
    /**
     * doFilter<br>
     * -Kiểm tra nếu user là Teacher thì mới cho qua , ngược lại không cho truy cập
     * @param request
     * @param response
     * @param chain
     * @throws java.io.IOException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        System.out.println("Debug: " + account);
        if (account!=null) System.out.println("Debug: " + account.getTypeAccount().getId());
        if(account!=null&&account.getTypeAccount().getId()==TypeAccount.TEACHER){
            System.out.println("Debug: " + true);
            chain.doFilter(request, response);
        }else{
            System.out.println("Debug: " + false);
            request.setAttribute("error", "You are not authorized to enter this page");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {        
    }

    /**
     * Init method for this filter
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {        
        
    }
    
}
