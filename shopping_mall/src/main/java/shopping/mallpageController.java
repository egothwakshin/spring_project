package shopping;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class mallpageController {
	PrintWriter pw = null;
	
	@Resource(name = "mallpage")
	private mallpageModule mm;
	
	@GetMapping("/mallpage/login")
	public String mallpage_login() {
		return "mallpage/login";
	}
	
	@GetMapping("/mallpage/join")
	public String mallpage_join() {
		return "mallpage/join";
	}
	
	@GetMapping("/mallpage/index")
	public String mallpage_index() {
		return "mallpage/index";
	}
	
	
	// 이용약관,개인정보약관 출력
	@GetMapping("/mallpage/agree")
	public String pp_select(Model m) throws Exception {

		String td = mm.terms_select();
		String pd = mm.privacyPolicy_select();

		m.addAttribute("termsText", td);
		m.addAttribute("privacy_Text", pd);

		return "/mallpage/agree";
	}
	// 개인정보 약관 수정
		@PostMapping("/submitPrivacyAjax")
		@ResponseBody
		public Map<String, String> submitPrivacyAjax(@RequestParam("privacy_Text") String privacy_Text) throws Exception {

			int result = mm.privacyPolicy_insert(privacy_Text);

			Map<String, String> response = new HashMap<String, String>();
			response.put("privacy_Text", privacy_Text);

			return response;

		}
		
		// 개인정보 약관 조회
		@GetMapping(value = "/getPrivacyPolicyAjax", produces = "application/text;charset=UTF8;")
		@ResponseBody
		public String getPrivacyPolicy() throws Exception {
		    // 약관을 조회하는 로직 (DB에서 가져오는 코드)
		    String PrivacyPolicy = mm.privacyPolicy_select(); // DB에서 이용약관을 가져오는 메서드
		    return PrivacyPolicy;
		}

		// 이용약관 수정
		// @PostMapping(value = "/submitTermsAjax", produces = "application/json")
		@PostMapping("/submitTermsAjax")
		@ResponseBody
		public Map<String, String> submitTermsAjax(@RequestParam("termsText") String termsText) throws Exception {

			int result = mm.terms_insert(termsText);

			Map<String, String> response = new HashMap<String, String>();
			response.put("terms", termsText);

			return response;
		}
		
		
		// 이용약관 조회
		@GetMapping(value = "/getTermsAjax", produces = "application/text;charset=UTF8;")
		@ResponseBody
		public String getTerms() throws Exception {
		    // 약관을 조회하는 로직 (DB에서 가져오는 코드)
		    String terms = mm.terms_select(); // DB에서 이용약관을 가져오는 메서드
		    return terms;
		}
		
		
}
