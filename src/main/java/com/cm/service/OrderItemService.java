package com.cm.service;

import java.util.List;

import com.cm.pojo.Order;
import com.cm.pojo.OrderItem;

public interface OrderItemService {
	void add(OrderItem oi);
	 
    void delete(int id);
    void update(OrderItem oi);
    OrderItem get(int id);
    List<OrderItem> list();
 
    void fill(List<Order> os);
 
    void fill(Order o);
    //显示产品的总销售额
    int getCountByPID(int pid);
    //用于购买，查看用户的订单
    List<OrderItem> listByUser(int uid);

    List<OrderItem> listByOid(int oid);
    
    int getCount(int oid);
}
