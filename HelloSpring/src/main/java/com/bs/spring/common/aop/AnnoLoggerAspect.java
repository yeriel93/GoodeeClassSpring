package com.bs.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

//어노테이션으로 aop적용하기
@Component
@Aspect
@Slf4j
public class AnnoLoggerAspect {

	//pointcut등록하기
	@Pointcut("execution(* com.bs.spring.member..*(..))")
	public void memberLogger() {}
	
	//advisor등록
	@Before("memberLogger()")
	public void loggerBefore(JoinPoint jp) {
		Signature sig = jp.getSignature();
		log.debug(sig.getName()+" 실행전");
		log.debug(sig.getDeclaringTypeName());
		
		log.debug("파라미터 데이터 받아오기");
		Object[] params = jp.getArgs();
		if(params!=null ) {
			for(Object o : params) {
				log.debug("파라미터 : {}",o);
			}
		}
		log.debug("==============================");
	}
	
	@After("memberLogger()")
	public void loggerAfter(JoinPoint jp) {
		Signature sig = jp.getSignature();
		log.debug(sig.getName()+" 실행후");
		log.debug(sig.getDeclaringTypeName());
		log.debug("==============================");
	}
	
	
	
	//실행 전후 모두 실행하는 메소드 구현하기 
	
	@Pointcut("execution(* com.bs.spring.demo..*(..))")
	public void demoLogger() {}
	
	@Around("demoLogger()")
	public Object demoLoggerArount(ProceedingJoinPoint join) throws Throwable{
		
		//실행전, 후를 구분하는 메소드 -> join.proceed();
		StopWatch stop = new StopWatch();
		stop.start();
		//파라미터값이나 Signature값도 가져올 수 있음
		Object[] params = join.getArgs();
		Signature sig = join.getSignature();
		
		Object obj = join.proceed();  //기준이 되므로 기준 앞에가 실행전, 뒤에는 실행 후가 됨! Object obj = join.proceed();위치에 따라 전후가 설정됨
		
		stop.stop();
		log.debug("실행시간 : "+stop.getTotalTimeMillis()+"ms");
		
		return obj;
	}
	
	
	
	@AfterThrowing("execution(* com.bs.spring..*(..))")
	public void exceptionTest(JoinPoint jp) {
		log.debug("에러발생!!!!");
	}
}
