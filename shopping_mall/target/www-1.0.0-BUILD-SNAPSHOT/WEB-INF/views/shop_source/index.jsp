<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 페이지</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/basic.css?v=4">
    <link rel="stylesheet" type="text/css" href="/resources/css/mainlogin.css?v=4">
    <link rel="icon" href="/resources/img/logo.png" sizes="128x128">
    <link rel="icon" href="/resources/img/logo.png" sizes="64x64">
    <link rel="icon" href="/resources/img/logo.png" sizes="32x32">
    <link rel="icon" href="/resources/img/logo.png" sizes="16x16">
</head>
<form id="frm" onsubmit="return go_ad()">
<body class="bodycss">
    <header class="admin_title">
        <p><img src="/resources/img/logo.png" class="logo_sm"> ADMINISTRATOR</p>
    </header>
    <section class="admin_bgcolor">
        <div class="admin_login">
            <span>
                <div class="left_div">
                <ul>
                <li><input type="text" class="input_text1" name="ad_id" placeholder="관리자 아이디를 입력하세요"></li>
                <li><input type="password" class="input_text1" name="ad_pw" placeholder="관리자 패스워드를 입력하세요"></li>
                </ul>
                </div>
                <div class="right_div">
                    <button type="submit" class="btn_submit" title="MASTER LOGIN">MASTER LOGIN</button>
                </div>
                <em class="alert_msg">※ 본 사이트는 관리자 외에는 접근을 금합니다. 페이지 접근에 대한 접속 정보는 모두 기록 됩니다.</em>
            </span>
            <span>
                <ol class="admin_info">
                    <li title="신규 관리자 등록요청" onclick="add_master()">신규 관리자 등록요청</li>
                    <li title="아이디/패스워드 찾기">아이디/패스워드 찾기</li>
                </ol>                
            </span>
        </div>
    </section>
    <footer class="admin_copy_login">
        <div>
            Copyright ⓒ 2024 shopbag All rights reserved.
        </div>
    </footer>
</body>
</form>    
<script>
function go_ad(){
	frm.method="post";
	frm.action="/shop_member_list"; 
	return true;		
}
function add_master(){
	frm.method="post";
	frm.action="/go_add_master";
	frm.submit();
}
</script>
</html>