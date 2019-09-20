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
 * Servlet Filter implementation class UserAuthentication
 */
@WebFilter({"/w/*","/e/webuser/logout"})
public class UserAuthentication implements Filter {
    
    private static final Set<String> WHITE_URL_SET = new HashSet<String>()
    {
        /**
         * 白名单
         */
        private static final long serialVersionUID = -8700909693733057360L;

        {
            add("/favicon.ico");
            add("/w");
            add("/w/index");
            add("/w/hotels");
            add("/w/touristspots");
            add("/w/lines");
            add("/w/lineDetail");
            add("/w/messages");
            add("/w/news");
            add("/w/logout");
            add("/w/login");
            add("/w/user/checkAccount");
            add("/w/user/create");
            add("/w/tourism/index");
            add("/w/tourism/detail");
            
        }
    };
    /**
     * Default constructor.
     */
    public UserAuthentication() {
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
            String uri = request.getRequestURI();
            HttpSession session = request.getSession();
            String path = request.getServletPath();
            Object object = session.getAttribute(Constants.Commons.SESSION_WEB_USER);
            if (WHITE_URL_SET.contains(path) || path.startsWith("/w")
                    || path.startsWith("/w/login")|| path.startsWith("/w/user/checkAccount")
                    || path.startsWith("/w/user/create")) {
                //跳转到下一个filter
                fchain.doFilter(request, response);
                return;
            }
            if(uri.indexOf("logout")!=-1 || object == null){
                session.invalidate();
                response.setStatus(403);
                response.sendRedirect("http://127.0.0.1:8080/tourism/w/index");
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
