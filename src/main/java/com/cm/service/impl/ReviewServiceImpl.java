package com.cm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cm.mapper.ReviewMapper;
import com.cm.pojo.OrderItem;
import com.cm.pojo.Review;
import com.cm.pojo.User;
import com.cm.service.OrderItemService;
import com.cm.service.ReviewService;
import com.cm.service.UserService;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	ReviewMapper reviewMapper;
	@Autowired
	UserService userService;
	@Autowired
	OrderItemService orderItemService;
     
	
	@Override
	public void add(Review r) {
		reviewMapper.add(r);
	}

	@Override
	public void delete(int id) {
		reviewMapper.delete(id);
	}

	@Override
	public void update(Review r) {
		reviewMapper.update(r);
	}

	@Override
	public Review get(int id) {
		return reviewMapper.get(id);
	}

	@Override
	public List<Review> list(int pid) {
		List<Review> rs=reviewMapper.list(pid);
		setUser(rs);
		return rs;
	}
    //给评论设置user属性
	public void setUser(List<Review> reviews){
		for (Review review : reviews) {
			setUser(review);
		}
	}
	//给评论设置user属性
	private void setUser(Review review) {
		int uid = review.getUid();
		User user =userService.get(uid);
		review.setUser(user);
	}

	@Override
	public int getCount(int pid) {//评论总数
		//return reviewMapper.getCount(pid);
		return list(pid).size();//根据list查询出的集合个数也可以代表评论总数
	}

	@Override
	public Review getByOiid(int oiid) {
		return reviewMapper.getByOiid(oiid);
	}

	@Override
	public List<Review> listByOid(int oid) {
		return reviewMapper.listByOid(oid);
	}

	//事务管理
	@Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
	@Override
	public void add(Review r, int oiid) {
        add(r);
        OrderItem oi=orderItemService.get(oiid);
        oi.setIsreview("Y");
        orderItemService.update(oi);
	}
}
