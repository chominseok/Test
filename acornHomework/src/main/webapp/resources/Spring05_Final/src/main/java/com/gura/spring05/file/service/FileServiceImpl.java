package com.gura.spring05.file.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.exception.NotDeleteException;
import com.gura.spring05.file.dao.FileDao;
import com.gura.spring05.file.dto.FileDto;

@Service
public class FileServiceImpl implements FileService{
	@Autowired
	private FileDao fileDao;

	//한 페이지에 나타낼 row 의 갯수
	final int PAGE_ROW_COUNT=5;
	//하단 디스플레이 페이지 갯수
	final int PAGE_DISPLAY_COUNT=5;
	
	@Override
	public void getList(HttpServletRequest request) {
		//로그인 된 아이디 읽어오기 (로그인을 하지 않으면 null 이다)
		String id = (String)request.getSession().getAttribute("id");
		
		
		//보여줄 페이지의 번호
		int pageNum=1;
		//보여줄 페이지의 번호가 파라미터로 전달되는지 읽어와 본다.	
		String strPageNum=request.getParameter("pageNum");
		if(strPageNum != null){//페이지 번호가 파라미터로 넘어온다면
			//페이지 번호를 설정한다.
			pageNum=Integer.parseInt(strPageNum);
		}
		//보여줄 페이지 데이터의 시작 ResultSet row 번호
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//보여줄 페이지 데이터의 끝 ResultSet row 번호
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		/*
			검색 키워드에 관련된 처리 
		*/
		String keyword=request.getParameter("keyword");
		String condition=request.getParameter("condition");
		if(keyword==null){//전달된 키워드가 없다면 
			keyword=""; //빈 문자열을 넣어준다. 
			condition="";
		}
		//인코딩된 키워드를 미리 만들어 둔다. 
		String encodedK=URLEncoder.encode(keyword);
		
		//검색 키워드와 startRowNum, endRowNum 을 담을 FileDto 객체 생성
		FileDto dto=new FileDto();
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);

		if(!keyword.equals("")){ //만일 키워드가 넘어온다면 
			if(condition.equals("title_filename")){
				//검색 키워드를 FileDto 객체의 필드에 담는다. 
				dto.setTitle(keyword);
				dto.setOrgFileName(keyword);

			}else if(condition.equals("title")){
				dto.setTitle(keyword);
				
			}else if(condition.equals("writer")){
				dto.setWriter(keyword);
				
			}
		}
		//파일 목록 얻어오기
		List<FileDto> list = fileDao.getList(dto);
		
		//전체 row 의 갯수를 담을 변수 
		int totalRow=fileDao.getCount(dto);
		
		//전체 페이지의 갯수 구하기
		int totalPageCount=
				(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);//정수를 정수로 나눠봐야 실수가 아니기 때문에 pageNum = 1~5 사이에서는 다 1페이지가 시작 번호
		//시작 페이지 번호
		int startPageNum=   
			1+((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		//끝 페이지 번호가 잘못된 값이라면   항상 글의 갯수가 맞춰져 있지 않기 떄문에 있는 만큼의 페이지를 끝 페이지로 설정한다.
		if(totalPageCount < endPageNum){
			endPageNum=totalPageCount; //보정해준다. 
		}
		
		//	EL에서 사용할 값을 미리 request에 담아두기
		request.setAttribute("list", list); //List<FileDto> list
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("totalPageCount", totalPageCount);
		request.setAttribute("condition", condition);
		request.setAttribute("keyword", keyword);
		request.setAttribute("encodedK", encodedK);
	}

	@Override
	public void saveFile(FileDto dto, ModelAndView m, HttpServletRequest request) {
		//업로드된 파일의 정보를 가지고 있는 MultipartFile 객체의 참조값 얻어오기
		MultipartFile myFile = dto.getMyFile();
		
		//원본 파일명
		String orgFileName = myFile.getOriginalFilename();
		//파일의 크기
		long fileSize = myFile.getSize();
		//web-app/upload 폴더까지의 실제 경로(서버 경로)
		String realPath = request.getServletContext().getRealPath("/upload");
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
			myFile.transferTo(new File(filePath+saveFileName));
			System.out.println(filePath + saveFileName);
		}catch(Exception e) {
			e.printStackTrace();
		}
		String id = (String)request.getSession().getAttribute("id");
		//dto에 업로드된 파일의 정보를 담는다.
		dto.setWriter(id); //작성자는 세션으로 넣어준다.
		dto.setOrgFileName(orgFileName);
		dto.setSaveFileName(saveFileName);
		dto.setFileSize(fileSize);
		
		fileDao.insert(dto);
		
		m.addObject("dto", dto);
	}

	@Override
	public void getFileData(int num, ModelAndView m) {
		//파일을 다운로드 하기 위해서 num으로 받은 인자를 이용해 Data를 추출하고 담아주기
		FileDto dto = fileDao.getData(num);
		m.addObject("dto", dto);
	}

	@Override
	public void delteFile(int num, HttpServletRequest request) {
		//1.삭제할 파일의 정보를 읽어온다.
		FileDto dto = fileDao.getData(num);
		//2.본인이 작성한 글이 아닌 경우 에러 처리를 한다.
		//String id = (String)request.getSession().getAttribute("id");
		//세션으로 가져온 로그인 되어있는 아이디와 db에 있는 아이디가 일치하지 않는다면 exception을 발생시킨다.
		// >>페이지 상에서는 삭제가 불가능 하지만 주소 치고 들어가면 가능하기 떄문에 (본인꺼는 지우기 가능 ㅎ)
		//if(!id.equals(dto.getWriter())) {
		//	throw new NotDeleteException("가세요 ㅎㅎ");
		//}
//		aspect로 대체합니다.
		
		
		//파일 시스템에서 파일 삭제
		String saveFileName = dto.getSaveFileName();
		
		String path = request.getServletContext().getRealPath("/upload")
				+File.separator+dto.getSaveFileName();
		new File(path).delete();
		//2.db에서 삭제
		fileDao.delete(num);
	}
}
