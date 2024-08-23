package shopping;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Repository("addmaster")
public class addmaster_module {
	
	@Resource(name = "template2")
	private SqlSessionTemplate tm2;
	
	//개인정보약관 출력
	public List<privacyPolicy_dao> privacyPolicy_select(){
		List<privacyPolicy_dao> pd = tm2.selectList("shop_source.privacyPolicy_select");
		return pd;
	}
	
	//개인정보약관 수정(입력)
	public int privacyPolicy_insert(String content) {
		int result = tm2.insert("shop_source.privacyPolicy_insert",content);
		return result;
	}
	
	//이용약관 출력
	public List<terms_dao> terms_select(){
		List<terms_dao> td = tm2.selectList("shop_source.terms_select");
		return td;
	}
	
	//이용약관 수정(입력)
	public int terms_insert(String content) {
		int result = tm2.insert("shop_source.terms_insert", content);
		return result;
	}
	
	//일반회원리스트 출력
	public List<gmember_dao> gm_selectList(){
		List<gmember_dao> gm = tm2.selectList("shop_source.gmember_select");		
		return gm;
	}
	
	
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
	
	
	//상품 선택삭제
	public int product_delete(List<String> dp){
		
		int result = tm2.delete("shop_source.product_delete", dp);
		return result;
	}
	
	
	
	//상품 셋팅
	public List<product_dao> product_selectlist(product_dao dao){
		List<product_dao> lp = tm2.selectList("shop_source.product_select",dao);
		return lp;
	}
	
	
	
	//파일명을 랜덤메소드를 이용하여 생성
	public String rename() {
		Date day = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String today = sf.format(day);
		
		int no = (int)Math.ceil(Math.random()*1000);
		String datacode = today + no;
		return datacode;
	}
	//
	private String saveFile(MultipartFile file, String uploadPath) throws Exception{
		
		if(file.getSize() > 0) {
			String oriName = file.getOriginalFilename();
			String newName = this.rename() + "_" + oriName;
			
            File saveFile = new File(uploadPath + newName);
            file.transferTo(saveFile);

            return newName;			
		}		
		return null;
	}	
	//
	public void handleFile(MultipartFile[] pd_image,product_dao dao,HttpServletRequest req) throws Exception{
				
		String uploadPath  = req.getServletContext().getRealPath("/project0729/");
		int index = 0;
		
		for(MultipartFile file : pd_image) {
			String newName = saveFile(file, uploadPath);
			
			if (newName != null) {
                switch (index) {
                    case 0:
                        dao.setProduct_image_origin(newName);
                        break;
                    case 1:
                        dao.setProduct_image_additional_1(newName);
                        break;
                    case 2:
                        dao.setProduct_image_additional_2(newName);
                        break;
                    default:
                        // 3개 이상의 파일이 있는 경우 추가 처리 가능
                        break;
                }
                index++;		
			}			
		}
	}
	
    // 상품 정보를 DB에 insert하는 메서드
    public int insertProduct(product_dao dao) {
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
	
	
	
	//일반회원 정지상태 업데이트
	public int update_gstop(int gidx, String gstop) {
		
		Map<String, Object> mp = new HashMap<String, Object>();
		mp.put("gidx", gidx);
		mp.put("gstop", gstop);
		int result = this.tm2.update("shop_source.gm_stop", mp);
		
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
		
		SecurityPasswordEntity SecurityPasswordEntity = new SecurityPasswordEntity(dao);

		addmaster_dao onedata = tm2.selectOne("shop_source.search_ad", SecurityPasswordEntity);
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
