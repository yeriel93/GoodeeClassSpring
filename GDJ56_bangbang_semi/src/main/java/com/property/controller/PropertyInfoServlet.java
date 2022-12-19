package com.property.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.account.model.service.AccountService;
import com.broker.model.vo.Broker;
import com.property.model.vo.Files;
import com.property.model.vo.Property;
import com.property.service.PropertyService;
import com.user.model.vo.User;

@WebServlet("/property/propertyInfo.bb")
public class PropertyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PropertyInfoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int propertyNo = Integer.parseInt(request.getParameter("propertyNo"));
//		System.out.println(propertyNo);
		
		List propertyInfo = PropertyService.getPropertyService().searchPropertyInfo(propertyNo);
//		propertyInfo.forEach(p->System.out.println(p));
		
		Property property = (Property) propertyInfo.get(0);
		List<Files> files = property.getFiles();
		List option = (List)propertyInfo.get(1);
//		files.forEach(f->System.out.println(f));
//		System.out.println(property);
//		System.out.println(option);
		Broker broker = (Broker)propertyInfo.get(2);
		
		//문의하기 한번만 할 수 있게
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("loginUser");
		int userNo=user.getUserNo();
		
//		System.out.println(userNo);
//		System.out.println(propertyNo);
		
		int inquiryCount=AccountService.getAccountService().inquiryCount(userNo,propertyNo);
		
		request.setAttribute("inquiryCount", inquiryCount);
		
		request.setAttribute("property", property);
		request.setAttribute("files", files);
		request.setAttribute("option", option);
		request.setAttribute("broker", broker);
		request.getRequestDispatcher("/views/property/propertyInfo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
