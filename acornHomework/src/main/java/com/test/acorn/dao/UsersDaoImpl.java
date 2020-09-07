package com.test.acorn.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.acorn.dto.UsersDto;


@Repository
public class UsersDaoImpl implements UsersDao{
	@Autowired
	private SqlSession session;
	
	@Override
	public boolean checkId(String id) {
		String checkId = session.selectOne("users.checkId", id);
		
		if(checkId == null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public void signup(UsersDto dto) {
		session.insert("users.signup", dto);
	}

	@Override
	public boolean loginId(UsersDto dto) {
		String id = session.selectOne("users.loginId", dto);
		if(id == null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public String encoderPwd(UsersDto dto) {
		String encoderPwd = session.selectOne("users.encoderPwd", dto);
		
		return encoderPwd;
	}

	@Override
	public UsersDto getData(String id) {
		UsersDto dto = session.selectOne("users.getData", id);
		return dto;
	}

	@Override
	public void UpdatePwd(UsersDto dto) {
		session.update("users.updatePwd", dto);
	}

	@Override
	public void deleteId(String id) {
		session.delete("users.deleteId",id);
	}

	@Override
	public void update(UsersDto dto) {
		session.update("users.update", dto);
	}
	
	
}
