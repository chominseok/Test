package com.gura.spring05.shop.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

public interface ShopService {
	//상품 목록을 m객체에 담아주는 메소드
	public void getList(ModelAndView m);
	//상품 주문 처리를 하는 메소드
	public void buy(HttpServletRequest reuqest, ModelAndView m);
}
