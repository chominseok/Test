<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1>회원가입이 완료되었습니다.</h1>
		<p><strong>${param.id }</strong>님 안녕하세요?</p>		
		<p>당신의 비밀번호는 <strong>${param.pwd }이고</strong></p>
		<p>이메일은  <strong>${param.email } 입니다 .ㅎㅎㅎㅎㅎ</strong></p>
		<p>가입 날짜 <strong>${param.regdate }</strong></p>
		<a href="loginform.do">로그인 하러가기</a>
		<a href="../home.do">홈으로 가기</a>
	</div>
</body>
</html>