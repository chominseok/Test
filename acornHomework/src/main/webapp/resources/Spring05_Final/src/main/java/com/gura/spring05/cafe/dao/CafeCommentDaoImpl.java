package com.gura.spring05.cafe.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring05.cafe.dto.CafeCommentDto;

@Repository
public class CafeCommentDaoImpl implements CafeCommentDao{
	@Autowired
	private SqlSession session;
	
	@Override
	public List<CafeCommentDto> getList(CafeCommentDto dto) {
		
		return session.selectList("cafeComment.getList", dto);
	}

	@Override
	public void delete(int num) {
		session.update("cafeComment.delete", num); 
		//댓글 삭제를 하면 밑에 달린 리플들을 구조상 이해하기 위해서 삭제가 아닌 수정으로 조정
	}

	@Override
	public void insert(CafeCommentDto dto) {
		
		session.insert("cafeComment.insert", dto);
	}

	@Override
	public int getSequence() {
		
		return session.selectOne("cafeComment.getSequence");
	}

	@Override
	public void update(CafeCommentDto dto) {
		session.update("cafeComment.update", dto);
	}

	@Override
	public CafeCommentDto getData(int num) {
		
		return session.selectOne("cafeComment.getData", num);
	}

	@Override
	public int getCount(int ref_group) {
		
		return session.selectOne("cafeComment.getCount", ref_group);
	}
}
