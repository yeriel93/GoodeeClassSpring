package com.broker.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/account/broker/updatePropertyEnd.bb")
public class UpdatePropertyEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdatePropertyEndServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		
		int result=0;
		
		String msg ="",loc="";
		if(result>0) {
			msg = "방내놓기 성공! 축하드립니다~ ヽ(✿ﾟ▽ﾟ)ノ 짝짝짝!!";
			loc = "/account/broker/propertyList.bb";
		}else {
			msg = "방내놓기 실패! 다시 시도해주세요~~ o(TヘTo)";
			loc = "/property/insertProperty.bb";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
