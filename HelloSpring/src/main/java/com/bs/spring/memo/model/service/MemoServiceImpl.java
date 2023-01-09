package com.bs.spring.memo.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.memo.model.dao.MemoDao;
import com.bs.spring.memo.model.vo.Memo;

@Service
public class MemoServiceImpl implements MemoService  {
	
	private SqlSessionTemplate session;
	private MemoDao dao;
	
	@Autowired
	public MemoServiceImpl(SqlSessionTemplate session, MemoDao dao) {
		this.session = session;
		this.dao = dao;
	}
	
	
	@Override
	public int insertMemo(Memo m) {
		return dao.insertMemo(session, m);
	}


	@Override
	public List<Memo> selectMemoAll() {
		return dao.selectMemoAll(session);
	}


	@Override
	public int selectMemoListCount() {
		return dao.selectMemoListCount(session);
	}

}
