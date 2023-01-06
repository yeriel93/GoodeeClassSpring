package com.bs.spring.member.service;

import java.util.Map;

import com.bs.spring.member.vo.Member;

public interface MemberService {
	void test();
	
	Member selectMemberById(Member m);
	
	int insertMember(Member m);
}
