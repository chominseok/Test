<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>/file/private/upload.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
</head>
<body>
<div class="container">
	<h1>파일이 업로드 되었습니다.</h1>
	<table border="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>파일명</th>
				<th>파일크기</th>
				<th>추가한 날짜</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${dto.num }</td>
				<td>${dto.title} }</td>
				<td>${dto.writer }</td>
				<td>${dto.saveFileName }</td>
				<td>${dto.fileSize }</td>
				<td>${dto.regdate }</td>
			</tr>
		</tbody>
	</table>
	<a href="${pageContext.request.contextPath }/file/list.do">돌아가기</a>
</div>
</body>
</html>



