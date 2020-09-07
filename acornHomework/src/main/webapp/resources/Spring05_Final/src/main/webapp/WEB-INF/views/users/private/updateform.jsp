<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath }/resources/js/jquery-3.5.1.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/jquery.form.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
<style>
	#profileImage{
		width : 100px;
		height : 100px;
		border : 1px solid black;
		border-radius : 50%;
		curser : pointer;
	}
</style>
</head>
<body>
	<div class="container">
		<h1>회원정보 수정 폼 입니다.</h1>
		<c:choose>     
			<c:when test="${empty dto.profile }">
				<a id="profileLink" href="javascript:">
					<svg id="profileImage" viewBox="0 0 16 16" class="bi bi-person-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
								<path fill-rule="evenodd" d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
					</svg>
				</a>
			</c:when>
			<c:otherwise>
				<a id="profileLink" href="javascript:">
					<img id="profileImage" src="${pageContext.request.contextPath}${dto.getProfile()}">
				</a>
			</c:otherwise>
		</c:choose>
		
		<form action="update.do" method="post">
			<input type="hidden" name="profile" id="profile" value="${dto.profile}"/>
			<div class="form-group">
				<label for="id">아이디</label>
				<input class="form-control" type="text" id="id" value="${dto.id}" disabled/>
				<input class="form-control" type="hidden" name="id" id="id" value="${dto.id}" />
			</div>
			<div class="form-group">
				<label for="email">이메일</label>
				<input class="form-control" type="text" id="email" name = "email" value="${dto.email}"/>
			</div>
			<button class="btn btn-outline-primary" type="submit">수정확인</button>
			<button class="btn btn-outline-secondary" type="reset">취소</button>
		</form>
		
		<form id="profileForm" action="profile_upload.do" method="post" enctype="multipart/form-data">
			<input style="display : none;" id="image" type="file" name="image" accept=".jpg, .jpeg, .JPG, .JPEG"/>
		</form>
		<a href="${pageContext.request.contextPath }/users/private/info.do">인포</a>
	</div>
	<script>
		$('#profileLink').on('click',function(){
			//input type="file" 을 클릭한다. >> img를 선택하면 change event 가 실행된다.
			$('#image').click();	
		});
		
		//이미지를 선택했을 때 실행할 함수 등록
		$('#image').on('change',function(){
			//폼을 강제 제출한다.
			$('#profileForm').submit();
		});
		
		//폼이 ajax로 제출될 수 있또록 플러그인을 동작 시킨다.
		$('#profileForm').ajaxForm(function(data){
			//기존 프로필 이미지 요소를 제거한다. 
			$('#profileImage').remove();
			//새로 img 요소를 만들어 #profileLink에 추가한다.
			$('<img/>').attr('id','profileImage')
			.attr('src',"${pageContext.request.contextPath }"+data.imageSrc)
			.appendTo('#profileLink');
			console.log(data.imageSrc);
			//회원정보 수정폼이 전송될 때 dto.getProfile()도 null을 방지하기 위해서 같이 전송 되도록 한다.
			$('#profile').val(data.imageSrc);  //input type="hidden"의 value 값
		});

	</script>
</body>
</html>