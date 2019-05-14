package com.cm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.mapper.ProductImageMapper;
import com.cm.pojo.ProductImage;
import com.cm.service.ProductImageService;
@Service
public class ProductImageServiceImpl implements ProductImageService{
    @Autowired
    ProductImageMapper productImageMapper;
	
	@Override
	public void add(ProductImage pi) {
		productImageMapper.add(pi);	
	}

	@Override
	public void delete(int id) {
		productImageMapper.delete(id);		
	}

	@Override
	public ProductImage get(int id) {
		return productImageMapper.get(id);
	}

	@Override
	public List<ProductImage> list(int pid, String type) {
		return productImageMapper.list(pid, type);
	}

}
