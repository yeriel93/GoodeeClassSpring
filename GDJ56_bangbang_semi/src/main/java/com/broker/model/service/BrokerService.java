package com.broker.model.service;

import static com.bangbang.common.JDBCTemplate.close;
import static com.bangbang.common.JDBCTemplate.getConnection;

import java.sql.Connection;


public class BrokerService {
	//[BD] singleton 방식으로 만들었음. getUserService() 사용해서 호출할 것.
	private static BrokerService brokerService;
	private BrokerService() {}
	public static BrokerService getBrokerService() {
		if(brokerService == null) brokerService = new BrokerService();
		return brokerService;
	}
		

}
