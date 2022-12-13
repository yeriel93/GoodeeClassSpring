package com.property.controller;

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

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.property.model.vo.Property;
import com.property.service.PropertyService;

@WebServlet("/property/insertPropertyEnd.bb")
public class InsertPropertyEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertPropertyEndServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사진파일 가져오기
		if(!ServletFileUpload.isMultipartContent(request)) {
			response.sendRedirect(request.getContextPath());
		}else {
			String path = request.getServletContext().getRealPath("/upload/property");
			int maxSize = 1024*1024*10;  //최대사이즈는 몇까지 해야할까요?!
			String encoding = "UTF-8";
			DefaultFileRenamePolicy dfr = new DefaultFileRenamePolicy();
			MultipartRequest mr = new MultipartRequest(request,path,maxSize,encoding,dfr);
			
			String fileName ="";
			if(mr.getFilesystemName("mainFile")!=null) {
				fileName = mr.getFilesystemName("mainFile");
			}
			
			String addFileName1="";
			String addFileName2="";
			String addFileName3="";
			String addFileName4="";
			if(mr.getFilesystemName("upFile1")!=null) {
				addFileName1 = mr.getFilesystemName("upFile1");
			} else if(mr.getFilesystemName("upFile2")!=null) {
				addFileName2 = mr.getFilesystemName("upFile2");
			} else if(mr.getFilesystemName("upFile3")!=null) {
				addFileName3 = mr.getFilesystemName("upFile3");
			} else if(mr.getFilesystemName("upFile4")!=null) {
				addFileName4 = mr.getFilesystemName("upFile4");
			}
			System.out.println(fileName+"/"+addFileName1+"/"+addFileName2+"/"+addFileName3+"/"+addFileName4);
			
			//주소
			String address = mr.getParameter("address");
			
			//좌표
			double x = Double.parseDouble(mr.getParameter("addrX"));
			double y = Double.parseDouble(mr.getParameter("addrY"));
			System.out.println("지번:"+address + " x값:"+ x +" y값:"+y);
			
			//층
			String floor = mr.getParameter("floorSelect");
			if(floor.equals("지상")) {
				floor = mr.getParameter("floorIn")+"층";
			}
			
			//전,월세 금액
			int deposit = 0;
			int monCharge = 0;
			try {
				deposit = Integer.parseInt(mr.getParameter("yPrice"));
			} catch (NumberFormatException e) {
				deposit = Integer.parseInt(mr.getParameter("mPrice"));
				monCharge = Integer.parseInt(mr.getParameter("mPrice2"));
			}
			
			//관리비
			int managementC = 0; 
			if(mr.getParameter("costSelect").equals("있음")) {
				 managementC = Integer.parseInt(mr.getParameter("costIn"));
			}
			System.out.println("층:"+floor+" 보증금:"+deposit+" 월세:"+monCharge+" 관리비:"+managementC);
			
			//관리비 포함항목
			char electric = 'N'; 
			char water = 'N';
			char gas = 'N';
			String[] costInclude = mr.getParameterValues("costInclude");
			for(String c : costInclude) {
				if(c.contains("전기")) {
					electric = 'Y';
				}else if(c.contains("가스")) {
					gas = 'Y';
				}else if(c.contains("수도")) {
					water = 'Y';
				}
			}
			
			//방구조
			String roomType = mr.getParameter("roomSelect");
			
			//면적
			double area = Double.parseDouble(mr.getParameter("areaIn"));
			System.out.println("전기:"+electric+" 가스:"+gas+" 수도:"+water+" 구조:"+roomType+" 면적:"+area);
			
			//공실예정일
			String vacancy="";
			if(mr.getParameter("edSelect").equals("choice")) {
				vacancy = mr.getParameter("exdayIn");
			} else {
				vacancy = mr.getParameter("edSelect");
			}
			
			//옵션
			String[] option = mr.getParameterValues("option");
			
			//반려동물,주차
			char pet = mr.getParameter("petSelect").charAt(0);
			char parking = mr.getParameter("parkSelect").charAt(0);
			System.out.println("공실여부:"+vacancy+" 옵션:"+Arrays.toString(option)+" 동물:"+pet+" 주차:"+parking);
			
			//상세설명
			String detail = mr.getParameter("detail");
			System.out.println("내용: "+detail);
			
			
			Property p = Property.builder()
					.address(address)
					.longitude(x)
					.latitude(y)
					.floor(floor)
					.deposit(deposit)
					.monthlyCharge(monCharge)
					.managementCharge(managementC)
					.electric(electric)
					.gas(gas)
					.water(water)
					.propertyStructure(roomType)
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
//					System.out.println(date);
					
					p.setVacancyDate(date);

				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			System.out.println(p);
			int result = PropertyService.getPropertyService().insertProperty(p);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
