package com.broker.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.broker.model.vo.Broker;
import com.property.model.vo.Files;
import com.property.model.vo.Property;
import com.property.service.PropertyService;


@WebServlet("/account/broker/updateProperty.bb")
public class UpdatePropertyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdatePropertyServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int propertyNo = Integer.parseInt(request.getParameter("propertyNo"));
		System.out.println(propertyNo);
				
		List propertyInfo = PropertyService.getPropertyService().searchPropertyInfo(propertyNo);
//		propertyInfo.forEach(p->System.out.println(p));
		
		Property property = (Property) propertyInfo.get(0);
//		System.out.println("updateProeprtyServlet(매물): "+property);
		List<Files> files = property.getFiles();
//		files.forEach(f->System.out.println("updateProeprtyServlet(파일): "+f));
		List optionList = (List)propertyInfo.get(1);
		String option = optionList.toString();
//		System.out.println("updateProeprtyServlet(옵션): "+option);
		
		request.setAttribute("property", property);
		request.setAttribute("files", files);
		request.setAttribute("option", option);
		request.getRequestDispatcher("/views/account/broker/updateProperty.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
