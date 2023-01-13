package com.admin.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.service.AdminService;
import com.google.gson.Gson;

/**
 * Servlet implementation class AdminFakeReportListServlet
 */
@WebServlet("/admin/reportList.bb")
public class AdminReportListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminReportListServlet() {
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
			numPerpage = 15;
		}
		//System.out.println(searchType+" / " + searchKeyword + " / " + cPage + " / " + numPerpage);
	
		String query = "";
		if(searchType.equals("USER_NO")||searchType.equals("PROPERTY_NO")) {
			query += " WHERE R." + searchType + " LIKE '%" + searchKeyword + "%' ";
		} else if(searchType.equals("BROKER_NO")){
			query += " WHERE P." + searchType + " LIKE '%" + searchKeyword + "%' ";
		} else if(searchType.equals("PROCESSING_DATE")) {
			query += "WHERE R." + searchType + " " + searchKeyword; 
		}
		query += " ORDER BY REPORT_DATE DESC) RR) WHERE RNUM BETWEEN ";
		query += ((cPage-1)*numPerpage + 1) + " AND " + (cPage*numPerpage);
		
		//2022-12-18 메모 : report 객체 필요
		//신고리스트 뽑음
		List list = AdminService.getAdminService().searchReportList(query);
		
		//매물당신고누적
		Map<Integer, Integer> propertyReportCount = AdminService.getAdminService().searchPropertyReportCount();
//		Iterator it = propertyReportCount.keySet().iterator();
//		while(it.hasNext()) {
//			int zz = (int)it.next();
//			System.out.println(zz + " / " + propertyReportCount.get(zz));
//		}
		
		//중개사별 신고누적
		Map<Integer, Integer> brokerReportCount = AdminService.getAdminService().searchBrokerReportCount();
//		Iterator it2 = brokerReportCount.keySet().iterator();
//		while(it2.hasNext()) {
//			int zz = (int)it2.next();
//			System.out.println(zz + " / " + brokerReportCount.get(zz));
//		}
		
		list.add(propertyReportCount);
		list.add(brokerReportCount);
		//pageBar 만들어서 반환하기
		//1. totalData : 전체 페이지 수를 알기 위해
		String totalQuery = "";
		if(searchType.equals("USER_NO")||searchType.equals("PROPERTY_NO")) {
			totalQuery += " WHERE R." + searchType + " LIKE '%" + searchKeyword + "%' ";
		} else if(searchType.equals("BROKER_NO")){
			totalQuery += " WHERE P." + searchType + " LIKE '%" + searchKeyword + "%' ";
		} else if(searchType.equals("PROCESSING_DATE")) {
			totalQuery += "WHERE R." + searchType + " " + searchKeyword; 
		}
		int totalData = AdminService.getAdminService().searchReportListCount(totalQuery);
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
			pageBar += "<a class='pageBarTag' href='javascript:void(0);'>[이전] </a>";
		}
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(cPage==pageNo) {
				pageBar += "<span class='pageBarTag'>"+pageNo+" </span>";
			} else { 
				pageBar += "<a class='pageBarTag' href='javascript:void(0);'>"+pageNo+" </a>";
			}
			pageNo++;
		}
		if(pageNo>totalPage) {
			pageBar += "<span class='pageBarTag'> [다음]</span>";
		} else {
			pageBar += "<a class='pageBarTag' href='javascript:void(0);'> [다음]</a>";
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
