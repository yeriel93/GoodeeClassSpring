package com.property.model.dao;

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

import com.property.model.vo.Property;

public class PropertyDao {
	// [BD] singleton 방식으로 만들었음. getPropertyDao() 사용해서 호출할 것.
	private static PropertyDao propertyDao;
	private Properties sql = new Properties();
	private PropertyDao() {
		try {
			String path = Property.class.getResource("/sql/property/property_sql.properties").getPath();
			sql.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static PropertyDao getPropertyDao() {
		if(propertyDao == null) propertyDao = new PropertyDao();
		return propertyDao;
	}
	
	public List<Property> searchProperty(Connection conn, String propertyQuery){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Property> list = null;
		String query = sql.getProperty("searchProperty") + propertyQuery;
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			list = new ArrayList();
			while(rs.next()) {
				Property p = getRsData(rs);
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	
	//중개사 방내놓기 (Property테이블) 
	public int insertProperty(Connection conn, Property p) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("insertProperty"));
//			INSERT INTO PROPERTY VALUES(SEQ_PROPERTY_NO.NEXTVAL, 200000, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, DEFAULT, NULL, 'N');
			pstmt.setString(1, p.getAddress());
			pstmt.setDouble(2, p.getLongitude());
			pstmt.setDouble(3, p.getLatitude());
			pstmt.setString(4, p.getFloor());
			pstmt.setString(5, p.getRenttype());
			pstmt.setInt(6, p.getDeposit());
			pstmt.setInt(7, p.getMonthlyCharge());
			pstmt.setInt(8, p.getManagementCharge());
			pstmt.setString(9, String.valueOf(p.getElectric()));
			pstmt.setString(10, String.valueOf(p.getWater()));
			pstmt.setString(11, String.valueOf(p.getGas()));
			pstmt.setString(12, p.getPropertyStructure());
			pstmt.setDouble(13,	p.getArea());
			pstmt.setDate(14, p.getVacancyDate()==null ? null : p.getVacancyDate());
			pstmt.setString(15, p.getVacancy());
			pstmt.setString(16, String.valueOf(p.getPet()));
			pstmt.setString(17, String.valueOf(p.getParking()));
			pstmt.setString(18, p.getDetail());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	private Property getRsData(ResultSet rs) {
		Property p = null;
		try {
			p = Property.builder()
				.propertyNo(rs.getInt("PROPERTY_NO"))
				.brokerNo(rs.getInt("BROKER_NO"))
				.address(rs.getString("ADDRESS"))
				.longitude(rs.getDouble("LONGITUDE"))
				.latitude(rs.getDouble("LATITUDE"))
				.floor(rs.getString("FLOOR"))
				.renttype(rs.getString("RENTTYPE"))
				.deposit(rs.getInt("DEPOSIT"))
				.monthlyCharge(rs.getInt("MONTHLY_CHARGE"))
				.managementCharge(rs.getInt("MANAGEMENT_CHARGE"))
				.electric(rs.getString("ELETRIC").charAt(0))
				.water(rs.getString("WATER").charAt(0))
				.gas(rs.getString("GAS").charAt(0))
				.propertyStructure(rs.getString("PROPERTY_STRUCTURE"))
				.area(rs.getDouble("AREA"))
				.vacancyDate(rs.getDate("VACANCY_DATE"))
				.vacancy(rs.getString("VACANCY"))
				.pet(rs.getString("PET").charAt(0))
				.parking(rs.getString("PARKING").charAt(0))
				.detail(rs.getString("DETAIL"))
				.enrollDate(rs.getDate("ENROLL_DATE"))
				.editDate(rs.getDate("EDIT_DATE"))
				.hiding(rs.getString("HIDING").charAt(0))
				.thumbnail(rs.getString("RENAMED_FILENAME"))
				.build();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return p;
	}
	
	
	
	
	
}
