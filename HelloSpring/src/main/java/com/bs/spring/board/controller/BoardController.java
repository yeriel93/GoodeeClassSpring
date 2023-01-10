package com.bs.spring.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.board.model.service.BoardService;
import com.bs.spring.board.model.vo.Board;
import com.bs.spring.common.PageFactory;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private BoardService service;

	@Autowired
	public BoardController(BoardService service) {
		super();
		this.service = service;
	}
	
	
	@RequestMapping("/boardList.do")
	public ModelAndView boardList(ModelAndView mv, 
			@RequestParam (value="cPage", defaultValue = "1") int cPage, 
			@RequestParam (value = "numPerpage", defaultValue = "5") int numPerpage) {
		
		List<Board> list = service.selectBoardList(Map.of("cPage", cPage, "numPerpage", numPerpage));
		mv.addObject("boardlist", list);
		
		int totalData = service.selectBoardCount();
		mv.addObject("pageBar", PageFactory.getPage(cPage, numPerpage, totalData, "boardList.do"));
		mv.addObject("totalContents", totalData);
		
		mv.setViewName("board/boardList");
		return mv;
	}
	
	@RequestMapping("/boardView.do")
	public ModelAndView BoardView(ModelAndView mv, int boardNo) {
		
		Board board = service.selectBoardByNo(boardNo);
		mv.addObject("board", board);
		mv.setViewName("board/boardView");
		return mv;
	}
	
}
