package shopping;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class product_dao {
	int product_id,sale_price,discount_rate,discount_price,stock_quantity;
	
	String category_pd,product_code,product_name,product_description,sale_status,early_end,main_image,additional_image1,additional_image2,detailed_description;
}
