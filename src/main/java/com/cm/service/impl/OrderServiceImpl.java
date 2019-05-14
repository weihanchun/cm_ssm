package com.cm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cm.mapper.OrderMapper;
import com.cm.pojo.Order;
import com.cm.pojo.OrderItem;
import com.cm.pojo.Product;
import com.cm.pojo.Review;
import com.cm.pojo.User;
import com.cm.service.OrderItemService;
import com.cm.service.OrderService;
import com.cm.service.ProductService;
import com.cm.service.ReviewService;
import com.cm.service.UserService;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
	OrderMapper orderMapper;
    @Autowired
    UserService userService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ProductService productService;
    @Autowired
    ReviewService reviewService;
    
	@Override
	public void add(Order c) {
       orderMapper.add(c);		
	}

	@Override
	public void delete(int id) {
      orderMapper.delete(id);		
	}

	@Override
	public void update(Order c) {
       orderMapper.update(c);		
	}

	@Override
	public Order get(int id) {
		Order o=orderMapper.get(id);
		setUser(o);
		return o;
	}

	@Override
	public List<Order> list() {
		List<Order> os=orderMapper.list();
		setUser(os);
		return os;
	}
	
	public void setUser(List<Order> os){
        for (Order o : os)
            setUser(o);
    }
    public void setUser(Order o){
        int uid = o.getUid();
        User u = userService.get(uid);
        o.setUser(u);
    }
    
    /*同时修改两个表   要进行事务管理，否则新增了Order表的数据，
     * 还没有来得及修改OrderItem的时候出问题，比如突然断电，那么OrderItem的数据就会是脏数据了（没有指向正确的Order表数据）*/
    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
	@Override
	public float add(Order o, List<OrderItem> ois) {
        float total=0;
        add(o);//为order表新增数据
        if(false)
            throw new RuntimeException();
        
        for (OrderItem oi: ois) {
            oi.setOid(o.getId());
            orderItemService.update(oi);//修改orderitem表
            total+=oi.getProduct().getPromotePrice()*oi.getNumber();
            Product p=productService.get(oi.getPid());
            int stock=p.getStock()-oi.getNumber();//下订单之后，库存更新
            p.setStock(stock);
            productService.update(p);
        }
		return total;
	}

    //用于显示用户的订单信息
	@Override
	public List<Order> listByStatus(int uid, String excludedStatus) {
		return orderMapper.listByStatus(uid, excludedStatus);
	}


}
