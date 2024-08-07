package shopping;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("addmaster")
public class addmaster_module {
	
	@Resource(name = "template2")
	private SqlSessionTemplate tm2;
	
	//일반회원리스트 출력
	public List<gmember_dao> gm_selectList(gmember_dao dao){
		List<gmember_dao> gm = tm2.selectList("shop_source.gmember_select",dao);		
		return gm;
	}
	
	//일반회원 가입
	
	//카테고리 출력
	public List<category_dao> category_selectList(category_dao dao){
		List<category_dao> lc = tm2.selectList("shop_source.category_select",dao);
		return lc;
	}
	
	//카테고리 등록
	public int ct_insert(category_dao dao) throws Exception {
		int result = tm2.insert("shop_source.category_insert", dao);
		return result;
	}
	
	//상품 셋팅
	public List<product_dao> product_selectlist(product_dao dao){
		List<product_dao> lp = tm2.selectList("shop_source.product_select",dao);
		return lp;
	}
	
	//상품 등록
	public int pd_insert(product_dao dao) throws Exception {
		int result = tm2.insert("shop_source.product_insert", dao);
		return result;
	}
	
	
	
	//홈페이지 셋팅 (selectone)
	public ArrayList<Object> siteinfo_selectone(sitesettings_dao dao){
		
		sitesettings_dao onedata = tm2.selectOne("shop_source.siteinfo_onedata", dao);
		ArrayList<Object> onearr = new ArrayList<Object>();
		onearr.add(onedata.getSidx());
		onearr.add(onedata.getHomepage_title());
		onearr.add(onedata.getAdmin_email());
		onearr.add(onedata.getPoint_use());
		onearr.add(onedata.getInitial_point());
		onearr.add(onedata.getMembership_level());
		onearr.add(onedata.getCompany_name());
		onearr.add(onedata.getBusiness_number());
		onearr.add(onedata.getCeo_name());		
		onearr.add(onedata.getRepresentative_phone());
		onearr.add(onedata.getMail_order_number());
		onearr.add(onedata.getAdditional_business_number());
		onearr.add(onedata.getCompany_zipcode());
		onearr.add(onedata.getCompany_address());
		onearr.add(onedata.getInfo_manager_name());
		onearr.add(onedata.getInfo_manager_email());
		onearr.add(onedata.getBank_name());
		onearr.add(onedata.getBank_account());
		onearr.add(onedata.getCredit_card_use());
		onearr.add(onedata.getMobile_payment_use());
		onearr.add(onedata.getBook_coupon_payment_use());
		onearr.add(onedata.getMin_payment_point());
		onearr.add(onedata.getMax_payment_point());
		onearr.add(onedata.getOffline_payment_use());
		onearr.add(onedata.getDelivery_company_name());
		onearr.add(onedata.getDelivery_cost());
		onearr.add(onedata.getDesired_delivery_date_use());
			
		return onearr;
	}
	
	//홈페이지 셋팅 (insert)
	public int siteinfo_insert(sitesettings_dao dao) throws Exception {
		int result = tm2.insert("shop_source.siteinfo_insert", dao);
		return result;
	}
	
	
	//관리자 승인 업데이트
	public int update_approve(int aidx,String approve) {

		Map<String, Object> mp = new HashMap<String, Object>();
		mp.put("adix", aidx);
		mp.put("approve", approve);
		int result = this.tm2.update("shop_source.update_approve", mp);
		
		return result;
	}	
	
	//로그인한 당사자 셀렉트원
	public ArrayList<Object> select_one(addmaster_dao dao) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(dao.ad_pw.getBytes());
			byte m[] = md5.digest();
			StringBuilder sb = new StringBuilder();
			for(byte w : m) {
				String word = String.format("%x", w);
				sb.append(word);
			}
			dao.ad_pw = sb.toString();
		}catch(Exception e) {
			System.out.println(e);
		}		
		
		addmaster_dao onedata = tm2.selectOne("shop_source.search_ad", dao);
		ArrayList<Object> onearr = new ArrayList<Object>();
		if(onedata==null) {
			onearr.add(null);
		}else {
			onearr.add(onedata.getAd_name());
			onearr.add(onedata.getAd_id());
			onearr.add(onedata.getAd_approve());
			
		}
		return onearr;
	}
	
	//관리자 리스트 출력 (select)
	public List<addmaster_dao> master_select() {
		List<addmaster_dao> data = new ArrayList<addmaster_dao>();
		data = tm2.selectList("shop_source.select_master");
		return data;
	}
	
	//관리자 등록
	public int master_insert(addmaster_dao dao) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(dao.ad_pw.getBytes());
			byte m[] = md5.digest();
			StringBuilder sb = new StringBuilder();
			for(byte w : m) {
				String word = String.format("%x", w);
				sb.append(word);
			}
			dao.ad_pw = sb.toString();
		}catch(Exception e) {
			System.out.println(e);
		}		
		int result = tm2.insert("shop_source.insert_master", dao);
		return result;				
	}
	
	//상품코드 중복체크
	public int duplicate_pdselect(String pd_code) {
		int result = tm2.selectOne("shop_source.duplicate_pd", pd_code);
		return result;
	}
	
	//아이디 중복체크
	public int duplicate_select(String ad_id) {
		int result = tm2.selectOne("shop_source.duplicate_id", ad_id);
		return result;
	}

	
}
