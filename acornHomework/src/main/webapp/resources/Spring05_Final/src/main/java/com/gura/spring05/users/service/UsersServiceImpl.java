package com.gura.spring05.users.service;

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

import com.gura.spring05.users.dao.UsersDao;
import com.gura.spring05.users.dto.UsersDto;

@Service
public class UsersServiceImpl implements UsersService{
	@Autowired
	private UsersDao dao;
	
	@Override
	public Map<String, Object> isExistId(String inputId) {
		Map<String, Object> map = new HashMap<String, Object>();
		//아이디가 존재하는지 여부를 Map에 담아서 리턴해준다.
		boolean isExist = dao.isExist(inputId);
		
		map.put("isExist", isExist);
		
		return map;
	}

	@Override
	public void addUser(UsersDto dto) {
		//dto 객체에 비밀번호를 암호화 해서 넣어준다.
		String inputPwd=dto.getPwd(); //회원가입 form 에 입력한 비밀번호
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		String encodedPwd=encoder.encode(inputPwd); //암호화된 비밀번호
		//암호화된 비밀번호를 dto 객체에 다시 넣어준다. 
		dto.setPwd(encodedPwd);
				
		dao.insert(dto);
	}
	
	@Override									//로그인 상태 유지를 위해서 session도 인자로 추가한다.
	public void loginProcess(UsersDto dto, ModelAndView m, HttpSession session) {
		boolean isValid = false;
		
		UsersDto resultDto = dao.getData(dto.getId());
		
		if(resultDto != null) {
			//DB 에 저장된 암호화된 비밀번호 
			String encodedPwd=resultDto.getPwd();
			//로그인폼에 입력한 비밀번호 
			String inputPwd=dto.getPwd();
			//BCrypt 클래스의 static 메소드를 이용해서 일치 여부를 얻어낸다. 
			isValid=BCrypt.checkpw(inputPwd, encodedPwd);
		}
		
		if(isValid) {//만일 유효한 정보이면 
			//로그인 처리를 한다. 
			session.setAttribute("id", dto.getId());
			//view 페이지에서 사용할 정보 
			m.addObject("isSuccess", true);
		}else {
			m.addObject("isSuccess", false);
		}
	}
	


	@Override
	public void getInfo(HttpServletRequest req, ModelAndView m) {
		String id = (String)req.getSession().getAttribute("id");
//		String id = (String)session.getAttribute("id");
		UsersDto dto = dao.getData(id);
		m.addObject("dto", dto);
	}
	
	@Override
	public void deleteUser(HttpSession session) {
		String id = (String)session.getAttribute("id");
		dao.deleteUser(id);
		//로그인 상태에서 삭제하므로, 탈퇴하면 세션을 없애버리자
		session.invalidate();
	}

	@Override
	public Map<String, Object> saveProfileImage(HttpServletRequest req, MultipartFile image) {
		
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
		
		return map;
	}

	@Override
	public void updateUser(HttpSession session, UsersDto dto) {
		String id = (String)session.getAttribute("id");
		dto.setId(id);
		
		dao.update(dto);
	}

	@Override
	public void updateUserPwd(HttpSession session, UsersDto dto, ModelAndView m) {
		String id = (String)session.getAttribute("id");
		dto.setId(id);
		//작업성공여부
		boolean isSuccess = false;
		
		//1.기존 비밀번호와 암호화된 비밀번호가 일치하는지 확인
		
		UsersDto resultDto=dao.getData(id);
		
		//DB 에 저장된 암호화된 비밀번호 
		String encodedPwd=resultDto.getPwd();
		//로그인폼에 입력한 비밀번호 
		String inputPwd=dto.getPwd();
		//BCrypt 클래스의 static 메소드를 이용해서 일치 여부를 얻어낸다. 
		boolean isValid = BCrypt.checkpw(inputPwd, encodedPwd);
		
		//2.만일 일치한다면 새로운 비밀번호를 암호화해서 저장한다.
		if(isValid) {
			//새로운 비밀번호를 암호화 한다.
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String encodedNewPwd = encoder.encode(dto.getNewPwd());
			//암호화된 새 비밀번호를 dto에 다시 넣어준다.
			dto.setNewPwd(encodedNewPwd);
			//dao를 이용해서 db에 반영한다.
			dao.updatePwd(dto);
			//성공
			isSuccess = true;
		}
		
		m.addObject("isValid", isValid);
	}
}
