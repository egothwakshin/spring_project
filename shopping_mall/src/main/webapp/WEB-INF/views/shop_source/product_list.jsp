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
    <link rel="stylesheet" type="text/css" href="/resources/css/basic.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/product.css?v=5">
    <link rel="icon" href="/resources/img/logo.png" sizes="128x128">
    <link rel="icon" href="/resources/img/logo.png" sizes="64x64">
    <link rel="icon" href="/resources/img/logo.png" sizes="32x32">
    <link rel="icon" href="/resources/img/logo.png" sizes="16x16">
</head>
<body>
<%@ include file="./top.jsp" %>
<main class="maincss">
<section>
<form id="frm" method="post" action="/deleteProduct">
<p>상품관리 페이지</p>
<div class="subpage_view">
    <span>등록된 상품 0건</span>
    <span>
        <select class="p_select1">
            <option>상품명</option>
            <option>상품코드</option>
        </select>
        <input type="text" class="p_input1" placeholder="검색어를 입력해 주세요">
        <input type="submit" value="검색" title="상품검색" class="p_submit">

    </span>
</div>
<div class="subpage_view2">
    <ul>
        <li><input type="checkbox"></li>
        <li>코드</li>
        <li>이미지</li>
        <li>상품명</li>
        <li>카테고리 분류</li>
        <li>판매가격</li>
        <li>할인가격</li>
        <li>할인율</li>
        <li>재고현황</li>
        <li>판매유/무</li>
        <li>품절</li>
        <li>관리</li>
    </ul>
   <cr:choose>
    <cr:when test="${empty pd_data}">
    <ul>
        <li style="width: 100%;">등록된 상품이 없습니다.</li>
    </ul>
    </cr:when>
    <cr:otherwise>
    	<cr:forEach var="pd_data" items="${pd_data}" >

    <ul> 	
        <li><input type="checkbox" name="ck" value="${pd_data.product_id}" ></li>
        <li>${pd_data.product_code}</li>
        <!--  <li><img src="../project0729/${pd_data.product_image_origin}" style="width: 30px; height: 30px;"/></li>-->
        <cr:choose>
	        <cr:when test="${pd_data.product_image_origin ne null}">
	        	<li><a href="../project0729/${pd_data.product_image_origin}"><img src="../project0729/${pd_data.product_image_origin}" style="width: 25px; height: 25px;"/></a></li>
	        </cr:when>
	        <cr:otherwise>
	        	<li>(이미지 첨부 없음)</li>
	        </cr:otherwise>
        </cr:choose>
        <li>${pd_data.product_name}</li>
        <li>${pd_data.category_pd}</li>
        <li>${pd_data.sale_price}</li>
        <li>${pd_data.discount_price}</li>
        <li>${pd_data.discount_rate}</li>
        <li>${pd_data.stock_quantity}</li>
        <li>${pd_data.sale_status}</li>
        <li>${pd_data.early_end}</li>
        <li>관리</li>
    </ul>

   		</cr:forEach>
   	 </cr:otherwise>
   	</cr:choose>  
</div>
<div class="subpage_view3">
    <ul class="pageing">
        <li><img src="/resources/ico/double_left.svg"></li>
        <li><img src="/resources/ico/left.svg"></li>
        <li>1</li>
        <li><img src="/resources/ico/right.svg"></li>
        <li><img src="/resources/ico/double_right.svg"></li>
    </ul>
</div>
<div class="subpage_view4">
    <input type="button" value="선택상품 삭제" title="선택상품 삭제" class="p_button" id="DeleteProductBtn">
    <span style="float: right;">
    <input type="button" value="신규상품 등록" title="신규상품 등록" class="p_button p_button_color1" onclick="go_pd_write()">
    <input type="button" value="카테고리 등록" title="카테고리 등록" class="p_button p_button_color2" onclick="go_ct_list()">
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
function go_pd_write(){
	location.href = "/product_write";
}
function go_ct_list(){
	location.href = "/cate_list";
}


const DeleteProduct = function(){
	const ckBox = document.querySelectorAll("input[name='ck']:checked")
	const ckArr = [];
	const bean = [];
	for(var a=0; a<ckBox.length; a++){
		ckArr[a]= ckBox[a].value;
	}
	
	if(ckArr.length==0){
		alert('선택하신 상품이 없습니다.');
	}
	else{
		if(confirm('선택하신 상품을 삭제하시겠습니까?')){
			fetch('/deleteProduct',{
				method:"POST",
				headers : {
					"Content-Type" : "application/json"
				},
				body : JSON.stringify(ckArr)
				
			})
			.then(function(response){
				if(response.ok==true){
					alert('선택하신 상품이 삭제되었습니다.');
					location.href='/product_list';
				}else{
					alert('선택하신 상품의 삭제가 실패하였습니다')
					location.href='/product_list';
				}
			})
			.catch(function(error){
				console.log(error);
			})
		}
	}
		
}
	
document.querySelector("#DeleteProductBtn").addEventListener("click", DeleteProduct);


</script>
</html>