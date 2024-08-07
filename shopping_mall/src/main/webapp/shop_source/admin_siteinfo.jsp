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
    <title>관리자 등록 페이지</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="./css/main.css">
    <link rel="stylesheet" type="text/css" href="./css/subpage.css?v=5">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
</head>
<body>
<%@ include file="./top.jsp" %>
<main class="maincss">
<section>
<cr:set var="siteinfo" value="${siteinfo}" />
<form id="frm">
    <p>홈페이지 가입환경 설정</p>
<div class="subpage_view">
<ul class="info_form">
    <li>홈페이지 제목</li>
    <li>
        <input type="text" class="in_form1" name="homepage_title" value="${siteinfo.get(1)}"> 
    </li>
</ul>    
<ul class="info_form">
    <li>관리자 메일 주소</li>
    <li>
        <input type="text" class="in_form2" name="admin_email" value="${siteinfo.get(2)}"> ※ 관리자가 보내고 받는 용도로 사용하는 메일 주소를 입력합니다.(회원가입,인증메일,회원메일발송 등에서 사용)
    </li>
</ul> 
<ul class="info_form">
    <li>포인트 사용 유/무</li>
    <li class="checkcss">
        <em><label><input type="radio" class="ckclass" name="point_use" value="Y" <cr:if test="${siteinfo.get(3) == 'Y'}">checked</cr:if>>포인트 사용</label></em> 
        <em><label><input type="radio" class="ckclass" name="point_use" value="N" <cr:if test="${siteinfo.get(3) == 'N'}">checked</cr:if>>포인트 미사용</label></em>
    </li>
</ul>
<ul class="info_form2" style="border-bottom:1px solid rgb(81, 61, 61);">
    <li>회원가입시 적립금</li>
    <li>
        <input type="text" class="in_form3" maxlength="5" name="initial_point" value="${siteinfo.get(4)}"> 점
    </li>
    <li>회원가입시 권한레벨</li>
    <li>
        <input type="text" class="in_form3" maxlength="1" name="membership_level" value="${siteinfo.get(5)}"> 레벨
    </li>
</ul> 
</div>
<p>홈페이지 기본환경 설정</p>
<div class="subpage_view">
<ul class="info_form2">
    <li>회사명</li>
    <li>
        <input type="text" class="in_form0" name="company_name" value="${siteinfo.get(6)}"> 
    </li>
    <li>사업자등록번호</li>
    <li>
        <input type="text" class="in_form0" name="business_number" value="${siteinfo.get(7)}"> 
    </li>
</ul> 
<ul class="info_form2">
    <li>대표자명</li>
    <li>
        <input type="text" class="in_form0" name="ceo_name" value="${siteinfo.get(8)}"> 
    </li>
    <li>대표전화번호</li>
    <li>
        <input type="text" class="in_form0" name="representative_phone" value="${siteinfo.get(9)}"> 
    </li>
</ul>
<ul class="info_form2">
    <li>통신판매업 신고번호</li>
    <li>
        <input type="text" class="in_form0" placeholder="필수입력 항목이 아닙니다." name="mail_order_number" value="${siteinfo.get(10)}"> 
    </li>
    <li>부가통신 사업자번호</li>
    <li>
        <input type="text" class="in_form0" placeholder="필수입력 항목이 아닙니다." name="additional_business_number" value="${siteinfo.get(11)}"> 
    </li>
</ul>
<ul class="info_form2">
    <li>사업장 우편번호</li>
    <li>
        <input type="text" class="in_form0" name="company_zipcode" value="${siteinfo.get(12)}"> 
    </li>
    <li>사업장 주소</li>
    <li>
        <input type="text" class="in_form2" name="company_address" value="${siteinfo.get(13)}"> 
    </li>
</ul>
<ul class="info_form2" style="border-bottom:1px solid rgb(81, 61, 61);">
    <li>정보관리책임자명</li>
    <li>
        <input type="text" class="in_form0" name="info_manager_name" value="${siteinfo.get(14)}"> 
    </li>
    <li>정보책임자 E-mail</li>
    <li>
        <input type="text" class="in_form1" name="info_manager_email" value="${siteinfo.get(15)}"> 
    </li>
</ul>
</div>
<p>결제 기본환경 설정</p>
<div class="subpage_view">  
<ul class="info_form2">
    <li>무통장 은행</li>
    <li>
        <input type="text" class="in_form0" placeholder="필수입력 항목이 아닙니다." name="bank_name" value="${siteinfo.get(16)}"> 
    </li>
    <li>은행계좌번호</li>
    <li>
        <input type="text" class="in_form1" placeholder="무통장 은행 항목 입력 시 필수입력" name="bank_account" value="${siteinfo.get(17)}"> 
    </li>
</ul>
<ul class="info_form">
    <li>신용카드 결제 사용</li>
    <li class="checkcss">
        <em><label><input type="radio" class="ckclass" name="credit_card_use" value="Y" <cr:if test="${siteinfo.get(18) == 'Y'}">checked</cr:if>> 사용</label></em> 
        <em><label><input type="radio" class="ckclass" name="credit_card_use" value="N" <cr:if test="${siteinfo.get(18) == 'N'}">checked</cr:if>> 미사용</label></em> ※ 해당 PG사가 있을 경우 사용으로 변경합니다.
    </li>
</ul>
<ul class="info_form">
    <li>휴대폰 결제 사용</li>
    <li class="checkcss">
        <em><label><input type="radio" class="ckclass" name="mobile_payment_use" value="Y" <cr:if test="${siteinfo.get(19) == 'Y'}">checked</cr:if>> 사용</label></em> 
        <em><label><input type="radio" class="ckclass" name="mobile_payment_use" value="N" <cr:if test="${siteinfo.get(19) == 'N'}">checked</cr:if>> 미사용</label></em> ※ 주문시 휴대폰 결제를 가능하게 할 것인지를 설정합니다.
    </li>
