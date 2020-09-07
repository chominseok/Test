<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<c:choose>
			<c:when test="${loginValid eq true }">
				<h1>로그인에 성공하셨습니다.!</h1>
				<a href="${pageContext.request.contextPath }/home.do">인덱스로 가기</a>
			</c:when>
			<c:otherwise>
				<h1>로그인에 실패하였습니다.</h1>
				<a href="${pageContext.request.contextPath }/users/login_form.do">다시 로그인하기</a>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>