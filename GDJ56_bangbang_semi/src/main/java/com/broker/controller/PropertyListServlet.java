package com.broker.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.broker.model.vo.Broker;
import com.property.model.vo.Files;
import com.property.model.vo.Property;
import com.property.service.PropertyService;

@WebServlet("/account/broker/propertyList.bb")
public class PropertyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PropertyListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		Broker broker = (Broker)session.getAttribute("loginBroker");
//		System.out.println(broker);
		
		int brokerNo=0;
		try {
			brokerNo = broker.getBrokerNo();
	//		System.out.println(brokerNo);
			List<Property> propertys= PropertyService.getPropertyService().brokerPropertyList(brokerNo);
			
			request.setAttribute("propertys", propertys);
			request.getRequestDispatcher("/views/account/broker/propertyList.jsp").forward(request, response);
		}catch(NullPointerException e) {
			String msg = "로그인 후 이용해주세요!";
			String loc = "/user/login.bb";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
