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
</head>
<body>
	<div class="container">
		<c:choose>
			<c:when test="${empty sessionScope.id}">
				<a href="${pageContext.request.contextPath }/users/login_form.do" class="btn btn-outline-info">로그인</a>
				<a href="${pageContext.request.contextPath }/users/signup_form.do" class="btn btn-outline-primary">회원가입</a>
			</c:when>
			<c:otherwise>
				<p><a href="${pageContext.request.contextPath }/users/private/info.do" class="btn btn-outline-primary"><strong>${id }</strong></a>님 안녕하세요!</p>
				<a href="${pageContext.request.contextPath }/logout.do" class="btn btn-outline-danger">로그아웃</a>
			</c:otherwise>
		</c:choose>
		<h1>안녕하세요 테스트 합니다.</h1>
		
		<a href="${pageContext.request.contextPath }/cafe/list.do" class="btn btn-outline-secondary">카페 글 목록</a>
	</div>
</body>
</html>