package com.bs.spring.demo.model.service;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.demo.model.vo.Demo;

public interface DemoUpdateService {
	
	Demo selectDemo(int no);
	
	int updateDemo(Demo demo);
	
}
