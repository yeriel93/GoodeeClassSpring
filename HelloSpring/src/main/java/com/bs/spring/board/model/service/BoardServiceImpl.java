package com.bs.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.board.model.dao.BoardDao;
import com.bs.spring.board.model.vo.Board;

@Service
public class BoardServiceImpl implements BoardService {

	private BoardDao dao;
	private SqlSession session;
	
	@Autowired
	public BoardServiceImpl(BoardDao dao, SqlSession session) {
		this.dao = dao;
		this.session = session;
	}

	@Override
	public List<Board> selectBoardList(Map<String, Integer> map) {
		return dao.selectBoardList(session, map);
	}

	@Override
	public int selectBoardCount() {
		return dao.selectBoardCount(session);
	}

	@Override
	public Board selectBoardByNo(int boardNo) {
		return dao.selectBoardByNo(session, boardNo);
	}
	
	
}
