<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 내용 확인 페이지</title>
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
    <p>공지사항 확인 페이지</p>
<div class="write_view">
<ul>
    <li>공지사항 제목</li>
    <li>
       <strong>${notice.title}</strong>
    </li>
</ul>
<ul>
    <li>글쓴이</li>
    <li>
      ${notice.writer}
    </li>
</ul>
<ul>
    <li>첨부파일</li>
    <li>
        <c:if test="${not empty notice.attachment}">
            <a href="/downloadFile?fileName=${notice.attachment}" download>${notice.attachment}</a>
        </c:if>
    </li>
</ul>
<ul class="ul_height">
    <li>공지내용</li>
    <li>
        <div class="notice_input3" style="overflow-y: auto;">
        	${notice.content}
        </div>
    </li>
</ul>
</div>
<div class="board_btn">
    <button class="border_del" onclick="notice_list()">공지목록</button>
    <button class="border_add" onclick="notice_modify()">공지수정</button>
    <button class="border_modify" style="margin-left: 8px;" onclick="notice_delete()">공지삭제</button>
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
function notice_list(){
	location.href = "/noticeList";
}
function notice_modify(){
	location.href="/noticeModify?id=${notice.id}";
}
function notice_delete(){
    if (confirm("공지사항을 삭제하시겠습니까?")) {
        location.href = "/noticeDelete?id=" + id;
    }
}
</script>
</html>