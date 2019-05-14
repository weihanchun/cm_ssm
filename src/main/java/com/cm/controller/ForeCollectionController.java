package com.cm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.pojo.Collection;
import com.cm.pojo.OrderItem;
import com.cm.pojo.Product;
import com.cm.pojo.PropertyValue;
import com.cm.pojo.User;
import com.cm.service.CollectionService;
import com.cm.service.ProductService;
import com.cm.service.PropertyValueService;
import com.cm.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("")
public class ForeCollectionController {
	@Autowired
	CollectionService collectionService;
	@Autowired
	ProductService productService;
    @Autowired
    PropertyValueService propertyValueService;
	
	/*加入收藏
	 * 1. 获取参数pid
	2. 根据pid获取产品对象p
	3. 从session中获取用户对象user*/
	@RequestMapping("foreaddCollection")
	@ResponseBody
	public String addCollection(HttpSession session,int pid) {
		User user=(User)session.getAttribute("user");
		Product p=productService.get(pid);
		List<Collection> cls=collectionService.listByUid(user.getId());
		boolean found=false;
		for(Collection c:cls) {
			if(c.getProduct().getId()==p.getId()) {
				found=true;
				break;
			}
		}
		if(!found){
			Collection c=new Collection();
			c.setPid(pid);
			c.setUid(user.getId());
			collectionService.add(c);
		}
		return "success";
	}
	/*显示收藏的商品*/
	@RequestMapping("forecollection")
	public String listCollection(Model model,HttpSession session,Page page) {
		User user=(User)session.getAttribute("user");
		PageHelper.offsetPage(page.getStart(),page.getCount());
		List<Collection> cls=collectionService.listByUid(user.getId());
	    int total=(int)new PageInfo<>(cls).getTotal();
	    page.setTotal(total);
		model.addAttribute("cls",cls);
		model.addAttribute("page",page);
		return "fore/collection";
	}
	//删除
	@RequestMapping("foredeleteCollection")
	@ResponseBody
	public String deleteCollection(HttpSession session,int clid) {
		User user =(User)  session.getAttribute("user");
		if(null==user)
			return "fail";
		collectionService.delete(clid);
		return "success";
	}
}
