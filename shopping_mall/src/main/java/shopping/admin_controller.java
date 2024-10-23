package shopping;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.Document;

import org.apache.tomcat.util.log.UserDataHelper.Mode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class admin_controller {
	PrintWriter pw = null;

	@Resource(name = "addmaster")
	private addmaster_module am;
	
	@GetMapping("/index")
	public String index() {	
		return "/shop_source/index";
	}
	
	
	@GetMapping("/noticeView")
	public String notice_view() {
		return "shop_source/notice_view";
	}
	
	@GetMapping("/noticeWrite")
	public String notice_write() {
		return "shop_source/notice_write";
	}
	
	
	@GetMapping("/noticeList")
	public String notice_list() {
		return "/shop_source/notice_list";
	}
	
	@GetMapping("/cate_write")
	public String cate_write() {	
		return "/shop_source/cate_write";
	}
	@PostMapping("/go_add_master")
	public String go_add_master() {
		return "/shop_source/add_master";
	}


	// 일반회원관리
	@GetMapping("/shop_member_list")
	public String gm_list(Model m, gmember_dao dao) throws Exception {
		List<gmember_dao> gm = am.gm_selectList();
		m.addAttribute("gm", gm);

		return "/shop_source/shop_member_list";
	}

	// 상품등록 페이지에 '카테고리 품목' 보내기
	@GetMapping("/product_write")
	public String pd_write(Model m, category_dao dao) throws Exception {
		List<category_dao> ct_data = am.category_selectList(dao);
		m.addAttribute("ct_data", ct_data);

		return "/shop_source/product_write";
	}

	// 카테고리 리스트 출력
	@GetMapping("/cate_list")
	public String ct_select(Model m, category_dao dao) throws Exception {
		List<category_dao> ct_data = am.category_selectList(dao);
		m.addAttribute("ct_data", ct_data);
		return "/shop_source/cate_list";

	}

	// 카테고리 등록
	@PostMapping("/cate_regist")
	public String ct_regist(@ModelAttribute category_dao dao, HttpServletResponse res) throws Exception {
		try {
			System.out.println(dao.category_code);
			System.out.println(dao.main_menu_name);
			System.out.println(dao.category_use);
			res.setContentType("text/html;charset=utf-8");
			int result = am.ct_insert(dao);
			if (result > 0) {
				this.pw = res.getWriter();
				this.pw.print("<script>" + "alert('카테고리 등록이 완료 되었습니다');" + "location.href='/cate_list';"
						+ "</script>");
				this.pw.close();
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}

	// @상품 선택삭제
	@PostMapping("/deleteProduct")
	@ResponseBody
	public ResponseEntity<Integer> deleteProduct(@RequestBody List<String> dp) throws Exception {
		ResponseEntity<Integer> response = am.product_delete(dp);

		return response;
	}

	// 상품 리스트 출력
	@GetMapping("/product_list")
	public String pd_select(Model m, product_dao dao) throws Exception {
		List<product_dao> pd_data = am.product_selectlist(dao);
		m.addAttribute("pd_data", pd_data);

		return "/shop_source/product_list";
	}

	// 상품등록
	@PostMapping("/product_regist")
	public String pd_regist(Model m, product_dao dao, MultipartFile pd_image[], HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		res.setContentType("text/html;charset=utf-8");

		am.handleFile(pd_image, dao, req);
		int result = am.insertProduct(dao);
		if (result > 0) {
			this.pw = res.getWriter();
			this.pw.print("<script>" + "alert('정상적으로 상품 등록이 완료 되었습니다');"
					+ "location.href='/product_list';" + "</script>");
			this.pw.close();
		}

		return null;
	}

	// 홈페이지 출력 (select)
	@GetMapping("/siteinfo_list")
	public String siteinfo_select(Model m, sitesettings_dao dao) throws Exception {
		ArrayList<Object> siteinfo = am.siteinfo_selectone(dao);
		m.addAttribute("siteinfo", siteinfo);

		return "/shop_source/admin_siteinfo";
	}

	// 홈페이지 셋팅 (insert)
	@PostMapping("/siteinfo_regist")
	public String admin_siteinfo(sitesettings_dao dao, HttpServletResponse res) throws Exception {
		int result = am.siteinfo_insert(dao);
		if (result > 0) {
			this.pw = res.getWriter();
			this.pw.print("<script>" + "location.href='/siteinfo_list';" + "</script>");
			this.pw.close();
		}

		return null;
	}

	// 일반회원 정지상태 여부
	@GetMapping("/gmember_stop")
	public String gm_stop(Model m, int gidx, String gstop) throws Exception {

		int result = am.update_gstop(gidx, gstop);
		
		if (result > 0) {
			List<gmember_dao> gm = am.gm_selectList();
			m.addAttribute("gm", gm);
			return "/shop_source/shop_member_list";
		}
		return null;
	}

	// 관리자 승인,미승인 여부
	@GetMapping("/admin_approve.do")
	public String admin_approve(Model m, int aidx, String approve) throws Exception {

		int result = am.update_approve(aidx, approve);
		if (result > 0) {
			List<addmaster_dao> adminlist = am.master_select();
			m.addAttribute("adminlist", adminlist);

			return "/shop_source/admin_list";
		}
		return null;
	}

	// 관리자 로그아웃시 세션 종료 후 로그인페이지로 돌아감
	@GetMapping("/admin_logout")
	public String admin_logout(HttpServletRequest req) throws Exception {
		HttpSession session = req.getSession();
		session.invalidate();
		return "/shop_source/index";
	}

	// 상품 중복 체크
	@PostMapping("/duplicate_pd")
	public String duplicate_pd(String pd_code, HttpServletResponse res) throws Exception {
		String str = "";
		int result = am.duplicate_pdselect(pd_code);
		this.pw = res.getWriter();
		if (result > 0) {
			str = "duplicated";
		} else {
			str = "ok";
		}
		this.pw.print(str);
		this.pw.close();
		return null;
	}

	// 아이디 중복체크
	@PostMapping("/duplicate_id")
	public String duplicate_id(String ad_id, HttpServletResponse res) throws Exception {
		String str = "";
		int result = am.duplicate_select(ad_id);
		this.pw = res.getWriter();
		if (result > 0) {
			str = "duplicated";
		} else {
			str = "ok";
		}
		this.pw.print(str);
		this.pw.close();
		return null;
	}

	// 관리자 등록
	@PostMapping("/add_master")
	public String add_master(addmaster_dao dao, HttpServletResponse res) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		int result = am.master_insert(dao);
		if (result > 0) {
			this.pw = res.getWriter();
			this.pw.print("<script>" + "alert('회원가입이 완료되었습니다.');" + "location.href='./index.jsp';" + "</script>");
		}
		this.pw.close();
		return null;
	}

	// 관리자 리스트(selectList) 버튼을 눌렀을때 -> list페이지로 이동 메소드
	@GetMapping("/admin_list")
	public String admin_list(Model m) throws Exception {
		List<addmaster_dao> adminlist = am.master_select();
		m.addAttribute("adminlist", adminlist);

		return "/shop_source/admin_list";
	}

	// 관리자 로그인 -> 아이디,패스워드 확인(selectOne) 후 세션만듬-> 관리자 메인페이지로 이동
	@PostMapping("/shop_member_list")
	public String admin_main(Model m, HttpServletResponse res, HttpServletRequest req, addmaster_dao dao)
			throws Exception {
		res.setContentType("text/html;charset=utf-8");
		ArrayList<Object> onearr = am.select_one(dao);
		if (onearr.get(0) == null) {
			this.pw = res.getWriter();
			this.pw.print("<script>" + "alert('아이디 및 패스워드를 확인하세요');" + "location.href='/index';" + "</script>");
			this.pw.close();
		} else {
			if (onearr.get(2).equals("Y") || onearr.get(2).equals("X")) {
				HttpSession session = req.getSession();
				session.setAttribute("mname", onearr.get(0));
				session.setAttribute("mid", onearr.get(1));
				session.setMaxInactiveInterval(1800);

				List<gmember_dao> gm = am.gm_selectList();
				m.addAttribute("gm", gm);
				return "/shop_source/shop_member_list";
			} else {
				this.pw = res.getWriter();
				this.pw.print(
						"<script>" + "alert('관리자 승인이 완료되지 않았습니다.');" + "location.href='./index.jsp';" + "</script>");
				this.pw.close();
			}
		}
		return null;
	}
}
