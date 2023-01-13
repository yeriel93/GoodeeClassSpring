package com.account.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.account.model.service.AccountService;

/**
 * Servlet implementation class WithdrawalEndServlet
 */
@WebServlet("/account/withdrawalEnd.bb")
public class WithdrawalEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WithdrawalEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userNo=Integer.parseInt(request.getParameter("userNo"));
		
		int result=AccountService.getAccountService().deleteUser(userNo);
		
		String msg="",loc="";
		if(result>0) {
			msg="🙇‍♀️그동안 방방을 이용해주셔서 감사합니다.🙇‍♂️";
			loc="/user/logout.bb";
		}else {
			msg="🙅‍♀️탈퇴에 실패했습니다. 문제가 지속될 경우 관리자에게 문의해주세요.🙅‍♂️";
			loc="/account/withdrawal.bb";
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
