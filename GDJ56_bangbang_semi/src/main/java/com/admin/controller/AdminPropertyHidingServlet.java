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
 * Servlet implementation class AdminPropertyHidingServlet
 */
@WebServlet("/admin/propertyHiding.bb")
public class AdminPropertyHidingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPropertyHidingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int propertyNo = Integer.parseInt(request.getParameter("propertyNo"));
		String setHiding = request.getParameter("setHiding");
		
		//System.out.println(propertyNo + " / " + setHiding);
		int result = AdminService.getAdminService().updatePropertyHiding(propertyNo, setHiding);
		String msg = "";
		if(result>0 && setHiding.equals("Y")) {
			msg = "매물이 숨김상태로 전환되었습니다.";
		} else if(result>0 && setHiding.equals("N")) {
			msg = "매물이 공개상태로 전환되었습니다.";
		}
		else {
			msg = "실패했습니다.";
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
