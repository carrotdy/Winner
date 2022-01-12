<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="resources/assets/favicon.ico" />
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="resources/css/styles.css" rel="stylesheet" />
	<meta charset="UTF-8">
	<title>로그인</title>
<style>
.login-id, .login-pw{
	margin-top: 30px;
	margin-bottom: 30px;
}
	
.login-wrapper{
	margin: 0 auto;
	border: 1px solid orange;
	width: 500px;
	text-align: center;
	margin-bottom: 40px;
}
	
.login-logo{
	color:#e95420;
	font-size:30px;
	font-weight: bold;
	margin-top: 50px;
	margin-bottom: 50px;
}
	
.loginform{
	font-size:18px;
}
	
.loginform a{
	text-decoration: none;
	color:black;
}
	
.login-button{
	color:white;
	background-color: #e95420;
	border-radius: 5px;
	width:240px;
	height:50px;
	border: 0px;
	margin-top: 30px;
	margin-bottom: 30px;
	font-size:20px;
}
	
.joindiv{
	margin-bottom:40px;
}
</style>
</head>
<body>
	<c:import url="/WEB-INF/views/common/header.jsp"/>
		<div class="login-wrapper">
			<div class="login-logo">
			<img src="" style="width:50px; height:50px;"><span>로그인</span>				
			</div>
			<form action="/login" method="post" class="loginform">
				<div class="login-id">
				<label for="memberId">　아이디</label>
				<input type="text" name="memberId" id="memberId" placeholder="아이디를 입력해주세요">
				</div>	
				<div class="login-pw">
				<label for="memberPw">비밀번호</label>
				<input type="password" name="memberPw" id="memberPw" placeholder="비밀번호를 입력해주세요">
				</div>
				<div>
				<a href="/findIdFrm">아이디 찾기</a> / <a href="/findPwFrm">비밀번호 찾기</a>
				</div>
				<div>
				<input type="submit" value="로그인" class="login-button">
				</div>
				<div class="joindiv">
					<a href="/joinFrm">회원가입</a>
				</div>
			</form>
		</div>
	</div>
	<c:import url="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>