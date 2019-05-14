package com.cm.comparator;

import java.util.Comparator;

import com.cm.pojo.Product;

//综合比较器(销量X评价 高的放前面)  
public class ProductAllComparator implements Comparator<Product>{

	@Override
	public int compare(Product p1, Product p2) {
		return p2.getReviewCount()*p2.getSaleCount()-p1.getReviewCount()*p1.getSaleCount();
	}

}
