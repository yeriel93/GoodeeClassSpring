package com.bangbang.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.user.model.vo.User;

//LoginCheckFilter는 나중에 지워야함 임시로 적어놓은 주소임
@WebFilter(urlPatterns = {
		"/LoginCheckFilter"
})
public class LoginCheckFilter extends HttpFilter implements Filter {
       
    public LoginCheckFilter() {
        super();
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession(false);
		
		User loginUser = (User)session.getAttribute("loginUser");
		
		if(loginUser!=null) {
			chain.doFilter(request, response);
		
		}else {
			request.setAttribute("msg", "접근할 권한이 없습니다 (¬_¬)");		
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
