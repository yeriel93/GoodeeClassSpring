package com.bs.spring.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.member.service.MemberService;
import com.bs.spring.member.vo.Member;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Controller
// 기본적인 주소를 설정 가능 
@RequestMapping("/member")
//Model저장된 아이들 중에 같은 키값을 가지고 있는 아이들의 생명주기를 session 처럼 줄 수 있음  
@SessionAttributes({"loginMember"})
//lombok에서 제공하는 로그 
@Slf4j
public class MemberController {
	//의존하는 모든 클래스를 적어줘야함
	
//	private final Logger logger = LoggerFactory.getLogger(MemberController.class);
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
	public String loginMember(Member m, Model model) {
		//Session에 데이터를 저장하고 관리
		
		Member loginMember = service.selectMemberById(m);
//		System.out.println(loginMember);
		
		//암호화된 패스워드를 원본값이랑 비교하기 위해서는
		//BCryptPasswordEncoder클래스가 제공하는 메소드를 이용해서 동등비교를 해야한다.
		//matches("원본값", 암호화값)메소드를 이용
		if(loginMember!= null && 
				//loginMember.getPassword().equals(m.getPassword())) { 
				passwordEncoder.matches(m.getPassword(), loginMember.getPassword())) {
			//로그인 성공
			//session.setAttribute("loginMember", loginMember);
			model.addAttribute("loginMember",loginMember);
			
		}
		return "redirect:/";
	}
	
	@RequestMapping("/logoutMember.do")
//	public String logoutMember(HttpSession session) {
//		session.invalidate();
	public String logoutMember(SessionStatus session) {
		//setComplete() : model객체에 session값처럼 설정된 아이나 session에 있는 두개의 값을 다 지울 수 있음
		//isComplete(): session값이 있니? 
		if(!session.isComplete()) { //if(session!=null)과 동일함
			session.setComplete();  //session을 삭제
		}
		return "redirect:/";
	}
	
	@RequestMapping("/enrollMember.do")
	public String enrollMember() {
		return "/member/enrollMember";
	}
	
	@RequestMapping("/enrollMemberEnd.do")
	public ModelAndView enrollMemberEnd(Member m, ModelAndView mv) {
		log.debug("파라미터로 전달된 member : {}", m);
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
	
	@RequestMapping("duplicateId.do")
	public void duplicateId(String userId, HttpServletResponse response) throws IOException{
		
		Member m = service.selectMemberById(Member.builder().userId(userId).build());
		
		response.setContentType("application/json;charset=utf-8");
		
//		response.getWriter().print(m==null ? false : true);
		new Gson().toJson(m, response.getWriter());
	}
	
	
//	jackson바이더를 이용해서 json응답 메소드 구현하기
//	메소드에 @ResponseBody어노테이션 적용
	
	
	@RequestMapping("/duplicateConverter.do")
	@ResponseBody
	public Member duplicateUserId(Member m) {
		Member result = service.selectMemberById(m);
		return result;
	}
	
	
	@RequestMapping("memberList.do")
	@ResponseBody
	public List<Member> selectMemberAll(){
		List<Member> list = service.selectMemberAll();
		return list;
	}
	
	@RequestMapping(value="/ajax/insert", 
			consumes=MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean insertTest(@RequestBody Member m) {
		log.debug("{}", m);
		return true;
	}
	
	@RequestMapping("/loginpage.do")
	public String loginpage() {
		return "member/loginpage";
	}
	
	
	
	
}
