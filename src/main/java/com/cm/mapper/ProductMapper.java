package com.cm.mapper;

import java.util.List;

import com.cm.pojo.Product;

public interface ProductMapper {
	public List<Product> list(int cid);

	public void add(Product p);

	public void delete(Product p);

	public Product get(int id);

	public void update(Product p);
	
	public List<Product> listByName(String keyword);
}
