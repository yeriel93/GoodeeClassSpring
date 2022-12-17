package com.account.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.account.model.service.AccountService;
import com.user.model.vo.User;

/**
 * Servlet implementation class UpdateUserEndServlet
 */
@WebServlet("/account/updateUserEnd.bb")
public class UpdateUserEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u=new User();
		
		String userName=request.getParameter("userName");
		u.setName(userName);
		String userPhone=request.getParameter("userPhone");
		u.setPhone(userPhone);
		String userBirth=request.getParameter("userBirth");
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date birthday=new Date(sdf.parse(userBirth).getTime());
			u.setBirthday(birthday);
		}catch(ParseException e) {
			e.printStackTrace();
		}
		int userNo=Integer.parseInt(request.getParameter("userNo"));
		u.setUserNo(userNo);
		
		int result=AccountService.getAccountService().updateUser(u);
		
		String msg="",loc="";
		if(result>0) {
			msg="🟢 내 정보 수정 완료. 다시 로그인해주세요.";
			loc="/user/logout.bb";
		}else {
			msg="🔴 내 정보 수정 실패. 문제가 지속될 경우 관리자에게 문의하세요.";
			loc="/account/updateUser.bb";
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
