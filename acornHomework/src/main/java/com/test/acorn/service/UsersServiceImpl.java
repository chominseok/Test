package com.test.acorn.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.test.acorn.dto.UsersDto;


@Service
public class UsersServiceImpl implements UsersService{
	@Autowired
	private com.test.acorn.dao.UsersDao usersDao;
	
	@Override
	public Map<String, Object> checkId(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean checkId = usersDao.checkId(id);
		map.put("isValid", checkId);
		return map;
	}

	@Override
	public void signup(UsersDto dto) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String newPwd = encoder.encode(dto.getPwd());
		dto.setPwd(newPwd);
		usersDao.signup(dto);
		
	}

	@Override
	public ModelAndView loginId(ModelAndView m, UsersDto dto, HttpServletRequest request) {
		boolean isValid = false;
		UsersDto resultDto = usersDao.getData(dto.getId());
		
		if(resultDto != null) {
			String encoderPwd = resultDto.getPwd();
			String inputPwd = dto.getPwd();
			isValid = BCrypt.checkpw(dto.getPwd(), encoderPwd);
		}
		
		if(isValid) {
			request.getSession().setAttribute("id", dto.getId());
			m.addObject("loginValid", isValid);
		}else {
			m.addObject("loginValid", isValid);
		}
		return m;
	}

	@Override
	public ModelAndView info(ModelAndView m, String id) {
		UsersDto dto = usersDao.getData(id);
		m.addObject("dto", dto);
		return m;
	}

	@Override
	public void updatePwd(String newPwd, HttpSession session) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String pwd = encoder.encode(newPwd);
		String id = (String)session.getAttribute("id");
		UsersDto dto = new UsersDto();
		dto.setId(id);
		dto.setPwd(pwd);
		usersDao.UpdatePwd(dto);
	}

	@Override
	public void deleteId(HttpSession session) {
		String id = (String)session.getAttribute("id");
		usersDao.deleteId(id);
	}

	@Override
	public void updateForm(ModelAndView m, HttpSession session) {
		String id = (String)session.getAttribute("id");
		UsersDto dto = usersDao.getData(id);
		m.addObject("dto", dto);
	}

	@Override
	public Map<String, Object> profileUpload(MultipartFile image, HttpServletRequest req) {
		//원본 파일명
		String orgFileName = image.getOriginalFilename();
		
		//web-app/upload 폴더까지의 실제 경로(서버 경로)
		String realPath = req.getServletContext().getRealPath("/upload");
		//저장할 파일의 상세 경로                        +       \ 				saveFileName
		String filePath = realPath + File.separator;
		//디렉토리를 만들 파일 객체 생성
		File upload = new File(filePath);
		
		if(!upload.exists()) {//만일 디렉토리가 존재하지 않으면 
			upload.mkdir(); //디렉토리 생성
		}
		
		//저장할  파일 명을 구성한다.  >> 파일명이 겹치지 않게하기 위해서 timemillis()
		String saveFileName = System.currentTimeMillis()+orgFileName;
	
		try {
			//upload 폴더에 파일을 저장한다.
			image.transferTo(new File(filePath+saveFileName));
			System.out.println(filePath + saveFileName);
		}catch(Exception e) {
			e.printStackTrace();
		}
		//map에 업로드된 이미지 파일의 경로를 담아서 리턴한다.
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("imageSrc", "/upload/"+saveFileName);
		
		System.out.println("******************************* /upload/"+saveFileName);
		
		return map;
	}

	@Override
	public void update(UsersDto dto) {
		usersDao.update(dto);
	}
	
	
}
