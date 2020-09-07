<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
<!-- web.xml의 설정으로 home.jsp는 page에 views에 위치하고 있는게 아니라
     최상위 경로에 위치하고 있따. -->
</head>
<!-- spring ioc , di , aop-->
<body>
	<div class="container">
		<c:choose>
			<c:when test="${empty sessionScope.id}">
				<a class="btn btn-outline-primary" href="${pageContext.request.contextPath }/users/loginform.do">로그인</a>
				<a class="btn btn-outline-info" href="${pageContext.request.contextPath }/users/signup_form.do">회원가입</a>
			</c:when>
			<c:otherwise>
				<a href="users/private/info.do"><strong>${sessionScope.id }님 로그인중</strong></a>
				<a class="btn btn-outline-danger" href="${pageContext.request.contextPath }/users/logout.do">로그아웃</a>
			</c:otherwise>
		</c:choose>
		<h1>인덱스 페이지 입니다.</h1>
		<p>Spring Framework 동작중....</p>
		<ul>
			<li><a href="file/list.do">자료실 목록 보기</a></li>
			<li><a href="cafe/list.do">카페 목록 보기</a></li>
			<li><a href="shop/list.do">상품 목록 보기</a></li>
			<li><a href="angularjs/test01.jsp">angular test01</a></li>
			<li><a href="angularjs/test02.jsp">angular test02</a></li>
		</ul>
	</div>
</body>
</html>