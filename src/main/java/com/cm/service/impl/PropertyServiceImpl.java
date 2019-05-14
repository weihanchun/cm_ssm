package com.cm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.mapper.PropertyMapper;
import com.cm.pojo.Category;
import com.cm.pojo.Property;
import com.cm.service.CategoryService;
import com.cm.service.PropertyService;
@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
	PropertyMapper propertyMapper;
    @Autowired
    CategoryService categoryService;
    
	@Override
	public List<Property> list(int cid) {
		return propertyMapper.list(cid);
	}

	@Override
	public void add(Property p) {
        propertyMapper.add(p);
	}

	@Override
	public void delete(int id) {
       propertyMapper.delete(id);
	}

	@Override
	public Property get(int id) {
		Property p=propertyMapper.get(id);
		setCategory(p);
		return p;
	}

	@Override
	public void update(Property p) {
        propertyMapper.update(p);
	}
    
	//把property设置上category属性
	public void setCategory(Property p) {
		int cid=p.getCid();
		Category c=categoryService.get(cid);
		p.setCategory(c);
	}
}
