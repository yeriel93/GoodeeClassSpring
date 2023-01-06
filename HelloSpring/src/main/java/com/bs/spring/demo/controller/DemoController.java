package com.bs.spring.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.demo.model.service.DemoService;
import com.bs.spring.demo.model.vo.Demo;

@Controller
public class DemoController {
	
	@Autowired 
	private DemoService service;
	
	
	public DemoController(DemoService service) {
		this.service = service;
	}
	
	//클라이언트가 요청한 서비스를 실행해주는 기능
	//클라이언트가 요청한 서비스 주소(URL)에 맞는 메소드를 구현
	//메소드구현 할때 서비스주소와 연결해주는 @어노테이션을 선언
	//@RequestMapping, @GetMapping, @PostMapping 등등 @어노테이션을 사용
	//@RequestMapping(연결주소[,추가 옵션설정]) - Rest방식의 서비스 구현시 사용
	
	//demo페이지는 연결하는 메소드 구현
	//반환형은 String -> SpringBean으로 등록된 ViewResolver통해 페이지를 지정된 위치에서 찾아 응답
	//일반적으로 매개변수는 없음
	//메소드명은 알아서..ㅎ 
	@RequestMapping("/demo/demo.do")
	public String demoPage() {
		//InternalResourceViewResolver가 처리함
		//InternalResourceViewResolver에 저장된 prefix, suffix의 내용을 반환값이랑 연결해서 view화면을 찾음
		//prefix : /WEB-INF/views/
		//suffix: .jsp
		return "demo/demo";  // /WEB_INF/views/demo/demo.jsp -> RequestDispatcher("/WEB-INF/views/demo/demo.jsp").forward(request.response)를 실행함.
	}
	
	//요청맵핑메소드의 매개변수와 반환형
	//1. 반환형
	// String : ViewResolver에 의해서 view화면을 반환할때 사용
	// void : HttpResponse로 직접 응답메세지를 작성할때 사용(upload, download...)
	// ModelAndView : ModelAndView객체를 반환(화면정보, view에 전송할 데이터)
	// 클래스타입(RefferenceType) : 생성된 객체를 반환 -> json방식으로 반환
	
	//2. 매개변수 -> Spring이 알아서 넣어줌!
	// HttpServletRequest : 응답할때 썼던 서블릿의 그녀석..!
	// HttpServletResponse : 응답할때 썼던 서블릿의 그녀석..!
	// HttpSession : Session의 그녀석!
	// java.util.Locale : 운영되고 있는 서버의 지역정보
	// inputStream/Reader : 파일 IO할때 사용하는 stream객체
	// outputStream/Writer : 파일 IO할때 사용하는 stream객체
	// 기본자료형 변수 : 클라이언트가 보낸 name명칭과 변수명이 일치하면 대입해줌!
				// 주의!! 기본자료형 변수를 선언했을때는 반드시 모든 변수에 연결되는 값을 전달해야함(@RequestParam어노테이션을 사용하지 않고 기본자료형 변수를 보냈을때)
				// name과 일치하지 않는 변수명을 사용했을때는 @RequestParam어노테이션을 이용해서 mapping할 수 있음 
				// 추가적인 설정이 필요할때도 @RequestParam어노테이션을 사용할 수 있음.
	
				//기본 자료형 변수 선언시 @RequestHeader, @CookieValue 어노테이션을 이용하면 header나 cookie의 값을 바로 저장할 수 있음
	// 클래스(RefferenceType) 변수 : command라고 하고 클라이언트가 보낸 데이터를 지정한 타입의 클래스를 생성해서 대입해줌.
							//조건) 필드명과 클라이언트가 보낸 name명이 일치하는 값
	// java.util.Map : 클라이언트가 보낸 데이터를 map으로 대입해줌.
	// Model : 서버에서 데이터를 저장하는 용도의 객체 /1회용 데이터 저장
	// ModelAndView : 저장할 데이터, 화면 정보를 한번에 저장하는 객체 1회용
	
	//추가 @어노테이션
	// @ResponseBody -> json형태로 반환할때 사용
	// @RequestBody -> json형태로 전달된 데이터를 vo객체와 mapping해주는 것
	
