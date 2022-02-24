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
		<h2>자유게시판</h2><br>
		<table class="table" id="boardView" style="width:100%;">
		    <tr class="table-active">
			    <th colspan="1">제목</th>
			    <th colspan="3">${board.boardTitle }</th>
		    </tr>
		    <tr>
		    	<th>작성자</th>
		    	<td>${board.boardWriter }</td>
			    <th>작성일</th>
			    <td>${board.regDate }</td>
		    </tr>
		    <tr>
		    	<th>첨부파일</th>
		    		<c:choose>
						<c:when test="${not empty board.fileName }">
							<td>
								<img src="/resources/img/file.png" width="16px">
								<a href="/fileDown.do?filepath=${board.filePath }&filename=${board.fileName }">${board.filePath }</a>
							</td>
						</c:when>
						<c:otherwise>
							<td>첨부파일 없음</td>
						</c:otherwise>
					</c:choose>
			    <th>조회수</th>
			    <td>${board.readCount }</td>
		    </tr>
		    <tr>
		    	<th>내용</th>
		    	<td colspan="3" style="height:300px;">${board.boardContent }</td>
		    </tr>
		    <tr>
		    	<th colspan="4" style="text-align:center;">
			    	<a href="javascript:history.back();" class="btn btn-primary">이전화면</a>
			    	<c:if test="${sessionScope.m.memberId eq board.boardWriter}">
		    			<a href='/boardUpdateFrm.do?boardNo=${board.boardNo }' class="btn btn-primary">수정하기</a>
			    		<a href='/boardDelete.do?boardNo=${board.boardNo }' class="btn btn-primary">삭제하기</a>
		    		</c:if>
		    	</th>
		    </tr>
	    </table>
	</div>
	<c:import url="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>