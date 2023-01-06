package com.bs.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.member.service.MemberService;
import com.bs.spring.member.vo.Member;

@Controller
// 기본적인 주소를 설정 가능 
@RequestMapping("/member")
public class MemberController {
	//의존하는 모든 클래스를 적어줘야함
	
	public MemberService service;
	
	@Autowired
	public MemberController(MemberService service) {
		this.service = service;
	}
	
//	@RequestMapping("/test/")
//	public void test() {
//		System.out.println("controller - test() 실행");
//		service.test();
//	}
	
	@RequestMapping("/loginMember.do")
//	public String loginMember(String userId, String password) {
//	public String loginMember(@RequestParam Map m) {
	public String loginMember(Member m, HttpSession session) {
		//Session에 데이터를 저장하고 관리
		
		Member loginMember = service.selectMemberById(m);
//		System.out.println(loginMember);
		
		if(loginMember!= null && loginMember.getPassword().equals(m.getPassword())) { 
			//로그인 성공
			session.setAttribute("loginMember", loginMember);
		}
		return "redirect:/";
	}
	
	@RequestMapping("/logoutMember.do")
	public String logoutMember(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/enrollMember.do")
	public String enrollMember() {
		return "/member/enrollMember";
	}
	
	@RequestMapping("/enrollMemberEnd.do")
	public ModelAndView enrollMemberEnd(Member m, ModelAndView mv) {
		int result = service.insertMember(m);
//		System.out.println(result);
		if(result>0) {
			mv.addObject("msg","회원가입 완료");
			mv.addObject("loc","/");
		}else {
			mv.addObject("msg","회원가입 실패");
			mv.addObject("loc","/member/enrollMember.do");
		}
		mv.setViewName("common/msg");
		return mv;
	}
	
	@RequestMapping("/memberView.do")
	public String enrollLsit() {
		return "member/memberView";
	}
}
