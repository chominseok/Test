<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
</head>
<body>
	<div class="container">
		<h1>회원 가입이 완료되었습니다!</h1>
		<a class="btn btn-outline-info" href="${pageContext.request.contextPath }/users/login_form.do">로그인하러 가기</a>
		<a class="btn btn-outline-primary" href="${pageContext.request.contextPath }/home.do">인덱스로</a>
	</div>
</body>
</html>