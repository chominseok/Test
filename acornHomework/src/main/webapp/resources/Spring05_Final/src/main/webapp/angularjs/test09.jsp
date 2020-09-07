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
		//폼에 입력한 내용을 담을 모델
		$scope.formData = {};
		 
		$scope.send = function(){
			$http({
				method:"post",
		        url:"member_insert.jsp",
		       	params : $scope.formData,
		        //data:"msg="+$scope.msg,
				headers:{"Content-Type":"application/x-www-form-urlencoded;charset=utf-8"}
		      }).success(function(data){
		        	console.log(data);
		        	console.log(data.isSuccess);
		      });
		}
	});
</script>
</head>
<body ng-controller="one">
	<h3>$http 서비스 테스트</h3>
	<form ng-submit="send()">
		이름 <input type="text" ng-model="formData.name"/>
		주소 <input type="text" ng-model="formData.addr"/>
		<button type="submit">전송</button>
	</form>
	<p>{{formData}}</p>
</body>
</html>