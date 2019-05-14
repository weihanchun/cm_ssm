package com.cm.mapper;

import java.util.List;

import com.cm.pojo.OrderItem;

public interface OrderItemMapper {
    void add(OrderItem o);
    void delete(int id);
    void update(OrderItem c);
    OrderItem get(int id);
    List<OrderItem> list();
    //查询订单中有多少订单项
    List<OrderItem> listByOID(int oid);
    //获取产品的总销售额（查看订单项，有多少代表销量有多少）
    List<OrderItem> listByPID(int pid);
    //用于购买，查看用户的订单
    List<OrderItem> listByUID(int uid);
    
    int getCount(int oid);
    
}
