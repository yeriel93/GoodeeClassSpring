package com.property.model.dao;

import static com.bangbang.common.JDBCTemplate.close;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
