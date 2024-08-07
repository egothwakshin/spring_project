package com.navershop.www;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
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

//md5 : 회원가입,로그인,패스워드변경,1:1문의,자유게시판,상품구매
@Controller
public class web_Controller extends md5_pass {
	
	PrintWriter pw = null;

	//DAO 사용할 경우=> @ModelAttribute 필수
	//DAO 사용하지 않을 경우 => 자료형 객체 or @RequestParam 사용
	@Resource(name = "userselect")
	private user_select us;

	@PostMapping("/idsearch.do")
	public String idsearch(String uname, String uemail, HttpServletResponse res) throws Exception {		//아이디 찾기
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		try {
			if(uname==null || uemail==null) {
				this.pw.print("<script>"
						+ "alert('올바른 접근방식이 아닙니다.');"
						+ "history.go(-1);"
						+ "</script>");
			}else {

				ArrayList<Object> onedata = us.search_id(uname, uemail);				
			}
		}catch(Exception e) {
			System.out.println(e);
			this.pw.print("<script>"
					+ "alert('Database 문제로 인하여 해당정보가 확인되지 않습니다.');"
					+ "history.go(-1);"
					+ "</script>");
		}finally {
			this.pw.close();
		}
		
		return null;
	}
	
	@PostMapping("/passmodify.do")
	public String passmodify() {	//패스워드 찾기(변경)
		
		
		return null;
	}
	
	
	
	
	
	
	
	//@Resource(name = "md5pass")
	//private md5_pass md;
	
	//패스워드 변경 여부를 체크(MD5)
	@GetMapping("/passwd.do")
	public String passwd() {
		String pwd = "a1234";
		String result = this.md5_making(pwd);
		System.out.println(result);
		
		return null;
	}
	
	
	
	
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
	
	
	
	@PostMapping("/ajaxok4.do")
	public String ajaxok4(@RequestBody String basket,
			HttpServletResponse res) throws Exception {
		//System.out.println(basket);
		
	
		
		JSONArray ja = new JSONArray(basket);
		//System.out.println(ja);
		//System.out.println(ja.get(1));
		//System.out.println(ja.length());

		
		
		int f,ff;
		for(f=0; f<ja.length(); f++) {
			JSONObject jo = (JSONObject)ja.get(f);
				System.out.println(jo.get("seq"));
				System.out.println(jo.get("product"));
				System.out.println(jo.get("price"));		
		}	
		
		
		
		return null;
	}
	
	
	
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
