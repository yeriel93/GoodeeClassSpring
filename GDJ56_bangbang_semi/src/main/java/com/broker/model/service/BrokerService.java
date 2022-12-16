package com.broker.model.service;

import static com.bangbang.common.JDBCTemplate.*;

import java.sql.Connection;

import com.broker.model.dao.BrokerDao;
import com.broker.model.vo.Broker;

public class BrokerService {
	//[BD] singleton 방식으로 만들었음. getUserService() 사용해서 호출할 것.
	private static BrokerService brokerService;
	private BrokerService() {}
	public static BrokerService getBrokerService() {
		if(brokerService == null) brokerService = new BrokerService();
		return brokerService;
	}
	
	public Broker loginBroker(int userNo) {
		Connection conn=getConnection();
		Broker broker=BrokerDao.getBrokerDao().loginBroker(conn,userNo);
		close(conn);
		return broker;
	}

}
