package com.cm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.mapper.CollectionMapper;
import com.cm.pojo.Collection;
import com.cm.pojo.Product;
import com.cm.pojo.PropertyValue;
import com.cm.service.CollectionService;
import com.cm.service.ProductService;
import com.cm.service.PropertyValueService;

@Service
public class CollectionServiceImpl implements CollectionService {
	@Autowired
	CollectionMapper collectionMapper;
	@Autowired
	ProductService productService;
    @Autowired
    PropertyValueService propertyValueService;
    
	@Override
	public List<Collection> listByUid(int uid) {
		List<Collection> cls=collectionMapper.listByUid(uid);
		setProduct(cls);
		setFlowerLanguage(cls);
		return cls;
	}

	//把取出来的collection设置上product属性
	public void setProduct(List<Collection> list) {
		for(Collection c:list) {
			int pid=c.getPid();
			Product p=productService.get(pid);
			c.setProduct(p);
		}	
	}

	@Override
	public void add(Collection c) {
		collectionMapper.add(c);		
	}

	@Override
	public void delete(int id) {
		collectionMapper.delete(id);		
	}
	//获得花语
	public void setFlowerLanguage(List<Collection> cls) {
		for(Collection c:cls) {
			int pid=c.getPid();
			List<PropertyValue> pvs=propertyValueService.list(pid);   
			PropertyValue pv=pvs.get(4);
			String flower=pv.getValue();
			c.setFlowerLanguage(flower);
		}
	}
}
