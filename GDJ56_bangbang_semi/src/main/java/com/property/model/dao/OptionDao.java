package com.property.model.dao;

import static com.bangbang.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.property.model.vo.Property;

public class OptionDao {
	private static OptionDao optionDao;
	private Properties sql = new Properties();
	private OptionDao() {
		try {
			String path = Property.class.getResource("/sql/property/option_sql.properties").getPath();
			sql.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static OptionDao getOptionDao() {
		if(optionDao == null) optionDao = new OptionDao();
		return optionDao;
	}
	
	//옵션 insert
	public int insertOption(Connection conn, int propertyNo,String option) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("insertOption"));
//			INSERT INTO APPLIANCE_OPTION VALUES(?, ?)
			pstmt.setInt(1, propertyNo);
			pstmt.setInt(2, Integer.parseInt(option));
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	//옵션 불러오기
	public String searchOption(Connection conn,int propertyNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String option = "";
		try {
			pstmt = conn.prepareStatement(sql.getProperty("searchOption"));
//			SELECT T.PROPERTY_NO, F.APPLIANCE_NO FROM PROPERTY T JOIN (SELECT P.PROPERTY_NO , LISTAGG(A.APPLIANCE_NO,',') WITHIN GROUP(ORDER BY A.APPLIANCE_NO) AS APPLIANCE_NO FROM PROPERTY P JOIN APPLIANCE_OPTION A ON P.PROPERTY_NO = A.PROPERTY_NO GROUP BY P.PROPERTY_NO) F ON T.PROPERTY_NO = F.PROPERTY_NO WHERE T.PROPERTY_NO = ?
			pstmt.setInt(1, propertyNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				option = rs.getString("APPLIANCE_NO");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return option;
	}
}
