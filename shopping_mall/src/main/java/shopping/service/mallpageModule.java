package shopping.service;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("mallpage")
public class mallpageModule {
	
	@Resource(name = "template2")
	private SqlSessionTemplate tm2;
	
	//개인정보약관 수정(입력)
	public int privacyPolicy_insert(String content) {
		int result = tm2.insert("shop_source.privacyPolicy_insert",content);
		return result;
	}
	
	//개인정보약관 출력
	public String privacyPolicy_select(){
		String pd = tm2.selectOne("shop_source.privacyPolicy_select");
		return pd;
	}
	
	
	//이용약관 수정(입력)
	public int terms_insert(String content) {
		int result = tm2.insert("shop_source.terms_insert", content);
		return result;
	}
	
	//이용약관 출력
	public String terms_select() {
		String terms = tm2.selectOne("shop_source.terms_select");
		return terms;
	}
	

}
