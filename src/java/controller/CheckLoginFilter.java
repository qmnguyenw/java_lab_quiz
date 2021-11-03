
package controller;

import entity.Account;
import entity.TypeAccount;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * CheckLoginFilter.<br>
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
@WebFilter(filterName = "CheckLoginedFilter", urlPatterns = {"/login", "/register", "/home", "/logout"})
public class CheckLoginFilter implements Filter {
    
    /**
     * doFilter<br>
     * -Kiểm tra nếu user login thì mới cho qua , ngược lại không cho truy cập
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
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        String path = req.getRequestURI().replace(req.getContextPath(), "");
        if(account!=null || "/login".equals(path) || "/register".equals(path) || "/home".equals(path)){
            chain.doFilter(request, response);
        }else{
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
