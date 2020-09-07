package com.test.acorn.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.test.acorn.dto.CafeDto;
import com.test.acorn.service.CafeService;

@Controller
public class CafeController {
	@Autowired
	private CafeService cafeService;
	
	@RequestMapping("/cafe/list")
	public ModelAndView list(ModelAndView m, HttpServletRequest request) {
		cafeService.getList(m, request);
		
		m.setViewName("cafe/list");
		return m;
	}
	
	@RequestMapping("/cafe/private/insert_form")
	public String insertForm() {
		
		return "cafe/private/insert_form";
	}
	
	@RequestMapping("/cafe/private/insert")
	public ModelAndView insert(CafeDto dto, ModelAndView m) {
		cafeService.inserForm(dto);
		m.setViewName("cafe/private/insert");
		return m;
	}
	
	
	@RequestMapping("/cafe/detail")
	public ModelAndView detail(ModelAndView m, HttpServletRequest request) {
		cafeService.detail(m, request);
		
		m.setViewName("cafe/detail");
		return m;
	}
	
	@RequestMapping("/cafe/private/delete")
	public String delete(@RequestParam int num) {
		cafeService.delete(num);
		
		return "cafe/private/delete";
	}
	
	@RequestMapping("/cafe/private/updateform")
	public ModelAndView updateForm(int num, ModelAndView m) {
		cafeService.updateForm(num, m);
		m.setViewName("cafe/private/updateform");
		return m;
	}
	
	@RequestMapping("/cafe/private/update")
	public ModelAndView update(CafeDto dto, ModelAndView m) {
		cafeService.update(dto);
		m.addObject("num", dto.getNum());
		m.setViewName("cafe/private/update");
		return m;
	}
}
