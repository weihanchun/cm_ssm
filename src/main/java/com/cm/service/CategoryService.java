package com.cm.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.cm.pojo.Category;

public interface CategoryService {
    public List<Category> list();
	
	//public List<Category> list(Page page);
    
    //public int total();
    
    public void add(Category category);
  
    public void delete(Category category);
    
    public Category get(int id);
    
    public void update(Category category);
    
}
