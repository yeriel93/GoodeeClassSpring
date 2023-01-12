package com.bs.spring.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.member.vo.Member;

@Repository
public class MemberDaoImpl implements MemberDao{
	
	@Override
	public void test() {
		System.out.println("dao - Test() ½ÇÇà");
	}

	@Override
	public Member selectMemberById(SqlSessionTemplate session, Member m) {
		return session.selectOne("member.selectMemberById", m);
	}

	@Override
	public int insertMember(SqlSessionTemplate session, Member m) {
		return session.insert("member.insertMember", m);
	}

	@Override
	public List<Member> selectMemberAll(SqlSessionTemplate session) {
		return session.selectList("member.selectMemberAll");
	}
}
