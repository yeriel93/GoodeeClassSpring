package com.account.model.dao;

import static com.bangbang.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.account.model.vo.Alert;
import com.account.model.vo.AlertList;
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
	
	public int userAlertCount(Connection conn,int userNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("userAlertCount"));
			pstmt.setInt(1, userNo);
			rs=pstmt.executeQuery();
			if(rs.next()) count=rs.getInt(1);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return count;
	}
	
	public List<AlertList> searchUserAlert(Connection conn,int cPage,int numPerpage,int userNo){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<AlertList> list=new ArrayList();
		
		try {
			pstmt=conn.prepareStatement(sql.getProperty("searchUserAlert"));
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, (cPage-1)*numPerpage+1);
			pstmt.setInt(3, cPage*numPerpage);
			
			rs=pstmt.executeQuery();
			AlertList a=new AlertList();
			while(rs.next()) {
				a=getAlertList(rs);
				list.add(a);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
		
	}
	
	public static Alert getAlert(ResultSet rs) throws SQLException{
		return Alert.builder()
				.alertNo(rs.getInt("ALERT_NO"))
				.sendUserNo(rs.getInt("SEND_USER_NO"))
				.receiveUserNo(rs.getInt("RECEIVE_USER_NO"))
				.propertyNo(rs.getInt("PROPERTY_NO"))
				.content(rs.getString("CONTENT"))
				.alertDate(rs.getDate("ALERT_DATE"))
				.build();
	}
	
	public static AlertList getAlertList(ResultSet rs) throws SQLException{
		return AlertList.builder()
				.alertNo(rs.getInt("ALERT_NO"))
				.sendUserNo(rs.getInt("SEND_USER_NO"))
				.receiveUserNo(rs.getInt("RECEIVE_USER_NO"))
				.propertyNo(rs.getInt("PROPERTY_NO"))
				.content(rs.getString("CONTENT"))
				.alertDate(rs.getDate("ALERT_DATE"))
				.name(rs.getString("NAME"))
				.phone(rs.getString("PHONE"))
				.renttype(rs.getString("RENTTYPE"))
				.deposit(rs.getInt("DEPOSIT"))
				.monthlycharge(rs.getInt("MONTHLY_CHARGE"))
				.renamedFilename(rs.getString("RENAMED_FILENAME"))
				.build();
	}
	
}
