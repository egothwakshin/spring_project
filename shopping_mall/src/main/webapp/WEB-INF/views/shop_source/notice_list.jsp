<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 리스트 페이지</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/basic.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/login.css?v=10">
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css?v=10">
    <link rel="stylesheet" type="text/css" href="/resources/css/notice.css?v=10">
    <link rel="icon" href="/resources/img/logo.png" sizes="128x128">
    <link rel="icon" href="/resources/img/logo.png" sizes="64x64">
    <link rel="icon" href="/resources/img/logo.png" sizes="32x32">
    <link rel="icon" href="/resources/img/logo.png" sizes="16x16">
</head>
<body>
<%@ include file="./top.jsp" %>
<main class="maincss">
<section>
    <p>공지사항 관리페이지</p>
    <div class="subpage_view">
    <ul>
        <li><input type="checkbox"></li>
        <li>NO</li>
        <li>제목</li>
        <li>글쓴이</li>
        <li>날짜</li>
        <li>조회</li>
    </ul>
    <!-- 공지사항 리스트 출력 -->
    <c:choose>
    	<c:when test="${not empty noticeList}">
	    	<c:forEach var="notice" items="${noticeList}" varStatus="status">
			    <ol>
			        <li><input type="checkbox"></li>
			        <li>${status.index + 1}</li>
			        <li><a href="/noticeView?id=${notice.id}">${notice.title}</a></li>
			        <li>${notice.writer}</li>
			        <li><fmt:formatDate value="${notice.createdAt}" pattern="yyyy-MM-dd" /></li>
			        <li>${notice.views}</li>
			    </ol>
		    </c:forEach>
	    <ol class="none_text">
	        <li>등록된 공지 내용이 없습니다.</li>
	    </ol>
	    </c:when>
	    <c:otherwise>
	        <ol class="none_text">
	        	<li>등록된 공지 내용이 없습니다.</li>
	        </ol>
        </c:otherwise>
    </c:choose>
    </div>
    <div class="board_btn">
        <button class="border_del">공지삭제</button>
        <button class="border_add" onclick="notice_regist()">공지등록</button>
    </div>
    <div class="border_page">
        <ul class="pageing">
            <li><img src="/resources/ico/double_left.svg"></li>
            <li><img src="/resources/ico/left.svg"></li>
            <li>1</li>
            <li><img src="/resources/ico/right.svg"></li>
            <li><img src="/resources/ico/double_right.svg"></li>
        </ul>
    </div>
</section>
</main>
<footer class="main_copyright">
    <div>
        Copyright ⓒ 2024 shopbag All rights reserved.
    </div>
</footer>
</body>
<script>
function notice_regist(){
	location.href = "/noticeWrite";
}
</script>
</html>