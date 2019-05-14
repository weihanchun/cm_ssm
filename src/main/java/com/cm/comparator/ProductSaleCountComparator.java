package com.cm.comparator;

import java.util.Comparator;

import com.cm.pojo.Product;

//销量比较器(销量 高的放前面)  
public class ProductSaleCountComparator implements Comparator<Product>{

	@Override
	public int compare(Product p1, Product p2) {
		return p2.getSaleCount()-p1.getSaleCount();
	}

}
