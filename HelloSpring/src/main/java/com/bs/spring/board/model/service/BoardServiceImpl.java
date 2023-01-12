package com.bs.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bs.spring.board.model.dao.BoardDao;
import com.bs.spring.board.model.vo.Attachment;
import com.bs.spring.board.model.vo.Board;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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
	
	@Override
//	@Transactional
	public int insertBoard(Board b) {
		//1. 게시글을 등록하고 
		//2. 첨부파일을 등록 
		
		int boardNo = 0;
		log.debug("insert 전 : "+b.getBoardNo());

		int result = dao.insertBoard(session, b);
//		if(result>0) boardNo = dao.selectBoardSeq(session);
//		b.setBoardNo(boardNo);
		log.debug("insert 후 : "+b.getBoardNo());
		
		if(result>0) {
			for(Attachment a : b.getFiles()) {
				a.setBoardNo(b);
				dao.insertAttachment(session, a);
			}
		}

		return result;
	}
	
}
