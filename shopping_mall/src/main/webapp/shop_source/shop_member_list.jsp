<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>쇼핑몰 회원관리</title>
    <script src="./js/jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="./css/main.css">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
</head>
<body>
<%@ include file="./top.jsp" %>
<main class="maincss">
<section>
    <p>회원 리스트</p>
    <ol class="new_admin_title">
        <li>NO</li>
        <li>고객명</li>
        <li>아이디</li>
        <li>전화번호</li>
        <li>이메일</li>
        <li>이메일 수신</li>
        <li>SMS 수신</li>
        <li>가입일자</li>
        <li>상태</li>
        <li>정지여부</li>
    </ol>
    <cr:choose>
	    <cr:when test="${empty gm }">
		    <ol class="new_admin_none">
		        <li>가입된 회원이 없습니다.</li>
		    </ol>
	    </cr:when>
    <cr:otherwise>
    <cr:set var="gm_size" value="${fn:length(gm)}"/>
    <cr:forEach var="gm" items="${gm}" varStatus="status">
		    <ol class="new_admin_lists">
		        <li>${gm_size - status.index}</li>
		        <li>${gm.gname}</li>
		        <li>${gm.gid}</li>
		        <li>${gm.ghp}</li>
		        <li>${gm.gemail}</li>
		        <li>${gm.gemail_ok}</li>
		        <li>${gm.gsms_ok}</li>
		        <li>${fn:substring(gm.gdate, 0, 10)}</li>
		        <li>
           	  	<cr:choose>
                <cr:when test="${gm.gstop == 'Y'}">휴면</cr:when>
                <cr:otherwise>정상</cr:otherwise>
            	</cr:choose>
        		</li>
		        <li>
		            <input type="button" value="정지" class="new_addbtn1" title="정지" onclick="gstop_ok('${gm.gidx}','${gm.gstop}')">
		            <input type="button" value="해제" class="new_addbtn2" title="해제" onclick="gstop_no('${gm.gidx}','${gm.gstop}')">
		        </li>
		    </ol>
	    </cr:forEach>
    </cr:otherwise>
    </cr:choose>
</section>
<form id="frm">
<section style="width: 1100px; height: auto; margin: 0 auto; margin-top: 30px;">
    <p style="font-size: 15px;font-weight: bolder; margin-bottom: 10px;">■ 이용 약관</p>
    <textarea placeholder="이용약관에 대한 내용을 입력하세요" name="terms" style="width: 100%; height: 100px; resize: none;"></textarea>
    <input type="button" onclick="go_terms()" value="이용약관 수정" title="이용약관 수정" class="btn_button" style="position: relative; left: 100%; margin-left: -100px;">
</section>
<section style="width: 1100px; height: auto; margin: 0 auto; margin-top: 30px;">
    <p style="font-size: 15px;font-weight: bolder; margin-bottom: 10px;">■ 개인정보 수집 및 이용</p>
    <textarea placeholder="개인정보 수집 및 이용" name="privacy_policy" style="width: 100%; height: 100px; resize: none;"></textarea>
    <input type="button" onclick="go_privacy_policy()"  value="개인정보 약관 수정" title="개인정보 약관 수정" class="btn_button" style="position: relative; left: 100%; margin-left: -100px;">
</section>
</form>
</main>
<footer class="main_copyright">
    <div>
        Copyright ⓒ 2024 shopbag All rights reserved.
    </div>
</footer>
</body>
<script>
function go_terms(){

	
	$.ajax({
		type:"post",
		url:"/mallpage/agree.do",
		data:{terms: frm.terms.value},
		success:function(response){
			if(response.status=="success"){
				$(".agreement_box").html(response.terms);
			}else{
				alert("이용약관 ajax 송신 오류");
			}
		},
		error:function(){
			alert("오류 발생");
		}		
	});
	
}
function go_privacy_policy(){
	frm.method="post";
	frm.action= "/mallpage/agree.do";
	frm.submit();
}


function gstop_ok(gidx,gstop){
	
	if(gstop=='N'){
		if(confirm('해당 계정을 휴면계정으로 전환하시겠습니까?')){
			location.href = "./gmember_stop.do?gidx="+gidx+"&gstop="+gstop;
		}
	}
	else{
		alert('해당 계정은 이미 정지된 상태입니다');
	}
}

function gstop_no(gidx,gstop){
	
	if(gstop=='Y'){
		if(confirm('해당 계정을 휴면 해제하시겠습니까?')){
			location.href = "./gmember_stop.do?gidx="+gidx+"&gstop="+gstop;
		}
	}
	else{
		alert('해당 계정은 이미 휴면 해제된 상태입니다.');
	}
	
}

</script>
</html>