
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
</head>

<body>
	<div class="container">
		<h1>비밀번호 수정하기</h1>
		<form action="pwd_update.do" method="post" id="myForm">
			<div class="form-group">
				<input type="hidden" name = "pwd2" value="${dto.getPwd()}" id="pwd2"/>
				<label for="pwd">기존 비밀번호</label>
				<input class="form-control" type="password" name="pwd" id="pwd"/>
			</div>
			<div class="form-group">
				<label for="newPwd">새 비밀번호</label>
				<input class="form-control" type="password" name="newPwd" id="newPwd"/>
			</div>
			<div class="form-group">
				<label for="newPwd2">새 비밀번호 확인</label>
				<input class="form-control" type="password" id="newPwd2"/>
			</div>
			<button class="btn btn-outline-primary" type="submit">수정하기</button>
			<a class="btn btn-outline-warning" href="${pageContext.request.contextPath }/users/private/info.do">돌아가기</a>
		</form>
	</div>
	<script>
	console.log("${dto.getPwd()}");
	console.log("${sessionScope.id}");
	//form의 유효성은 js로 작성한다. 
		$('#myForm').on("submit",function(){
			var pwd1 = $('#newPwd').val();
			var pwd2 = $('#newPwd2').val();
			
			var pwd3 = $('#pwd').val();
			var pwd4 = $('#pwd2').val();
			
 			if(pwd3 != pwd4){
 				alert("기존 비밀번호가 일치하지 않습니다.");
 				$('#pwd').val("").focus();
 				
 				if(pwd1 != pwd2){
 					alert("비밀번호가 일치하지 않습니다.");
 					$('#newPwd').val("").focus();
 					$('#newPwd2').val("");
 					return false;
 				}
 				return false;
 			} 
			
			
		});
	</script>
</body>
</html>