package com.property.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.property.model.vo.Property;
import com.property.service.PropertyService;

/**
 * Servlet implementation class SearchPropertyServlet
 */
@WebServlet("/map/searchMapProperty.bb")
public class SearchMapPropertyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMapPropertyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] longitudes = request.getParameterValues("longitudes");
		String[] latitudes = request.getParameterValues("latitudes");
		String[] renttypes = request.getParameterValues("renttypes");
		String deposit = request.getParameter("deposit");
		String monthlyCharge = request.getParameter("monthlyCharge");
		String[] propertyStructures = request.getParameterValues("propertyStructures");
		String applianceAny = request.getParameter("applianceAny");
		String[] applianceOption = request.getParameterValues("applianceOptions");
		
		String propertyQuery = " WHERE ";
		propertyQuery += " THUMBNAIL = 'Y' AND ";
		//위도 경도 parsing
		propertyQuery += " LONGITUDE BETWEEN " + longitudes[0] + " AND " + longitudes[1];
		propertyQuery += " AND LATITUDE BETWEEN " + latitudes[0] + " AND " + latitudes[1];
		//전세, 월세 체크박스 parsing
		propertyQuery += " AND RENTTYPE IN ";
		if(renttypes[0].equals("true") && renttypes[1].equals("true")) {
			propertyQuery += " ('전세','월세') ";
		} else if(renttypes[0].equals("true")) {
			propertyQuery += " ('전세') ";
		} else if(renttypes[1].equals("true")) {
			propertyQuery += " ('월세') ";
		}
		//보증금, 월세 parsing
		propertyQuery += " AND DEPOSIT BETWEEN 0 AND " + deposit;
		propertyQuery += " AND MONTHLY_CHARGE BETWEEN 0 AND " + monthlyCharge;
		//방구조 parsing
		String[] temp = {"오픈형","분리형","복층","투룸"};
		propertyQuery += " AND REGEXP_LIKE (PROPERTY_STRUCTURE, '";
		for(int i=0;i<propertyStructures.length;i++){
			if(propertyStructures[i].equals("true")){
				propertyQuery += temp[i] + "|"; 
			}	
		}
		propertyQuery = propertyQuery.substring(0, propertyQuery.length()-1);
		propertyQuery+= "') ";
		if(applianceAny.equals("false")) {
			for(int i=0; i < applianceOption.length; i++) {
				if(applianceOption[i].equals("true")) {
					propertyQuery += " AND APPLIANCE_NO LIKE '%"+(i+1)+"%'";
				}
			}
		}
		propertyQuery += " ORDER BY ENROLL_DATE DESC";
		//System.out.println("쿼리문 " + propertyQuery);	
		List<Property> list = PropertyService.getPropertyService().searchMapProperty(propertyQuery);
		/*
		 * System.out.println("구분자 ㅋㅋ"); if(list!=null) {
		 * list.forEach(v->System.out.println(v)); } else { System.out.println("졷망"); }
		 */
		
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
