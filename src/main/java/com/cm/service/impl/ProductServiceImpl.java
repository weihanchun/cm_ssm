package com.cm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.mapper.ProductMapper;
import com.cm.pojo.Category;
import com.cm.pojo.Collection;
import com.cm.pojo.Product;
import com.cm.pojo.ProductImage;
import com.cm.pojo.PropertyValue;
import com.cm.service.CategoryService;
import com.cm.service.OrderItemService;
import com.cm.service.ProductImageService;
import com.cm.service.ProductService;
import com.cm.service.PropertyValueService;
import com.cm.service.ReviewService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductMapper productMapper;
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductImageService productImageService;
	@Autowired
	OrderItemService orderItemService;
	@Autowired
	ReviewService reviewService;
	@Autowired
	PropertyValueService propertyValueService;

	@Override
	public List<Product> list(int cid) {
		List<Product> ps=productMapper.list(cid);
		setCategory(ps);
		//调用方法为多个产品设置图片
		setFirstProductImage(ps);
		setSaleAndReviewNumber(ps);
		return ps;
	}

	@Override
	public void add(Product p) {
		productMapper.add(p);
	}

	@Override
	public void delete(Product p) {
		productMapper.delete(p);
	}

	@Override
	public Product get(int id) {
		Product p=productMapper.get(id);
		setCategory(p);
		//调用方法单个产品设置图片
		setFirstProductImage(p);
		setSaleAndReviewNumber(p);
		return p;
	}

	@Override
	public void update(Product p) {
		productMapper.update(p);		
	}

	//把取出来的product设置上category属性
	public void setCategory(Product p) {
		int cid=p.getCid();
		Category c=categoryService.get(cid);
		p.setCategory(c);
	}

	//把取出来的product设置上category属性
	public void setCategory(List<Product> ps) {
		for(Product p:ps) {
			setCategory(p);
		}
	}

	@Override
	//根据pid和图片类型查询出所有的单个图片，然后把第一个取出来放在firstProductImage上。
	public void setFirstProductImage(Product p) {
		List<ProductImage> pis=productImageService.list(p.getId(),ProductImageService.type_single);
		if(!pis.isEmpty()) {
			ProductImage pi=pis.get(0);
			p.setFirstProductImage(pi);
		}

	}
	//给多个产品设置图片
	public void setFirstProductImage(List<Product> ps) {
		for (Product p : ps) {
			setFirstProductImage(p);
		}
	}

	//为了实现一个分类对应多个产品(分类里面填充产品)
	@Override
	public void fill(List<Category> cs) {
		for(Category c:cs) {
			fill(c);
		}
	}
	//为了实现一个分类对应多个产品(分类里面填充产品)
	@Override
	public void fill(Category c) {
		List<Product> ps=list(c.getId());
		c.setProducts(ps);
	}

	@Override
	public void fillByRow(List<Category> cs) {
		int productNumberEachRow = 8;
		//将categorylist中每个category拿出来循环
		for (Category c : cs) {
			//获取每个分类中对应的产品，在使用这个方法前需要先使用fill方法，注入分类中的所有产品，因此在这里才可以取出产品	
			List<Product> products =  c.getProducts();
			//设置每一页的头图
			setFirstProductImage(products);
			//每一行产品的list
			List<List<Product>> productsByRow =  new ArrayList<>();
			for (int i = 0; i < products.size(); i+=productNumberEachRow) {
				int size = i+productNumberEachRow;
				size= size>products.size()?products.size():size;
				List<Product> productsOfEachRow =products.subList(i, size);//保证每行最多是8个信息
				productsByRow.add(productsOfEachRow);
			}
			c.setProductsByRow(productsByRow);
		}  		
	}

	@Override
	public void setSaleAndReviewNumber(Product p) {
		int sale=orderItemService.getCountByPID(p.getId());//获取该产品的总销量
		int review=reviewService.getCount(p.getId());//获取该产品的总评论
		p.setSaleCount(sale);
		p.setReviewCount(review);
	}

	@Override
	public void setSaleAndReviewNumber(List<Product> ps) {
		for(Product p:ps) {
			setSaleAndReviewNumber(p);
		}
	}

	@Override
	public List<Product> search(String keyword) {
		List<Product> ps=productMapper.listByName(keyword);
		setFirstProductImage(ps);
		setCategory(ps);
		return ps;
	}
}
