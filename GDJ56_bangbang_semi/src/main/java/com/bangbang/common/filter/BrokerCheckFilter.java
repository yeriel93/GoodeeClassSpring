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

import com.broker.model.vo.Broker;
import com.user.model.vo.User;

//BrokerCheckFilter는 나중에 지워야함 임시로 적어놓은 주소임
@WebFilter(urlPatterns = {
		"/account/broker/*",
		"/property/insertProperty.bb",
		"/property/insertPropertyEnd.bb"
})
public class BrokerCheckFilter extends HttpFilter implements Filter {
       
    public BrokerCheckFilter() {
        super();
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession(false);
		
		User loginUser = null;
		try {
			loginUser = (User)session.getAttribute("loginUser");
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		Broker loginBroker = null;
		try {
			loginBroker = (Broker)session.getAttribute("loginBroker");
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		//System.out.println(loginBroker);
		
		if(loginUser!=null && loginBroker!=null && 
				loginUser.getUserLevel()=='B' && loginBroker.getAdmissionState()=='Y') {
			chain.doFilter(request, response);
		
		}else {	
			request.setAttribute("msg", "승인된 중개사회원만 이용 가능한 서비스입니다. (¬_¬)");		
			request.setAttribute("loc", "/searchAddress.bb");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
