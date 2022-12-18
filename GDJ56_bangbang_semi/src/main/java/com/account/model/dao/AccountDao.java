package com.account.model.dao;

import static com.bangbang.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.account.model.vo.Alert;
import com.property.model.vo.Property;
import com.user.model.vo.User;

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
	
	public int sendMessage(Connection conn,Alert a) {
		PreparedStatement pstmt=null;
		int result=0;
		String content="문의합니다.";
		try {
			pstmt=conn.prepareStatement(sql.getProperty("sendMessage"));
			//sendMessage=INSERT INTO ALERT VALUES(SEQ_ALERT_NO.NEXTVAL,?,?,?,?,DEFAULT)
			//보내는유저번호, 받는 유저번호, 매물번호, 내용
			pstmt.setInt(1, a.getSendUserNo());
			pstmt.setInt(2, a.getReceiveUserNo());
			pstmt.setInt(3, a.getPropertyNo());
			pstmt.setString(4, content);
			result=pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
}
