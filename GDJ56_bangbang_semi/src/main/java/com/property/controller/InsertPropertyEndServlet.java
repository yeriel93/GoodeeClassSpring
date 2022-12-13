package com.property.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
		
			String address = mr.getParameter("address");
			
			double x = Double.parseDouble(mr.getParameter("addrX"));
			
			double y = Double.parseDouble(mr.getParameter("addrY"));
			System.out.println("지번:"+address + " x값 :"+ x +" y값:"+y);
			
			
			String floor = mr.getParameter("floorSelect");
			if(floor.equals("지상")) {
				floor = mr.getParameter("floorIn")+"층";
			}
			
			int deposit = 0;
			int monCharge = 0;
			try {
				deposit = Integer.parseInt(mr.getParameter("yPrice"));
			} catch (NumberFormatException e) {
				deposit = Integer.parseInt(mr.getParameter("mPrice"));
				monCharge = Integer.parseInt(mr.getParameter("mPrice2"));
			}
			
			int managementC = 0; 
			if(mr.getParameter("costSelect").equals("있음")) {
				 managementC = Integer.parseInt(mr.getParameter("costIn"));
			}
			System.out.println("층:"+floor+" 보증금:"+deposit+" 월세:"+monCharge+" 관리비:"+managementC);
			
			String electric = "N"; 
			String water = "N";
			String gas = "N";
			String[] costInclude = mr.getParameterValues("costInclude");
			System.out.println(Arrays.toString(costInclude));
			for(String c : costInclude) {
				if(c.contains("전기")) {
					electric = "Y";
				}else if(c.contains("가스")) {
					gas = "Y";
				}else if(c.contains("수도")) {
					water = "Y";
				}
			}
			
			String roomType = mr.getParameter("roomSelect");
	
			double area = Double.parseDouble(mr.getParameter("areaIn"));
			System.out.println("전기:"+electric+" 가스:"+gas+" 수도:"+water+" 구조:"+roomType+" 면적:"+area);
			
			String vacancy="";
			if(mr.getParameter("edSelect").equals("choice")) {
				vacancy = mr.getParameter("exdayIn");
			} else {
				vacancy = mr.getParameter("edSelect");
			}
			
			
			String[] option = mr.getParameterValues("option");
			System.out.println(Arrays.toString(option));
			
			String pet = mr.getParameter("petSelect");
			String parking = mr.getParameter("parkSelect");
			System.out.println("공실여부:"+vacancy+" 옵션:"+Arrays.toString(option)+" 동물:"+pet+" 주차:"+parking);
			
			String detail = mr.getParameter("detail");
			System.out.println(detail);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
