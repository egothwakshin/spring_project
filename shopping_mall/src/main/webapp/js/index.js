var basket = [
			{"seq":"1","product":"냉장고","price":"195000"},
			{"seq":"2","product":"세탁기","price":"28700"},
			{"seq":"3","product":"에어프라이어","price":"97000"}
		];



$(function(){
	
	//Front 배열값 응용편
	
	$("#btn3").click(function(){
		var $arr = [
				["10%","20%","30%"],
				["30%","40%","50%"]
			];
		console.log($arr);	
		
		$.ajax({
			url: "./ajaxok3.do",
			type: "post",
			cache: false,
			dataType: "text",
			contentType:"application/json",
			data: JSON.stringify($arr),
			success:function($result){
				console.log($result);
			},
			error:function(){
				console.log("error!!");
			}				
		});
	});
	
	
	
	
	
	
	
	
	
	
	$("#btn2").click(function(){
		var $data = new Array();
		$data[0] = "홍길동";
		$data[1] = "강감찬";
		$data[2] = "이순신";
		
		$.ajax({
			url: "/ajaxok2.do",
			type: "post",
			cache: false,
			dataType: "json",	//프론트가 받는 진짜 리얼 타입
			contentType:"application/json",	//백엔드가 보내는 형식적인 타입유형
			data: JSON.stringify({	//JSON.stringfy: Object형태로 전달됨
				"all_data":$data
			}),
			success:function($result){
				console.log($result);
			},
			error:function(){
				console.log("error!!");
			}		
		});	
	});
	
	
	$("#btn").click(function(){
		var $data = new Array();
		$data[0] = "20";
		$data[1] = "30";
		$data[2] = "40";
		//join->JSON.stringfy 형태로 변화
		//console.log($data.join(","));
	
		$.ajax({
			url:"./ajaxok.do",
			cache:"false",
			dataType:"text",
			contentType:"application/json",
			type:"get",
			data:{
				"all_data":$data.join(",")
			},
			success:function($result){
				console.log($result);
			},
			error:function(){
				console.log("error!!");
			}						
		});
	});
	
});