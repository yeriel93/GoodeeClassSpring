package com.property.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.property.model.vo.Property;


public class FilesDao {
	private static FilesDao filesDao;
	private Properties sql = new Properties();
	private FilesDao() {
		try {
			String path = Property.class.getResource("/sql/files/files_sql.properties").getPath();
			sql.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static FilesDao getFilesDao() {
		if(filesDao == null) filesDao = new FilesDao();
		return filesDao;
	}
}
