var uri = window.location.search;	//웹 URL에 있는 ?에 있는 파라미터 값 가져오기
var pageno = "";	//페이지 번호

if(uri == ""){	//최초 접속시
	pageno = 1;	
}
else{	//페이지번호 클릭시
	pageno = uri.split("?page=")[1];	//페이지번호만 추출하는 코드
}
//console.log(pageno);

function ajax_data(){
	var http;
	http = new XMLHttpRequest();
	http.onreadystatechange = function(){
		if(http.readyState == 4 && http.status == 200){
			html_code(JSON.parse(this.response));
		}
	}
	http.open("GET","./coupon_api.do",true);
	http.send();		
}
ajax_data();

//JSON데이터를 HTML로 출력
function html_code(data){
	
	var datano = 2;	//1페이지당 2개의 데이터
	
	var startpage = (pageno -1) * datano;	//데이터배열 시작하는 노드번호
	//console.log(startpage);
	var endpage = datano * pageno;	//데이터 배열의 끝나는 노드번호
	

	var ea = data.length;	//API Data 총 배열갯수
	var result = document.getElementById("list");
	document.getElementById("total").append(ea);
	var pagehtml = document.getElementById("pages");
	
	//페이징 출력
	var pgtotal = Math.ceil(ea / datano);	//소수점올림 페이지번호 생성
	for(var p=1; p<=pgtotal; p++){	//반복문을 이용하여 페이지 번호를 출력하는 코드
		pagehtml.innerHTML += `<td><a href='./coupon_list.jsp?page=`+p+`'>`+p+`</a></td>`;
	}
	
		
	//데이터 출력
	data.forEach(function(a,b,c){
		if(b >= startpage && b < endpage ){
			result.innerHTML += `
				<tr>
					<td>`+(ea-b)+`</td>
					<td>`+data[b]["cpname"]+`</td>
					<td>`+data[b]["cprate"]+`</td>
					<td>`+data[b]["cpuse"]+`</td>
					<td>`+data[b]["cpdate"]+`</td>
				</tr>
				`;
		}

	});
}