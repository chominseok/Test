package com.test.acorn.dao;

import java.util.List;

import com.test.acorn.dto.CafeDto;

public interface CafeDao {
	public List<CafeDto> getList(CafeDto dto);
	
	public void insertForm(CafeDto dto);
	
	public int getCount(CafeDto dto);
	
	public CafeDto getData(int num);
	
	public void addViewCount(int num);
	
	public void delete(int num);
	
	public void updateForm(CafeDto dto);
}
