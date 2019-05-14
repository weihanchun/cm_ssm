package com.cm.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.mapper.CategoryMapper;
import com.cm.pojo.Category;
import com.cm.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryMapper categoryMapper;
	
	/*@Override
	public List<Category> list(Page page) {
		
		return categoryMapper.list(page);
	}

	@Override
	public int total() {
		// TODO Auto-generated method stub
		return categoryMapper.total();
	}
*/
	@Override
	public void add(Category category) {
        categoryMapper.add(category);		
	}

	@Override
	public void delete(Category c) {
        categoryMapper.delete(c);		
	}

	@Override
	public Category get(int id) {
		return categoryMapper.get(id);
	}

	@Override
	public void update(Category category) {
         categoryMapper.update(category);		
	}

	@Override
	public List<Category> list() {
		return categoryMapper.list();
	}
	
}
