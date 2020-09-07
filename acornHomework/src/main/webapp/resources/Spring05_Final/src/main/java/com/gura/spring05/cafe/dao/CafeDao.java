package com.gura.spring05.cafe.dao;

import java.util.List;

import com.gura.spring05.cafe.dto.CafeDto;

public interface CafeDao {
	public List<CafeDto> getList(CafeDto dto);
	public int getCount(CafeDto dto);
	public void insert(CafeDto dto);
	public CafeDto getData(int num);
	//키워드를 활용한 글 정보 얻어오기
	public CafeDto getData(CafeDto dto);
	
	public void addViewCount(int num);
	public void delete(int num);
	public void update(CafeDto dto);
	
}
