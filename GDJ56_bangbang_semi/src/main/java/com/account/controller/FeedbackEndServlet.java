package com.account.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.account.model.service.AccountService;
import com.account.model.vo.Alert;

/**
 * Servlet implementation class FeedbackEndServlet
 */
@WebServlet("/account/broker/feedbackEnd.bb")
public class FeedbackEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedbackEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userCNo=Integer.parseInt(request.getParameter("userCNo"));
		int userBNo=Integer.parseInt(request.getParameter("userBNo"));
		int propertyNo=Integer.parseInt(request.getParameter("propertyNo"));
		String content=request.getParameter("feedbackContent");
		
//		System.out.println(userCNo);
//		System.out.println(userBNo);
//		System.out.println(propertyNo);
//		System.out.println(content);
		
		Alert a=Alert.builder()
				.sendUserNo(userBNo)
				.receiveUserNo(userCNo)
				.propertyNo(propertyNo)
				.content(content)				
				.build();
		
		int result=AccountService.getAccountService().giveFeedback(a);
		
		String msg="", loc="";
		if(result>0) {
			msg="🟢 답변이 전송되었습니다. ";
			loc="/account/inquiryBroker.bb";
			String script="opener.location.replace('"+request.getContextPath()+"/account/broker/inquiryBroker.bb');close();";
			request.setAttribute("script", script);
		}else {
			msg="🔴 답변전송에 실패했습니다. 문제가 지속될 경우 관리자에게 문의해주세요.";
			loc="/account/inquiryBroker.bb";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
