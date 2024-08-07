<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 페이징 사용법</title>
</head>
<body>
<p>쿠폰리스트</p>
${stringList.get(0)}
<table border="1">
	<thead>
		<tr>
			<th>번호</th>
			<th>쿠폰명</th>
			<th>할인율</th>
			<th>사용 유/무</th>
			<th>만료기한</th>
		</tr>
	</thead>
	<tbody>
	<cr:set var="ino" value="${total-startpg}" />
	<cr:forEach var="data" items="${all}" varStatus="idx">
		<tr>
			<td>${ino-idx.index}</td>
			<td>${data.get(1)}</td>
			<td>${data.get(2)}</td>
			<td>${data.get(3)}</td>
			<td>${data.get(4)}</td>
		</tr>
	</cr:forEach>
	</tbody>
</table>
<!-- 
	[파라미터로 page 번호 주는 형태]
	백엔드가 페이징과 카운팅을 해서 프론트엔드에게 데이터 전송
	
	[파라미터 page 없을 경우]
	백엔드가 JSONArray, JSONObject로 JSON 형태로 만들어서 전송 (RestAPI)
	프론트엔드가 API Data를 받아서 페이징과 카운팅 처리 후 출력

 -->

<!-- 페이지 번호 출력 -->
<table border="1">
	<tr>
		<cr:set var="pg" value="${total/2 + (1-(total/2) % 2) % 1) % 1 }"/>
		<cr:forEach var="no" begin="1" end="${pg}" step="1">
			<td style="width:15px;"><a href="./coupon_list.do?page=${no}">${no}</a></td>
		</cr:forEach>
	</tr>

</table>
</body>
</html>