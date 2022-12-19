package com.admin.model.service;

import static com.bangbang.common.JDBCTemplate.close;
import static com.bangbang.common.JDBCTemplate.commit;
import static com.bangbang.common.JDBCTemplate.getConnection;
import static com.bangbang.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.admin.model.dao.AdminDao;

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
	
	//중개사 승인 기능
	public int updateBrokerAdmissionToY(int brokerNo, String setAdmission) {
		Connection conn = getConnection();
		int result = AdminDao.getAdminDao().updateBrokerAdmissionToY(conn, brokerNo, setAdmission);
		close(conn);
		
		if(result>0)commit(conn);
		else rollback(conn);
		
		return result;
	}
	
	//매물 리스트, 매물당누적신고 출력
	public List searchPropertyList(String adminQuery) {
		Connection conn = getConnection();
		List list = AdminDao.getAdminDao().searchPropertyList(conn, adminQuery);
		close(conn);
		
		return list;
	}
	
	public int searchPropertyListCount(String totalQuery) {
		Connection conn = getConnection();
		int count = AdminDao.getAdminDao().searchPropertyListCount(conn, totalQuery);
		close(conn);
		
		return count;
	}
	
	//매물 숨김, 공개 기능
	//중개사 승인 기능
	public int updatePropertyHiding(int propertyNo, String setHiding) {
		Connection conn = getConnection();
		int result = AdminDao.getAdminDao().updatePropertyHiding(conn, propertyNo, setHiding);
		close(conn);
		
		if(result>0)commit(conn);
		else rollback(conn);
		
		return result;
	}
	
	//허위매물 조회
	public List searchReportList(String adminQuery) {
		Connection conn = getConnection();
		List list = AdminDao.getAdminDao().searchReportList(conn, adminQuery);
		close(conn);
		
		return list;
	}
	
	//매물당 신고횟수 조회
	public Map<Integer, Integer> searchPropertyReportCount(){
		Connection conn = getConnection();
		Map<Integer, Integer> propertyReportCount = AdminDao.getAdminDao().searchPropertyReportCount(conn);
		close(conn);
		
		return propertyReportCount;
	}
	
	//중개사 누적신고횟수 조회
	public Map<Integer, Integer> searchBrokerReportCount(){
		Connection conn = getConnection();
		Map<Integer, Integer> brokerReportCount = AdminDao.getAdminDao().searchBrokerReportCount(conn);
		close(conn);
		
		return brokerReportCount;
	}
	
	//페이지바용 카운트
	public int searchReportListCount(String totalQuery) {
		Connection conn = getConnection();
		int count = AdminDao.getAdminDao().searchReportListCount(conn, totalQuery);
		close(conn);
		
		return count;
	}
	
	//신고 처리(PROCESSING_DATE NULL to SYSDATE)
	public int updateProcessingDate(int userNo, int propertyNo) {
		Connection conn = getConnection();
		int result = AdminDao.getAdminDao().updateProcessingDate(conn, userNo, propertyNo);
		close(conn);
		
		if(result>0)commit(conn);
		else rollback(conn);
		
		return result;
	}
	
	//신고삭제
	public int deleteReport(String adminQuery) {
		Connection conn = getConnection();
		int result = AdminDao.getAdminDao().deleteReport(conn, adminQuery);
		close(conn);
		
		if(result>0)commit(conn);
		else rollback(conn);
		
		return result;
	}
	
}
