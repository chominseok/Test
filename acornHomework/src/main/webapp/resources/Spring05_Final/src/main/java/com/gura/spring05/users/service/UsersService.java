package com.gura.spring05.users.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.users.dto.UsersDto;

public interface UsersService {
	
	public Map<String, Object> isExistId(String id);
	public void addUser(UsersDto dto);
	public void loginProcess(UsersDto dto, ModelAndView m, HttpSession session);
	public void getInfo(HttpServletRequest req, ModelAndView m);
	public void deleteUser(HttpSession session);
	public Map<String, Object> saveProfileImage(HttpServletRequest req, MultipartFile image);
	public void updateUser(HttpSession session, UsersDto dto);
	public void updateUserPwd(HttpSession session, UsersDto dto, ModelAndView m);
}
