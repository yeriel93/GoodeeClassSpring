package com.property.service;

import static com.bangbang.common.JDBCTemplate.close;
import static com.bangbang.common.JDBCTemplate.commit;
import static com.bangbang.common.JDBCTemplate.getConnection;
import static com.bangbang.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.broker.model.dao.BrokerDao;
import com.broker.model.vo.Broker;
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
	public int insertProperty(Property p, List<Files> fileList, String[] option, int brokerNo) {
		Connection conn = getConnection();
		
		int propertyResult = PropertyDao.getPropertyDao().insertProperty(conn, p);
		int propertyNo = 0;
		int filesResult = 0;
		int optionResult = 0;
		int brokerResult = 0;
		if(propertyResult>0) {
			propertyNo = PropertyDao.getPropertyDao().searchPropertyNo(conn);
			
			for(int i=0;i<fileList.size();i++) {
				filesResult = FilesDao.getFilesDao().insertFiles(conn,propertyNo,fileList.get(i));
			}
			
			for(String o : option) {
				optionResult += OptionDao.getOptionDao().insertOption(conn,propertyNo, o);
			}
			
			brokerResult = BrokerDao.getBrokerDao().plusPropertyCount(conn, brokerNo);
		}
		
//		System.out.println("propertyService_insert(매물등록): "+propertyResult);
//		System.out.println("propertyService_insert(파일등록): "+filesResult);
//		System.out.println("propertyService_insert(옵션등록): "+optionResult);
//		System.out.println("propertyService_insert(매물카운트등록): "+brokerResult);
		int allResult = 0;
		if(propertyResult>0 && filesResult>0 && optionResult==option.length && brokerResult>0) {
			allResult = 1;
		}
//		System.out.println("propertyService_insert(전체등록): "+allResult);
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
//		System.out.println("propertyService_search(매물조회): "+property);		

		List<Files> files = FilesDao.getFilesDao().searchFileNames(conn, propertyNo);
		property.setFiles(files);
//		System.out.println("propertyService_search(파일조회): "+files);
		
		List option = OptionDao.getOptionDao().searchOption(conn,propertyNo);
//		System.out.println("propertyService_search(옵션): "+option);
		
		Broker broker = BrokerDao.getBrokerDao().searchBroker(conn,property.getBrokerNo());
//		System.out.println("propertyService_search(브로커): "+broker);
		
		list.add(property);
		list.add(option);
		list.add(broker);
//		System.out.println(list);
		close(conn);
		return list;
	}
	
	
	//매물수정 (매물테이블,옵션테이블)
	public int updateProperty(Property p, String[] option) {
		Connection conn = getConnection();
		
		int optionDelete = OptionDao.getOptionDao().deleteOption(conn, p.getPropertyNo()); 
//		System.out.println("propertyService_update(옵션삭제): "+optionDelete);
		
		int optionInsert = 0;
		for(String o : option) {
			optionInsert += OptionDao.getOptionDao().insertOption(conn,p.getPropertyNo(), o);
		}
//		System.out.println("propertyService_update(옵션등록): "+optionInsert);
		
		int propertyResult = PropertyDao.getPropertyDao().updateProperty(conn, p);
//		System.out.println("propertyService_update(매물등록): "+propertyResult);
		
		int resultAll = 0;
		if(propertyResult>0 && optionDelete>0 && optionInsert==option.length) {
			resultAll = 1;
		}
//		System.out.println("propertyService_update(전체결과): "+resultAll);

		if(resultAll>0) commit(conn);
		else rollback(conn);
		close(conn);
		return resultAll;
	}
	
	//중개인번호로 등록된 매물리스트
	public List<Property> brokerPropertyList(int borkerNo) {
		Connection conn = getConnection();
		
		List<Property> propertys  = PropertyDao.getPropertyDao().brokerPropertyList(conn,borkerNo);
//		System.out.println("propertyService_brokerProperty(매물+썸네일파일이름): "+propertys);
		
		close(conn);
		return propertys;
	}
	
	//선택한 매물의 파일들 이름 불러오기 
	public List<String> callFileNames(String propertyNo) {
		Connection conn = getConnection();
		List<String> fileNames = FilesDao.getFilesDao().callFileNames(conn,propertyNo);
//		System.out.println(fileNames);
		close(conn);
		return fileNames;
	}
	
	//선택한 매물 삭제
	public int deleteProperty(String propertyNo) {
		Connection conn = getConnection();
		int result = PropertyDao.getPropertyDao().deleteProperty(conn,propertyNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
}
