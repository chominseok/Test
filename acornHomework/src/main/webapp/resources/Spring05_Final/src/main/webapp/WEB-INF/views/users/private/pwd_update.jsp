<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	<a href="../../home.do">인덱스</a>
	<a href="info.do">인포</a>
	<c:choose>
		<c:when test="${isValid }">
			<p><strong>${sessionScope.id }</strong>님 비밀번호를 수정했습니다.</p>
			
		</c:when>
		<c:otherwise>
			<p>비밀번호 수정에 실파하였습니다.</p>
			
		</c:otherwise>
	
	</c:choose>
</body>
</html>