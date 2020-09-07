package com.test.acorn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.test.acorn.service.FriendsService;

@Controller
public class FriendsController {
	@Autowired
	private FriendsService service;
	
	@RequestMapping("/friends")
	public ModelAndView getList(ModelAndView m) {
		service.getList(m);
		m.setViewName("friends");
		return m;
	}
}
