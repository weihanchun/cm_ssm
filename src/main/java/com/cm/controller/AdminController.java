package com.cm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cm.pojo.Admin;
import com.cm.service.AdminService;

@Controller
@RequestMapping("")
public class AdminController {
  @Autowired
  AdminService adminService;

  @RequestMapping("adminlogin")
  public String admin(Model model,@RequestParam("name")String name,@RequestParam("password")String password,HttpSession session) {
		Admin admin=adminService.getByNameAndPassword(name, password);
		if(null==admin) {
			model.addAttribute("msg","账号密码错误");
			return "admin/login";
		}
		session.setAttribute("admin",admin);
		return "redirect:admin_category_list";
  }
  
    //管理员退出登录
	@RequestMapping("adminlogout")
	public String logout(HttpSession session) {
		session.removeAttribute("admin");
		return "admin/login";
	}
	
}
