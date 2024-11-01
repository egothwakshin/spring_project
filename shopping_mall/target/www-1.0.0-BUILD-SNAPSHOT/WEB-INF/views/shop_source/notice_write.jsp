<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 등록 페이지</title>
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
    <p>공지사항 등록페이지</p>
<div class="write_view">
<form action="/noticeRegister" method="post" enctype="multipart/form-data">
<ul>
    <li>공지사항 여부</li>
    <li>
        <label class="label_notice">
	        <em class="cbox">
	        	<input type="checkbox" name="is_notice">
	        </em> 공지 출력</label> ※ 공지출력을 체크할 시 해당 글 내용은 최상단에 노출 되어 집니다.
    </li>
</ul>
<ul>
    <li>공지사항 제목</li>
    <li>
        <input type="text" class="notice_input1" name="title" maxlength="150" required> ※ 최대 150자까지 입력이 가능
    </li>
</ul>
<ul>
    <li>글쓴이</li>
    <li>
        <input type="text" class="notice_input2" name="writer" readonly value="관리자"> ※ 관리자 이름이 노출 됩니다.       
    </li>
</ul>
<ul>
    <li>첨부파일</li>
    <li>
        <input type="file" name="attachmentFile"> ※ 첨부파일 최대 용량은 2MB 입니다.       
    </li>
</ul>
<ul class="ul_height">
    <li>공지내용</li>
    <li>
        <textarea class="notice_input3" name="content" placeholder="공지내용을 입력하세요!" required></textarea>
    </li>
</ul>
</div>
<div class="board_btn">
    <button type="button" class="border_del" onclick="notice_list()">공지목록</button>
    <button type="submit" class="border_add">공지등록</button>
</form>
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
</script>
</html>