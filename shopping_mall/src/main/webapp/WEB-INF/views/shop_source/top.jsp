<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	HttpSession sessionHttpSession = request.getSession();
	String mname = (String) session.getAttribute("mname");
	String mid = (String) session.getAttribute("mid");
	
	if(mname==null){
		//response.sendRedirect("/shop_source/index.jsp");
		response.sendRedirect("/shop_source/index.jsp");
		return;
	}
%>
<header class="headercss">
    <div class="header_div">
        <p><img src="/resources/img/logo.png" class="logo_sm"> ADMINISTRATOR</p>
        <p><%=mname%> 관리자 <a href="#">[개인정보 수정]</a> <a href="/admin_logout">[로그아웃]</a></p>
    </div>
</header>
<nav class="navcss">
    <div class="nav_div">
        <ol>
            <li title="쇼핑몰 상품관리" id="adminList" onclick="go_adminlist('<%=mid%>')">쇼핑몰 관리자 리스트</li>
            <li title="쇼핑몰 회원관리" onclick="go_gmemberlist('<%=mid%>')">쇼핑몰 회원관리</li>
            <li title="쇼핑몰 상품관리" onclick="go_productlist('<%=mid%>')">쇼핑몰 상품관리</li>
            <li title="쇼핑몰 기본설정" onclick="go_siteinfo('<%=mid%>')">쇼핑몰 기본설정</li>
            <li title="쇼핑몰 공지사항" onclick="go_notice('<%=mid%>')">쇼핑몰 공지사항</li>
        </ol>
    </div>
</nav>
<script>

var mid = '<%=mid%>';

if (mid !== 'master') {
    document.getElementById('adminList').style.display = 'none';
}

function go_adminlist(id){
	if(id=="master"){
		location.href = "/admin_list";
	}else{
		alert('접근권한이 없습니다.');
	}
}

function go_siteinfo(id){
	if(id=="null" || id==null || id==""){
		alert('접근권한이 없습니다.');		
	}else{
		location.href = "/siteinfo_list";
	}
}

function go_productlist(id){
	if(id=="null" || id==null || id==""){
		alert('접근권한이 없습니다.');		
	}else{
		location.href = "/product_list";
	}
}

function go_gmemberlist(id){
	if(id=="null" || id==null || id==""){
		alert('접근권한이 없습니다.');		
	}else{
		location.href = "/shop_member_list";
	}
}

function go_notice(id){
	if(id=="null" || id==null || id==""){
		alert('접근권한이 없습니다.');		
	}else{
		location.href = "/noticeList";
	}
}

</script>