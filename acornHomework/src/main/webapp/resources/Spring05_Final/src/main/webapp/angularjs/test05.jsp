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
		<!-- 입력한 문자열을 id 라는 모델명으로 관리, 반드시 입력해야한다. -->
			<div class="form-group">
				<label for="id">아이디</label>
				<input ng-model="id" ng-required="true" type="text" name="id" id="id" class="form-control" 
				ng-class="{'is-invalid': myForm.id.$invalid && myForm.id.$dirty, 'is-valid': myForm.id.$valid}"/>
				<div class="invalid-feedback">아이디는 반드시 입력하세요</div>
				<div class="valid-feedback">아이디를 제대로 입력 했습니다</div>
			</div>
			<div class="form-group">
				<label for="nick">닉네임</label>
				<input ng-model="nick" type="text" name="nick" id="nick" class="form-control" ng-required="true"
					   ng-class="{'is-invalid': myForm.nick.$invalid && myForm.nick.$dirty,'is-valid': myForm.nick.$valid}"
					   ng-minlength="3" ng-maxlength="10" ng-pattern="/^[가-휳]+$/">
				<div class="invalid-feedback">
					3~10 글자 이내로 입력하세요.
				</div>
				<div class="valid-feedback">
					유효한 아이디 입니다.
				</div>
			</div>
			<button ng-disabled="myForm.id.$invalid || myForm.nick.$invalid">제출</button>
		</form>
		
		<p>입력한 아이디 : <strong>{{id}}</strong></p>
		<p>아이디 유효성 : <strong>{{myForm.id.$valid}}</strong></p>
		<p>아이디 유효성2 : <strong>{{myForm.id.$invalid}}</strong></p>
		<!-- 한번도 입력한 적이 없으면 true <<>> false -->
		<p>한번이라도 입력한 적이 없으면 true : <strong>{{myForm.id.$pristine}}</strong></p>
		<p>한번이라도 입력한 적이 없으면 false : <strong>{{myForm.id.$dirty}}</strong></p>
		
		<hr />
		
		<p>입력한 닉네임 : <strong>{{nick}}</strong></p>
		<p>닉네임 유효성 : <strong>{{myForm.nick.$valid}}</strong></p>
		<p>닉네임 유효성2 : <strong>{{myForm.nick.$invalid}}</strong></p>
		<!-- 한번도 입력한 적이 없으면 true <<>> false -->
		<p>한번이라도 입력한 적이 없으면 true : <strong>{{myForm.nick.$pristine}}</strong></p>
		<p>한번이라도 입력한 적이 없으면 false : <strong>{{myForm.nick.$dirty}}</strong></p>
		
		<p>닉네임 입력을 안했는지 여부: <strong>{{myForm.nick.$error.required}}</strong></p>
		<p>닉네임을 입력했는지 여부 : <strong>{{!myForm.nick.$error.required}}</strong></p>
		
		<p>닉네임 최소 글자를 만족했는지 여부 : <strong>{{!myForm.nick.$error.minlength}}</strong></p>
		<p>닉네임 최대 글자를 만족했는지 여부 : <strong>{{!myForm.nick.$error.maxlength}}</strong></p>
		<p>닉네임을 정규표현식에 만족했는지여부 : <strong>{{!myForm.nick.$error.pattern}}</strong></p>
		
		<hr />
		<p>폼 전체의 유효성 여부 valid <strong>{{myForm.$valid}}</strong></p>
		<p>폼 전체의 유효성 여부2 invalid <strong>{{myForm.$invalid}}</strong></p>
	</div>
</body>
</html>