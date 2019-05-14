package com.cm.comparator;

import java.util.Comparator;

import com.cm.pojo.Product;

//人气比较器(评价 高的放前面)  
public class ProductReviewComparator implements Comparator<Product>{

	@Override
	public int compare(Product p1, Product p2) {
		return p2.getReviewCount()-p1.getReviewCount();
	}

}
