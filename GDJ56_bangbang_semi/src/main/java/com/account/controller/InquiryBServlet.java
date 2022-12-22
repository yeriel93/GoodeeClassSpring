package com.account.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.account.model.service.AccountService;
import com.account.model.vo.AlertList;
import com.user.model.vo.User;

/**
 * Servlet implementation class InquiryBServlet
 */
@WebServlet("/account/broker/inquiryBroker.bb")
public class InquiryBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryBServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int cPage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage")); 
		
		}catch(NumberFormatException e) {		
			cPage=1;
		}
		
		int numPerpage=10;
		
		HttpSession session=request.getSession();
		User loginUser=(User)session.getAttribute("loginUser");
//		System.out.println(loginUser);
		int userNo=loginUser.getUserNo();
//		System.out.println(userNo);
		
		
		//알림내용
		List<AlertList> aList=AccountService.getAccountService().searchUserAlert(cPage,numPerpage,userNo);		
//		System.out.println(aList);	

		int totalData=AccountService.getAccountService().userAlertCount(userNo);
//		System.out.println(totalData);
		String pageBar="";
		int pageBarSize=5;
		int totalPage=(int)Math.ceil((double)totalData/numPerpage);
		
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		if(pageNo==1){
			pageBar+="<span>| 이전 |</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/account/broker/inquiryBroker.bb?cPage="+(pageNo-1)+"'>| 이전 |</a>";

		}
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(cPage==pageNo) {
				pageBar+="<span>"+" "+pageNo+" "+"</span>";
				
			}else {
				pageBar+="<a href='	"+request.getContextPath()+"/account/broker/inquiryBroker.bb?cPage="+pageNo+"'>"+" "+pageNo+" "+"</a>";
			}
			
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar+="<span>| 다음 |</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/account/broker/inquiryBroker.bb?cPage="+pageNo+"'>| 다음 |</a>";
		}
		
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("userAlert", aList);
		
		request.getRequestDispatcher("/views/account/inquiry_B.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
