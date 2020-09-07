package com.gura.spring05.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.shop.service.ShopService;

@Controller
public class ShopController {
	@Autowired
	private ShopService service;
	
	@RequestMapping("/shop/list")
	public ModelAndView list(ModelAndView m) {
		service.getList(m);
		m.setViewName("shop/list");
		return m;
	}
	
	@RequestMapping("/shop/private/buy")
	public ModelAndView buy(int num, HttpServletRequest req, ModelAndView m) {
		service.buy(req, m);
		m.setViewName("shop/private/buy");
		return m;
	}
}
