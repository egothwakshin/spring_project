package shoppingDao;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class sitesettings_dao {
	int sidx,
		initial_point,
		membership_level,
		min_payment_point,
		max_payment_point,
		delivery_cost;
	String 	homepage_title,
			admin_email,
			point_use,
			company_name,
			business_number,
			ceo_name,
			representative_phone,
			mail_order_number,
			additional_business_number,
			company_zipcode,
			company_address,
			info_manager_name,
			info_manager_email,
			bank_name,
			bank_account,
			credit_card_use,
			mobile_payment_use,
			book_coupon_payment_use,
			offline_payment_use,
			delivery_company_name,
			desired_delivery_date_use;		
}
