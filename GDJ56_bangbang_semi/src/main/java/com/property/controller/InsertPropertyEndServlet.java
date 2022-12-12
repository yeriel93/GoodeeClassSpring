package com.property.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/property/insertPropertyEnd.bb")
public class InsertPropertyEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertPropertyEndServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String address = request.getParameter("address");
		
//		double x = Double.parseDouble(request.getParameter("addrX"));
		
//		double y = Double.parseDouble(request.getParameter("addrY"));
		
//		String floor = request.getParameter("floorSelect");
//		if(floor.equals("지상")) {
//			floor = request.getParameter("floorIn")+"층";
//		}
		
//		int deposit = 0;
//		int monCharge = 0;
//		if(request.getParameter("yPrice")!=""){
//			deposit = Integer.parseInt(request.getParameter("yPrice"));
//		} else if(request.getParameter("mPrice")!="") {
//			deposit = Integer.parseInt(request.getParameter("mPrice"));
//			monCharge = Integer.parseInt(request.getParameter("mPrice2"));
//		}
		
//		int managementC = 0; 
//		if(request.getParameter("costSelect").equals("있음")) {
//			 managementC = Integer.parseInt(request.getParameter("costIn"));
//		}
		
//		String electric = "N"; 
//		String water = "N";
//		String gas = "N";
//		String[] costInclude = request.getParameterValues("costInclude");
//		System.out.println(Arrays.toString(costInclude));
//		for(String c : costInclude) {
//			if(c.contains("전기")) {
//				electric = "Y";
//			}else if(c.contains("가스")) {
//				gas = "Y";
//			}else if(c.contains("수도")) {
//				water = "Y";
//			}
//		}
		
//		String roomType = request.getParameter("roomSelect");

//		double area = Double.parseDouble(request.getParameter("areaIn"));
		
//		String date = "";
//		if(request.getParameter("edSelect")!="") {
//			date = request.getParameter("exdayIn");
//			System.out.println(date);
//		}
//		
//		String vacancy = "";
//		if(request.getParameter("edSelect")!="") {
//			vacancy = request.getParameter("edSelect");
//		}
//		System.out.println(vacancy);
		
//		String pet = request.getParameter("petSelect");
//		String parking = request.getParameter("parkSelect");
		
		String detail = request.getParameter("detail");
		System.out.println(detail);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
