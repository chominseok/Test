<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html ng-app="myApp">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath }/resources/js/angular.min.js"></script>
<script>
	var myApp = angular.module("myApp",[]);   
                                        //ajax를 하기 위해선 $http 인자로 추가 >> 서버와 통신할 수 있는 함수이다.
	myApp.controller("one",function($scope, $http){
		$scope.send = function(){
			$http({
				method:"post",
		        url:"send.jsp",
		       	params : {msg : $scope.msg},
		        //data:"msg="+$scope.msg,
		        //post일 때만 설정정보를 넣어줘야한다. 알아서 못함 ㅎㅎㅎ
				headers:{"Content-Type":"application/x-www-form-urlencoded;charset=utf-8"}
		      }).success(function(data){
		        // data를 콘솔에 출력해 보기
		        console.log(data);
		        console.log(data.msg);
		        console.log(data.test);
		        $scope.result = data.test;
		      });
		}
		
		$scope.send2 = function(){
			$http({
				method : "get",
				url : "send.jsp",
				params : {msg : $scope.msg2}
			}).success(function(data){
				console.log(data);
			});
		};
	});
</script>
</head>
<body ng-controller="one">
	<h3>$http 서비스 테스트</h3>
	<input type="text" ng-model="msg" placeholder="서버에 할 말 입력,.."/>
	<button ng-click="send()">Ajax 전송(post)</button>
	<p ng-bind="result"></p>
	
	<input type="text" ng-model="msg2" placeholder="서버에 할 말 입력..."/>
	<button ng-click="send()">Ajax 전송(get)</button>
	<p ng-bind="result2"></p>
</body>
</html>