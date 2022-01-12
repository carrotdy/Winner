<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 폰트어썸(아이콘) -->
<link rel="stylesheet" href="/resources/fontawesome/css/all.css">
<script type="text/javascript" src="/resources/fontawesome/js/all.js"></script>
<!-- 부트스트랩 CSS -->
<link rel="stylesheet" href="/resources/css/bootstrap.css">
<!-- 글꼴적용(NotoSans 폰트) -->
<link rel="stylesheet" href="/resources/css/font.css">
<!-- 기본 CSS -->
<link rel="stylesheet" href="/resources/css/default.css">
<!-- header CSS-->
<link rel="stylesheet" href="/resources/css/header.css">
<!-- jQuery라이브러리 추가(2개) -->
<script type="text/javascript" src="/resources/js/jquery-3.3.1.js"></script>
<!-- 부트스트랩용 jQuery -->
<script type="text/javascript" src="/resources/js/bootstrap.bundle.min.js"></script>
<!-- trade CSS -->
<link rel="stylesheet" href="/resources/css/header.css">
<!-- 부트스트랩 icon -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">

<div class="header-wrapper">
    <nav class="navbar navbar-expand-lg">
        <div class="header-content navbar">
            <a href="/" style="margin-right: 50px;"><img src=""></a>
			<form style="position: relative;" action="/searchWish" method="get" id="searchBar">
                <input class="form-control me-sm-2" name="search" type="text" placeholder="원하시는 상품 또는 지역을 검색해보세요!"
                    style="height: 50px; min-width: 450px;">
                <input type="hidden" name="reqPage" value=1>
                <input type="submit" id="search" style="display: none;"></input>
                <label for="search"><img src="/resources/img/search_icon.png" style="width: 30px; height: 30px; top: 10px; left: 90%; position: absolute;"></label>
            </form>
            <ul class="headnavi-list">
                <c:choose>
                	<c:when test="${empty sessionScope.m }">
                		<li class="header-item"><a href="/loginFrm.do" style="color:#374859">로그인</a></li>
                		<li class="header-item"><a href="/joinFrm.do" style="color:#374859">회원가입</a></li>
                	</c:when>
                	<c:otherwise>
                		<c:choose>
	                		<c:when test="${sessionScope.m.memberLevel eq 9 }">
		                		<li class="header-item"><a href="/adminPage.do">[${sessionScope.m.memberName }]관리자</a></li>
				                <li class="header-item"><a href="/logout.do">로그아웃</a></li>
	                		</c:when>
	                		<c:otherwise>
	                	  		<li class="header-item">
	                	  		<c:if test="${sessionScope.m.memberLevel eq 2 }">
	                	  		<span class="btn btn-danger"><i class="bi bi-emoji-frown-fill"></i>블랙리스트</span>
	                	  		</c:if>
	                	  		<a href="/mypagel.do">${sessionScope.m.memberName }</a></li>
			                	<li class="header-item"><a href="/logout.do">로그아웃</a></li>
	                		</c:otherwise>
                		</c:choose>
                	</c:otherwise>
                  </c:choose>
                <li class="header-item"><a href="/noticeList.do" style="color:#374859">공지사항</a></li>
                <li class="header-item" style="margin-right: 0;" ><a href="/qnaList.do" style="color:#374859">QnA</a></li>
            </ul>
      </div>
    </nav>
</div>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
     <a class="navbar-brand ml-auto navbar-hidden-lg" href="/"><img src="/img/orange.png" style="width: 30px;"></a>
    <button class="navbar-toggler nav-bar-hidden-lg mr-auto" type="button" data-toggle="collapse" data-target="#navbarColor03" aria-controls="navbarColor03" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
 
    <div class="navi1 collapse navbar-collapse" id="navbarColor03">
      <ul class="headernavi navbar-nav">
        <li class="nav-item">
         	<a class="nav-link" href="/AllProduct.do">전체상품
	          <span class="visually-hidden"></span>
	        </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/life.do">생활</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/food.do">식품</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/baby.do">육아</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/beauty.do">뷰티</a>
        </li>
      </ul>
    </div>
</nav>

