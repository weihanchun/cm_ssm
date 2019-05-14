package com.cm.mapper;

import java.util.List;

import com.cm.pojo.Review;

public interface ReviewMapper {
	 public void add(Review r);
	    public void delete(int id);
	    public void update(Review r);
	    
	    public Review get(int id);
	    
	    public Review getByOiid(int oiid);
	    
	    List<Review> list(int pid);
	    
	    List<Review> listByOid(int oid);
	    
	    int getCount(int pid);
}
