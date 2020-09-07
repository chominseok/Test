package com.gura.spring05.file.view;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import com.gura.spring05.file.dto.FileDto;

/*
 * [View를 만들어서 동작 시키는 방법]
 * 
 * -AbstractView 추상 클래스르 상속받아서 클래스를 정의한다.
 * 	component scan : bean이 아니지만 @어노테이션을 붙여서 bean이 될 애들을 로딩 시 bean으로 만들어준다.
 * -@Component("마음대로 이름") 어노테이션을 붙여서 이름이 있는 bean이 되게 한다.
 * -Servlet-context.xml에 BeanNameViewResolver 설정을 추가한다.
 * 
 * */
@Component("fileDownView") //이름은 마음대로 지을 수 있음 단, 다른 이름과 겹치면 안됨
public class FileDownView extends AbstractView{

	@Override						
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//ModelAndView 객체에 .addObject()해서 담은 객체는 model에 들어있따.
		FileDto dto = (FileDto)model.get("dto");
	
		//해당 파일을 실제로 다운로드 시켜준다.(응답)
		//다운로드 시켜주기 ... 
		String orgFileName=dto.getOrgFileName(); //원본 파일명
		String saveFileName=dto.getSaveFileName(); //저장된 파일명
		
		//다운로드 시켜줄 파일의 실제 경로 구성하기 application은 servletContext 객체 request에서 얻어낸다.
		String path=request.getServletContext().getRealPath("/upload")
				+File.separator+saveFileName;
		System.out.println(path);
		//다운로드 할 파일을 읽어올 스트림 객체 생성하기
		FileInputStream fis=new FileInputStream(path);
		
		//다운로드 시켜주는 작업을 한다. (실제 파일 데이터와 원본파일명을 보내줘야한다.)
		//다운로드 시켜주는 작업을 한다. 
		String encodedName=null;
		System.out.println(request.getHeader("User-Agent"));
		//한글 파일명 세부처리 
		if(request.getHeader("User-Agent").contains("Firefox")){
			//벤더사가 파이어 폭스인경우 
			encodedName=new String
				(orgFileName.getBytes("utf-8"),"ISO-8859-1");
		}else{ //그외 다른 벤더사 
			encodedName=URLEncoder.encode(orgFileName, "utf-8");
			//파일명에 공백이있는 경우 처리 
			encodedName=encodedName.replaceAll("\\+"," ");
		}
		
		//응답 헤더 정보 설정
		response.setHeader("Content-Disposition","attachment;filename="+encodedName);
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		//다운로드할 파일의 크기 읽어와서 다운로드할 파일의 크기 설정
		response.setContentLengthLong(dto.getFileSize());
		
		//클라이언트에게 출력할수 있는 스트림 객체 얻어오기
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		//한번에 최대 1M byte 씩 읽어올수 있는 버퍼
		byte[] buffer=new byte[1024*1000];
		int readedByte=0;
		//반복문 돌면서 출력해주기
		while(true){
			//byte[] 객체를 이용해서 파일에서 byte 알갱이 읽어오기
			readedByte = fis.read(buffer);
			if(readedByte == -1)break; //더이상 읽을 데이터가 없다면 반복문 빠져 나오기
			//읽은 만큼 출력하기
			bos.write(buffer, 0, readedByte);
			bos.flush(); //출력
		}
		//스트림 닫아주기
		bos.close();
		fis.close(); 
		
//		이 코드 자체가 응답을 해주는 코드임 >> CONTROLLER에서도 응답을 해주려고 하니까 동작은 하지만 오류가 발생
//	    JAVA.LANG.ILLEGALSTATEEXCEPTION: 응답이 이미 커밋된 후에는, SENDREDIRECT()를 호출할 수 없습니다.
	}
}
