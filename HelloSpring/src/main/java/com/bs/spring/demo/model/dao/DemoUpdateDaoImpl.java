package com.bs.spring.demo.model.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bs.spring.demo.model.vo.Demo;

@Repository
public class DemoUpdateDaoImpl implements DemoUpdateDao {

	@Override
	public Demo selectDemo(SqlSession session, int no) {
		return session.selectOne("demoUpdate.selectDemo", no);
	}
	
	
	@Override
	public int updateDemo(SqlSession session, Demo demo) {
		return session.update("demoUpdate.updateDemo", demo);
	}


}
