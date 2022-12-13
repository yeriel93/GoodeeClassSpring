package com.centercoordinate.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.centercoordinate.model.vo.CenterCoordinate;
import com.centercoordinate.service.CenterCoordinateService;
import com.google.gson.Gson;

/**
 * Servlet implementation class ChangeDongAjax
 */
@WebServlet("/searchAddressAjax.do")
public class SearchAddressAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchAddressAjaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gu = request.getParameter("gu");
		String dong = request.getParameter("dong");

		CenterCoordinate cc = CenterCoordinateService.getCenterCoordinateService().searchCenterCoordinate(gu, dong);
		response.setContentType("application/json;charset=utf-8");
		Gson g = new Gson();
		g.toJson(cc, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
