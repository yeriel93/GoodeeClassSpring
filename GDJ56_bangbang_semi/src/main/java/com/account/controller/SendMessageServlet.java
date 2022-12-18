package com.account.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.account.model.service.AccountService;
import com.account.model.vo.Alert;
import com.google.gson.Gson;
import com.user.model.service.UserService;

/**
 * Servlet implementation class SendMessageServlet
 */
@WebServlet("/account/sendMessage.bb")
public class SendMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userNo=Integer.parseInt(request.getParameter("userNo")); 
		int propertyNo=Integer.parseInt(request.getParameter("propertyNo"));
		int brokerUserNo=Integer.parseInt(request.getParameter("brokerUserNo"));
		
//		System.out.println(userNo+" "+propertyNo+" "+brokerUserNo);
		
		Alert a=Alert.builder()
				.sendUserNo(userNo)
				.propertyNo(propertyNo)
				.receiveUserNo(brokerUserNo)
				.build();
		int result=AccountService.getAccountService().sendMessage(a);
		
//		System.out.println(result);
		
		response.setContentType("application/json;charset=utf-8");
		Gson g = new Gson();
		g.toJson(result, response.getWriter());
		
//		PrintWriter out=response.getWriter();		
//		out.write(result+"");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
