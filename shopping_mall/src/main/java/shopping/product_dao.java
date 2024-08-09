package shopping;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class product_dao {
	int product_id,sale_price,discount_rate,discount_price,stock_quantity;
	
	String category_pd,product_code,product_name,product_description,sale_status,early_end,
		   product_image_origin,product_image_additional_1,product_image_additional_2,detailed_description;
}
