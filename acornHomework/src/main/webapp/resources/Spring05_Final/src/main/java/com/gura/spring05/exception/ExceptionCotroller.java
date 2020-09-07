package com.gura.spring05.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//bean이 됨
@ControllerAdvice
public class ExceptionCotroller {
	//@ExceptionHandler() 괄호안의 타입의 exception이 발생하게 되면 실행순서가 여기로 들어온다.
	//예시  >>> throw new NotDeleteException("가세요 ㅎㅎ");
	@ExceptionHandler(NotDeleteException.class)
	public ModelAndView notDelete(NotDeleteException nde) {
		ModelAndView m = new ModelAndView();
		m.addObject("exception", nde);   // <<NotDeleteException 클래스의 message가 들어온다.
		m.setViewName("error/info");
		
		return m;
	}
	
	

	@ExceptionHandler(DataAccessException.class)
	public ModelAndView dataAccess(DataAccessException dae) {
		ModelAndView m = new ModelAndView();
		m.addObject("exception", dae);
		m.setViewName("error/data_access2");
		System.out.println("Data!!!");
		return m;
	}
	
	/*@REPOSITORY가 있는 CLASS에서
	 * Dao에서 DB 관련 작업을 하다가 예외가 발생하면 실행 순서가 여기로 들어온다.
	 * */
	@ExceptionHandler(NoDeliveryException.class)
	public ModelAndView dataAccess(NoDeliveryException nde) {
		ModelAndView m = new ModelAndView();
		
		m.addObject("exception", nde);
		m.setViewName("error/no_delivery");
		return m;
	}
}
