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
		<h2>자유게시판 수정</h2>
		<form action="/boardUpdate.do?boardNo=${board.boardNo }">
		<table class="table" id="freeView" style="width:100%;">
		    <tr class="table-active">
			    <th colspan="1">제목</th>
			    <th colspan="3">
			    	<input type="hidden" value="${board.boardNo }" name="boardNo">
			    	<input type="text" id="boardTitle" name="boardTitle" value="${board.boardTitle }" style="padding-left:10px; width:96%;">
			    </th>
		    </tr>
		    <tr>
		    	<th>작성자</th>
		    	<td>${board.boardWriter }</td>
			    <th>작성일</th>
			    <td>${board.regDate }</td>
		    </tr>
		    <tr>
		    	<th>첨부파일</th>
		    	<td>${board.filePath }</td>
			    <th>조회수</th>
			    <td>${board.readCount }</td>
		    </tr>
		    <tr>
		    	<th>내용</th>
		    	<td colspan="3" style="height:300px;">
		    		<textarea rows="20px;" cols="150px;" name="boardContent" style="padding-left:10px; padding-top:10px;">${board.boardContent }</textarea>
		    	</td>
		    </tr>
		    <tr>
		    	<th colspan="4" style="text-align:center;">
		    		<button type="button" onclick="history.back()" class="btn btn-primary">이전화면</button>
			    	<button type="submit" class="btn btn-primary">수정완료</button>
		    	</th>
		    </tr>
	    </table>
	    </form>
	</div>
	<c:import url="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>