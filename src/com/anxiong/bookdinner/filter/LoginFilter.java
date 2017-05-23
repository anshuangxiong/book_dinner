package com.anxiong.bookdinner.filter;

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

import com.anxiong.bookdinner.domain.User;


/**
 * Servlet Filter implementation class LoginFilter
 *用户登录过滤器
 */
@WebFilter({"/LoginFilter", "/jsps/cart/*", "/jsps/order/*","/cartServlet"} )
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		User user=(User) req.getSession().getAttribute("user");
		if(user==null){
			req.setAttribute("msg", "你还没有登录");
			req.getRequestDispatcher("/jsps/user/login.jsp").forward(req, response);
		
		}else{
			chain.doFilter(request, response);
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
