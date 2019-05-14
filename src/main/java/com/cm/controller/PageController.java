package com.cm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class PageController {//用于重定向的跳转
	@RequestMapping("registerPage")
	public String registerPage() {
		return "fore/register";
	}

	@RequestMapping("adminLoginPage")
	public String adminLogin() {
		return "admin/login";
	}

	@RequestMapping("registerSuccessPage")
	public String registerSuccessPage() {
		return "fore/registerSuccess";
	}
	@RequestMapping("changeSuccessPage")
	public String changeSuccessPage() {
		return "fore/changeSuccess";
	}
	@RequestMapping("loginPage")
	public String loginPage() {
		return "fore/login";
	}

	@RequestMapping("foreFind")
	public String Find(){
		return "fore/findPhone";
	}
	@RequestMapping("forepassword")
	public String changePwd() {
		return "fore/changePwd";
	}
}
