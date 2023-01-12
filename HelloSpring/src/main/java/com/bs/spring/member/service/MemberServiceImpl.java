package com.bs.spring.member.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.member.dao.MemberDao;
import com.bs.spring.member.vo.Member;

@Service
public class MemberServiceImpl implements MemberService{
	//매개변수있는 생성자 단축키 alt+shift+s+o

	private MemberDao dao;
	private SqlSessionTemplate session;
	
	@Autowired
	public MemberServiceImpl(MemberDao dao, SqlSessionTemplate session) {
		this.dao = dao;
		this.session = session;
	}
	
	@Override
	public void test() {
		System.out.println("service - test() 실행");
		dao.test();
	}

	@Override
	public Member selectMemberById(Member m) {
		return dao.selectMemberById(session, m);
	}

	@Override
	public int insertMember(Member m) {
		return dao.insertMember(session, m);
	}

	@Override
	public List<Member> selectMemberAll() {
		return dao.selectMemberAll(session);
	}
	
	

}
