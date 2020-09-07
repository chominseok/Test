package com.gura.spring05.file.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.file.dto.FileDto;

public interface FileService {

	public void getList(HttpServletRequest request);
	public void saveFile(FileDto dto, ModelAndView m, HttpServletRequest request);
	public void getFileData(int num, ModelAndView m);
	public void delteFile(int num, HttpServletRequest request);
}
