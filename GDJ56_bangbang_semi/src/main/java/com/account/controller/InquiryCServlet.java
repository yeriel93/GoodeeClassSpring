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
import com.account.model.vo.AlertListC;
import com.user.model.vo.User;

/**
 * Servlet implementation class InquiryServlet
 */
@WebServlet("/account/inquiry.bb")
public class InquiryCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryCServlet() {
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
		
//		System.out.println("loginUser:"+loginUser);
		
		int userNo=loginUser.getUserNo();
		
//		System.out.println("userNo:"+userNo);		

		List<AlertListC> aList=AccountService.getAccountService().searchUserAlertC(cPage,numPerpage,userNo);
				
//		System.out.println("aList:"+aList);	

		int totalData=AccountService.getAccountService().userAlertCountC(userNo);
		
		String pageBar="";
		int pageBarSize=5;
		int totalPage=(int)Math.ceil((double)totalData/numPerpage);
		
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		if(pageNo==1){
			pageBar+="<span>| 이전 |</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/account/inquiry.bb?cPage="+(pageNo-1)+"'>| 이전 |</a>";

		}
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(cPage==pageNo) {
				pageBar+="<span>"+" "+pageNo+" "+"</span>";
				
			}else {
				pageBar+="<a href='	"+request.getContextPath()+"/account/inquiry.bb?cPage="+pageNo+"'>"+" "+pageNo+" "+"</a>";
			}
			
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar+="<span>| 다음 |</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/account/inquiry.bb?cPage="+pageNo+"'>| 다음 |</a>";
		}
		
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("userAlertC", aList);
		
//		HttpSession session2=request.getSession();
//		session2.setAttribute("userAlertC", aList);
		
		request.getRequestDispatcher("/views/account/inquiry_C.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
