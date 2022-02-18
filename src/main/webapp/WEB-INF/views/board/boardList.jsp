<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:import url="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<fieldset>
			<legend>자유게시판</legend>
			<c:if test="${not empty sessionScope.m && (sessionScope.m.memberLevel eq 0 || sessionScope.m.memberLevel eq 1)}"> 
				<a class="btn btn-secondary" href='/boardWriteFrm.do' style="float:right; margin-bottom:5px;">글쓰기</a>
			</c:if>
			<table class="table table-hover">
			    <tr class="table-active">
			      <th scope="row">번호</th>
			      <th>제목</th>
			      <th>작성자</th>
			      <th>작성일</th>
			      <th>조회수</th>
			    </tr>
			    <c:forEach items="${list }" var="b" varStatus="i">
			    	<tr class="table-default">
			    		<td>${start + i.index }</td>
			    		<td><a href='/boardView.do?boardNo=${b.boardNo}' style="text-decoration:none;">${b.boardTitle}</a></td>
			    		<td>${b.boardWriter}</td>
			    		<td>${b.regDate}</td>
			    		<td>${b.readCount}</td>
			    	</tr>
			    </c:forEach>
			</table><br>
			<div id="pageNavi">${pageNavi }</div>
		</fieldset>
	</div>
	<c:import url="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>