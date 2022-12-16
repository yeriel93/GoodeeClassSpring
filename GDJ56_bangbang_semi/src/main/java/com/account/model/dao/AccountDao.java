package com.account.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.broker.model.dao.BrokerDao;
import com.property.model.vo.Property;

public class AccountDao {
	private static AccountDao accountDao;
	private Properties sql = new Properties(); 
	private AccountDao() {
		try {
			String path = Property.class.getResource("/sql/account/account_sql.properties").getPath();
			sql.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static AccountDao getAccountDao() {
		if(accountDao == null) accountDao = new AccountDao();
		return accountDao;
	}
	
	
}
