<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.js"></script>
</head>
<body>
	<c:import url="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<h2>자유게시판 수정</h2>
		<form action="/boardUpdate.do?boardNo=${board.boardNo }" method="post" enctype="multipart/form-data">
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
		    	<td>
		    	<input type="hidden" name="status" value="1"> <!-- 삭제 유무 확인 --> 
					<c:choose>
						<c:when test="${not empty board.fileName }">  <!-- 첨부파일이 있을 경우 -->
							<img src="/resources/img/file.png" width="16px";>
							<span class="fileDel">${board.filePath }</span>
							<button type="button" id="deleteBtn" class="btn btn-primary btn-sm fileDel">삭제</button>
							<input type="file" name="upfile" style="display: none;">
					    	<input type="hidden" name="fileName" value="${board.fileName }">
					    	<input type="hidden" name="filePath" value="${board.filePath }">
						</c:when>
						<c:otherwise>
							<input type="file" name="upfile">
						</c:otherwise>
					</c:choose>
				</td>
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
	<script>
	$(function(){
		$("#deleteBtn").click(function(){
			$(".fileDel").hide();
			$(this).next().show();
			$("[name=status]").val(2);
		});
	});
	</script>
	<c:import url="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>