package com.cm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cm.pojo.Order;

public interface OrderMapper {
	void add(Order c);
	 
    void delete(int id);
    void update(Order c);
    Order get(int oid);
    List<Order> list();
    //用于显示用户订单
    List<Order> listByStatus(@Param("uid")int uid,@Param("excludedStatus")String excludedStatus);
    
}
