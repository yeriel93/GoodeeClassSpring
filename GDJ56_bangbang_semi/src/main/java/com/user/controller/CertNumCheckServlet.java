package com.user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.user.model.service.UserService;

/**
 * Servlet implementation class CertNumCheckServlet
 */
@WebServlet("/user/certNumCheck.bb")
public class CertNumCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CertNumCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userCode=Integer.parseInt(request.getParameter("userCode"));

		HttpSession ss=request.getSession();		
		Integer certNum=(Integer)ss.getAttribute("certNum");
		
		//System.out.println("userCode : "+userCode+"|certNum : "+certNum);
		
		int result=0;
		if(userCode==certNum) {
			//인증번호 일치
			result=1;
		}else {
			//불일치
			result=0;
		}
		
		PrintWriter out=response.getWriter();		
		out.write(result+"");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
