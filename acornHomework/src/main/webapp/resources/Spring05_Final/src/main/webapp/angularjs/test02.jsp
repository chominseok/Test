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
</head>
<body ng-app>
	<h1 ng-init="count=0">이벤트 처리</h1>
	<button ng-click="count = count + 1">증가</button>	
	<button ng-click="count = count - 1">감소</button>
	<button ng-click="count = 0">리셋</button>
	<p>{{count}}</p>
<!--  출력 기능으로 둘은 같음 -->
	<p ng-bind="count"></p>
	
	<hr />
	<h1>스크립트</h1>
	<button id="bt">증가</button>
	<button id="min">감소</button>
	<button id="reset">리셋</button>
	<p id="pt">0</p>	
<script>
	var idx = 0;
	$('#bt').on('click',function(){
		idx++;
		$('#pt').text(idx);
	});
	
	$('#min').on('click',function(){
		idx--;
		$('#pt').text(idx);
	});
	
	$('#reset').on('click',function(){
		idx = 0;
		$('#pt').text(idx);
	});
</script>
</body>
</html>