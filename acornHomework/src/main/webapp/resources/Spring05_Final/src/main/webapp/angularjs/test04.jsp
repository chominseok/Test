<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath }/resources/js/angular.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/jquery-3.5.1.js"></script>
<link rel="stylesheet" href="../resources/css/bootstrap.css" />
</head>
<body ng-app>
	<div class="container">
		<h1>form 검증</h1>
		<!-- novalidate > 웹브라우저에서 자체 유효성 검증을 하지 못하도록 하는 설정 -->
		<form action="insert.jsp" method="post" name="myForm" novalidate>
		<!-- 입력한 문자열을 id 라는 모델명으로 관리,ng-required : 반드시 값이 있어야 한다.. -->
			<input type="text" name="id" ng-model="id" ng-required="true"/>
			<p ng-show="myForm.id.$invalid && myForm.id.$dirty" style="color : red;">아이디는 반드시 입력하세요오</p>
			<p ng-hide="myForm.id.$valid || myForm.id.$pristine" style="color : red;s">아이디를 입력하세요</p>
			<button ng-disabled="myForm.id.$invalid">제출</button>
		</form>
		<p>입력한 아이디 : <strong>{{id}}</strong></p>
		<p>아이디 유효성 : <strong>{{myForm.id.$valid}}</strong></p>
		<p>아이디 유효성2 : <strong>{{myForm.id.$invalid}}</strong></p>
		<!-- 한번도 입력한 적이 없으면 true <<>> false -->
		<p>한번이라도 입력한 적이 없으면 true : <strong>{{myForm.id.$pristine}}</strong></p>
		<p>한번이라도 입력한 적이 없으면 false : <strong>{{myForm.id.$dirty}}</strong></p>
	</div>
</body>
</html>