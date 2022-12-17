package com.broker.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.property.model.vo.Property;
import com.property.service.PropertyService;


@WebServlet("/account/broker/updatePropertyEnd.bb")
public class UpdatePropertyEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdatePropertyEndServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int propertyNo = Integer.parseInt(request.getParameter("propertyNo"));
		System.out.println(propertyNo);
		
		//전,월세 금액
		int deposit = 0;
		int monCharge = 0;
		try {
			deposit = Integer.parseInt(request.getParameter("yPrice"));
		} catch (NumberFormatException e) {
			deposit = Integer.parseInt(request.getParameter("mPrice"));
			monCharge = Integer.parseInt(request.getParameter("mPrice2"));
		}
		
		//관리비
		int managementC = 0; 
		if(request.getParameter("costSelect").equals("있음")) {
			 managementC = Integer.parseInt(request.getParameter("costIn"));
		}
//		System.out.println("층:"+floor+" 보증금:"+deposit+" 월세:"+monCharge+" 관리비:"+managementC);
		
		//관리비 포함항목
		char electric = 'N'; 
		char water = 'N';
		char gas = 'N';
		String[] costInclude = request.getParameterValues("costInclude");
		if(costInclude!=null) {
			for(String c : costInclude) {
				if(c.contains("전기")) {
					electric = 'Y';
				}else if(c.contains("가스")) {
					gas = 'Y';
				}else if(c.contains("수도")) {
					water = 'Y';
				}
			}
		}
		
		//면적
		double area = Double.parseDouble(request.getParameter("areaIn"));
//		System.out.println("전기:"+electric+" 가스:"+gas+" 수도:"+water+" 구조:"+roomType+" 면적:"+area);
		
		//공실예정일
		String vacancy="";
		if(request.getParameter("edSelect").equals("choice")) {
			vacancy = request.getParameter("exdayIn");
		} else {
			vacancy = request.getParameter("edSelect");
		}
		
		//옵션
		String[] option = request.getParameterValues("option");
//		System.out.println("updatePropertyEndServlet(옵션):"+Arrays.toString(option));
		
		//반려동물,주차
		char pet = request.getParameter("petSelect").charAt(0);
		char parking = request.getParameter("parkSelect").charAt(0);
//		System.out.println("공실여부:"+vacancy+" 옵션:"+Arrays.toString(option)+" 동물:"+pet+" 주차:"+parking);
		
		//상세설명
		String detail = request.getParameter("detail");
//		System.out.println("내용: "+detail);
		
		
		Property p = Property.builder()
				.propertyNo(propertyNo)
				.deposit(deposit)
				.monthlyCharge(monCharge)
				.managementCharge(managementC)
				.electric(electric)
				.gas(gas)
				.water(water)
				.area(area)
				.pet(pet)
				.parking(parking)
				.detail(detail)
				.build();
		
		if(monCharge==0) {
			p.setRenttype("전세");
		}else {
			p.setRenttype("월세");
		}
		
		if(vacancy.equals("공실") || vacancy.equals("협의입주")) {
			p.setVacancy(vacancy);
		}else {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date(sdf.parse(vacancy).getTime());
//				System.out.println(date);
				
				p.setVacancyDate(date);

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		System.out.println("updatePropertyEndServlet: "+p);
		
		
		int result = PropertyService.getPropertyService().updateProperty(p, option);
		
		String msg ="",loc="";
		if(result>0) {
			msg = "매물 수정 성공! 축하드립니다~ ヽ(✿ﾟ▽ﾟ)ノ 짝짝짝!!";
			loc = "/account/broker/propertyList.bb";
		}else {
			msg = "매물 수정 실패! 다시 시도해주세요~~ o(TヘTo)";
			loc = "/account/broker/updateProperty.bb";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
