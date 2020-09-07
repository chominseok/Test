package com.test.acorn.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.acorn.dto.FriendsDto;

@Repository
public class FriendsDaoImpl implements FriendsDao{
	@Autowired
	private SqlSession session;
	
	@Override
	public List<FriendsDto> getList() {
		
		return session.selectList("friends.getList");
	}

}
