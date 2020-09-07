<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
</head>

<body>
	<div class="container">
		<h1>로그인 폼</h1>
		
		<form action="login.do" method="post">
			<%--원래 가려던 목적지 정보를 url 이라는 파라미터 명으로 가지고 간다. --%>
			<input type="hidden" name="url" value="${url }"/>
			<div class="form-group">
				<label for="id">아이디</label>
				<input class="form-control" type="text" name="id" id="id"/>
			</div>
			<div class="form-group">
				<label for="pwd">비밀번호</label>
				<input class="form-control" type="password" name="pwd" id="pwd"/>
			</div>
			<label>
				<input type="checkbox" name="isSave" value="yes"/>
				로그인 정보 저장
			</label>
			<button class="btn btn-primary" type="submit">로그인</button>
		</form>
		<a class="btn btn-outline-info" href="../home.do">인덱스</a>
	</div>
	
</body>
</html>