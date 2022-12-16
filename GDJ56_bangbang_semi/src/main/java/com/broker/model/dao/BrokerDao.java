package com.broker.model.dao;

import static com.bangbang.common.JDBCTemplate.close;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.broker.model.vo.Broker;
import com.property.model.vo.Property;

public class BrokerDao {
	// [BD] singleton 방식으로 만들었음. getBrokerDao() 사용해서 호출할 것.
	private static BrokerDao brokerDao;
	private Properties sql = new Properties(); 
	private BrokerDao() {
		try {
			String path = Property.class.getResource("/sql/broker/broker_sql.properties").getPath();
			sql.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static BrokerDao getBrokerDao() {
		if(brokerDao == null) brokerDao = new BrokerDao();
		return brokerDao;
	}
	
	private Broker getRsData (ResultSet rs) {
		Broker b = null;
		try {
			b = Broker.builder()
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	

	public Broker searchBroker(Connection conn,int brokerNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Broker b = null;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("searchBroker"));
//			SELECT * FROM BROKER WHERE BROKER_NO =?
			pstmt.setInt(1, brokerNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) b = getRsData(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return b;
	}
	
	public Broker loginBroker(Connection conn,int userNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Broker broker=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("loginBroker"));
			pstmt.setInt(1, userNo);
			rs=pstmt.executeQuery();
			
			if(rs.next()) broker=getRsData(rs);			
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return broker;
	}
	
	public int enrollBroker(Connection conn,Broker b) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("insertBroker"));
			//USER_NO,REGISTRATION_NO,OFFICE_NAME,OFFICE_ADDRESS
			pstmt.setInt(1, b.getUserNo());
			pstmt.setString(2, b.getRegistrationNo());
			pstmt.setString(3, b.getOfficeName());
			pstmt.setString(4, b.getOfficeAddress());
			pstmt.setString(5, b.getTelephone());
			
			result=pstmt.executeUpdate();			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		return result;
		
	}
	
	
	
}
