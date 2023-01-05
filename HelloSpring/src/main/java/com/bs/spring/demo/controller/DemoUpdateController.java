package com.bs.spring.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.demo.model.service.DemoUpdateService;
import com.bs.spring.demo.model.vo.Demo;

@Controller
public class DemoUpdateController {
	
	@Autowired
	private DemoUpdateService service;
	
	public DemoUpdateController(DemoUpdateService service) {
		this.service = service;
	}
	
	
	@RequestMapping("/demo/demoUpdate.do")
	public String updateViewpage(int no, Model m) {
		Demo result = service.selectDemo(no);
		m.addAttribute("demo",result);
		return "/demo/demoUpdate";
	}
	
	@RequestMapping("/demo/updateDemoEnd.do")
	public String demoUpdate(Demo demo) {
		int result = service.updateDemo(demo);
		return "redirect:/demo/selectDemoList.do";
	}
}
