package com.admin.model.service;

import static com.bangbang.common.JDBCTemplate.close;
import static com.bangbang.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;

import com.admin.model.dao.AdminDao;
import com.user.model.vo.User;

public class AdminService {
	//[BD] singleton 방식으로 만들었음. getUserService() 사용해서 호출할 것.
	private static AdminService adminService;
	private AdminService() {}
	public static AdminService getAdminService() {
		if(adminService == null) adminService = new AdminService();
		return adminService;
	}
	
	public List searchUserList(String adminQuery){
		Connection conn = getConnection();
		List list = AdminDao.getAdminDao().searchUserList(conn, adminQuery);
		close(conn);
		
		return list;
	}
	
	public int searchUserListCount(String totalQuery){
		Connection conn = getConnection();
		int count = AdminDao.getAdminDao().searchUserListCount(conn, totalQuery);
		close(conn);
		
		return count;
	}
	
	
	
	//중개사 관리 페이지
	public List searchBrokerList(String adminQuery) {
		Connection conn = getConnection();
		List list = AdminDao.getAdminDao().searchBrokerList(conn, adminQuery);
		close(conn);
		
		return list;
	}
	
	
	public int searchBrokerListCount(String totalQuery) {
		Connection conn = getConnection();
		int count = AdminDao.getAdminDao().searchBrokerListCount(conn, totalQuery);
		close(conn);
		
		return count;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
