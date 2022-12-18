package com.account.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FeedbackEndServlet
 */
@WebServlet("/account/feedbackEnd.bb")
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
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
