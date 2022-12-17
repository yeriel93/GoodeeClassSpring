package com.fakereport.service;

import java.sql.Connection;

import com.fakereport.dao.FakeReportDao;
import static com.bangbang.common.JDBCTemplate.*;

public class FakeReportService {
	private static FakeReportService fakeReportService;
	private FakeReportService() {}
	public static FakeReportService getFakeReportService() {
		if(fakeReportService == null) fakeReportService = new FakeReportService();
		return fakeReportService;
		
	}
	
	
	
	public int insertFakeReport(int userNo, int propertyNo, String content) {
		Connection conn = getConnection();
		int result = FakeReportDao.getFakeReportDao().insertFakeReport(conn, userNo, propertyNo, content);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		return result;
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
}
