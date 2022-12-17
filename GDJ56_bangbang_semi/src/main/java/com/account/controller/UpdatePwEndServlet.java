package com.account.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.account.model.service.AccountService;
import com.user.model.service.UserService;
import com.user.model.vo.User;

/**
 * Servlet implementation class UpdatePwEndServlet
 */
@WebServlet("/account/updatePasswordEnd.bb")
public class UpdatePwEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePwEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId=request.getParameter("userId");
		String beforePw=request.getParameter("beforePw");
		String afterPw=request.getParameter("afterPw");
		
		User u=UserService.getUserService().loginUser(userId, beforePw);
		
		String msg="",loc="";
		if(u!=null) {
			int result=AccountService.getAccountService().updatePw(userId,afterPw);
			if(result>0) {
				msg="🟢 비밀번호 변경 완료. 다시 로그인해주세요.";
				loc="/user/logout.bb";
				String script="opener.location.replace('"+request.getContextPath()+"/user/logout.bb');close();";
				request.setAttribute("script", script);
			}else {
				msg="🔴 비밀번호 변경 실패. 문제가 지속되면 관리자에게 문의하세요.";
				loc="/account/updatePassword.bb"+userId;
			}
			
		}else {
			msg="🔴 현재 비밀번호와 일치하지 않습니다. 다시 입력해주세요.";
			loc="/account/updatePassword.bb?userId="+userId;
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
