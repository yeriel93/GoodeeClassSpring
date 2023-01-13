package com.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.account.model.service.AccountService;
import com.broker.model.service.BrokerService;
import com.broker.model.vo.Broker;

/**
 * Servlet implementation class EnrollBrokerEndServlet
 */
@WebServlet("/user/enrollBrokerEnd.bb")
public class EnrollBrokerEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollBrokerEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
				
		String officeName=request.getParameter("officeName");
		String officeAddress=request.getParameter("officeAddress");
		String registrationNo=request.getParameter("registrationNo");
		String telephone=request.getParameter("telephone");
		int userNo=Integer.parseInt(request.getParameter("userNo"));
		
		Broker b=Broker.builder()
				.officeName(officeName)
				.officeAddress(officeAddress)
				.registrationNo(registrationNo)
				.telephone(telephone)
				.userNo(userNo)
				.build();
		
		int result=BrokerService.getBrokerService().enrollBroker(b);
		
		int userLevelUpdateResult=AccountService.getAccountService().updateUserLevel(userNo);
//		System.out.println(result);
//		System.out.println(userLevelUpdateResult);
		String msg="",loc="";
		if(result>0&&userLevelUpdateResult>0) {
			//중개사 등록신청 성공
			msg="🟢 중개사 등록신청 성공! 평일 기준 1~3일 소요됩니다.";
			loc="/searchAddress.bb";
			
		}else {
			//중개사 등록신청 실패
			msg="🔴 중개사 등록신청 실패! 문제가 지속될 경우 관리자에게 문의해주세요.";
			loc="/user/enrollBroker.bb";			
			
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
