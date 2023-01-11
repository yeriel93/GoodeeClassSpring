package com.bs.spring.memo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.common.PageFactory;
import com.bs.spring.memo.model.service.MemoService;
import com.bs.spring.memo.model.vo.Memo;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/memo")
@Slf4j
public class MemoController {

	private MemoService service;
	
	@Autowired
	public MemoController(MemoService service) {
		this.service = service;
	}
	
	//get방식으로 보낼때만 메소드를 실행하겠다는 뜻
	@RequestMapping(value="/memoList.do", method= {RequestMethod.GET})
	public ModelAndView selectMemoList(ModelAndView mv, 
			@RequestParam (value="cPage", defaultValue = "1") int cPage, 
			@RequestParam (value = "numPerpage", defaultValue = "5") int numPerpage) {
		//페이징처리하기
		
		mv.addObject("memolist",service.selectMemoListPage(Map.of("cPage",cPage, "numPerpage", numPerpage)));
		
		int totalData = service.selectMemoListCount();
		mv.addObject("pageBar", PageFactory.getPage(cPage, numPerpage, totalData, "memoList.do"));
		
		
		mv.setViewName("memo/memoList");
		return mv;
	}
	
	@RequestMapping("/insertMemoEnd.do")
	public ModelAndView insertMemo(Memo m, ModelAndView mv) {
//		log.debug("{}",m);
		
		if(service.insertMemo(m)>0) {
			mv.addObject("msg","메모 등록 완료");
			mv.addObject("loc","/memo/memoList.do");
		}else {
			mv.addObject("msg","메모등록 실패");
			mv.addObject("loc","/memo/insertMemo.do");
		}
		mv.setViewName("common/msg");
		
		return mv;
	}
}
