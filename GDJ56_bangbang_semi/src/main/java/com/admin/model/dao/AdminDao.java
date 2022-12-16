package com.admin.model.dao;

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

import com.broker.model.vo.Broker;
import com.property.model.vo.Property;
import com.user.model.vo.User;

public class AdminDao {
	// [BD] singleton 방식으로 만들었음. getUser() 사용해서 호출할 것.
	private static AdminDao adminDao;
	private Properties sql = new Properties(); 
	private AdminDao() {
		try {
			String path = Property.class.getResource("/sql/admin/admin_sql.properties").getPath();
			sql.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static AdminDao getAdminDao() {
		if(adminDao == null) adminDao = new AdminDao();
		return adminDao;
	}
	
	public List searchUserList(Connection conn, String adminQuery){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List list = null;
		List<User> userList = null;
		List<Integer> brokerNoList = null;
		String query = sql.getProperty("searchUserListAdmin");
		try {
			pstmt = conn.prepareStatement(query + adminQuery);
			rs = pstmt.executeQuery();
			list = new ArrayList();
			userList = new ArrayList();
			brokerNoList = new ArrayList();
			while(rs.next()) {
				User u = getRsUserData(rs);
				userList.add(u);
				int brokerNo = rs.getInt("BROKER_NO");
				brokerNoList.add(brokerNo);
			}
			list.add(userList);
			list.add(brokerNoList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	public int searchUserListCount(Connection conn, String totalQuery) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String query = sql.getProperty("searchUserListCount");
		try {
			pstmt = conn.prepareStatement(query + totalQuery);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return count;
	}
	
	public List searchBrokerList(Connection conn, String adminQuery) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List list = null;
		List<Broker> brokerList = null;
		List<String> userIdList = null;
		String query = sql.getProperty("searchBrokerListAdmin");
		//System.out.println(query + adminQuery);
		try {
			pstmt = conn.prepareStatement(query + adminQuery);
			rs = pstmt.executeQuery();
			list = new ArrayList();
			brokerList = new ArrayList();
			userIdList = new ArrayList();
			while(rs.next()) {
				Broker b = getRsBrokerData(rs);
				brokerList.add(b);
				String id = rs.getString("ID");
				userIdList.add(id);
				//System.out.println(id + " / " + b);
			}
			list.add(brokerList);
			list.add(userIdList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}	
		return list;
	}
	
	public int searchBrokerListCount(Connection conn, String totalQuery) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String query = sql.getProperty("searchBrokerListCount");
		try {
			pstmt = conn.prepareStatement(query + totalQuery);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return count;
	}
	
	public int updateBrokerAdmissionToY(Connection conn, int brokerNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = sql.getProperty("updateBrokerAdmissionToY");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, brokerNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	private User getRsUserData(ResultSet rs) throws SQLException {
		return User.builder()
				.userNo(rs.getInt("USER_NO"))
				.id(rs.getString("ID"))
				.password(rs.getString("PASSWORD"))
				.name(rs.getString("NAME"))
				.email(rs.getString("EMAIL"))
				.phone(rs.getString("PHONE"))
				.birthday(rs.getDate("BIRTHDAY"))
				.userLevel(rs.getString("USER_LEVEL").charAt(0))
				.enrollDate(rs.getDate("ENROLL_DATE"))
				.editDate(rs.getDate("EDIT_DATE"))
				.build();
	}
	
	private Broker getRsBrokerData(ResultSet rs) throws SQLException {
		return Broker.builder()
				.brokerNo(rs.getInt("BROKER_NO"))
				.userNo(rs.getInt("USER_NO"))
				.registrationNo(rs.getString("REGISTRATION_NO"))
				.officeName(rs.getString("OFFICE_NAME"))
				.officeAddress(rs.getString("OFFICE_ADDRESS"))
				.telephone(rs.getString("TELEPHONE"))
				.admissionState(rs.getString("ADMISSION_STATE").charAt(0))
				.propertyCount(rs.getInt("PROPERTY_COUNT"))
				.RestrictionDate(rs.getDate("RESTRICTION_DATE"))
				.enrollDate(rs.getDate("ENROLL_DATE"))
				.editDate(rs.getDate("EDIT_DATE"))
				.build();
	}
	
}
