package com.gura.spring05.users.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.users.dto.UsersDto;
import com.gura.spring05.users.service.UsersService;

@Controller
public class UsersController {
	@Autowired
	private UsersService service;
	
	@RequestMapping("/users/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/home.do";
	}
	
	//회원가입 폼 요청처리
	@RequestMapping("/users/signup_form")
	public String signupForm() {
		
		return "users/signup_form";
	}
	
	//아이디가 존재하는지 여부를 처리하는 요청처리
	@RequestMapping("/users/checkid")
	@ResponseBody
	public Map<String, Object> checkid(@RequestParam String inputId){
		//ajax 요청이기 때문에 json응답을 위해 리턴타입을 map으로 함
		Map<String, Object> map  = service.isExistId(inputId);
		
		return map;
	}
	
	@RequestMapping("/users/signup")
	public ModelAndView signup(UsersDto dto, ModelAndView m) {
		service.addUser(dto);
		//dto로 model에 안담아서 넘어갈 때는 param으로 넘어감
		m.setViewName("users/signup");
		return m;
	}
	
	@RequestMapping("/users/loginform")
	public String loginform(HttpServletRequest request) {
		//url 파라미터가 넘어오는지 읽어와보기
		String url = request.getParameter("url");
		if(url == null){//목적지 정보가 없다면
			String cPath = request.getContextPath();
			url = cPath+"/home.do";  //로그인 후 인덱스 페이지로 가도록
		}
		request.setAttribute("url", url);
		return "users/loginform";
	}
	
	@RequestMapping("/users/login")
	public ModelAndView login(UsersDto dto, HttpSession session, HttpServletRequest request) {
		ModelAndView m = new ModelAndView();
		//로그인 후 가야하는 목적지 정보
		String url = request.getParameter("url");
		//목적지 정보도 미리 인코딩이 해 놓는다.
		String encodedUrl = URLEncoder.encode(url);
		
//		request.setAttribute("url", url);
//		request.setAttribute("encodedUrl", encodedUrl);
		
		m.addObject("url", url);
		m.addObject("encodedUrl", encodedUrl);
		
		service.loginProcess(dto, m, session);
		m.setViewName("users/login");
		return m;
	}	
	
	@RequestMapping("/users/private/info")
	public ModelAndView info(HttpServletRequest req,ModelAndView m) {
		//service에서 session으로 id를 받음 , 여기서도 session을 받아도 되지만 requset 써 봄
		service.getInfo(req, m);
//		service.getInfo(req.getSession(), m); //session으로 인자를 받을 떄
//		service.getInfo(session, m);
		m.setViewName("users/private/info");
		
		return m;
	}
	
	@RequestMapping("/users/private/delete")
	public ModelAndView delete(HttpSession session, ModelAndView m) {
		service.deleteUser(session);
		m.setViewName("users/private/delete");
		return m;
	}
	
	
	@RequestMapping("/users/private/updateform")
	public ModelAndView updateForm(HttpServletRequest req, ModelAndView m) {
		service.getInfo(req, m);
		
		m.setViewName("users/private/updateform");
		return m;
	}

	//ajax 프로필 사진 업로드 요청 처리
	@RequestMapping("/users/private/profile_upload")
	@ResponseBody							//servlet-context에 bean을 설정해줘야 작동  //뒤에 request를 받는 코드가 있어서 추가
	public Map<String, Object> prifile_upload(MultipartFile image, HttpServletRequest req){
		Map<String, Object> map = service.saveProfileImage(req, image);
		
		return map;
	}
	
	@RequestMapping("/users/private/update")
	public ModelAndView update(HttpServletRequest req, UsersDto dto, ModelAndView m) {
		service.updateUser(req.getSession(), dto);
		
		m.setViewName("redirect:/users/private/info.do");
		return m;
	}
	
	@RequestMapping("/users/private/pwd_updateform")
	public ModelAndView pwd_updateform(ModelAndView m, HttpServletRequest req) {
		service.getInfo(req, m);
		m.setViewName("users/private/pwd_updateform");
		return m;
	}
	
	@RequestMapping("/users/private/pwd_update")
	public ModelAndView pwdUpdate(ModelAndView m, UsersDto dto, HttpServletRequest req) {
		service.updateUserPwd(req.getSession(), dto, m);
		
		m.setViewName("users/private/pwd_update");
		return m;
	}
	
}
