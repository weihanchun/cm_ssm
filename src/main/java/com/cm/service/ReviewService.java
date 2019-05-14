package com.cm.service;

import java.util.List;

import com.cm.pojo.Review;

public interface ReviewService {
    public void add(Review r);
    public void delete(int id);
    public void update(Review r);
    public Review get(int id);
    public Review getByOiid(int oiid);
    List<Review> list(int pid);
    List<Review> listByOid(int oid);
    public void add(Review r,int oiid);
    int getCount(int pid);
}
