package com.broker.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.property.service.PropertyService;

@WebServlet("/account/broker/deleteProperty.bb")
public class DeletePropertyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeletePropertyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String propertyNo = request.getParameter("propertyNo");
//		System.out.println(propertyNo);
		
		Map fileNames = PropertyService.getPropertyService().callFileNames(propertyNo);
		System.out.println(fileNames);
//		int result = PropertyService.getPropertyService().deleteProperty(propertyNo);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
