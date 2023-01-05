package com.bs.spring.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.member.service.MemberService;

@Controller
public class MemberController {
	//의존하는 모든 클래스를 적어줘야함
	@Autowired
	public MemberService service;
	
	@RequestMapping("/test/")
	public void test() {
		System.out.println("controller - test() 실행");
		service.test();
	}
}
