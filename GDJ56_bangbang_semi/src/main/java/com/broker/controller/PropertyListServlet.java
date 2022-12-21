package com.broker.controller;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.codegen.IntegerCache;

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
			//페이징처리
			int cPage; 
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));
			} catch (NumberFormatException e) {
				cPage=1;
			}
			int numPerpage = 8;
			
			brokerNo = broker.getBrokerNo();
//			System.out.println(brokerNo);
			List<Property> propertys= PropertyService.getPropertyService().brokerPropertyList(brokerNo, cPage, numPerpage);
//			System.out.println(propertys);
			
			int totalData = PropertyService.getPropertyService().selectPropertyCount(brokerNo);
//			System.out.println(totalData);
			
			String pageBar="";
			//1. pageBar의 번호 갯수
			int pageBarSize = 5;
			//2. 총 페이지수
			int totalPage = (int)Math.ceil((double)totalData/numPerpage);  
			//3. 출력할 번호세팅
			int pageNo = ((cPage-1)/pageBarSize)*pageBarSize+1;
			int pageEnd = pageNo+pageBarSize-1;
			
			if(pageNo==1) {
				pageBar +="<span class='page'> [◀이전] </span> ";
			}else {
				pageBar +="<a class='page' href='"+request.getContextPath()+"/account/broker/propertyList.bb?cPage="+(pageNo-1)+"'> [◀이전] </a> ";
			}
			
			while(!(pageNo>pageEnd || pageNo>totalPage)) {
				if(cPage==pageNo) {
					//보고있는 페이지
					pageBar +="<span class='page' id='cPage'> "+pageNo+" </span>";
				}else {
					pageBar +="<a class='page' href='"+request.getContextPath()+"/account/broker/propertyList.bb?cPage="+pageNo+"'> "+pageNo+" </a>";
				}
				pageNo++;
			}
			
			if(pageNo>totalPage) {
				pageBar +="<span class='page'> [다음▶] </span> ";
			} else {
				pageBar +="<a class='page' href='"+request.getContextPath()+"/account/broker/propertyList.bb?cPage="+pageNo+"'> [다음▶] </a>";
			}
			
			request.setAttribute("pageBar", pageBar);
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
