package com.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.service.AdminService;
import com.google.gson.Gson;

/**
 * Servlet implementation class AdminReportDeleteServlet
 */
@WebServlet("/admin/reportDelete.bb")
public class AdminReportDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminReportDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] userNoArray = request.getParameterValues("userNoArray");
		String[] propertyNoArray = request.getParameterValues("propertyNoArray");
		
		String query = " WHERE ";
		for(int i = 0; i<userNoArray.length; i++) {
			query += "(USER_NO="+ userNoArray[i] + " AND PROPERTY_NO="+propertyNoArray[i]+") OR";
		}
		query = query.substring(0, query.length()-3);
		
		int result = AdminService.getAdminService().deleteReport(query);
		
		String msg = "";
		if(result > 0) {
			msg = "삭제 성공!";
		} else {
			msg = "삭제 실패!";
		}
		
		response.setContentType("application/json;charset=utf-8");
		Gson g = new Gson();
		g.toJson(msg, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
