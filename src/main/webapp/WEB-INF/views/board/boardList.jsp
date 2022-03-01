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
			<h2>자유게시판</h2> 
			<c:if test="${not empty sessionScope.m && (sessionScope.m.memberLevel eq 0 || sessionScope.m.memberLevel eq 1)}">
				<a class="btn btn-secondary" href='/boardWriteFrm.do' style="float:right; margin-bottom:5px;">글쓰기</a>
			</c:if>&emsp;
			<form action="/boardSearch.do?reqPage=1" method="post">
			<a class="search" style="float:left;">
				<select name="searchType">
				    <option value="boardTitle"<c:out value="${searchType eq 'boardTitle' ? 'selected' : ''}"/>>제목</option>
				    <option value="boardContent"<c:out value="${searchType eq 'boardContent' ? 'selected' : ''}"/>>내용</option>
				    <option value="boardWriter"<c:out value="${searchType eq 'boardWriter' ? 'selected' : ''}"/>>작성자</option>
				    <option value="tc"<c:out value="${searchType eq 'tc' ? 'selected' : ''}"/>>제목+내용</option>
			    </select>
			    <input type="text" name="keyword"/>
			    <input type="submit" class="btn btn-secondary" value="검색">
			</a>
			</form>
			<table class="table table-hover">
			    <tr class="table-active">
			      <th scope="row">번호</th>
			      <th>제목</th>
			      <th>작성자</th>
			      <th>작성일</th>
			      <th>조회수</th>
			    </tr>
			    <c:forEach items="${list }" var="b" varStatus="i">
			    <c:choose>
			    	<c:when test="${b.priority == 0}">  <!-- 고정게시물이 아닐 때 -->
			    		<tr class="table-default">
				    		<td>${start + i.index - fixPage}</td>
				    		<td><a href='/boardView.do?boardNo=${b.boardNo}' style="text-decoration:none;">${b.boardTitle}</a></td>
				    		<td>${b.boardWriter}</td>
				    		<td>${b.regDate}</td>
			    			<td>${b.readCount}</td>
			    		</tr>
			    	</c:when>
			    	<c:otherwise>
				    	<tr class="table-default" style="background-color:#EAEAEA;">		<!-- 고정게시물 -->
				    		<td><img src="resources/img/bell.png" style="width:20px; height:20px;"></td> 
				    		<td><a href='/boardView.do?boardNo=${b.boardNo}' style="text-decoration:none;">${b.boardTitle}</a></td>
				    		<td>${b.boardWriter}</td>
				    		<td>${b.regDate}</td>
				    		<td>${b.readCount}</td>
				    	</tr>
			    	</c:otherwise>
			     </c:choose>
			    </c:forEach>
			</table><br>
			<div id="pageNavi">${pageNavi }</div>
		</fieldset>
	</div>
	<c:import url="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>