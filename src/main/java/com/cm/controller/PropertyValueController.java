package com.cm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.pojo.Product;
import com.cm.pojo.PropertyValue;
import com.cm.service.ProductService;
import com.cm.service.PropertyValueService;

@Controller
@RequestMapping("")
public class PropertyValueController {
      @Autowired
      PropertyValueService propertyValueService;
      @Autowired
      ProductService productService;
      

      @RequestMapping("admin_propertyValue_edit")
      public String edit(Model model,int pid) {
    	  Product p=productService.get(pid);
    	  propertyValueService.init(p);//初始化属性值
    	  List<PropertyValue> pvs=propertyValueService.list(pid);
    	  
    	  model.addAttribute("p",p);
    	  model.addAttribute("pvs",pvs);
    	  return "admin/editPropertyValue";
      }

      @RequestMapping("admin_propertyValue_update")
      @ResponseBody
      public String update(PropertyValue pv) {//参数 PropertyValue 获取浏览器Ajax方式提交的参数
    	  propertyValueService.update(pv);
    	  return "success";
      }
}
