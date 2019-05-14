package com.cm.comparator;

import java.util.Comparator;

import com.cm.pojo.Product;

//新品比较器(创建日期晚 的放前面)  
public class ProductDateComparator implements Comparator<Product>{

	@Override
	public int compare(Product p1, Product p2) {//Date实现了comparable接口
		return p2.getCreateDate().compareTo(p1.getCreateDate());
	}

}
