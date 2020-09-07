package com.gura.spring05.exception;

import org.springframework.dao.DataAccessException;

/*
 * DB관련 exception 처리는 DataAccessException을 상속받아서 ExceptionHandler에서 받는다.
 * */
public class NoDeliveryException extends DataAccessException{

	public NoDeliveryException(String msg) {
		super(msg);
		
	}
}
