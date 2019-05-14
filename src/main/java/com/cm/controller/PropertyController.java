package com.cm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cm.pojo.Category;
import com.cm.pojo.Property;
import com.cm.service.CategoryService;
import com.cm.service.PropertyService;
import com.cm.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("")
public class PropertyController {
    @Autowired
    CategoryService categoryService;
    
    @Autowired
    PropertyService propertyService;
    
    
    @RequestMapping("admin_property_list")
    public String list(int cid,Model model,Page page) {
    	Category c=categoryService.get(cid);
    	PageHelper.offsetPage(page.getStart(), page.getCount());
    	List<Property> ps=propertyService.list(cid);
    	int total=(int)new PageInfo<>(ps).getTotal();
    	page.setTotal(total);
    	page.setParam("&cid="+c.getId());
    	/*拼接字符串"&cid="+c.getId()，设置给page对象的Param值。
        因为属性分页都是基于当前分类下的分页，所以分页的时候需要传递这个cid*/
    	model.addAttribute("c",c);
    	model.addAttribute("ps",ps);
    	model.addAttribute("page",page);
    	return "admin/listProperty";
    }
    
    @RequestMapping("admin_property_add")
    public String add(Property p) {
    	propertyService.add(p);
    	return "redirect:/admin_property_list?cid="+p.getCid();
    }
    
    @RequestMapping("admin_property_delete")
    public String delete(int id) {
    	Property p=propertyService.get(id);
    	propertyService.delete(id);
    	return "redirect:/admin_property_list?cid="+p.getCid();
    }

    @RequestMapping("admin_property_edit")
    public String edit(Model model,int id) {
    	Property p=propertyService.get(id);
    	model.addAttribute("p",p);
    	return "admin/editProperty";
    }
 
    @RequestMapping("admin_property_update")
    public String update(Property p) {
    	propertyService.update(p);
    	return "redirect:/admin_property_list?cid="+p.getCid();
    }
    
}
