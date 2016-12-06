package hhh.app.data.bean;

import java.util.List;

/**
 * Created by hhh on 2016/11/7.
 */
public class ProductOption {
    private	int	product_option_id;
    private	int	product_id;
    private	int	option_id;
    private	String	value;
    private	int	required;

    private    List<ProductOptionValue> valueList;
}
