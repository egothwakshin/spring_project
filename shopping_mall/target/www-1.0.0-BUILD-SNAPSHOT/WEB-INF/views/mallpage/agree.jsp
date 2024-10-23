<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html lang="ko">
  <head>
    <title>Shop Bag</title>
    <meta charset="utf-8" />
    <link href="/resources/css/index.css" rel="stylesheet" />
    <link href="/resources/css/subpage2.css" rel="stylesheet" />
    <link href="/resources/css/agree.css" rel="stylesheet" />
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
    <form>
        <h3>약관동의</h3>
				<div class="join_agreement_cont">
					<div class="join_agreement_box">
						<div class="form_element">
							<input type="checkbox" id="allAgree"/>
							<label class="check" for="allAgree"> <em>ShopBag의 모든 약관을 확인하고 전체 동의합니다.</em></label>
							<span>(전체동의, 선택항목도 포함됩니다.)</span>
						</div>
					</div>
					
					<div class="join_agreement_box js_terms_view">
						<div class="form_element">
							<input type="checkbox" id="termsAgree1" name="agreementInfoFl" class="require" />
							<label class="check_s" for="termsAgree1"><strong>(필수)</strong> 이용약관</label>
							<span><a href="#" target="_blank">전체보기</a></span>
						</div>
						<div class="agreement_box">
							<cr:out value="${termsText}" /> 
						</div>
					
					</div>
					
					<div class="join_agreement_box js_terms_view">
						<div class="form_element">
							<input type="checkbox" id="termsAgree2" name="privateApprovalFl" class="require" />
							<label class="check_s" for="termsAgree2"><strong>(필수)</strong> 개인정보 수집 및 이용 </label>
							<span><a href="#" target="_blank">전체보기</a></span>
						</div>
						<div class="agreement_box">
							<cr:out value="${privacy_Text}" /> 
						</div>
					</div>
					<div class="important_check_box">
						<strong class="important_check dn">이용약관과 개인정보 수집 및 이용에 대한 안내 모두 동의해주세요.</strong>
					</div>
				</div>
				
				<div class="btn_center_box">
					<button type="button" id="btnNextStep" class="btn_join">다음단계</button>
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
</html>