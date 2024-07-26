package com.navershop.www;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class web_Controller {
	
	PrintWriter pw = null;
	
	
	
	
	
	@GetMapping("/restapi.do")
	//@SessionAttribute: session이 이미 등록되어있는 상황일 경우 해당 정보를 가져올 수 있음
	public String restapi(@SessionAttribute(name="mid", required = false) String mid) throws Exception  {
		System.out.println(mid);
		if(mid==null) {
			System.out.println("로그인 후 결제내역을 확인하실 수 있습니다.");
		}
		else {
			System.out.println("결제내역은 다음과 같습니다.");
		}
		return null;
	}
	
	
	//HttpSession : interface를 활용하여, 세션을 빠르게 구현하는 방식 스타일
	@PostMapping("/loginok.do")
	public String loginok(@RequestParam(value="", required = false) String mid, HttpSession session) {
		if(mid != null || mid != "") {
		session.setAttribute("mid", mid);
		session.setMaxInactiveInterval(1800);
		}
		return null;
	}
	
	
	
	/*
	@PostMapping("/loginok.do")
	public String loginok(String mid, HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.setAttribute("mid", mid);
		session.setMaxInactiveInterval(1800 );
		System.out.println(mid);
		return null;
	}
	*/
	
	
	
	@PostMapping("/ajaxok3.do")
	public String ajaxok3(@RequestBody String arr,
			HttpServletResponse res) throws Exception {
		System.out.println(arr);
		JSONArray ja = new JSONArray(arr);
		//System.out.println(ja);
		//System.out.println(ja.get(1));
		//System.out.println(ja.length());
		
		int f,ff;
		for(f=0; f<ja.length(); f++) {
			JSONArray ja2 = (JSONArray)ja.get(f);
			for(ff=0; ff<ja2.length(); ff++) {
				System.out.println(ja2.get(ff));
			}
		}	
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
