package com.broker.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
		
		List<String> fileNames = PropertyService.getPropertyService().callFileNames(propertyNo);
		System.out.println(fileNames);
		for(String f : fileNames) {
			
			String path = getServletContext().getRealPath("/upload/property/");
			File delFile = new File(path+f);
			if(delFile.exists()){
				delFile.delete();
			}
		}
		int result = PropertyService.getPropertyService().deleteProperty(propertyNo);
		
		String msg="", loc="";
		if(result>0) {
			msg = "삭제 완료";
			loc = "/account/broker/propertyList.bb";
		}else {
			msg = "삭제 실패";
			loc = "/account/broker/propertyList.bb";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
