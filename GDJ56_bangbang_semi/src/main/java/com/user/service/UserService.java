package com.user.service;

public class UserService {
	//[BD] singleton 방식으로 만들었음. getUserService() 사용해서 호출할 것.
	private static UserService userService;
	private UserService() {}
	public static UserService getUserService() {
		if(userService == null) userService = new UserService();
		return userService;
	}
}
