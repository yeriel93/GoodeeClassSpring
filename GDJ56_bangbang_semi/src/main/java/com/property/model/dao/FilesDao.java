package com.property.model.dao;

import static com.bangbang.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.property.model.vo.Files;
import com.property.model.vo.Property;


public class FilesDao {
	private static FilesDao filesDao;
	private Properties sql = new Properties();
	private FilesDao() {
		try {
			String path = Property.class.getResource("/sql/files/files_sql.properties").getPath();
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
	public List<Files> searchFileNames(Connection conn,int propertyNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Files> result = new ArrayList<Files>();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("searchFileNames"));
//			SELECT * FROM FILES WHERE PROPERTY_NO=? ORDER BY FILES_NO ASC
			pstmt.setInt(1, propertyNo);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Files files = new Files();
				files.setFilesNo(rs.getInt("FILES_NO"));
				files.setPropertyNo(rs.getInt("PROPERTY_NO"));
				files.setRenameFilename(rs.getString("RENAMED_FILENAME"));
				files.setThumbnail((rs.getString("THUMBNAIL")).charAt(0));
				result.add(files);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	//선택한 매물의 파일들 이름 불러오기 
	public Map callFileNames(Connection conn,String propertyNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map map = new HashMap();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("callFileNames")+propertyNo);
//			SELECT PROPERTY_NO,LISTAGG(RENAMED_FILENAME, ',') WITHIN GROUP(ORDER BY PROPERTY_NO ASC) AS RENAMED_FILENAME_PARSING FROM FILES GROUP BY PROPERTY_NO HAVING PROPERTY_NO IN 
			
//			System.out.println(sql.getProperty("callFileNames")+propertyNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				map.put(rs.getInt("PROPERTY_NO"), rs.getString("RENAMED_FILENAME_PARSING"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
}
