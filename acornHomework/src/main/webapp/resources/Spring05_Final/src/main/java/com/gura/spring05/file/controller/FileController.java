package com.gura.spring05.file.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.file.dto.FileDto;
import com.gura.spring05.file.service.FileService;

@Controller
public class FileController {
	@Autowired
	private FileService fileService;
	
	@RequestMapping("/file/list")
	public ModelAndView list(HttpServletRequest request, ModelAndView m) {
		fileService.getList(request);
		
		m.setViewName("file/list");
		return m;
	}
	
	@RequestMapping("/file/private/upload_form")
	public ModelAndView uploadForm(ModelAndView m) {
		
		m.setViewName("file/private/upload_form");
		return m;
	}
	
	@RequestMapping("/file/private/upload")
	public ModelAndView upload(FileDto dto,  ModelAndView m, HttpServletRequest request) {
		fileService.saveFile(dto, m, request);
		
		m.setViewName("file/private/upload");
		return m;
	}
	
	@RequestMapping("/file/download")
	public ModelAndView download(@RequestParam int num, ModelAndView m) {
		//m에 다운로드 할 파일의 정보를 담고
		fileService.getFileData(num, m);
		//view 페이지로 이동					//보통 이미지를 다운받을 때 사용
		m.setViewName("fileDownView"); //view클래스에 있는 @component의 이름
		return m;
	}
	
	@RequestMapping("/file/private/delete")
	public ModelAndView delete(@RequestParam int num, ModelAndView m,
			HttpServletRequest request) {
		
		fileService.delteFile(num, request);
		
		m.setViewName("redirect:/file/list.do");
		return m;
	}
}
