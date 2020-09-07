package com.test.acorn.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.test.acorn.dto.UsersDto;

@Controller
public class UsersController {
	@Autowired
	private com.test.acorn.service.UsersService usersService;
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "home";
	}
	
	@RequestMapping("/users/signup_form")
	public String signupForm() {
		return "users/signup_form";
	}
	
	@ResponseBody
	@RequestMapping("/users/checkId")
	public Map<String, Object> checkId(@RequestParam String id){
		Map<String, Object> map = usersService.checkId(id);
		
		return map;
	}
	
	@RequestMapping("/users/signup")
	public String signup(UsersDto dto) {
		usersService.signup(dto);
		return "users/signup";
	}
	
	@RequestMapping("/users/login_form")
	public String longinForm() {
		return "users/login_form";
	}
	
	@RequestMapping("/users/login")
	public ModelAndView login(ModelAndView m, UsersDto dto, HttpServletRequest request) {
		usersService.loginId(m, dto, request);
		
		m.setViewName("users/login");
		return m;
	}
	
	@RequestMapping("/users/private/info")
	public ModelAndView info(ModelAndView m, HttpSession session) {
		String id = (String)session.getAttribute("id");
		
		usersService.info(m, id);
		
		m.setViewName("users/private/info");
		return m;
	}
	
	@ResponseBody
	@RequestMapping("/users/private/update_pwd_form")
	public Map<String, Object> updatePwdForm(String newPwd, HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		usersService.updatePwd(newPwd, session);
		return map;
	}
	
	@RequestMapping("/users/private/delete")
	public String delete(HttpSession session) {
		usersService.deleteId(session);
		
		return "users/private/delete";
	}
	
	@RequestMapping("/users/private/update_form")
	public ModelAndView updateForm(ModelAndView m, HttpSession session) {
		usersService.updateForm(m, session);
		m.setViewName("users/private/update_form");
		return m;
	}
	
	@ResponseBody
	@RequestMapping("/users/private/profile_upload")
	public Map<String, Object> profileUpload(MultipartFile image,  HttpServletRequest req){
		Map<String, Object> map = usersService.profileUpload(image, req);
		
		return map;
	}
	
	@RequestMapping("/users/private/update")
	public String update(UsersDto dto) {
		
		usersService.update(dto);
		return "users/private/update";
	}
}
