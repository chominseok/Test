package com.test.acorn.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/users/private/*")
public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		
		String id = (String)session.getAttribute("id");
		
		if(id != null) {
			chain.doFilter(request, response);
		}else {
			//원래 가려던 url 정보 읽어오기
			String url = req.getRequestURI();
			//get방식ㅇ 전송 파라미터를 query String으로 얻어오기
			String query = req.getQueryString();
			//인코딩을 한다
			String encodedUrl = null;
			if(query == null) {//전송 파라미터가 없다면
				encodedUrl = URLEncoder.encode(url);
			}else {
				encodedUrl = URLEncoder.encode(url+"?"+query);
			}
			//로그인 폼으로 리다이렉트 이동하라고 응답
			HttpServletResponse res = (HttpServletResponse)response;
			String cPath = req.getContextPath();
			res.sendRedirect(cPath+"/users/loginform.do?url="+encodedUrl);
		}
	}

	@Override
	public void destroy() {
		
	}
	
}
