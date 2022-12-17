package com.fakereport.dao;


import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import static com.bangbang.common.JDBCTemplate.close;

public class FakeReportDao {
	private static FakeReportDao fakeReportDao;
	private Properties sql = new Properties();
	private FakeReportDao() {
		try {		
			String path = FakeReportDao.class.getResource("/sql/fakereport/fakereport_sql.properties").getPath();
			sql.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static FakeReportDao getFakeReportDao() {
		if(fakeReportDao == null) fakeReportDao = new FakeReportDao();
		return fakeReportDao;
	}
	
	
	public int insertFakeReport(Connection conn, int userNo, int propertyNo, String content) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("insertFakeReport"));
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, propertyNo);
			pstmt.setString(3, content);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
