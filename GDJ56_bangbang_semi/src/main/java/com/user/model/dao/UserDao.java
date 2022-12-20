package com.user.model.dao;

import static com.bangbang.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.property.model.vo.Property;
import com.user.model.vo.User;

public class UserDao {
	// [BD] singleton 방식으로 만들었음. getUser() 사용해서 호출할 것.
	private static UserDao userDao;
	private Properties sql = new Properties(); 
	private UserDao() {
		try {
			String path = Property.class.getResource("/sql/user/user_sql.properties").getPath();
			sql.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static UserDao getUserDao() {
		if(userDao == null) userDao = new UserDao();
		return userDao;
	}
	
	private User getUser(ResultSet rs) throws SQLException {
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
	
	//로그인
	public User loginUser(Connection conn,String userId, String userPw) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		User user=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("searchUser"));
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				user=getUser(rs);				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}	
		
		return user;
	}
	
	//회원가입
	public int insertUser(Connection conn, User u) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("insertUser"));
			pstmt.setString(1, u.getId());
			pstmt.setString(2, u.getPassword());
			pstmt.setString(3, u.getName());
			pstmt.setString(4, u.getEmail());
			pstmt.setString(5, u.getPhone());
			pstmt.setDate(6, u.getBirthday());
			result=pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	//아이디 중복확인
	public int checkId(Connection conn,String id) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("checkId"));
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()||id.equals("")) {
				result=0;
			}else {
				result=1;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	//이메일 중복확인
	public int checkEmail(Connection conn,String email) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("checkEmail"));
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			if(rs.next()||email.equals("")) {
				result=0;
			}else {
				result=1;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	//아이디 찾기
	public String searchId(Connection conn,String userName,String userEmail,String userPhone) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String userId=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("searchId"));
			pstmt.setString(1, userName);
			pstmt.setString(2, userEmail);
			pstmt.setString(3, userPhone);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				userId=rs.getString("ID");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return userId;
	}
	
	//비밀번호 찾기
	public int searchPw(Connection conn,String userId,String userEmail,String userPhone) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("searchPw"));
			pstmt.setString(1, userId);
			pstmt.setString(2, userEmail);
			pstmt.setString(3, userPhone);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt("USER_NO");
			}else {
				result=0;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}
	
	//임시비밀번호로 변경
	public int tempPassword(Connection conn, int result,String tempPw) {
		PreparedStatement pstmt=null;
		int chResult=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("tempPw"));
			pstmt.setString(1, tempPw);
			pstmt.setInt(2, result);
			chResult=pstmt.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return chResult;
	}
	
	
	
}
