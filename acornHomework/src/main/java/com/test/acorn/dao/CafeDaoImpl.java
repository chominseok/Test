package com.test.acorn.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.acorn.dto.CafeDto;

@Repository
public class CafeDaoImpl implements CafeDao{
	@Autowired
	private SqlSession session;
	
	@Override
	public void insertForm(CafeDto dto) {
		session.insert("cafe.insertForm", dto);
		
	}

	@Override
	public List<CafeDto> getList(CafeDto dto) {
		
		return session.selectList("cafe.getList",dto);
	}

	@Override
	public int getCount(CafeDto dto) {
		
		return session.selectOne("cafe.getCount", dto);
	}

	@Override
	public CafeDto getData(int num) {
		
		return session.selectOne("cafe.getData", num);
	}

	@Override
	public void addViewCount(int num) {
		session.update("cafe.addViewCount", num);
	}

	@Override
	public void delete(int num) {
		session.delete("cafe.delete", num);
	}

	@Override
	public void updateForm(CafeDto dto) {
		session.update("cafe.updateForm", dto);
	}
	
}
