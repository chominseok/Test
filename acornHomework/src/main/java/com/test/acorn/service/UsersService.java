package com.test.acorn.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.test.acorn.dto.UsersDto;


public interface UsersService {
	public Map<String, Object> checkId(String id);
	
	public void signup(com.test.acorn.dto.UsersDto dto);
	
	public ModelAndView loginId(ModelAndView m, UsersDto dto, HttpServletRequest request);
	
	public ModelAndView info(ModelAndView m, String id);
	
	public void updatePwd(String pwd, HttpSession session);
	
	public void deleteId(HttpSession session);
	
	public void updateForm(ModelAndView m, HttpSession session);
	
	public Map<String, Object> profileUpload(MultipartFile image, HttpServletRequest req);
	
	public void update(UsersDto dto);
}
