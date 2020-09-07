<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
<script src="https://cdn.jsdelivr.net/gh/jquery-form/form@4.3.0/dist/jquery.form.min.js" integrity="sha384-qlmct0AOBiA2VPZkMY3+2WqkHtIQ9lSdAsAn5RUJD/3vA5MKDgSGcdmIv4ycVxyn" crossorigin="anonymous"></script>
<style>
	#profileImage{
		width : 80px;
		height : 80px;
	}
</style>
</head>
<body>
	<div class="container">
		<a class="btn btn-outline-primary" href="${pageContext.request.contextPath }/home.do">인덱스로</a>
		<a class="btn btn-outline-danger"href="javascript:deleteId();">탈퇴</a>
		<h1>회원 정보 입니다!</h1>
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th scope="col">아이디</th>
					<th scope="col">비밀번호</th>
					<th scope="col">이미지</th>
					<th scope="col">업로드 경로</th>
					<th scope="col">가입 날짜</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${dto.id }</td>
					<td>
					<p class="btn btn-outline-secondary pwd">비밀번호 수정</p>
					<form action="${pageContext.request.contextPath }/users/private/update_pwd_form.do" method="post" style="display : none;">
						<input type="password" name="newPwd"/>
						<button type="submit">수정</button>
					</form>
					</td>
					<td>
						<c:choose>     
							<c:when test="${empty dto.profile }">
								<svg id="profileImage" viewBox="0 0 16 16" class="bi bi-person-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  									<path fill-rule="evenodd" d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
								</svg>
							</c:when>
							<c:otherwise>
								<img id="profileImage" src="${pageContext.request.contextPath}${dto.profile}">
							</c:otherwise>
						</c:choose>
					</td>
					<td>${dto.profile }</td>
					<td>${dto.regdate }</td>
				</tr>
			</tbody>
		</table>
		<a class="btn btn-outline-info" href="${pageContext.request.contextPath}/users/private/update_form.do">개인정보 수정</a>
	</div>
<script>
	$('.pwd').on('click',function(){
		$('form').css('display','block');
	});
	
	$('form').ajaxForm({
		success:function(data){
			alert("수정이 완료되었습니다!");
			$('form').css('display','none');
		}
	});
	
	function deleteId(){
		var isDelete = confirm("탈퇴하시겠습니까?");
		if(isDelete){
			location.href="${pageContext.request.contextPath}/users/private/delete.do";
		}
	}
</script>
</body>
</html>