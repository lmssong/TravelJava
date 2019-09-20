package com.iss.sdb.commons.filters;

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

import com.iss.sdb.pet.commons.Constants;

/**
 * Servlet Filter implementation class UserAuthentication
 */
@WebFilter("/recharge/*")
public class RechargeAuthentication implements Filter {
    /**
     * Default constructor.
     */
    public RechargeAuthentication() {
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
            Object object = session.getAttribute(Constants.Commons.SESSION_STORE_USER);
            if(uri.indexOf("logout")!=-1 || object == null){
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
