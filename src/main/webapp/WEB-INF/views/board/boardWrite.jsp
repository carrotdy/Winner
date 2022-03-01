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
		<h2>게시글 작성</h2><br>
		<form action="/boardWrite.do" method="post" enctype="multipart/form-data">
		<table class="table" style="width:100%;">
		    <tr class="table-active">
			    <th colspan="1">제목</th>
			    <th colspan="3">
			    	<input type="text" name="boardTitle" placeholder="제목을 입력해주세요" style="padding-left:10px; width:96%;">
			    </th>
		    </tr>
		    <tr>
		    	<th>작성자</th>
		    	<td>${sessionScope.m.memberId }
		    		<input type="hidden" name="boardWriter" value="${sessionScope.m.memberId }">
		    	</td>
		    </tr>
		    <tr>
		    	<th>첨부파일</th>
		    	<td>
		    		<input type="file" name="upfile" accept=".jpg,.jpeg,.png,.gif">
		    	</td>
		    </tr>
 			<c:if test="${sessionScope.m.memberLevel eq 0 }"> 
 			<tr>
				<th>고정글</th>
		    	<td>
					<input type="checkbox" name="priority" value='1' id="check">
					<input type="hidden" name="priority" value='0' id="check_hidden"/>
		    	</td>
		    </tr>
		    </c:if>
		    <tr>
		    	<th>내용</th>
		    	<td colspan="3" style="height:300px;">
		    		<textarea rows="20px;" cols="150px;" name="boardContent" placeholder="내용을 입력해주세요" style="padding-left:10px; padding-top:10px;">${board.boardContent }</textarea>
		    	</td>
		    </tr>
		    <tr>
		    	<th colspan="4" style="text-align:center;">
		    		<button type="submit" class="btn btn-primary" id="register">등록하기</button>
		    	</th>
		    </tr>
	    </table>
	    </form>
		</div>
	<c:import url="/WEB-INF/views/common/footer.jsp"/>
</body>
<script>
	$(function(){
		$("#register").click(function(){
		    if($('[name=boardTitle]').val() == ""){
	          alert('제목을 입력하세요.');
	          return false;
	        }else if($('[name=boardContent]').val() == ""){
	          alert('내용을 입력해주세요.');
	          return false;
	        }
		});
		
		//고정게시물 체크했을 때 1, 체크 안했을 때 0
		if(document.getElementById("check").checked) {
		    document.getElementById("check_hidden").disabled = true;
		}
	});
	
</script>
</html>