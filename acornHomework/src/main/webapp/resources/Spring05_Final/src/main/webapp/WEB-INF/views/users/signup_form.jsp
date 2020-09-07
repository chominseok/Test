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
		<h1>회원 가입 폼 입니다.</h1>
		<form action="signup.do" method="post" id="myForm">
			<div class="form-group">
<!-- 				form-group 안에서 invalid-feedback class를 가지고 있고, 
					한 요소에 is-valid or is-invliad를 쓰면 display none에서 block로 바뀜		-->
				<label for="id">아이디</label>
				<input class="form-control" type="text" name="id" id="id"/>
				<small class="form-text text-muted">영 소문자로 시작하고 5~10글자를 입력하세요</small>
				<div class="invalid-feedback">
					사용할 수 없는 아이디 입니다.
				</div>
			</div>
			<div class="form-group">
				<label for="pwd">비밀번호</label>
				<input class="form-control" type="password" name="pwd" id="pwd"/>
				<small class="form-text text-muted">5~10글자로 입력하라~</small>
			</div>
			<div class="form-group">
				<label for="pwd2">비밀번호 확인</label>
				<input class="form-control" type="password" name="pwd2" id="pwd2"/>
				<div class="invalid-feedback">
					비밀번호를 다시 입력해주세요.
				</div>
			</div>
			<div class="form-group">
				<label for="email">이메일</label>
				<input class="form-control" type="text" name="email" id="email"/>
				<div class="invalid-feedback">
					이메일을 형식에 맞게 입력해주세요.
				</div>
			</div>
			<button class="btn btn-outline-primary" type="submit">전송</button>
			<button class="btn btn-outline-danger" type="reset">취소</button>
		</form>
		<a href="../home.do">인덱스로 돌아가기</a>
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
		isFormValid = isIdValid && isPwdValid && isEmailValid;
		
		if(!isFormValid){
			alert("입력란을 확인해주세요");
			return false;
		}
	});
	
	$('#id').on('input',function(){
		
		var inputId = $('#id').val();
		//아이디를 형식에 맞게 입력했는지 여부
		var result = reg_id.test(inputId);
		$(this).removeClass('is-invalid is-valid');
		//중복 여부
		if(result){
			//ajax 를 이용해서 서버에 보낸후 결과를 응답 받는다.
			$.ajax({
				method:"GET",
				url:"checkid.do",
				data:"inputId="+inputId,
				success:function(data){
					console.log(data.isExist);
					//data => {isExist:true} or {isExist:false} 인 object 이다.
					if(data.isExist){//이미 존재하는 아이디임으로 사용 불가
						isIdValid = false;
						$('#id').addClass('is-invalid');
					}else{//사용가능 
						isIdValid = true;
						$('#id').addClass('is-valid');
					}
	 			}
			});
		}else{
			isIdValid = false;
			$('#id').addClass('is-invalid');
		}
	});
	
	//비밀번호 입력란 혹은 비밀번호 확인란에  input 이벤트 처리
	$('#pwd, #pwd2').on('input',function(){
		//입력한 비밀번호를 읽어오기
		var inputPwd = $('#pwd').val();
		var inputPwd2 = $('#pwd2').val();
		
		//형식에 맞게 입력했는지 여부
		var result = reg_pwd.test(inputPwd);
		
// 		$('#pwd').removeClass('is-invalid is-valid');
		$('#pwd2').removeClass('is-invalid is-valid');
		//2개일 때 해당 요소 하나씩만 작동
// 		$(this).removeClass('is-invalid is-valid');

		if(result){
			if(inputPwd == inputPwd2){ //형식이 맞고 비밀번호가 같다면
				isPwdValid = true;
				$('#pwd2').addClass('is-valid');
			}else{//형식은 같으나 비밀번호가 다르면
				isPwdValid = false;
				$('#pwd2').addClass('is-invalid');
			}
		}else{
			isPwdValid = false;
			$('#pwd2').addClass('is-invalid');
		}
	});
	
	$('#email').on('input',function(){
		var inputEmail = $('#email').val();
		
		isEmailValid = reg_email.test(inputEmail);
		$(this).removeClass('is-invalid is-valid');
		if(isEmailValid){
			$(this).addClass('is-valid');
		}else{
			$(this).addClass('is-invalid');
		}
	});
	</script>
</body>
</html>