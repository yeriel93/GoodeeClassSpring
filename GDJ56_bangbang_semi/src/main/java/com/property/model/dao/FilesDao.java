package com.property.model.dao;

import static com.bangbang.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.property.model.vo.Files;
import com.property.model.vo.Property;


public class FilesDao {
	private static FilesDao filesDao;
	private Properties sql = new Properties();
	private FilesDao() {
		try {
			String path = Property.class.getResource("/sql/property/files_sql.properties").getPath();
			sql.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static FilesDao getFilesDao() {
		if(filesDao == null) filesDao = new FilesDao();
		return filesDao;
	}
	
	//파일등록하기
	public int insertFiles(Connection conn, int propertyNo, Files f) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("insertFiles"));
//			INSERT INTO FILES VALUES(SEQ_FILES_NO.NEXTVAL, ?, ?, ?)
			pstmt.setInt(1, propertyNo);
			pstmt.setString(2, f.getRenameFilename());
			pstmt.setString(3, String.valueOf(f.getThumbnail()));
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	//매물번호로 등록된 파일 불러오기
	public Files[] searchFileNames(Connection conn,int propertyNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Files[] result = null;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("searchFileNames"));
//			SELECT RENAMED_FILENAME FROM FILES WHERE PROPERTY_NO=? ORDER BY THUMBNAIL DESC
			pstmt.setInt(1, propertyNo);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				rs.getString("RENAMED_FILENAME");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
}
