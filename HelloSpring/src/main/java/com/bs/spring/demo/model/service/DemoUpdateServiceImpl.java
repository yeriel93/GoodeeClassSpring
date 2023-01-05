package com.bs.spring.demo.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.demo.model.dao.DemoUpdateDao;
import com.bs.spring.demo.model.vo.Demo;

@Service
public class DemoUpdateServiceImpl implements DemoUpdateService {

	private DemoUpdateDao dao;
	private SqlSessionTemplate session;
	
	@Autowired
	public DemoUpdateServiceImpl(DemoUpdateDao dao, SqlSessionTemplate session) {
		this.dao = dao;
		this.session = session;
	}
	
	@Override
	public Demo selectDemo(int no) {
		return dao.selectDemo(session, no);
	}
	
	@Override
	public int updateDemo(Demo demo) {
		return dao.updateDemo(session, demo);
	}


}
