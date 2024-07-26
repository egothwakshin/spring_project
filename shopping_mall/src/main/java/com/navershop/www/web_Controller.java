package com.navershop.www;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class web_Controller {
	
	PrintWriter pw = null;
	
	
	@PostMapping("/ajaxok3.do")
	public String ajaxok3(@RequestBody String arr,
			HttpServletResponse res) throws Exception {
		System.out.println(arr);
		JSONArray ja = new JSONArray(arr);
		//System.out.println(ja);
		//System.out.println(ja.get(1));
		System.out.println(ja.length());
		JSONArray ja2 = (JSONArray)ja.get(1);
		System.out.println(ja2.get(0));
		System.out.println(ja2.get(1));
		System.out.println(ja2.get(2));
		/*
		int f,ff;
		for(f=0; f<ja.length(); f++) {
			for(ff=0; ff<)
		}
		*/
		

		
		this.pw = res.getWriter();
		this.pw.print("ok");
		this.pw.close();
		
		return null;
	}
	
	
	
	//@RequestBody : JSON.stringify
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/ajaxok2.do")
	public String ajaxok2(@RequestBody String all_data, HttpServletResponse res) throws Exception {
		//System.out.println(all_data);		//{"all_data":["홍길동","강감찬","이순신"]} : 문자열임
		
		
		JSONObject jo = new JSONObject(all_data); //{"all_data":["홍길동","강감찬","이순신"]} : 진짜 JSONObject로 변환됨
		System.out.println(jo.get("all_data")); //["홍길동","강감찬","이순신"]
		
		//내가 쓴 해결방식
		//JSONArray ja = jo.getJSONArray("all_data");
		//JSONArray ja = new JSONArray(jo.get("all_data").toString());
		
		//선생님 방식
		JSONArray ja = (JSONArray)jo.get("all_data");
		System.out.println(ja.get(0));	//데이터를 출력
		
		JSONObject result = new JSONObject();	//Front-end가 dataType="json" 로 받으므로 JSON으로 결과값을 회신
		result.put("result", "ok");
		this.pw = res.getWriter();
		this.pw.print(result);
		this.pw.close();

		return null;
	}
	
	
	
	
	
	
	
	
	//@RequestBody : GET/POST(X) JSON기반일 경우. (주로 post)
	//@ResponseBody : 미디어타입, 파라미터타입 (단, 인자값에 선언하지 않음)
	//ajax통신 - CORS방식
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	
	//@RequestParam : 배열을 이용하여 대표키로 전달 또는 대표키없이 보조키로 전달될 경우 사용할수 있음
	@GetMapping("/ajaxok.do")
	public String ajaxok(@RequestParam(value = "all_data") List<String> alldata,
			HttpServletResponse res) throws Exception {
		System.out.println(alldata);
		System.out.println(alldata.get(0));
		this.pw = res.getWriter();
		JSONObject jo = new JSONObject();
		jo.put("result", "ok");
		this.pw.print(jo);
		this.pw.close();
		return null;
	}
	
}
