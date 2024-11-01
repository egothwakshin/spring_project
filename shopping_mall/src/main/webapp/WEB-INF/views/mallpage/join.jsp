<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <title>Shop Bag</title>
    <meta charset="utf-8" />
    <link href="/resources/css/index.css" rel="stylesheet" />
    <link href="/resources/css/subpage.css" rel="stylesheet" />
    <link href="/resources/css/agree.css?v=1" rel="stylesheet" />
    <link href="/resources/css/join.css?v=1" rel="stylesheet" />
  </head>

  <body>
    <div class="navbar">
      <a href="#" id="logo">
        <img src="/resources/images/logo.jpg" width="149">
        </a>
        <ul id="menu">
          <li><a href="#">LOGIN</a></li>
          <li><a href="#">MEMBER SHIP</a></li>
          <li><a href="#">CART</a></li>
          <li><a href="#">CUSTOMER CENTER</a></li>
        </ul>
    </div>
 <main>
    <div class="products">
    <h3>MEMBER_JOIN</h3>
    <div class="sub_view">
   
    <div class="joinview">     
    <form id="frm">
    <h3>회원가입</h3>
	<span class="join_info">
    <ol>
    <li>기본정보 </li>
    <li>※ 표시는 반드시 입력하셔야 하는 항목 입니다.</li>
    </ol>
    </span>
    <ul class="join_ul">
    <li>※ 아이디</li>
    <li>
    <input type="text" class="join_in1" name="gid"> <input type="button" value="중복확인" class="join_btn1">
    </li>
    <li>※ 비밀번호</li>
    <li>
    <input type="password" class="join_in1 join_in2" name="gpw"> ※ 최소 6자 이상 입력하셔야 합니다.
    </li>
    <li>※ 비밀번호 확인</li>
    <li>
    <input type="password" class="join_in1 join_in2" name="gpw_ok"> ※ 동일한 패스워드를 입력하세요.
    </li>
    <li>※ 이름</li>
    <li>
    <input type="text" class="join_in1" name="gname">
    </li>
    <li>※ 이메일</li>
    <li>
    <input type="text" class="join_in1" name="gemail"> <input type="button" value="이메일 인증" class="join_btn1"> ※ 입력하신 이메일을 확인해 주세요.
    </li>
    <li>※ 인증번호</li>
    <li>
    <input type="text" class="join_in1 join_in3" maxlength="8"> ※ 8자리 인증번호를 입력하세요.
    </li>
    <li>※ 전화번호</li>
    <li>
    <input type="text" class="join_in1 join_in2" maxlength="11" name="ghp"> ※ 숫자만 입력하세요
    </li>
    <li>※ 이벤트 메일 수신</li>
    <li>
    <label><input type="checkbox" class="join_ck1" name="gemail_ok" value="Y"> 정보/이벤트 메일 수신에 동의합니다.</label>
    </li>
    <li>※ 이벤트 SMS 수신</li>
    <li>
    <label><input type="checkbox" class="join_ck1" name="gsms_ok" value="Y"> 정보/이벤트 SMS 수신에 동의합니다.</label>
    </li>
    </ul>
    <div class="btn_center_box">
    <button type="button" id="btnNextStep" class="btn_join" onclick="go_gjoin()">회원가입</button>
    </div>
	</form>
    </div>
    </div>
    </div>
</main>
<footer>
    <div class="footer">
      <a href="http://facebook.com">
        <img src="/resources/images/facebook.png"height="20">
      </a>
      <a href="http://instagram.com">
        <img src="/resources/images/instagram.png"height="20">
      </a>
      <a href="http://twitter.com">
        <img src="/resources/images/twitter.png"height="20">
      </a>
    </div>
    <section class="foot_section"></section>
    <aside class="aside_footer">
        <div class="div_footer">
        <ul>
        <li><img src="/resources/images/foot_logo.png"></li>
        <li>
회사명 :  
대표자 : 
주소 :   <br>
고객센터 : 
상담시간 : 
E-Mail : 
사업자등록번호 :  <br>
통신판매업신고번호 : 
개인정보보호책임자 :    <br>
Copyright © 도메인명 All Rights Reserved.
        </li>
        </ul>    
        </div>
    </aside>
</footer>
  </body>
<script>
function go_gjoin(){
	/*
	frm.method = "post";
	frm.action = "";
	frm.submit();
	*/	
}

</script>  
</html>