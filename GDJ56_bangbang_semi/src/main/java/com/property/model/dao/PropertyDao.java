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
	
	public List<Property> searchMapProperty(Connection conn, String propertyQuery){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Property> list = null;
		String query = sql.getProperty("searchMapPropertyAll") + propertyQuery;
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
//			INSERT INTO PROPERTY VALUES(SEQ_PROPERTY_NO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, DEFAULT, NULL, 'N')
			pstmt.setInt(1, p.getBrokerNo());
			pstmt.setString(2, p.getAddress());
			pstmt.setDouble(3, p.getLongitude());
			pstmt.setDouble(4, p.getLatitude());
			pstmt.setString(5, p.getFloor());
			pstmt.setString(6, p.getRenttype());
			pstmt.setInt(7, p.getDeposit());
			pstmt.setInt(8, p.getMonthlyCharge());
			pstmt.setInt(9, p.getManagementCharge());
			pstmt.setString(10, String.valueOf(p.getElectric()));
			pstmt.setString(11, String.valueOf(p.getWater()));
			pstmt.setString(12, String.valueOf(p.getGas()));
			pstmt.setString(13, p.getPropertyStructure());
			pstmt.setDouble(14,	p.getArea());
			pstmt.setDate(15, p.getVacancyDate()==null ? null : p.getVacancyDate());
			pstmt.setString(16, p.getVacancy()==null ? null : p.getVacancy());
			pstmt.setString(17, String.valueOf(p.getPet()));
			pstmt.setString(18, String.valueOf(p.getParking()));
			pstmt.setString(19, p.getDetail());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	//현재 매물번호 조회
	public int searchPropertyNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int proNo = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("searchPropertyNo"));
//			SELECT SEQ_PROPERTY_NO.CURRVAL FROM DUAL
			rs = pstmt.executeQuery();
			if(rs.next()) proNo = rs.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return proNo;
	}

	public List<Property> searchMapPropertyList(Connection conn, String propertyQuery){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Property> list = null;
		String query = sql.getProperty("searchMapPropertyList") + propertyQuery;
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
	
	
	//매물상세보기(매물내용가져오기)
	public Property searchPropertyInfo(Connection conn,int propertyNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Property result = new Property();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("searchPropertyInfo"));
//			SELECT * FROM PROPERTY WHERE PROPERTY_NO=?
			pstmt.setInt(1, propertyNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = getRsDataYJ(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	private Property getRsDataYJ(ResultSet rs) {
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
				.build();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	
	//매물번호로 매물수정
	public int updateProperty(Connection conn, Property p) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("updateProperty"));
//			UPDATE PROPERTY SET RENTTYPE=?, DEPOSIT=?, MONTHLY_CHARGE=?, MANAGEMENT_CHARGE=?, ELETRIC=?, WATER=?, GAS=?, AREA=?, 
//								VACANCY_DATE=?, VACANCY=?, PET=?, PARKING=?, DETAIL=?, EDIT_DATE=SYSDATE WHERE PROPERTY_NO = ?
			pstmt.setString(1, p.getRenttype());
			pstmt.setInt(2, p.getDeposit());
			pstmt.setInt(3, p.getMonthlyCharge());
			pstmt.setInt(4, p.getManagementCharge());
			pstmt.setString(5, String.valueOf(p.getElectric()));
			pstmt.setString(6, String.valueOf(p.getWater()));
			pstmt.setString(7, String.valueOf(p.getGas()));
			pstmt.setDouble(8, p.getArea());
			pstmt.setDate(9, p.getVacancyDate()==null ? null : p.getVacancyDate());
			pstmt.setString(10, p.getVacancy()==null ? null : p.getVacancy());
			pstmt.setString(11, String.valueOf(p.getPet()));
			pstmt.setString(12, String.valueOf(p.getParking()));
			pstmt.setString(13, p.getDetail());
			pstmt.setInt(14, p.getPropertyNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	//브로커번호로 등록된 매물리스트(+썸네일)
	public List<Property> brokerPropertyList(Connection conn,int borkerNo){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Property> propertys = new ArrayList<Property>();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("brokerPropertyList"));
//			SELECT * FROM PROPERTY P JOIN FILES F ON P.PROPERTY_NO = F.PROPERTY_NO WHERE THUMBNAIL='Y' AND BROKER_NO = ?
			pstmt.setInt(1, borkerNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Property p = Property.builder()
							.propertyNo(rs.getInt("PROPERTY_NO"))
							.renttype(rs.getString("RENTTYPE"))
							.deposit(rs.getInt("DEPOSIT"))
							.monthlyCharge(rs.getInt("MONTHLY_CHARGE"))
							.managementCharge(rs.getInt("MANAGEMENT_CHARGE"))
							.address(rs.getString("ADDRESS"))
							.propertyStructure(rs.getString("PROPERTY_STRUCTURE"))
							.thumbnail(rs.getString("RENAMED_FILENAME"))
							.build();
				
				propertys.add(p);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return propertys;
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
