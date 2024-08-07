package pay;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
public class pay_controller {
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value="/pay/coupon_api.do", method=RequestMethod.GET)
	public String coupon_api(HttpServletResponse res, HttpServletRequest req) throws Exception {
		
		String ips = req.getRemoteAddr();	//접속 도메인 및 IP 정보를 확인
		String browser = req.getHeader("User-Agent");
		if(browser.contains("Edg")) {
			System.out.println("Edge로 접속확인");
		}
		else if(browser.contains("Chrome")) {
			System.out.println("Chrome으로 접속 확인");
		}
		
		
		System.out.println(browser);
		
		/* Spring-boot 에서 사용하는 IP 정보 형태를 출력
		HttpServletRequest req2 = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		String ips2 = req.getHeader("X-FORWARED-FOR");
		System.out.println(ips2);
		*/
		PrintWriter pw = null;
		JSONObject jo = null;
		JSONArray ja = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int startpg = 0;
		int pageno = 2;	//2개씩
		pw = res.getWriter();
		
		try {
			con = new dbinfo().info();
			String sql = "select * from coupon order by cidx desc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			ja = new JSONArray();
			while(rs.next()) {
				jo = new JSONObject();
				jo.put("cidx", rs.getString(1));
				jo.put("cpname", rs.getString(2));
				jo.put("cprate", rs.getString(3));
				jo.put("cpuse", rs.getString(4));
				jo.put("cpdate", rs.getString(5));			
				ja.put(jo);
			}
			pw.print(ja);
			
			
			
		}catch(Exception e) {
			pw.print("error");
		}finally {
			pw.close();
			rs.close();
			ps.close();
			con.close();
			
		}
		return null;
	}
	
	
	

	
	//1page당 데이터 2개씩
	@GetMapping("/coupon_list.do")
	public String coupon_list(@RequestParam(value="", required=false)Integer page,
			Model m) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int pageno = 2; 	//한페이지당 2개씪
		int startpg = 0;
		
		try {
			if(page==null || page==1) {
				startpg = 0;		
			}
			else {
				startpg =(pageno -1) * pageno;
			}
			m.addAttribute("startpg", startpg); //가공된 page 번호
			con = new dbinfo().info();
			//데이터 총개수
			String count = "select count(*) as ctn from coupon";
			ps = con.prepareStatement(count);
			rs = ps.executeQuery();
			rs.next();
			m.addAttribute("total", rs.getInt("ctn"));
			ps.close();
			rs.close();
			
			
			//데이터

			String sql = "select * from coupon order by cidx desc limit ?,?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, startpg);
			ps.setInt(2, pageno);
			rs = ps.executeQuery();
			List<ArrayList<String>> all = new ArrayList<ArrayList<String>>();
			
			while(rs.next()) {
				ArrayList<String> al = new ArrayList<String>();
				al.add(rs.getString(1));
				al.add(rs.getString(2));
				al.add(rs.getString(3));
				al.add(rs.getString(4));
				al.add(rs.getString(5));
				all.add(al);
			}
			
			//addAllAttributes : Object 에서도 배열관련 객체를 차례대로 추가하는 방식
			// 단점: 2차배열은 jsp에 전달에 문제가 발생함
			//m.addAllAttributes(all);
			
		}catch(Exception e) {
			System.out.println(e);
			System.out.println("DB연결실패!!");
		}finally {
			rs.close();
			ps.close();
			con.close();
			
		}		
		return "/pay/coupon_list";
	}
	
	
	@PostMapping("/pay/pay3.do")
	public String pay3(@ModelAttribute("payinfo") pay_dao dao, 
			HttpServletRequest req) throws Exception {
		
		req.setAttribute("goodcode", dao.getGoodcode());	//상품코드
		req.setAttribute("goodname", dao.getGoodname());	//상품명
		req.setAttribute("goodea", dao.getGoodea());	//상품개수
		req.setAttribute("goodprice", dao.getGoodprice());	//상품가격
		req.setAttribute("price", dao.getPrice());	//결제금액		
		req.setAttribute("buyername", dao.getBuyername());	//결제자 이름
		req.setAttribute("buyertel", dao.getBuyertel());	//결제자 연락처
		req.setAttribute("buyeremail", dao.getBuyeremail());	//결제자 이메일
		req.setAttribute("rec_post", dao.getRec_post());	//우편번호
		req.setAttribute("rec_way", dao.getRec_way());	//도로명주소
		req.setAttribute("rec_addr", dao.getRec_addr());	//상세주소
		req.setAttribute("gopaymethod", dao.getGopaymethod());	//결제방식
		
		return "/pay3";
	}
	
	
	
	@PostMapping("/pay/pay2.do")
	public String pay2(@ModelAttribute("product") pay_dao dao ,Model m) throws Exception {
		List<String> as = new ArrayList<String>();
		as.add(dao.getProduct_code());
		as.add(dao.getProduct_nm());
		as.add(dao.getProduct_money());
		as.add(dao.getProduct_ea());
		as.add(dao.getPrice());
		
		/*
		Collection<String> cl = new ArrayList<String>();
		cl.add(null);
		*/
		/*
		Map<String, String> mp = new HashMap<String, String>();
		mp.put("pdnm", dao.getProduct_code());
		m.addAllAttributes(Arrays.asList(mp));
		*/
		
		//addAllAttributes : key가 없음, 단 배열명 + 자료형을 기반으로 jsp 출력
		m.addAllAttributes(Arrays.asList(as));	// jsp에서 출력을 하기 위해 get(), []로 출력가능함
		return "/pay2"; // WEB-INF/views
	}
	
	
}
