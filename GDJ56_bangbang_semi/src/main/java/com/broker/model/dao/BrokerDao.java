package com.broker.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.property.model.vo.Property;

public class BrokerDao {
	// [BD] singleton 방식으로 만들었음. getBrokerDao() 사용해서 호출할 것.
	private static BrokerDao brokerDao;
	private Properties sql = new Properties(); 
	private BrokerDao() {
		try {
			String path = Property.class.getResource("/sql/broker/broker_sql.properties").getPath();
			sql.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static BrokerDao getBrokerDao() {
		if(brokerDao == null) brokerDao = new BrokerDao();
		return brokerDao;
	}
}
