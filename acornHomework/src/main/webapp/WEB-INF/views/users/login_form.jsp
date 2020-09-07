<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
</head>
<body>
	<div class="container">
		<a class="btn btn-outline-primary" href="${pageContext.request.contextPath }/home.do">인덱스로</a>
		<h1>로그인 페이지 입니다.</h1>
		<form action="${pageContext.request.contextPath }/users/login.do" method="post">
			<div class="form-group">
				<label for="id">아이디</label>
				<input type="text" id="id" name="id" class="form-control"/>
			</div>
			<div class="form-group">
				<label for="pwd">비밀번호</label>
				<input type="password" id="pwd" name="pwd" class="form-control"/>
			</div>
			<button type="submit" class="btn btn-outline-primary">로그인</button>
		</form>
	</div>
</body>
</html>