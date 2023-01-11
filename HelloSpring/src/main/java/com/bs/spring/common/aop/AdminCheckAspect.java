package com.bs.spring.common.aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.bs.spring.common.AdminAccessException;
import com.bs.spring.member.vo.Member;

@Component
@Aspect
public class AdminCheckAspect {
	
	@Before("execution(* com.bs.spring.memo..select*(..))")
	public void adminCheck(JoinPoint jp) {
		
		//로그인정보 가져오기
		//RequestContextHolder클래스에서 currentRequestAttribute() static메소드 제공
		//currentRequestAttribute() 메소드를 이용해 session객체를 가져올 수 있다. 
		HttpSession session = (HttpSession) RequestContextHolder.currentRequestAttributes().resolveReference(RequestAttributes.REFERENCE_SESSION);
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		//AOP는 조건이 맞지 않을때 서버를 중단하는 방법은 에러를 던지는 방법 밖에는 없음 
		if(loginMember==null || !loginMember.getUserId().equals("admin")) {
			throw new AdminAccessException("관리자만 접근할 수 있습니다! :( ");
		}
	}
}
