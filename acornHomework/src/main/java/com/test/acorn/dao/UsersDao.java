package com.test.acorn.dao;

import java.util.Map;

import com.test.acorn.dto.UsersDto;

public interface UsersDao {
	public boolean checkId(String id);
	
	public void signup(UsersDto dto);
	
	public boolean loginId(UsersDto dto);
	
	public String encoderPwd(UsersDto dto);
	
	public UsersDto getData(String id);
	
	public void UpdatePwd(UsersDto dto);
	
	public void deleteId(String id);
	
	public void update(UsersDto dto);
}
