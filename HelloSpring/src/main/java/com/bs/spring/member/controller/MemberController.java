package com.bs.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	private MemberService service;
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public MemberController(MemberService service, BCryptPasswordEncoder passwordEncoder) {
		this.service = service;
		this.passwordEncoder = passwordEncoder;
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
		
		//암호화된 패스워드를 원본값이랑 비교하기 위해서는
		//BCryptPasswordEncoder클래스가 제공하는 메소드를 이용해서 동븡비교를 해야한다.
		//matches("원본값", 암호화값)메소드를 이용
		if(loginMember!= null && 
				//loginMember.getPassword().equals(m.getPassword())) { 
				passwordEncoder.matches(m.getPassword(), loginMember.getPassword())) {
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
		//패스워드 암호화 처리하기
		String encodePassword = passwordEncoder.encode(m.getPassword());
		m.setPassword(encodePassword);
		
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
