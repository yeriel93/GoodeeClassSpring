package com.user.model.service;

import java.sql.Connection;

import com.user.model.dao.UserDao;
import com.user.model.vo.User;
import static com.bangbang.common.JDBCTemplate.*;

public class UserService {
	//[BD] singleton 방식으로 만들었음. getUserService() 사용해서 호출할 것.
	private static UserService userService;
	private UserService() {}
	public static UserService getUserService() {
		if(userService == null) userService = new UserService();
		return userService;
	}
	
	//로그인
	public User loginUser(String userId,String userPw) {
		Connection conn=getConnection();
		User user=UserDao.getUserDao().loginUser(conn,userId,userPw);
		close(conn);
		return user;
	}
	
	//회원가입
	public int insertUser(User u) {
		Connection conn=getConnection();
		int result=UserDao.getUserDao().insertUser(conn, u);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	//아이디 중복확인
	public int checkId(String id) {
		Connection conn=getConnection();
		int result=UserDao.getUserDao().checkId(conn,id);
		close(conn);
		return result;
	}
	
	//아이디 찾기
	public String searchId(String userName,String userEmail,String userPhone) {
		Connection conn=getConnection();
		String result=UserDao.getUserDao().searchId(conn,userName,userEmail,userPhone);
		close(conn);
		return result;
	}
	
}
