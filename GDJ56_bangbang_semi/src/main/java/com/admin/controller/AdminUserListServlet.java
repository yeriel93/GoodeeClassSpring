package com.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.service.AdminService;
import com.google.gson.Gson;

/**
 * Servlet implementation class AdminUserListServlet
 */
@WebServlet("/admin/userList.bb")
public class AdminUserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUserListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		int cPage;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			cPage = 1;
		}
		int numPerpage;
		try {
			numPerpage = Integer.parseInt(request.getParameter("numPerpage"));
		} catch (NumberFormatException e) {
			numPerpage = 20;
		}
		
		System.out.println(searchType + " " + searchKeyword + " " + cPage + " " + numPerpage);
		
		String query = "";
		if(searchType.equals("ID")||searchType.equals("NAME")||searchType.equals("USER_LEVEL")) {
			query += " WHERE U." + searchType + " LIKE '%" + searchKeyword + "%' ";
		}
		query += " ORDER BY U.ENROLL_DATE DESC) RR) WHERE RNUM BETWEEN ";
		query += ((cPage-1)*numPerpage + 1) + " AND " + (cPage*numPerpage);
		
		List list = AdminService.getAdminService().searchUserList(query);
		
		//pageBar 만들어서 반환하기
		//1. totalData : 전체 페이지 수를 알기 위해
		String totalQuery = "";
		if(searchType.equals("ID")||searchType.equals("NAME")||searchType.equals("USER_LEVEL")) {
			totalQuery += " WHERE U." + searchType + " LIKE '%" + searchKeyword + "%' ";
		}
		int totalData = AdminService.getAdminService().searchUserListCount(totalQuery);
		
		//pageBar html코드를 저장할 수 있는 변수 선언
		String pageBar = "";
		
		int pageBarSize = 5;
		//2. 총 페이지수 -> ceil 쓰면 나머지 발생시 무조건 올림
		int totalPage = (int)Math.ceil((double)totalData/numPerpage);
		//3. 출력할 번호 세팅
		int pageNo = (cPage-1)/pageBarSize*pageBarSize + 1;
		int pageEnd = pageNo + pageBarSize - 1;
		
		//html코드 생성하기
		if(pageNo==1) {
			pageBar += "<span>[이전] </span>";
		} else {
			pageBar += "<a href='javascript:void(0);'>[이전] </a>";
		}
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(cPage==pageNo) {
				pageBar += "<span>"+pageNo+" </span>";
			} else { 
				pageBar += "<a href='javascript:void(0);'>"+pageNo+" </a>";
			}
			pageNo++;
		}
		if(pageNo>totalPage) {
			pageBar += "<span> [다음]</span>";
		} else {
			pageBar += "<a href='javascript:void(0);'> [다음]</a>";
		}
		
		list.add(pageBar);
		
		response.setContentType("application/json;charset=utf-8");
		Gson g = new Gson();
		g.toJson(list, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
