package com.cm.service;

import java.util.List;

import com.cm.pojo.Category;
import com.cm.pojo.Property;

public interface PropertyService {
    public List<Property> list(int cid);
	
    public void add(Property p);
  
    public void delete(int id);
    
    public Property get(int id);
    
    public void update(Property p);
}
