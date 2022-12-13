package com.user.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.property.model.vo.Property;

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
}
