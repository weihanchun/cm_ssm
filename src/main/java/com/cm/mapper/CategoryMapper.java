package com.cm.mapper;

import java.util.List;

import com.cm.pojo.Category;
import com.cm.util.Page;

public interface CategoryMapper {
      public List<Category> list();
	
	//public List<Category> list(Page page);
     
     //public int total();
     
     public void add(Category category);

     public void delete(Category category);
     
     public Category get(int id);
     
     public void update(Category category);
}
