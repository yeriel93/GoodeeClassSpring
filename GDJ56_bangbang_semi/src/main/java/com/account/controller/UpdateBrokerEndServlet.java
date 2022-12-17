package com.account.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.broker.model.service.BrokerService;
import com.broker.model.vo.Broker;

/**
 * Servlet implementation class UpdateBrokerEndServlet
 */
@WebServlet("/account/broker/updateBrokerEnd.bb")
public class UpdateBrokerEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBrokerEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String officeName=request.getParameter("officeName");
		String officeAddress=request.getParameter("officeAddress");
		String registrationNo=request.getParameter("registrationNo");
		String telephone=request.getParameter("telephone");
		int brokerNo=Integer.parseInt(request.getParameter("brokerNo"));
		char admissionState=request.getParameter("admissionState").charAt(0);
		System.out.println(officeName);
		System.out.println(officeAddress);
		System.out.println(registrationNo);
		System.out.println(telephone);
		System.out.println(brokerNo);
		System.out.println(admissionState);
		
		Broker b=Broker.builder()
				.officeName(officeName)
				.officeAddress(officeAddress)
				.registrationNo(registrationNo)
				.telephone(telephone)
				.brokerNo(brokerNo)
				.admissionState(admissionState)
				.build();
		System.out.println(b);
		
		int result=BrokerService.getBrokerService().updateBroker(b);
		
		String msg="",loc="";
		if(result>0) {
			msg="🟢 회원정보 수정 완료. 다시 로그인 해주세요.";
			loc="/user/logout.bb";
		}else {
			msg="🔴 회원정보 수정 실패. 문제가 지속될 경우 관리자에게 문의하세요.";
			loc="/account/broker/updateBroker.bb";			
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
