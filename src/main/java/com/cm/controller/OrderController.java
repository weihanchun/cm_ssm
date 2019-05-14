package com.cm.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cm.pojo.Order;
import com.cm.service.OrderItemService;
import com.cm.service.OrderService;
import com.cm.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;
    
    @RequestMapping("admin_order_list")
    public String list(Model model,Page page) {
    	//获取分页对象
    	PageHelper.offsetPage(page.getStart(),page.getCount());
        //查询订单集合
    	List<Order> os=orderService.list();
        //获取订单总数并设置在分页对象上
    	int total=(int)new PageInfo<>(os).getTotal();
    	page.setTotal(total);
       //借助orderItemService.fill()方法为这些订单填充上orderItems信息
    	orderItemService.fill(os);
        
        model.addAttribute("os",os);
        model.addAttribute("page",page);
    	return "admin/listOrder";
    }
    
    @RequestMapping("admin_order_delivery")
    public String update(Order o) {
    	o.setDeliveryDate(new Date());//设置发货时间
    	o.setStatus(OrderService.waitConfirm);//设置为发货
    	orderService.update(o);//更新到数据库
    	return "redirect:/admin_order_list";
    }
}
