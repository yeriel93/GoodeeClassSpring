package com.user.service;

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
	
	public User searchUser(String userId,String userPw) {
		Connection conn=getConnection();
		User user=UserDao.getUserDao().searchUser(conn,userId,userPw);
		close(conn);
		return user;
	}
}
