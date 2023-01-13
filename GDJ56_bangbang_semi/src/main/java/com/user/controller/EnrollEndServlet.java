package com.user.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.model.vo.User;
import com.user.model.service.UserService;

/**
 * Servlet implementation class EnrollEndServlet
 */
@WebServlet(name="enrollEnd",urlPatterns = "/user/enrollEnd.bb")
public class EnrollEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**    
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User u=new User();
		
		String id=request.getParameter("userId");
		String pw=request.getParameter("userPw");
		String name=request.getParameter("userName");
		String email=request.getParameter("userEmail");
		String phone=request.getParameter("userPhone");
		u.setId(id);
		u.setPassword(pw);
		u.setName(name);
		u.setEmail(email);
		u.setPhone(phone);
				
		String birth=request.getParameter("userBirth");
//		System.out.println(id);
//		System.out.println(name);
//		System.out.println(email);

		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date birthday=new Date(sdf.parse(birth).getTime());
			u.setBirthday(birthday);
		}catch(ParseException e) {
			e.printStackTrace();
		}
		
//		System.out.println(u);
		
		int result=UserService.getUserService().insertUser(u);
		
		String msg="",loc="";
		if(result>0) {
			msg="방방 가입을 축하합니다! 로그인 후 이용해주세요.";
			loc="/user/login.bb";
		}else {
			msg="회원가입에 실패했습니다. 문제가 지속될 경우 관리자에게 문의해주세요.";
			loc="/user/enroll.bb";
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
