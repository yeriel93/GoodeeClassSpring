package com.bs.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bs.spring.board.model.vo.Board;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Override
	public List<Board> selectBoardList(SqlSession session, Map<String, Integer> map) {
		return session.selectList("board.selectBoardList", null, new RowBounds((map.get("cPage")-1) * map.get("numPerpage"), map.get("numPerpage")));
	}

	@Override
	public int selectBoardCount(SqlSession session) {
		return session.selectOne("board.selectBoardCount");
	}

	@Override
	public Board selectBoardByNo(SqlSession session, int boardNo) {
		return session.selectOne("board.selectBoardByNo", boardNo);
	}

}
