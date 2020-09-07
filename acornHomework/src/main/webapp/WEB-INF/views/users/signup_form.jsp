<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<a class="btn btn-outline-primary" href="${pageContext.request.contextPath }/home.do">인덱스로</a>
		<h1>회원 가입 폼 입니다.</h1>
		<form action="${pageContext.request.contextPath }/users/signup.do" method="post" id="myForm">
			<div class="form-group">
				<label for="id">아이디</label>
				<input type="text" id="id" name="id" class="form-control" />
				<small class="form-text text-muted">영 소문자로 시작하고 5~10글자를 입력하세요</small>
				<div class="invalid-feedback">
					아이디 형식을 맞춰주세요.
				</div>
			</div>
			<div class="form-group">
				<label for="pwd">비밀번호</label>
				<input type="password" id="pwd" name="pwd" class="form-control" />
			</div>
			<div class="form-group">
				<label for="pwd2">비밀번호 확인</label>
				<input type="password" id="pwd2" class="form-control" />
				<div class="invalid-feedback">
					비밀번호가 다릅니다.
				</div>
				<div class="valid-feedback">
					비밀번호가 같습니다.
				</div>
			</div>
			<div class="form-group">
				<label for="email">이메일</label>
				<input type="text" id="email" name="email" class="form-control" />
				<div class="invalid-feedback">
					유효하지 않은 이메일 주소입니다.
				</div>
			</div>
			<button class="btn btn-outline-primary" type="submit">회원가입</button>
		</form>
	</div>
<script>
	//아이디를 검증할 정규 표현식
	var reg_id = /^[a-z].{4,9}$/;
	//비밀번호를 검증할 정규 표현식
	var reg_pwd = /^.{5,10}$/;
	//이메일을 검증할 정규 표현식
	var reg_email = /@/;
	
	//각각의 input 요소의 유효성 여부
	var  isIdValid = false;
	var  isPwdValid = false;
	var  isEmailValid = false;
	
	//폼 전체의 유효성 여부
	var isFormValid = false;

	$('#myForm').on('submit',function(){
		isFormValid = isIdValid == isPwdValid== isEmailValid;
		console.log(isFormValid);
		if(!isFormValid){
			alert("입력창을 확인해주세요.");
			return false;
		}
	});
	
	$('#id').on('input',function(){
		var id = $('#id').val();
		var result = reg_id.test(id);
 		$('#id').removeClass('is-valid is-invalid');
		
		if(result){
			$.ajax({
				method : 'get',
				url : 'checkId.do',
				data : 'id='+id,
				success:function(data){
					console.log("data.isValid = "+data.isValid);
					if(data.isValid){
						isIdValid = false;
						$('#id').addClass('is-invalid');
					}else{
						isIdValid = true;
						$('#id').addClass('is-valid');
						console.log("id value = "+isIdValid);
					}
				}
			})
		}else{
			$('#id').addClass('is-invalid');
		}
	});
	
	$('#pwd, #pwd2').on('input',function(){
		var inputPwd = $('#pwd').val();
		var inputPwd2 = $('#pwd2').val();

		var result = reg_pwd.test(inputPwd);
		$('#pwd2').removeClass('is-valid is-invalid');
		if(result){
			if(inputPwd == inputPwd2){
				$('#pwd2').addClass('is-valid');
				isPwdValid = true;
				console.log("pwd value = "+isPwdValid);
			}else{
				$('#pwd2').addClass('is-invalid');
			}	
		}else{
			$('#pwd2').addClass('is-invalid');
		}
	});
	
	$('#email').on('input',function(){
		var inputEmail = $('#email').val();

		var result = reg_email.test(inputEmail);
		$('#email').removeClass("is-valid is-invalid");
		if(result){
			$('#email').addClass("is-valid");
			isEmailValid = true;
			console.log("email value = "+isEmailValid);
		}else{
			$('#email').addClass("is-invalid");
		}
	});
</script>
</body>
</html>