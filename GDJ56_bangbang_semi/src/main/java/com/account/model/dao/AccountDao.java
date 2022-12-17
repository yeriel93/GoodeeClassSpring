package com.account.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.property.model.vo.Property;
import com.user.model.vo.User;

import static com.bangbang.common.JDBCTemplate.*;

public class AccountDao {
	private static AccountDao accountDao;
	private Properties sql = new Properties(); 
	private AccountDao() {
		try {
			String path = Property.class.getResource("/sql/account/account_sql.properties").getPath();
			sql.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static AccountDao getAccountDao() {
		if(accountDao == null) accountDao = new AccountDao();
		return accountDao;
	}
	
	public int updateUser(Connection conn,User u) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("updateUser"));
			pstmt.setString(1, u.getName());
			pstmt.setString(2, u.getPhone());
			pstmt.setDate(3, u.getBirthday());
			pstmt.setInt(4, u.getUserNo());
			result=pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int updatePw(Connection conn,String userId,String afterPw) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("updatePw"));
			//updatePw=UPDATE USER_C SET PASSWORD=? WHERE ID=?
			pstmt.setString(1, afterPw);
			pstmt.setString(2, userId);
			result=pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	}
	
}
