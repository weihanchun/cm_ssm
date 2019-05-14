package com.cm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.mapper.OrderItemMapper;
import com.cm.pojo.Order;
import com.cm.pojo.OrderItem;
import com.cm.pojo.Product;
import com.cm.service.OrderItemService;
import com.cm.service.ProductService;

@Service
public class OrderItemServiceImpl implements OrderItemService{
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    ProductService productService;
	
	@Override
	public void add(OrderItem oi) {
		orderItemMapper.add(oi);  		
	}

	@Override
	public void delete(int id) {
		orderItemMapper.delete(id);		
	}

	@Override
	public void update(OrderItem oi) {
		orderItemMapper.update(oi);		
	}

	@Override
	public OrderItem get(int id) {
		OrderItem oo=orderItemMapper.get(id);
		setProduct(oo);
		return oo;
	}

	@Override
	public List<OrderItem> list() {//没有用到
		List<OrderItem> ois=orderItemMapper.list();
		setProduct(ois);
		return ois;
	}

	@Override
	public void fill(List<Order> os) {
		for(Order o:os) {
			fill(o);
		}
	}

	//实现订单与订单项的一对多关系
	@Override
	public void fill(Order o) {
		//根据订单查询出其对应的所有订单项
         List<OrderItem> ois=orderItemMapper.listByOID(o.getId());
         setProduct(ois);//通过setProduct为所有的订单项设置Product属性
         
         float total = 0;
         int totalNumber = 0;
         //遍历所有的订单项，然后计算出该订单的总金额和总数量
         for (OrderItem oi : ois) {
             total+=oi.getNumber()*oi.getProduct().getPromotePrice();
             totalNumber+=oi.getNumber();
         }
         o.setTotal(total);
         o.setTotalNumber(totalNumber);
         //最后再把订单项设置在订单的orderItems属性上。
         o.setOrderItems(ois);
	}
    //给订单添加产品属性
	public void setProduct(List<OrderItem> ois) {
	       for(OrderItem o:ois) {
	    	   setProduct(o);
	       }
	}
    //给订单添加产品属性
	private void setProduct(OrderItem o) {
            Product p=productService.get(o.getPid());
            o.setProduct(p);
	}

	@Override
	public int getCountByPID(int pid) {//获取产品的总销量(保证订单id不为空)
		List<OrderItem> os=orderItemMapper.listByPID(pid);
		int count=0;
		for(OrderItem o:os) {
			count+=o.getNumber();
		}
		return count;
	}
    
	//获取用户的订单并放进产品信息
	@Override
	public List<OrderItem> listByUser(int uid) {
		List<OrderItem> ois=orderItemMapper.listByUID(uid);
		setProduct(ois);
		return ois;
	}

	@Override
	public List<OrderItem> listByOid(int oid) {
		List<OrderItem> ois=orderItemMapper.listByOID(oid);
		setProduct(ois);
		return ois;
	}

	@Override
	public int getCount(int oid) {
		return orderItemMapper.getCount(oid);
	}
}