	//서블릿처럼 이용하기
	@RequestMapping("/demo/demo1.do")
//	public String demo1(HttpServletRequest req, HttpServletResponse res) {
	public void demo1(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException{
		System.out.println(req);
		System.out.println(res);
		
		String name = req.getParameter("devName");
		int age = Integer.parseInt(req.getParameter("devAge"));
		String gender = req.getParameter("devGender");
		String email = req.getParameter("devEmail");
		String[] devlang = req.getParameterValues("devLang");
		System.out.println(name+age+gender+email+Arrays.toString(devlang));
		
		Demo d = Demo.builder().devName(name).devAge(age).devGender(gender).devEmail(email).devLang(devlang).build();
		
//		req.setAttribute("demo", d);
//		req.getRequestDispatcher("/WEB-INF/views/demo/demoResult.jsp").forward(req,res);
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print("<h1>데이터<h1>");
		
//		return "demo/demoResult";
	}
	
	
	//기본자료형으로 데이터 받아오기
	@RequestMapping("demo/demo2.do")
	public String basicType(String devName, int devAge, String devEmail, String devGender, String[] devLang, Model model) {
		System.out.println(devName+" "+devAge+" "+devEmail+" "+devGender);
		for(String l : devLang) {
			System.out.println(l);
		}
		
		Demo d = Demo.builder().devName(devName).devAge(devAge).devEmail(devEmail).devGender(devGender).devLang(devLang).build();
		//model객체에 데이터 저장하기 
		//key,value형식으로 데이터를 저장함
		//addAttribute()메소드를 이용
		model.addAttribute("demo",d);
		
		
		return "demo/demoResult";
	}
	
	//@RequestParam사용하기
	@RequestMapping("demo/demo3.do")
	public String requestparamTest(@RequestParam(value="devName")String name, 
			@RequestParam(value="devAge", defaultValue = "1")int age, //사용자가 값을 입력하지 않았을때 default값을 설정 가능 defaultValue =""(String 타입)
			@RequestParam(value="devEmail" )String email,  
			@RequestParam(value="devGender")String gender,
			@RequestParam(value="devLang", required = false)String[] lang,  //사용자가 값을 입력하지 않아도 에러나지 않고 그냥 넘어가도록 설정 required=false
			Model model) {
		
		System.out.println(name+age+gender+email);
		if(lang!=null) {
			for(String l : lang) {
				System.out.println(l);
			}
		}
		Demo d = Demo.builder().devName(name).devAge(age).devGender(gender).devEmail(email).devLang(lang).build();
		model.addAttribute("demo", d);
		
		return "demo/demoResult";
	}

	//vo객체로 바로 받기
	//필드에 자료형들이 기본자료형들만 가능 (java.util.Date등등 객체타입은 안됨 /근데 java.sql.Date는 가능함 )
	@RequestMapping("demo/demo4.do")
	public String commandMappingTest(Demo demo, Model model) {
		System.out.println(demo);
		model.addAttribute("demo", demo);
		return "demo/demoResult";
	}
	
	
	//Map객체로 바로 받기
	//매개변수 Map에 @RequestParam 이걸 꼭 써줘야 함! 
	//단일값만 가져오기 때문에 배열은 따로 넘겨줘야한다.
	@RequestMapping("demo/demo5.do")
	public String mapMappingTest(@RequestParam Map param, String[] devLang, Model model) {
		System.out.println(param);
		param.put("devLang", devLang);
		model.addAttribute("demo", param);
		return "demo/demoResult";
	}
	
	
	//쿠키, 세션 값 받아오기
	@RequestMapping("demo/demo6.do")
	public String setraTest(@CookieValue(value="testData", required = false) String testData,	@RequestHeader(value="User-agent") String userAgent,
							@SessionAttribute(value="sessionId") String id, @RequestHeader(value="Referer") String referer) {
		System.out.println(testData);
		System.out.println(userAgent);
		System.out.println(id);
		System.out.println(referer);
		
		return "demo/demoResult";
	}
	
	
	//ModelAndView로 반환하기
	@RequestMapping("/demo/demo7.do")
	public ModelAndView moderlAndViewTest(ModelAndView mv, Demo demo) {
		//ModelAndView객체는 view설정과, 데이터 저장을 같이 할 수 있는 객체
		//data저장 : addObject("key", value) 메소드 이용
		//view설정 : setViewName("view이름")메소드 이용
		mv.addObject("demo", demo);
		mv.setViewName("demo/demoResult");
		
		return mv;
	}
	
	
//	restful하게 서비스를 구현할때 사용 -> @RestController를 사용하게 됨.
//	@GetMapping : 조회
//	@PostMapping : 저장 
//	@PutMapping : 수정
//	@DeleteMapping : 삭제
	
//	@PathVariable
	
	@RequestMapping("/demo/responsetest.do")
	@ResponseBody
	//@ResponseBody를 사용하면 그냥 반환값을 그냥 데이터로 보내버림 
	public String responseTest() {
//	public List<String> responseTest() {
		
		return "hello responseBody";
//		return List.of("1","2","3","4");
	}
	
	
	@RequestMapping("/demo/insertDemo.do")
	public String insertDemo(Demo demo) {
		int result = service.insertDemo(demo);
		//spring에서 redirect처리하기 (메소드 실행 후 페이지 전환)
		return "redirect:/demo/demo.do";
	}
	
	
	@RequestMapping("/demo/selectDemoList.do")
	public ModelAndView selectDemoList(ModelAndView mv) {
		List<Demo> list = service.selectDemoList();
//		System.out.println(list);
		mv.addObject("demos",list);
		mv.setViewName("demo/demoList");
		return mv;
	}
	
	
	
	
}
