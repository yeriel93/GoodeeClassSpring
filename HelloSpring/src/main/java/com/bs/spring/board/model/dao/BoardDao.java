package com.bs.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bs.spring.board.model.vo.Attachment;
import com.bs.spring.board.model.vo.Board;

public interface BoardDao {
	
	List<Board> selectBoardList(SqlSession session,Map<String, Integer> map);
	
	int selectBoardCount(SqlSession session);
	
	Board selectBoardByNo(SqlSession session,int boardNo);
	
	int insertBoard(SqlSession session, Board b);
	
	int insertAttachment(SqlSession session, Attachment a);
}
