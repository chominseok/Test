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
		<a href="${pageContext.request.contextPath }/home.do">인덱스로</a>
		<c:choose>
			<c:when test="${empty id }">
				<p>
					<a class="btn btn-outline-primary" href="${pageContext.request.contextPath }/users/loginform.do">로그인</a>
					<a class="btn btn-outline-info" href="${pageContext.request.contextPath }/users/signup_form.do">회원가입</a>
				</p>
			</c:when>
			<c:otherwise>
				<p><strong>${id }님 로그인 중 입니다.</strong></p>
			</c:otherwise>
		</c:choose>
		<h1>상품 목록 입니다.</h1>
		<div class="row">
			<c:forEach var="tmp" items="${list }">
				<div class="col">
					<div class="card">
						<img class="card-img-top" src="${pageContext.request.contextPath }/resources/images/1.jpg"/>
						<div class="card-body">
							<h5>상품번호 : ${tmp.num }</h5>
							<h3 class="card-title">${tmp.name }</h3>
							<p class="card-text">
								가격 : ${tmp.price }
								재고 : ${tmp.remainCount}
							</p>
							<a class="card-link" href="${pageContext.request.contextPath }/shop/private/buy.do?num=${tmp.num }">구입하기</a>
						</div>
					</div>
				</div>
			</c:forEach>		
		</div>
	</div>
</body>
</html>