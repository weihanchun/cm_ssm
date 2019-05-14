package com.cm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cm.pojo.Address;
import com.cm.pojo.User;
import com.cm.service.AddressService;
import com.cm.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Controller
@RequestMapping("")
public class ForeAddressController {
	@Autowired
    AddressService addressService;
	
	//地址管理
    @RequestMapping("foreaddress")
    public String listAddress(HttpSession session,Model model,Page page) {
    	User u=(User)session.getAttribute("user");
    	PageHelper.offsetPage(page.getStart(),page.getCount());
    	List<Address> as=addressService.listById(u.getId());
    	int total=(int)new PageInfo<>(as).getTotal();
    	page.setTotal(total);
    	model.addAttribute("page",page);
    	model.addAttribute("as",as);
    	model.addAttribute("u",u);
    	return "fore/address";
    }
    @RequestMapping("foreAddressadd")
    public String addAddress(Address address) {
    	addressService.add(address);
    	return "redirect:/foreaddress";
    }
    @RequestMapping("foreAddressdelete")
    public String deleteAddress(int id) {
    	addressService.delete(id);
    	return "redirect:/foreaddress";
    }
    @RequestMapping("foreAddressedit")
    public String editAddress(int id,Model model) {
    	Address a=addressService.get(id);
    	model.addAttribute("a",a);
    	return "fore/editAddress";
    }
    @RequestMapping("foreAddressupdate")
    public String updateAddress(Address address) {
    	addressService.update(address);
    	return "redirect:/foreaddress";
    }
    //设为默认地址
    @RequestMapping("foreDefaultAddress")
    public String defaultAddress(int aid,HttpSession session) {
    	User user=(User)session.getAttribute("user");
    	int uid=user.getId();
    	addressService.defaultAddress(aid,uid);
    	return "redirect:/foreaddress";
    }
}
