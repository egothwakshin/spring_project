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
    <link rel="stylesheet" type="text/css" href="./css/main.css?v=1">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
</head>
<body>
<%@ include file="./top.jsp" %>
<main class="maincss">
<section>
    <p>신규등록 관리자</p>
    <ol class="new_admin_title2">
        <li>NO</li>
        <li>관리자명</li>
        <li>아이디</li>
        <li>전화번호</li>
        <li>이메일</li>
        <li>담당부서</li>
        <li>담당직책</li>
        <li>가입일자</li>
        <li>승인여부</li>
    </ol>
    <cr:choose>
    	<cr:when test="${empty adminlist}">
	    <ol class="new_admin_none">
	        <li>신규 등록된 관리자가 없습니다.</li>
	    </ol>
		</cr:when>
	<cr:otherwise>
	<cr:set var="adminlist_size" value="${fn:length(adminlist)}" />
	<cr:forEach var="adminlist" items="${adminlist}" varStatus="status">
		<cr:if test="${adminlist.ad_approve!='X'}">	
			<cr:if test="${adminlist.ad_approve =='N'}">		
			    <ol class="new_admin_lists2">
			        <li>${(adminlist_size-1) - status.index}</li>
			        <li>${adminlist.ad_name}</li>
			        <li>${adminlist.ad_id}</li>
			        <li>${adminlist.ad_tel1}${adminlist.ad_tel2}${adminlist.ad_tel3}</li>
			        <li>${adminlist.ad_email}</li>
			        <li>${adminlist.ad_department}</li>
			        <li>${adminlist.ad_position}</li>
			        <li>${fn:substring(adminlist.adate, 0, 10)}</li>
			        <li>
			        <form id="frm">
			            <input type="button" value="승인" class="new_addbtn1" title="승인" onclick="approve('${adminlist.aidx}','${adminlist.ad_approve}')">
			        </form>
			        </li>
			    </ol>	    
		    </cr:if>
		    <cr:if test="${adminlist.ad_approve =='Y'}">		
			    <ol class="new_admin_lists2">
			        <li>${(adminlist_size-1) - status.index}</li>
			        <li>${adminlist.ad_name}</li>
			        <li>${adminlist.ad_id}</li>
			        <li>${adminlist.ad_tel1}${adminlist.ad_tel2}${adminlist.ad_tel3}</li>
			        <li>${adminlist.ad_email}</li>
			        <li>${adminlist.ad_department}</li>
			        <li>${adminlist.ad_position}</li>
			        <li>${fn:substring(adminlist.adate, 0, 10)}</li>
			        <li>
			        <form id="frm">
			            <input type="button" value="미승인" class="new_addbtn2" title="미승인" onclick="approve('${adminlist.aidx}','${adminlist.ad_approve}')">
			        </form>
			        </li>
			    </ol>	    
		    </cr:if>	    
		</cr:if>
    </cr:forEach>
    </cr:otherwise>
	</cr:choose>
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
function approve(aidx,approve){
	
	console.log(approve)
	if(approve=='N'){
		if(confirm('관리자 승인을 진행 하시겠습니까?')){
			location.href = "./admin_approve.do?aidx="+aidx+"&approve="+approve;
		}
	}
	else{	
		if(confirm('관리자 승인을 해제 하시겠습니까?')){
			location.href = "./admin_approve.do?aidx="+aidx+"&approve="+approve;
		}
	}

}
</script>
</html>