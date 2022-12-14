package com.property.service;

import static com.bangbang.common.JDBCTemplate.close;
import static com.bangbang.common.JDBCTemplate.commit;
import static com.bangbang.common.JDBCTemplate.getConnection;
import static com.bangbang.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.property.model.dao.FilesDao;
import com.property.model.dao.OptionDao;
import com.property.model.dao.PropertyDao;
import com.property.model.vo.Files;
import com.property.model.vo.Property;

public class PropertyService {
	//[BD] singleton 방식으로 만들었음. getPropertyService() 사용해서 호출할 것.
	private static PropertyService propertyService;
	private PropertyService() {}
	public static PropertyService getPropertyService() {
		if(propertyService == null) propertyService = new PropertyService();
		return propertyService;
	}
	
	
	//서비스 작성
	public List<Property> searchMapProperty(String propertyQuery){
		Connection conn = getConnection();
		List<Property> list = PropertyDao.getPropertyDao().searchMapProperty(conn, propertyQuery);
		close(conn);
		
		return list;
	}
	
	public List<Property> searchMapPropertyList(String propertyQuery){
		Connection conn = getConnection();
		List<Property> list = PropertyDao.getPropertyDao().searchMapPropertyList(conn, propertyQuery);
		close(conn);
		return list;
	}
	
	//중개사 방내놓기 
	public int insertProperty(Property p, List<Files> fileList, String[] option) {
		Connection conn = getConnection();
		
		int propertyResult = PropertyDao.getPropertyDao().insertProperty(conn, p);
		int propertyNo = 0;
		int filesResult = 0;
		int optionResult = 0;
		if(propertyResult>0) {
			propertyNo = PropertyDao.getPropertyDao().searchPropertyNo(conn);
			
			for(int i=0;i<fileList.size();i++) {
				filesResult = FilesDao.getFilesDao().insertFiles(conn,propertyNo,fileList.get(i));
			}
			
			if(option!=null) {
				for(String o : option) {
					optionResult = OptionDao.getOptionDao().insertOption(conn,propertyNo, o);
				}
			}
		}
		int allResult = 0;
		if(propertyResult>0 && filesResult>0 && optionResult>0) {
			allResult = 1;
		}
		if(allResult>0) commit(conn);
		else rollback(conn);
		close(conn);
		return allResult;
	}
	
	//매물상세페이지 (매물테이블+옵션+파일명)
	public List searchPropertyInfo(int propertyNo) {
		Connection conn = getConnection();
		List list = new ArrayList();
		
		Property property = PropertyDao.getPropertyDao().searchPropertyInfo(conn,propertyNo);
//		System.out.println(property);		
		List<Files> files = FilesDao.getFilesDao().searchFileNames(conn, propertyNo);
		property.setFiles(files);
//		System.out.println(files);
		List option = OptionDao.getOptionDao().searchOption(conn,propertyNo);
//		System.out.println(option);
		list.add(property);
		list.add(option);
//		System.out.println(list);
		close(conn);
		return list;
	}
	
}
