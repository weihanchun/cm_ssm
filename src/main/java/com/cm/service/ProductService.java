package com.cm.service;

import java.util.List;

import com.cm.pojo.Category;
import com.cm.pojo.Collection;
import com.cm.pojo.Product;

public interface ProductService {
    public List<Product> list(int cid);

    public List<Product> search(String keyword);
	
    public void add(Product p);
  
    public void delete(Product p);
    
    public Product get(int id);
    
    public void update(Product p);
    //在后台显示产品图片
    void setFirstProductImage(Product p);
    
    // 为多个分类填充产品集合
    void fill(List<Category> cs);
    //为分类填充产品集合
    void fill(Category c);
    //为多个分类填充推荐产品集合，即把分类下的产品集合，按照8个为一行，
    //拆成多行，以利于后续页面上进行显示
    void fillByRow(List<Category> cs);
    //为产品设置销量和评价数量的方法
    void setSaleAndReviewNumber(Product p);
    void setSaleAndReviewNumber(List<Product> ps);
    /*//获得花语
    String setFlowerLanguage(List<Collection> cls);*/
}
