package shopping.service;

import java.security.MessageDigest;

import lombok.Getter;
import lombok.Setter;
import shoppingDao.addmaster_dao;

@Getter
@Setter
public class SecurityPasswordEntity {

	String ad_id,ad_pw;
	
	public SecurityPasswordEntity(addmaster_dao dao) {
		this.setAd_id(dao.getAd_id());
		this.setAd_pw(this.passwordSecurity(dao.getAd_pw()));
	
	}
	
	public String passwordSecurity(String pw) {
		
		try {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(pw.getBytes());
		byte m[] = md5.digest();
		StringBuilder sb = new StringBuilder();
		for(byte w : m) {
			String word = String.format("%x", w);
			sb.append(word);
		}
		pw = sb.toString();
		}catch(Exception e) {
			
		}	
		return pw;
	}
}
