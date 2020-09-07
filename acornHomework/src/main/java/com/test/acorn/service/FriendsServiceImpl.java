package com.test.acorn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.test.acorn.dao.FriendsDao;
import com.test.acorn.dto.FriendsDto;

@Service
public class FriendsServiceImpl implements FriendsService{
	@Autowired
	private FriendsDao friendsDao;
	
	@Override
	public void getList(ModelAndView m) {
		List<FriendsDto> list = friendsDao.getList();
		m.addObject("list", list);
	}
}
