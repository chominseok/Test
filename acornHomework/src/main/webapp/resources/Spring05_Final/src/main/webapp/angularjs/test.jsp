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
<script>
	var myApp = angular.module("myApp",[]);
	
	myApp.controller("minseok",function($scope, $http){
		$scope.obj = {};
		
		$scope.send = function(){
			alert("동작함?");
			$http({
				method:"post",
		        url:"member_insert.jsp",
				params : $scope.obj,
				headers:{"Content-Type":"application/x-www-form-urlencoded;charset=utf-8"}
			}).success(function(data){
				console.log(data);
				console.log(data.name);
				console.log(data.addr);
				$scope.nick = data.name;
				$scope.addr = data.addr;
			});	
		}
	});
</script>
</head>
<body ng-app="myApp">
	<div ng-controller="minseok">
		<h1>ajax 테스트하기</h1>
		<p>{{obj}}</p>
		이름<input type="text" ng-model="obj.name"/>
		주소<input type="text" ng-model="obj.addr"/>
		<button ng-click="send()">전송</button>
		<p>이름 : {{nick}}</p>
		<p>주소 : {{addr}}</p>
	</div>
</body>
</html>