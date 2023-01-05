package com.bs.spring.demo.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.bs.spring.demo.model.vo.Demo;


public interface DemoUpdateDao {
	
	
	Demo selectDemo(SqlSession session, int no);
	
	int updateDemo(SqlSession session, Demo demo);
}
