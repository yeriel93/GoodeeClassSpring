package com.bs.spring.board.model.service;

import java.util.List;
import java.util.Map;

import com.bs.spring.board.model.vo.Board;

public interface BoardService {
	
	List<Board> selectBoardList(Map<String, Integer> map);
	
	int selectBoardCount(); 
	
	Board selectBoardByNo(int boardNo);
	
	int insertBoard(Board b);
}
