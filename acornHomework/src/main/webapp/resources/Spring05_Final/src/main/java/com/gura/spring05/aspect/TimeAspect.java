package com.gura.spring05.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Aspect
@Component
public class TimeAspect {
	
	@Around("execution(* com.gura.spring05.file.controller.*.*(..))")
	public Object checkTime(ProceedingJoinPoint joinPoint) throws Throwable {
		//로그를 출력할 객체의 참조값 얻어오기
		Logger logger=LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		logger.info("looger 요청처리 시작-----------------!");
		//System.out.println("요청처리 시작-----------------!");
		
		long startTime = System.currentTimeMillis();
		
		Object obj = joinPoint.proceed();  //return값이 들어옴
		
		long endTime = System.currentTimeMillis();
		long time = (endTime - startTime);
		
		//System.out.println("요청 처리에 소요된 시간 : "+time);
		//System.out.println("요청처리 끝------------------!");
		logger.info("time" +time);
		logger.info("logger 요청처리 끝------------------!");
		
		
		return obj;
	}
}
