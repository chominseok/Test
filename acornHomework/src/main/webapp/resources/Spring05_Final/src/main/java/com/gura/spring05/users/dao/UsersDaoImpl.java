package com.gura.spring05.users.dao;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.gura.spring05.users.dto.UsersDto;
/*
 * @Repository는 DB 관련 작업을 하다가 SQLException이 발생하는 경우 해당 Exception 대신에
 * DataAccessException을 발생시킨다.
 * 
 * >> 따라서 DB 관련 작업을 하다가 발생하는 예외는 예외 컨트롤러에서 처리를 한다.
 * */
@Repository
public class UsersDaoImpl implements UsersDao{
	@Autowired
	private SqlSession session;

	@Override
	public boolean isExist(String inputId) {
		String id = session.selectOne("users.isExist", inputId);
		if(id == null) {//존재하지 않는 아이디
			return false;
		}else {//존재하는 아이디
			return true;
		}
	}

	@Override
	public void insert(UsersDto dto) {
		session.insert("users.insert", dto);
	}

//	@Override
//	public boolean isValid(UsersDto dto) {
//		String id = session.selectOne("users.isValid", dto);
//		if(id == null) {
//			return false;
//		}else {
//			return true;
//		}
//	}

	@Override
	public UsersDto getData(String id) {
		UsersDto dto = session.selectOne("users.getData", id);
		return dto;
	}

	@Override
	public void deleteUser(String id) {
		session.delete("users.deleteUser", id);
	}

	@Override
	public void update(UsersDto dto) {
		session.update("users.update", dto);
	}

	@Override
	public void updatePwd(UsersDto dto) {
		session.update("users.updatePwd", dto);
	}
}
