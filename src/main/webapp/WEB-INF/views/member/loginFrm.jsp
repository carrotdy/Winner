<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Winner</title>
<link rel="stylesheet" href="/resources/css/member/login.css">
</head>
<body>
	<c:import url="/WEB-INF/views/common/header.jsp"/>
		<div class="login-wrapper">
			<div class="login-logo">
			<span>로그인</span>				
			</div>
			<form action="/login.do" method="post" class="loginform">
				<div class="login-id">
				<label for="memberId" style="padding-left:15px;">아이디</label>
				<input type="text" name="memberId" id="memberId" placeholder="아이디를 입력해주세요" style="width:240px;">
				</div>	
				<div class="login-pw">
				<label for="memberPw">비밀번호</label>
				<input type="password" name="memberPw" id="memberPw" placeholder="비밀번호를 입력해주세요" style="width:240px;">
				</div>
				<div>
				<a href="/findIdFrm.do">아이디 찾기</a> / <a href="/findPwFrm.do">비밀번호 찾기</a>
				</div>
				<div>
				<input type="submit" value="로그인" class="login-button">
				</div>
				<div class="joindiv">
					<a href="/joinFrm.do">회원가입</a>
				</div>
			</form>
		</div>
	<c:import url="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>