<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 등록 페이지</title>
    <script src="./js/jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="/resources/css/basic.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/login.css?v=1">
    <link rel="icon" href="/resources/img/logo.png" sizes="128x128">
    <link rel="icon" href="/resources/img/logo.png" sizes="64x64">
    <link rel="icon" href="/resources/img/logo.png" sizes="32x32">
    <link rel="icon" href="/resources/img/logo.png" sizes="16x16">
</head>
<body>
    <header class="admin_title_add">
        <p><img src="/resources/img/logo.png" class="logo_sm"> ADMINISTRATOR ADD</p>
    </header>

    <section class="admin_bgcolor_add">
        <div class="admin_login_add">
        	<form id="frm">
        	<input type="hidden" name="ad_approve" value="N">
            <ul>
                <li class="font_color1">아이디 및 패스워드 정보</li>
                <li>
                <input type="text" class="add_input1" name="ad_id" id="ad_id" placeholder="생성할 관리자 아이디를 입력하세요">
                <button type="button" class="btn_button" id="btn">중복체크</button>
                </li>
                <li>
                    <input type="text" class="add_input1" name="ad_pw" placeholder="접속할 패스워드를 입력하세요">
                    <input type="text" class="add_input1" name="ad_pw_ok" placeholder="동일한 패스워드를 입력하세요">
                </li>
                <li class="font_color1">관리자 기본정보 입력</li>
                <li>
                    <input type="text" class="add_input1" name="ad_name" placeholder="담당자 이름을 입력하세요">
                </li>
                <li>
                <input type="text" class="add_input1 emails" name="ad_email" placeholder="담당자 이메일을 입력하세요">
                </li>
                <li class="font_color1">
                <input type="text" class="add_input1 hp1" name="ad_tel1" placeholder="HP" value="010" maxlength="3">
                -
                <input type="text" class="add_input1 hp2" name="ad_tel2" placeholder="1234" maxlength="4">
                -
                <input type="text" class="add_input1 hp2" name="ad_tel3" placeholder="5678" maxlength="4">
                </li>
                <li class="font_color1">관리자 담당부서 및 직책</li>
                <li>
                    <select class="add_select1" name="ad_department">
                        <option value="">담당자 부서를 선택하세요</option>
                        <option value="임원">임원</option>
                        <option value="전산팀">전산팀</option>
                        <option value="디자인팀">디자인팀</option>
                        <option value="CS팀">CS팀</option>
                        <option value="MD팀">MD팀</option>
                    </select>
                    <select class="add_select1" name="ad_position">
                        <option value="">담당자 직책을 선택하세요</option>
                        <option value="대표">대표</option>
                        <option value="부장">부장</option>
                        <option value="팀장">팀장</option>
                        <option value="과장">과장</option>
                        <option value="대리">대리</option>
                        <option value="사원">사원</option>
                    </select>
                </li>
                <li class="font_color1">※ 가입완료 후 전산 담당자가 확인 후 로그인 할 수 있습니다.</li>
            </ul>
            </form>
            <span class="admin_addbtn">
                <button type="button" class="btn_button btncolor1" title="관리자 등록" onclick="go_add()">관리자 등록</button>
                <button type="button" class="btn_button btncolor2" title="관리자 취소">등록 취소</button>
            </span>
        </div>
    </section>
    <footer class="admin_copy">
        <div>
            Copyright ⓒ 2024 shopbag All rights reserved.
        </div>
    </footer>
</body>
<script>
$(function(){
	$("#btn").click(function(){
		var ad_id = $('#ad_id').val();
		
		$.ajax({
			url: '/duplicate_id',
			type: 'POST',
			data: {ad_id: ad_id},
			success: function($response){
				console.log($response);
				if($response=="duplicated"){
					alert('이미 사용중인 아이디입니다.');
				}else{
					alert('사용가능한 아이디입니다.');
				}
			},
			error: function(error){
				console.log(error);
			}
		
		});
	});
});

function go_add(){

	if(frm.ad_id.value==""){
		alert("아이디를 입력해주세요.");
	}
	else if(frm.ad_pw.value==""){
		alert("패스워드를 입력해주세요.");
	}
	else if(frm.ad_pw_ok.value==""){
		alert("동일한 패스워드를 입력해주세요.");
	}
	else if(frm.ad_pw.value!=frm.ad_pw_ok.value){
		alert("패스워드가 일치하지 않습니다. 다시 입력해주세요.");
	}
	else if(frm.ad_name.value==""){
		alert("담당자 이름을 입력해주세요.");
	}
	else if(frm.ad_email.value==""){
		alert("담당자 이메일을 입력해주세요.");
	}
	else if(frm.ad_tel1.value==""||frm.ad_tel2.value==""||frm.ad_tel3.value==""){
		alert("전화번호를 모두 입력해주세요.");
	}
	else if(frm.ad_department.value==""){
		alert("담당자 부서를 선택하세요.");
	}
	else if(frm.ad_position.value==""){
		alert("담당자 직책를 선택하세요.");
	}
	else{
		frm.method="post";
		frm.action="/add_master";
		frm.submit();		
	}
	
	
}

</script>
</html>