package com.cm.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cm.pojo.Category;
import com.cm.pojo.Product;
import com.cm.service.CategoryService;
import com.cm.service.ProductService;
import com.cm.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("")
public class ProductController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;

	@RequestMapping("admin_product_list")
	public String list(int cid,Model model,Page page) {
		Category c=categoryService.get(cid);

		PageHelper.offsetPage(page.getStart(),page.getCount());
		List<Product> ps=productService.list(cid);
		int total=(int) new PageInfo<>(ps).getTotal();
		page.setTotal(total);
		page.setParam("&cid="+c.getId());
		model.addAttribute("c",c);
		model.addAttribute("ps",ps);
		model.addAttribute("page",page);

		return "admin/listProduct";
	}

	@RequestMapping("admin_product_add")
	public String add(Product p) {
		p.setCreateDate(new Date());
		productService.add(p);
		return "redirect:/admin_product_list?cid="+p.getCid();
	}

	@RequestMapping("admin_product_delete")
	public String delete(int id) {
		Product p=productService.get(id);
		p.setIsdelete("Y");
		productService.delete(p);
		return "redirect:/admin_product_list?cid="+p.getCid();
	}

	@RequestMapping("admin_product_edit")
	public String edit(Model model,int id) {
		Product p=productService.get(id);
		model.addAttribute("p",p);
		return "admin/editProduct";
	}

	@RequestMapping("admin_product_update")
	public String update(Product p) {
		productService.update(p);
		return "redirect:/admin_product_list?cid="+p.getCid();
	}
}
