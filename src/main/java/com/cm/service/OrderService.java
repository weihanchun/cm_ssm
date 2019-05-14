package com.cm.service;

import java.util.List;

import com.cm.pojo.Order;
import com.cm.pojo.OrderItem;

public interface OrderService {
	String waitPay = "waitPay";
    String waitDelivery = "waitDelivery";
    String waitConfirm = "waitConfirm";
    String waitReview = "waitReview";
    String finish = "finish";
    String delete = "delete";
 
    void add(Order o);
    //用于生成订单
    float add(Order o,List<OrderItem> ois);
    void delete(int id);
    void update(Order o);
    //获取订单
    Order get(int id);
    List<Order> list();
    
    List<Order> listByStatus(int uid,String excludedStatus);
    
}
