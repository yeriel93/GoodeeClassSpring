package com.property.service;

import static com.bangbang.common.JDBCTemplate.close;
import static com.bangbang.common.JDBCTemplate.commit;
import static com.bangbang.common.JDBCTemplate.getConnection;
import static com.bangbang.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.property.model.dao.FilesDao;
import com.property.model.dao.OptionDao;
import com.property.model.dao.PropertyDao;
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
	public int insertProperty(Property p) {
		Connection conn = getConnection();
		int propertyResult = PropertyDao.getPropertyDao().insertProperty(conn, p);
		int filesResult = FilesDao.getFilesDao().insertFiles(conn);
		int optionResult = OptionDao.getOptionDao().insertOption(conn);
		
		int allResult = 0;
		if(propertyResult>0 && filesResult>0 && optionResult>0) {
			allResult =1;
		}
		if(allResult>0) commit(conn);
		else rollback(conn);
		close(conn);
		return allResult;
	}

}
