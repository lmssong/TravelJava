package com.iss.sdb.commons.filters;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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

import com.iss.sdb.pet.commons.Constants;

/**
 * Servlet Filter implementation class SysUserAuthentication
 */
@WebFilter({"/m/*","/e/sysuser/logout"})
public class SysUserAuthentication implements Filter {
    
    private static final Set<String> WHITE_URL_SET = new HashSet<String>()
    {
        /**
         * 白名单
         */
        private static final long serialVersionUID = -8700909693733057360L;

        {
            add("/favicon.ico");
            add("/m/login");

        }
    };
    /**
     * Default constructor.
     */
    public SysUserAuthentication() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain fchain)
            throws IOException, ServletException {
         if (srequest instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) srequest;
            HttpServletResponse response = (HttpServletResponse)sresponse;
            HttpSession session = request.getSession();
            String path = request.getServletPath();
            Object object = session.getAttribute(Constants.Commons.SESSION_STORE_USER);
            if (WHITE_URL_SET.contains(path)) {
                //跳转到下一个filter
                fchain.doFilter(request, response);
                return;
            }
            if(path.indexOf("logout")!=-1 || object == null){
                session.invalidate();
                response.setStatus(403);
                response.sendRedirect("http://127.0.0.1:8080/tourism/login.jsp");
                return;
            }
        }

        fchain.doFilter(srequest, sresponse);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
