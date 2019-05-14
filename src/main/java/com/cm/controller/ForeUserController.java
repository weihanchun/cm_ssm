package com.cm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import com.cm.pojo.User;
import com.cm.service.AddressService;
import com.cm.service.CategoryService;
import com.cm.service.OrderItemService;
import com.cm.service.OrderService;
import com.cm.service.ProductImageService;
import com.cm.service.ProductService;
import com.cm.service.PropertyValueService;
import com.cm.service.ReviewService;
import com.cm.service.UserService;

@Controller
@RequestMapping("")
public class ForeUserController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@Autowired
	UserService userService;
	@Autowired
	ProductImageService productImageService;
	@Autowired
	PropertyValueService propertyValueService;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderItemService orderItemService;
	@Autowired
	ReviewService reviewService;
    @Autowired
    AddressService addressService;
	
	//注册  SpringMVC参数绑定
	@RequestMapping("foreregister")
	public String register(Model model,User user) {
		String name=user.getName();
		//因为有些同学在恶意注册的时候，
		//会使用诸如 <script>alert('papapa')</script> 这样的名称，
		//会导致网页打开就弹出一个对话框。 那么在转义之后，就没有这个问题了。
		name=HtmlUtils.htmlEscape(name);
		user.setName(name);
		String salt=userService.getRandomSalt();
		user.setSalt(salt);
		String src=user.getPassword();
		String password=userService.getEncrpytedPassword(src, salt);
		user.setPassword(password);
		boolean exist = userService.isExist(name);
		boolean Pexist = userService.PhoneisExist(user.getPhone());
        
		if(exist){
			String n ="用户名已经被使用,不能使用";
			model.addAttribute("message", n);
			//当用户存在，服务端跳转到register.jsp的时候不带上参数user, 
			//否则当注册失败的时候，会在原本是“请登录”的超链位置显示刚才注册的名称。
			model.addAttribute("user", null);
			return "fore/register";
		}
		if(Pexist){
			String p ="手机号码已经被使用,不能使用";
			model.addAttribute("message", p);
			model.addAttribute("user", null);
			return "fore/register";
		}
		
		userService.add(user);
		return "redirect:registerSuccessPage";
	}
	//用户登录
	@RequestMapping("forelogin")
	public String login(Model model,@RequestParam("name")String name,@RequestParam("password")String password,HttpSession session) {
		name=HtmlUtils.htmlEscape(name);
		User user=userService.getByName(name);
		if(null==user) {
			model.addAttribute("message","用户名错误");
			return "fore/login";
		}
		String salt=user.getSalt();
		String pwd=userService.getEncrpytedPassword(password, salt);
		user=userService.getByNameAndPassword(name, pwd);
		if(null==user) {
			model.addAttribute("message","密码错误");
			return "fore/login";
		}
		session.setAttribute("user",user);
		return "redirect:forehome";
	}
	//用户登出
	@RequestMapping("forelogout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:forehome";
	}
	
	//验证用户是否登录
	@RequestMapping("forecheckLogin")
	@ResponseBody//加上这句，直接把返回值写入http响应正文，不会被解析为跳转路径，用于异步（requestmapping返回值被解析为跳转路径）
	public String check(HttpSession session) {
		User user=(User) session.getAttribute("user");
		if(null==user) 
			return "fail";
		return "success";
	}
	//模态登录  (requestparam要求必须传参，不然报错)
	@RequestMapping("foreloginAjax")
	@ResponseBody
	public String loginAjax(Model model,@RequestParam("name")String name,@RequestParam("password")String password,HttpSession session) {
		name=HtmlUtils.htmlEscape(name);
		User user=userService.getByName(name);
		String salt=user.getSalt();
		String pwd=userService.getEncrpytedPassword(password, salt);
		user=userService.getByNameAndPassword(name, pwd);
		if(null==user) {
			return "fail";
		}
		session.setAttribute("user",user);
		return "success";
	}

    //获取信息
    @RequestMapping("foreperson")
    public String person(Model model,HttpSession session) {
    	User u=(User)session.getAttribute("user");
    	model.addAttribute("u",u);
    	return "fore/changeInfo";
    	/*return "fore/person";*/
    }
    //修改信息
    @RequestMapping("forechangeinfo")
    public String changeInfo(Model model,HttpSession session,String name,String phone) {
    	User user=(User)session.getAttribute("user");
    	name=HtmlUtils.htmlEscape(name);
		user.setName(name);
		user.setPhone(phone);
		int res=userService.update(user);
		if(res>0) {
    		return "redirect:changeSuccessPage";
      	}else{
      		model.addAttribute("message","修改失败");
      		return "fore/changeInfo";
      	}
    }
    //修改密码
    @RequestMapping("forechangepwd")
    public String changePwd(Model model,HttpSession session,String password,String npassword) {
        User user=(User)session.getAttribute("user");
        String salt=user.getSalt();
        String pwd=userService.getEncrpytedPassword(password, salt);
        if(user.getPassword().equals(pwd)) {
        	String npwd=userService.getEncrpytedPassword(npassword, salt);
        	user.setPassword(npwd);
        	int res=userService.update(user);
        	if(res>0) {
        		return "redirect:changeSuccessPage";
          	}else{
          		model.addAttribute("message","修改失败");
          		return "fore/changePwd";
          	}
        }else {
           model.addAttribute("message","原密码错误");
    	   return "fore/changePwd";
    	}
    }
    //找回密码1
    @RequestMapping("foreFindByPhone")
    public String find(Model model,String phone) {
    	User user=userService.getByPhone(phone);
    	if(user==null) {
    		model.addAttribute("message","手机号码不正确");
        	return "fore/findPhone";
    	}
    	model.addAttribute("user",user);
    	return "fore/findMiBao";
    }
    //找回密码2
    @RequestMapping("foreFindByMiBao")
    public String MiBao(Model model,String result,int uid) {
    	User user=userService.get(uid);
    	String res=user.getResult();
    	model.addAttribute("user",user);
    	if(result.equals(res)) {
    		return "fore/findPwd";
    	}
    	model.addAttribute("message","密保答案错误,请重新输入");
    	return "fore/findMiBao";
    }
    //找回密码3
    @RequestMapping("foreFindByPwd")
    public String changePassword(Model model,String npassword,int uid) {
    	User user=userService.get(uid);
    	String salt=user.getSalt();
    	String pwd=userService.getEncrpytedPassword(npassword, salt);
    	user.setPassword(pwd);
    	int res=userService.update(user);
    	if(res>0) {
    		return "redirect:changeSuccessPage";
      	}else{
      		model.addAttribute("message","修改失败");
      		return "fore/findPwd";
      	}
    }
    
}