</ul>
<ul class="info_form">
    <li>도서상품권 결제사용</li>
    <li class="checkcss">
        <em><label><input type="radio" class="ckclass" name="book_coupon_payment_use" value="Y" <cr:if test="${siteinfo.get(20) == 'Y'}">checked</cr:if>> 사용</label></em> 
        <em><label><input type="radio" class="ckclass" name="book_coupon_payment_use" value="N" <cr:if test="${siteinfo.get(20) == 'N'}">checked</cr:if>> 미사용</label></em> ※ 도서상품권 결제만 적용이 되며, 그 외에 상품권은 결제 되지 않습니다.
    </li>
</ul>
<ul class="info_form2">
    <li>결제 최소 포인트</li>
    <li>
        <input type="text" class="in_form0" maxlength="5" placeholder="1000이상 입력해주세요." name="min_payment_point" value="${siteinfo.get(21)}"> 점
    </li>
    <li>결제 최대 포인트</li>
    <li>
        <input type="text" class="in_form0" maxlength="5" name="max_payment_point" value="${siteinfo.get(22)}"> 점
    </li>
</ul>
<ul class="info_form">
    <li>현금 영수증 발급사용</li>
    <li class="checkcss">
        <em><label><input type="radio" class="ckclass" name="offline_payment_use" value="Y" <cr:if test="${siteinfo.get(23) == 'Y'}">checked</cr:if>> 사용</label></em> 
        <em><label><input type="radio" class="ckclass" name="offline_payment_use" value="N" <cr:if test="${siteinfo.get(23) == 'N'}">checked</cr:if>> 미사용</label></em> ※ 현금영수증 관련 사항은 PG사 가입이 되었을 경우 사용가능 합니다.
    </li>
</ul>
<ul class="info_form2">
    <li>배송업체명</li>
    <li>
        <input type="text" class="in_form0" name="delivery_company_name" value="${siteinfo.get(24)}"> 
    </li>
    <li>배송비 가격</li>
    <li>
        <input type="text" class="in_form0" maxlength="7" name="delivery_cost" value="${siteinfo.get(25)}"> 원
    </li>
</ul>
<ul class="info_form" style="border-bottom:1px solid rgb(81, 61, 61);">
    <li>희망배송일</li>
    <li class="checkcss">
        <em><label><input type="radio" class="ckclass" name="desired_delivery_date_use" value="Y" <cr:if test="${siteinfo.get(26) == 'Y'}">checked</cr:if>> 사용</label></em> 
        <em><label><input type="radio" class="ckclass" name="desired_delivery_date_use" value="N" <cr:if test="${siteinfo.get(26) == 'N'}">checked</cr:if>> 미사용</label></em> ※ 희망배송일 사용시 사용자가 직접 설정 할 수 있습니다.
    </li>
</ul>
</div>
<div class="btn_div">
    <button type="button" class="sub_btn1" title="설정 저장" onclick="go_site_setting()">설정 저장</button>
    <button type="button" class="sub_btn2" title="저장 취소" onclick="location.reload();">저장 취소</button>
</div>
</form>
</section>
<section></section>
<section></section>
</main>
<footer class="main_copyright">
    <div>
        Copyright ⓒ 2024 shopbag All rights reserved.
    </div>
</footer>
</body>
<script>
function go_site_setting(){
	
	if(frm.homepage_title.value==""||frm.admin_email.value==""||frm.point_use.value==""||frm.initial_point.value==""||frm.membership_level.value==""){
		alert("'홈페이지 가입환경 설정'의 모든 항목에 내용을 입력해주세요.");
	}
	else if(frm.company_name.value==""||frm.business_number.value==""||frm.ceo_name.value==""||frm.representative_phone.value==""||frm.company_zipcode.value==""||frm.company_address.value==""||frm.info_manager_name.value==""||frm.info_manager_email.value==""){
		alert("'홈페이지 기본환경 설정'에 있는 모든 필수 입력 항목에 내용을 입력입력해주세요.");
	}
	else if(frm.credit_card_use.value==""||frm.mobile_payment_use.value==""||frm.book_coupon_payment_use.value==""||frm.min_payment_point.value==""||frm.max_payment_point.value==""||frm.offline_payment_use.value==""||frm.delivery_company_name.value==""||frm.delivery_cost.value==""||frm.desired_delivery_date_use.value==""){
		alert("'결제 기본환경 설정'에 있는 모든 필수 입력 항목에 내용을 입력입력해주세요.");
	}
	else if(frm.min_payment_point.value<1000){
		alert("결제 최소 포인트는 1000 이상 입력해주세요.");
	}
	else if(frm.min_payment_point.value>=frm.max_payment_point.value){
		alert("결제 최대 포인트는 결제 최소 포인트보다 큰 값을 입력해주세요.");
	}
	else if(frm.bank_name.value=="" && frm.bank_account.value==""){
		frm.method="post";
		frm.action="./siteinfo_regist.do";
		frm.submit();
	}
	else{
		var regex = /^\d{1,8}-\d{1,8}-\d{1,8}$/;

		if(frm.bank_name.value==""){
			alert("무통장 은행 항목을 입력해주세요");
		}
		else if(frm.bank_account.value==""){
			alert("은행계좌번호 항목을 입력해주세요.");
		}
		else if(!regex.test(frm.bank_account.value)){
			alert("은행계좌번호 형식이 올바르지 않습니다.");
		}
		else{
			frm.method="post";
			frm.action="./siteinfo_regist.do";
			frm.submit();
		}

	}

	
	
}


	
</script>
</html>