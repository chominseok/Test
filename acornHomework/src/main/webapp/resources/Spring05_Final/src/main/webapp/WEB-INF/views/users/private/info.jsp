<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#profileImage{
		width : 50px;
		heigth : 50px;
		border : 1px solid black;
		border-radius : 50%;  
	}
</style>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
</head>
<!-- getdate로 한명의 회원 정보 얻어오기! -->
<body>
	<div class="container">
		<h1>${sessionScope.id }님의 회원정보</h1>
		
		<table border="1">
			<thead>
				<tr>
					<th>아이디</th>
					<th><a href="pwd_updateform.do">비밀번호</a></th>
					<th>프로필 이미지</th>
					<th>이메일</th>
					<th>업로드 경로</th>
					<th>가입 날짜</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${sessionScope.id }</td>
					<td>통신보안</td>
					<td>
						<c:choose>     
							<c:when test="${empty dto.profile }">
								<svg id="profileImage" viewBox="0 0 16 16" class="bi bi-person-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  									<path fill-rule="evenodd" d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
								</svg>
							</c:when>
							<c:otherwise>
								<img id="profileImage" src="${pageContext.request.contextPath}${dto.getProfile()}">
							</c:otherwise>
						</c:choose>
					</td>
					<td>${dto.getEmail() }</td>
					<td>${dto.getProfile() }</td>
					<td>${dto.getRegdate() }</td>
				</tr>
			</tbody>
		</table>
		<hr>
		<a class="btn btn-outline-info" href="${pageContext.request.contextPath}/users/private/updateform.do">개인정보 수정</a>
		<a class="btn btn-outline-danger" href="javascript:deleteConfirm()">탈퇴</a>
		<a class="btn btn-outline-primary" href="../../home.do">인덱스로 가기</a>
	</div>
	
<script>
	function deleteConfirm(){
		var isDelete = confirm("${sessionScope.id} 회원님 탈퇴하시겠습니까?");
		if(isDelete){
			location.href="${pageContext.request.contextPath}/users/private/delete.do?id=${id}";
		}
	}
</script>
</body>
</html>