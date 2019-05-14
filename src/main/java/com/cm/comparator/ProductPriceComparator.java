package com.cm.comparator;

import java.util.Comparator;

import com.cm.pojo.Product;

//价格比较器(价格 高的放前面)  
public class ProductPriceComparator implements Comparator<Product>{

	@Override
	public int compare(Product p1, Product p2) {//把差异放大,可以识别零点几的差价
       	float result=p1.getPromotePrice()-p2.getPromotePrice();
       	if(result>0)
            return (int) (result+1);
        else if(result==0)
            return 0;
        else
            return (int) (result-1);
       	//return Float.compare(p1.getPromotePrice(), p2.getPromotePrice());
	}

}
