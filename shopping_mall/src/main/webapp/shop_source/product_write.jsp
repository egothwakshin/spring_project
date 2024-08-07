<%@page import="javax.swing.text.Document"%>
<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Random rd = new Random();
	
	String pgcode = "";
	int a = 0;
	while(a<6){
	
		pgcode += rd.nextInt(9);
		a++;
	}
	request.setAttribute("pgcode", pgcode);

%>    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>상품등록 페이지</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="./css/main.css">
    <link rel="stylesheet" type="text/css" href="./css/product.css?v=5">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
</head>
<body>
<%@ include file="./top.jsp" %>
<main class="maincss">
<section>
<form id="frm">
<p>상품 등록 페이지</p>
<div class="product_insert">
    <ul>
        <li>대메뉴 카테고리</li>
        <li>
            <select class="product_input1" name="category_pd">
                <option value="카테고리 test">카테고리 test</option>
            </select><input type="button" value="카테고리 등록" title="카테고리 등록" class="product_btn" onclick="go_ct_list()"> <span class="help_text">※ 해당 카테고리가 없을 경우 신규 등록하시길 바랍니다.</span>
        </li>
    </ul>
    <ul>
        <li>상품코드</li>
        <li>
            <input type="text" class="product_input1" name="product_code" value="<%=pgcode%>"> 
            <input type="button" value="중복확인" title="중복확인" class="product_btn"> <span class="help_text">※ 상품코드는 절대 중복되지 않도록 합니다.</span>
        </li>
    </ul>
    <ul>
        <li>상품명</li>
        <li>
            <input type="text" class="product_input2" maxlength="100" name="product_name"> <span class="help_text">※ 상품명은 최대 100자까지만 적용할 수 있습니다.</span>
        </li>
    </ul>
    <ul>
        <li>상품 부가설명</li>
        <li>
            <input type="text" class="product_input4" maxlength="200" name="product_description"> <span class="help_text">※ 상품명은 최대 200자까지만 적용할 수 있습니다.</span>
        </li>
    </ul>
    <ul>
        <li>판매가격</li>
        <li>
            <input type="text" class="product_input3" maxlength="7" name="sale_price" onchange="price(this.value)"> <span class="help_text">※ , 없이 숫자만 입력하세요 최대 7자리</span>
        </li>
    </ul>
    <ul>
        <li>할인율</li>
        <li>
            <input type="text" class="product_input3" maxlength="2" name="discount_rate" value="0" onchange="dc_rate(this.value)" >% <span class="help_text">※ 숫자만 입력하세요</span>
        </li>
    </ul>
    <ul>
        <li>할인가격</li>
        <li>
            <input type="text" class="product_input3" maxlength="7" name="discount_price" value="0" readonly> <span class="help_text">※ 할인율이 0%일 경우 할인가격은 0으로 처리 합니다.</span>
        </li>
    </ul>
    <ul>
        <li>상품재고</li>
        <li>
            <input type="text" class="product_input3" maxlength="4" name="stock_quantity" value="0">EA <span class="help_text">※ 숫자만 입력하세요. 재고가 0일 경우 soldout이 됩니다.</span>
        </li>
    </ul>
    <ul>
        <li>판매 유/무</li>
        <li>
            <label class="product_label">
            <input type="radio" name="sale_status" value="Y" style="vertical-align:-1px;" checked> 판매시작
            </label>
            <label class="product_label">
            <input type="radio" name="sale_status" value="N" style="vertical-align:-1px;"> 판매종료
            </label> <span class="help_text">※ 숫자만 입력하세요. 재고가 0일 경우 soldout이 됩니다.</span>
        </li>
    </ul>
    <ul>
        <li>조기품절</li>
        <li>
            <label class="product_label">
                <input type="radio" name="early_end" value="Y" style="vertical-align:-1px;"> 사용
            </label>
            <label class="product_label">
                <input type="radio" name="early_end" value="N" style="vertical-align:-1px;" checked> 미사용
            </label>
        </li>
    </ul>
    <ul style="height: 160px;">
        <li>상품 대표이미지</li>
        <li>
            <ol style="width:100%; height: auto;">
            <li style="width:100%; height:45px;">
            <input type="file" name="main_image">
            <span class="help_text">※ 상품 대표이미지 이며, 이미지 용량은 2MB 까지 입니다.</span>
            </li>
            <li style="height:45px;">
            <input type="file" name="additional_image1">
            <span class="help_text">※ 추가 이미지 이며, 이미지 용량은 2MB 까지 입니다.</span>
            </li>
            <li style="height:45px;">
            <input type="file" name="additional_image2">
            <span class="help_text">※ 추가 이미지 이며, 이미지 용량은 2MB 까지 입니다.</span>
            </li>
            </ol>
        </li>
    </ul>
    <ul style="height: 400px;">
        <li>상품 상세설명</li>
        <li>
            <textarea class="product_text1" name="detailed_description"></textarea>
        </li>
    </ul>
</div>
<div class="subpage_view4" style="text-align:center; margin-bottom: 100px;">
    <input type="button" value="상품 리스트" title="상품 리스트" class="p_button p_button_color1" style="margin-right: 5px;" onclick="go_pd_list()">
    <input type="button" value="상품 등록" title="상품 등록" class="p_button p_button_color2" onclick="go_pd_regist()">
    </span>
</div>
</form>    
</section>
</main>
<footer class="main_copyright">
    <div>
        Copyright ⓒ 2024 shopbag All rights reserved.
    </div>
</footer>
</body>
<script>
function go_ct_list(){
	location.href = "./cate_list.do";
}
function go_pd_list(){
	location.href = "./product_list.jsp";
}
function go_pd_regist(){
	frm.method = "post";
	frm.action = "./product_regist.do";
	frm.submit();	
}

let salePrice = 0;
let discountRate = 0;
function price(value) {
    salePrice = parseFloat(value) || 0;
    calculate();
}
function dc_rate(value) {
    discountRate = parseFloat(value) || 0;
    calculate();
}
function calculate() {

    let discountPrice;
    if (discountRate === 0) {
        discountPrice = 0;
    } else {
        discountPrice = salePrice - (salePrice * discountRate / 100);
    }
    document.querySelector('input[name="discount_price"]').value = Math.floor(discountPrice);
}


</script>
</html>