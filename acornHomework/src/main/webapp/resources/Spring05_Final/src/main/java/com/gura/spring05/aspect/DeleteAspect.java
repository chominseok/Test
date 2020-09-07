package com.gura.spring05.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gura.spring05.cafe.dao.CafeDao;
import com.gura.spring05.cafe.dto.CafeDto;
import com.gura.spring05.exception.NotDeleteException;
import com.gura.spring05.file.dao.FileDao;
import com.gura.spring05.file.dto.FileDto;

@Aspect
@Component
public class DeleteAspect {
	@Autowired
	private FileDao fileDao;
	@Autowired
	private CafeDao cafeDao;
	
	@Around("execution(void com.gura.spring05.file.service.*.delte*(..))")
	public void checkFileDelete(ProceedingJoinPoint jointPoint) throws Throwable {
		//메소드에 전달된 인자값을 저장할 지역 변수
		int num = 0;
		HttpServletRequest request = null;
		Object[] obj = jointPoint.getArgs();
		for(Object tmp : obj) {
			if(tmp instanceof Integer) { //type = int
				num = (int)tmp;
			}else if(tmp instanceof HttpServletRequest) { //type= HttpServletRequest
				request = (HttpServletRequest)tmp;
			}
		}
		//삭제할 파일의 정보를 얻어온다.
		FileDto fileDto = fileDao.getData(num);
		//세션에 저장된 아이디를 읽어온다.(로그인 된 아이디)
		String id = (String)request.getSession().getAttribute("id");
		if(! id.equals(fileDto.getWriter())) {
			throw new NotDeleteException("가세요 ㅎㅎ");
		}
		System.out.println("aspect*******************************");
		jointPoint.proceed();
	}
	
	@Around("execution(void com.gura.spring05.cafe.service.*.delete(..))")
	public void cafeDelete(ProceedingJoinPoint joinPoint) throws Throwable {
		int num = 0;
		HttpServletRequest request = null;
		
		Object[] obj = joinPoint.getArgs();
		for(Object tmp : obj) {
			if(tmp instanceof Integer) {
				num = (int)tmp;
			}else if(tmp instanceof HttpServletRequest) {
				request = (HttpServletRequest)tmp;
			}
		}
		
		CafeDto dto = cafeDao.getData(num);
		String id = (String)request.getSession().getAttribute("id");
		if(!id.equals(dto.getWriter())) {
			throw new NotDeleteException("가세요 ㅎㅎ");
		}
		System.out.println("잘~~ 동작합니다! ****************************************************************************");
		joinPoint.proceed();
	}
}
