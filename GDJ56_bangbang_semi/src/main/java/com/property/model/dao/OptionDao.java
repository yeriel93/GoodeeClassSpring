package com.property.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.property.model.vo.Property;

public class OptionDao {
	private static OptionDao optionDao;
	private Properties sql = new Properties();
	private OptionDao() {
		try {
			String path = Property.class.getResource("/sql/property/option_sql.properties").getPath();
			sql.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static OptionDao getOptionDao() {
		if(optionDao == null) optionDao = new OptionDao();
		return optionDao;
	}
}
