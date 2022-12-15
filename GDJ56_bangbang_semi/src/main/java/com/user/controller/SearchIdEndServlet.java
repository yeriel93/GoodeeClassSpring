package com.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.user.model.service.UserService;

/**
 * Servlet implementation class SearchIdEndServlet
 */
@WebServlet("/user/searchIdEnd.bb")
public class SearchIdEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchIdEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String userName=request.getParameter("userName");
		String userEmail=request.getParameter("userEmail");
		String userPhone=request.getParameter("userPhone");
//		System.out.println(userName);
//		System.out.println(userEmail);
//		System.out.println(userPhone);
		
		String searchId=UserService.getUserService().searchId(userName,userEmail,userPhone);
		
		//뒤 세자리 * 처리 
		int idLength=searchId.length();
		String subsId=searchId.substring(0, idLength-3)+"***";		
		
		
		request.setAttribute("searchIdResult", subsId);		
		request.getRequestDispatcher("/views/user/searchIdResult.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
