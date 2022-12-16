package com.centercoordinate.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.centercoordinate.model.vo.CenterCoordinate;
import com.centercoordinate.service.CenterCoordinateService;
import com.google.gson.Gson;

/**
 * Servlet implementation class ChangeDongServlet
 */
@WebServlet("/changedong.bb")
public class ChangeDongServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeDongServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gu = request.getParameter("gu");
		List<CenterCoordinate> list = CenterCoordinateService.getCenterCoordinateService().searchCenterCoordinate(gu);
		
//		request.setAttribute("centerCoordinate", list);
//		request.getRequestDispatcher("/").forward(request, response);
		
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
