package shopping;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.Document;

import org.apache.tomcat.util.log.UserDataHelper.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class admin_controller {
	PrintWriter pw = null;
	
	@Resource(name = "addmaster")
	private addmaster_module am;
	
	

	

	
	//일반회원관리
	@GetMapping("/shop_source/shop_member_list.do")
	public String gm_list(Model m, gmember_dao dao) throws Exception{
		List<gmember_dao> gm = am.gm_selectList();
		m.addAttribute("gm", gm);
		
		return "/shop_source/shop_member_list";
	}
	
	//상품등록 페이지에 '카테고리 품목' 보내기
	@GetMapping("/shop_source/product_write.do")
	public String pd_write(Model m, category_dao dao) throws Exception{
		List<category_dao> ct_data = am.category_selectList(dao);
		m.addAttribute("ct_data", ct_data);
		
		return "/shop_source/product_write";
	}
	
	//카테고리 리스트 출력
	@GetMapping("/shop_source/cate_list.do")
	public String ct_select(Model m, category_dao dao) throws Exception{
		List<category_dao> ct_data = am.category_selectList(dao);
		m.addAttribute("ct_data", ct_data);
		return "/shop_source/cate_list";

	}
	
	//카테고리 등록
	@PostMapping("/shop_source/cate_regist.do")
	public String ct_regist(@ModelAttribute category_dao dao,HttpServletResponse res) throws Exception{
		try {
		System.out.println(dao.category_code);
		System.out.println(dao.main_menu_name);
		System.out.println(dao.category_use);
		res.setContentType("text/html;charset=utf-8");
		int result = am.ct_insert(dao);
		if(result>0) {
			this.pw = res.getWriter();
			this.pw.print("<script>"
					+ "alert('카테고리 등록이 완료 되었습니다');"
					+ "location.href='/shop_source/cate_list.do';"
					+ "</script>");
			this.pw.close();
		}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
	}
	
	//상품 리스트 출력
	@GetMapping("/shop_source/product_select.do")
	public String pd_select(Model m,product_dao dao) throws Exception{
		List<product_dao> pd_data = am.product_selectlist(dao);
		m.addAttribute("pd_data", pd_data);
		
		return "/shop_source/product_list";
	}	
	//상품등록
	@PostMapping("/shop_source/product_regist.do")
	public String pd_regist(Model m,
			product_dao dao,
			MultipartFile pd_image[],
			HttpServletRequest req,
			HttpServletResponse res) throws Exception{
		res.setContentType("text/html;charset=utf-8");
	
		
		try {
			//파일 경로 배열 생성
			String[] imagePaths = new String[3];
			//파일 저장 및 경로 설정
            for (int i = 0; i < pd_image.length; i++) {
                imagePaths[i] = am.savefile(pd_image[i],req);
            }       
            //경로를 DAO에 설정
            
           dao.setProduct_image_origin(imagePaths[0]);
           dao.setProduct_image_additional_1(imagePaths[1]);
           dao.setProduct_image_additional_2(imagePaths[2]);	

            //나머지 상품 정보와 함께 DB에 저장
            int result = am.insertProduct(dao);
            if(result > 0) {
    			this.pw = res.getWriter();
    			this.pw.print("<script>"
    					+ "alert('정상적으로 상품 등록이 완료 되었습니다');"
    					+ "location.href='/shop_source/product_select.do';"
    					+ "</script>");
    			this.pw.close();           	
            }
            
            
		}catch (Exception e) {
			 e.printStackTrace();
			 System.out.println("오류: " + e.getMessage());
		}
		
           
		/*
		int result = am.pd_insert(dao, pd_image, req);
		System.out.println(result);
		
		if(result>0) {
			this.pw = res.getWriter();
			this.pw.print("<script>"
					+ "alert('정상적으로 상품 등록이 완료 되었습니다');"
					+ "location.href='/shop_source/product_select.do';"
					+ "</script>");
			this.pw.close();
		}
		*/		
		return null;		
	}
	
	//홈페이지 출력 (select)
	@GetMapping("/shop_source/siteinfo_select.do")
	public String siteinfo_select(Model m, sitesettings_dao dao) throws Exception {
		ArrayList<Object> siteinfo = am.siteinfo_selectone(dao);
		m.addAttribute("siteinfo", siteinfo);
		
		return "/shop_source/admin_siteinfo";
	}
	
	//홈페이지 셋팅 (insert)
	@PostMapping("/shop_source/siteinfo_regist.do")
	public String admin_siteinfo(sitesettings_dao dao,HttpServletResponse res) throws Exception {		
		int result = am.siteinfo_insert(dao);	
		if(result>0) {
			this.pw = res.getWriter();
			this.pw.print("<script>"
					+ "location.href='/shop_source/siteinfo_select.do';"
					+ "</script>");
			this.pw.close();
		}
		
		return null;
	}
	
	
	
	//일반회원 정지상태 여부
	@GetMapping("/shop_source/gmember_stop.do")
	public String gm_stop(Model m, int gidx, String gstop) throws Exception{
		
		int result = am.update_gstop(gidx,gstop);
		System.out.println(result);
		if(result>0) {
			List<gmember_dao> gm = am.gm_selectList();
			m.addAttribute("gm", gm);
			return "/shop_source/shop_member_list";
		}
		return null;
	}
	
	
	//관리자 승인,미승인 여부
	@GetMapping("/shop_source/admin_approve.do")
	public String admin_approve(Model m,int aidx,String approve) throws Exception {
		
		int result = am.update_approve(aidx,approve);	
		if(result>0) {
			List<addmaster_dao> adminlist = am.master_select();
			m.addAttribute("adminlist", adminlist);

			return "/shop_source/admin_list";
		}	
		return null;		
	}
	
	
	//관리자 로그아웃시 세션 종료 후 로그인페이지로 돌아감
	@GetMapping("/shop_source/admin_logout.do")
	public String admin_logout(HttpServletRequest req) throws Exception{
		HttpSession session = req.getSession();
		session.invalidate();
		return "/shop_source/index";
	}
		
	//상품 중복 체크
	@PostMapping("/shop_source/duplicate_pd.do")
	public String duplicate_pd(String pd_code, HttpServletResponse res) throws Exception{
		String str = "";
		int result = am.duplicate_pdselect(pd_code);
		this.pw = res.getWriter();
		if(result>0) {
			str="duplicated";
		}
		else {
			str="ok";
		}
		this.pw.print(str);
		this.pw.close();
		return null;
	}
	
	//아이디 중복체크
	@PostMapping("/shop_source/duplicate_id.do")
	public String duplicate_id(String ad_id, HttpServletResponse res) throws Exception{		
		String str = "";
		int result = am.duplicate_select(ad_id);
		this.pw = res.getWriter();
		if(result>0) {
			str="duplicated";
		}
		else {
			str="ok";
		}
		this.pw.print(str);
		this.pw.close();
		return null;
	}

	//관리자 등록
	@PostMapping("/shop_source/add_master.do")
	public String add_master(addmaster_dao dao, HttpServletResponse res) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		int result = am.master_insert(dao);
			if(result>0) {
				this.pw = res.getWriter();
				this.pw.print("<script>"
						+ "alert('회원가입이 완료되었습니다.');"
						+ "location.href='./index.jsp';"
						+ "</script>");
			}
			this.pw.close();	
		return null;
	}
	
	
	//관리자 리스트(selectList) 버튼을 눌렀을때 -> list페이지로 이동 메소드
	@GetMapping("/shop_source/admin_list.do")
	public String admin_list(Model m) throws Exception {
		List<addmaster_dao> adminlist = am.master_select();
		m.addAttribute("adminlist", adminlist);
		
		return "/shop_source/admin_list";
	}
	
	//관리자 로그인 -> 아이디,패스워드 확인(selectOne) 후 세션만듬-> 관리자 메인페이지로 이동
	@PostMapping("/shop_source/admin_main.do")
	public String admin_main(Model m, HttpServletResponse res, HttpServletRequest req,
			addmaster_dao dao) throws Exception {
		
		res.setContentType("text/html;charset=utf-8");
		ArrayList<Object> onearr = am.select_one(dao);	
	
		
		if(onearr.get(0) == null) {
			this.pw = res.getWriter();
			this.pw.print("<script>"
					+ "alert('아이디 및 패스워드를 확인하세요');"
					+ "location.href='./index.jsp';"
					+ "</script>");
			this.pw.close();
		}
		else {
			if(onearr.get(2).equals("Y")||onearr.get(2).equals("X")) {
				HttpSession session = req.getSession();
				session.setAttribute("mname", onearr.get(0));
				session.setAttribute("mid", onearr.get(1));
				session.setMaxInactiveInterval(1800);				
			}
			else {
				this.pw = res.getWriter();
				this.pw.print("<script>"
						+ "alert('관리자 승인이 완료되지 않았습니다.');"
						+ "location.href='./index.jsp';"
						+ "</script>");
				this.pw.close();
			}
		}
		
		return "/shop_source/admin_main";
	}
}
