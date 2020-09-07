<%@page import="java.net.URLEncoder"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	<c:choose>
		<c:when test="${isSuccess }">
			<p><strong>${id }</strong>님 로그인 되었습니다. </p>
			<a href="${url }">확인</a>
		</c:when>
		<c:otherwise>
			<p>로그인에 실패하였습니다.</p>
			<a href="loginform.do?url=${encodedUrl }">다시 시도</a>
		</c:otherwise>
	</c:choose>
</body>
</html>