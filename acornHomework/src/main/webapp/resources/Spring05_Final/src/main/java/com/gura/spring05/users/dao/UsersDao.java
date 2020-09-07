package com.gura.spring05.users.dao;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.gura.spring05.users.dto.UsersDto;

public interface UsersDao {

	public boolean isExist(String id);
	public void insert(UsersDto dto);
	
//	public boolean isValid(UsersDto dto);
	
	public UsersDto getData(String id);
	public void deleteUser(String id);
	public void update(UsersDto dto);
	public void updatePwd(UsersDto dto);
}
