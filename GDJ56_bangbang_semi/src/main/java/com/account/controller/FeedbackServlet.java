package com.account.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FeedbackServlet
 */
@WebServlet("/account/feedback.bb")
public class FeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedbackServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String alertNo=request.getParameter("alertNo");
		String propertyNo=request.getParameter("propertyNo");
		String userCName=request.getParameter("userCName");
		
		String userCNo=request.getParameter("userCNo");
		String userBNo=request.getParameter("userBNo");
		
		//System.out.println(alertNo+"|"+propertyNo+"|"+userCName+"|"+userCNo+"|"+userBNo);
		
		request.setAttribute("alertNo", alertNo);
		request.setAttribute("propertyNo", propertyNo);
		request.setAttribute("userCName", userCName);	
		
		request.setAttribute("userCNo", userCNo);
		request.setAttribute("userBNo", userBNo);
		
		request.getRequestDispatcher("/views/account/feedback.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
