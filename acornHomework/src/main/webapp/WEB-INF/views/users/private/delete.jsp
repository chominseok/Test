<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
</head>
<body>
	<%
		session.invalidate();
	%>
	<script>
		alert("회원 탈퇴가 완료되었습니다!");
		location.href="${pageContext.request.contextPath }/home.do";
	</script>
</body>
</html>