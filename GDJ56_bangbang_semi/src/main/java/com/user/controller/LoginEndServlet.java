package com.user.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.account.model.service.AccountService;
import com.account.model.vo.AlertListC;
import com.broker.model.service.BrokerService;
import com.broker.model.vo.Broker;
import com.user.model.service.UserService;
import com.user.model.vo.User;


/**
 * Servlet implementation class LoginEndServlet
 */
@WebServlet("/user/loginEnd.bb")
public class LoginEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인
		String userId=request.getParameter("userId");
		String userPw=request.getParameter("userPw");
				
		User user=UserService.getUserService().loginUser(userId,userPw);
//		System.out.println(user);
		
		
		
		if(user!=null) {
			if(user.getUserLevel()=='B') {
				Broker broker=BrokerService.getBrokerService().loginBroker(user.getUserNo());
				
				if(broker.getAdmissionState()=='Y') {
					HttpSession session=request.getSession();
					session.setAttribute("loginBroker", broker);					
					
				}
			}
			
			//로그인세션
			HttpSession session=request.getSession();
			session.setAttribute("loginUser", user);	
			
//			//알림
//			int userNo=user.getUserNo();
//			List<AlertListC> aList=AccountService.getAccountService().searchUserAlertC(1,1000,userNo);
//			
//			HttpSession session2=request.getSession();
//			session2.setAttribute("alertListC", aList);
		
			
			response.sendRedirect(request.getContextPath()+"/searchAddress.bb"); //흠..? 로그인한 페이지로 돌아가게 짬있을 때 ㄱㄱ
//			response.sendRedirect(request.getHeader("referer")); // 이전페이지로 돌아가게 하는 건데 이전페이지가 로그인페이지라 못씀

		}else {	
			
			request.setAttribute("msg", "아이디/비밀번호를 다시 확인해주세요.");
			request.setAttribute("loc", "/user/login.bb");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
