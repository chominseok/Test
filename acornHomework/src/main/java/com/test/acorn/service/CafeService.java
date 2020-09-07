package com.test.acorn.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.test.acorn.dto.CafeDto;

public interface CafeService {
	public void getList(ModelAndView m, HttpServletRequest request);
	
	public void inserForm(CafeDto dto);
	
	public void detail(ModelAndView m, HttpServletRequest request);
	
	public void delete(int num);
	
	public void updateForm(int num, ModelAndView m);
	
	public void update(CafeDto dto);
}
