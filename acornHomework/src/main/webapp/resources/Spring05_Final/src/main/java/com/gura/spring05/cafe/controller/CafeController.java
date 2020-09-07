package com.gura.spring05.cafe.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.cafe.dto.CafeCommentDto;
import com.gura.spring05.cafe.dto.CafeDto;
import com.gura.spring05.cafe.service.CafeService;

@Controller
public class CafeController {
	@Autowired
	private CafeService cafeService;
	
	@RequestMapping("/cafe/list")
	public ModelAndView getList(ModelAndView m, HttpServletRequest request) {
		cafeService.getList(request);
		m.setViewName("cafe/list");
		return m;
	}
	
	@RequestMapping("/cafe/detail")
	public ModelAndView detail(HttpServletRequest request, ModelAndView m) {
		cafeService.getDetail(request);
		
		m.setViewName("cafe/detail");
		return m;
	}
	
	@RequestMapping("/cafe/private/insertform")
	public ModelAndView insertFomr(ModelAndView m) {
		
		m.setViewName("cafe/private/insertform");
		return m;
	}
	
	@RequestMapping("/cafe/private/insert")
	public ModelAndView insert(ModelAndView m, CafeDto dto, HttpServletRequest request) {
		String id = (String)request.getSession().getAttribute("id");
		dto.setWriter(id);
		
		cafeService.insert(dto);
		m.setViewName("cafe/private/insert");
		return m; 
	}
	
	@RequestMapping("/cafe/private/delete")
	public String delete(int num, HttpServletRequest request) {
		cafeService.delete(num, request);
		return "cafe/private/delete";
	}
	
	@RequestMapping("/cafe/private/updateform")
	public ModelAndView updateForm(ModelAndView m, CafeDto dto) {
		cafeService.updateForm(dto, m);
		
		m.setViewName("cafe/private/updateform");
		return m;
	}
	
	@RequestMapping("/cafe/private/update")
	public String update(CafeDto dto) {
		cafeService.update(dto);
		
		return "cafe/private/update";
	}
	
	@RequestMapping("/cafe/private/comment_insert")
	public ModelAndView commentInsert(ModelAndView m, HttpServletRequest request, int ref_group) {
		//새 댓글을 작성하고
		cafeService.saveComment(request);
		//보고 있던 글(ref_group 글번호) 자세히 보기로 다시 리다이렉트한다.
		m.setViewName("redirect:/cafe/detail.do?num="+ref_group);
		return m;
	}
	
	@RequestMapping("/cafe/private/comment_delete")
	public ModelAndView commentDelete(HttpServletRequest request, ModelAndView m, int ref_group) {
		cafeService.deleteComment(request);
		m.setViewName("redirect:/cafe/detail.do?num="+ref_group);
		return m;
	}
	
	
	//기존 수정 폼
//	@RequestMapping("/cafe/private/comment_update")
//	public ModelAndView updateComment(CafeCommentDto dto, ModelAndView m) {
//		cafeService.updateComment(dto);
//		m.setViewName("redirect:/cafe/detail.do?num="+dto.getRef_group());
//		return m;
//	}
	
	@RequestMapping("/cafe/private/comment_update")
	@ResponseBody
	public Map<String, Object> updateComment(CafeCommentDto dto){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", dto.getNum());
		map.put("content", dto.getContent());
		
		cafeService.updateComment(dto);
		
		return map;
	}

}
