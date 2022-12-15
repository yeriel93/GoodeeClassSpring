package com.broker.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.property.model.vo.Property;
import com.property.service.PropertyService;


@WebServlet("/account/broker/updateProperty.bb")
public class UpdatePropertyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdatePropertyServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//임시로 매물번호를 받아서 데이터 불러옴 -> 나중에 수정필요
		//매물리스트에서 통해서 넘어오는 페이지니 거기서 매물 객체전체를 넘기면 더 좋지 않을까? 생각 좀 더 필요
		int propertyNo = 300042;
		Property property = PropertyService.getPropertyService().searchPropertyOne(propertyNo);
//		System.out.println("UpdateProperty서블릿: "+property);
		
		
		request.setAttribute("property", property);
		request.getRequestDispatcher("/views/account/broker/updateProperty.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
