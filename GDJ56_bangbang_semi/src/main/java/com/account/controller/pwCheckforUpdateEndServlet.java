package com.account.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.broker.model.service.BrokerService;
import com.broker.model.vo.Broker;
import com.user.model.service.UserService;
import com.user.model.vo.User;

/**
 * Servlet implementation class pwCheckforUpdateEndServlet
 */
@WebServlet(name="pwCheckUpdateEnd",urlPatterns="/account/pwCheckEnd.bb")
public class pwCheckforUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pwCheckforUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId=request.getParameter("userId");
		String userPw=request.getParameter("userPw");
				
		User user=UserService.getUserService().loginUser(userId,userPw);
		
		
		
		if(user!=null&&user.getUserLevel()=='B') {	
			request.getRequestDispatcher("/views/account/updateBrokerMain.jsp").forward(request, response);

		}else if(user!=null&&user.getUserLevel()=='C') {	
			request.getRequestDispatcher("/views/account/updateUser.jsp").forward(request, response);
			
			
		}else {
			request.setAttribute("msg", "비밀번호를 확인해주세요.");
			request.setAttribute("loc", "/account/pwCheck.bb");
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
