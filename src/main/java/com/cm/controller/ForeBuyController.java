package com.cm.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.cm.pojo.Address;
import com.cm.pojo.Order;
import com.cm.pojo.OrderItem;
import com.cm.pojo.Product;
import com.cm.pojo.Review;
import com.cm.pojo.User;
import com.cm.service.AddressService;
import com.cm.service.CategoryService;
import com.cm.service.CollectionService;
import com.cm.service.OrderItemService;
import com.cm.service.OrderService;
import com.cm.service.ProductImageService;
import com.cm.service.ProductService;
import com.cm.service.PropertyValueService;
import com.cm.service.ReviewService;
import com.cm.service.UserService;
import com.cm.util.AlipayConfig;


@Controller
@RequestMapping("")
public class ForeBuyController {
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
	@Autowired
	CollectionService collectionService;
    

	//立即购买
	@RequestMapping("forebuyone")
	public String buyone(int pid,int number,HttpSession session) {
		Product p = productService.get(pid);
		int oiid = 0;

		User user =(User)  session.getAttribute("user");
		boolean found = false;
		/*a. 如果已经存在这个产品对应的OrderItem，并且还没有生成订单，即还在购物车中。
		 *  那么就应该在对应的OrderItem基础上，调整数量 
          a.1 基于用户对象user，查询没有生成订单的订单项集合
          a.2 遍历这个集合
          a.3 如果产品是一样的话，就进行数量追加
          a.4 获取这个订单项的 id
		 * */
		List<OrderItem> ois = orderItemService.listByUser(user.getId());
		for (OrderItem oi : ois) {
			if(oi.getProduct().getId()==p.getId()){
				oi.setNumber(oi.getNumber()+number);
				orderItemService.update(oi);
				found = true;
				oiid = oi.getId();
				break;
			}
		}
		/*
		 *  b. 如果不存在对应的OrderItem,那么就新增一个订单项OrderItem
          b.1 生成新的订单项
          b.2 设置数量，用户和产品
          b.3 插入到数据库
          b.4 获取这个订单项的 id
		 * */
		if(!found){
			OrderItem oi = new OrderItem();
			oi.setUid(user.getId());
			oi.setNumber(number);
			oi.setPid(pid);
			orderItemService.add(oi);
			oiid = oi.getId();
		}
		return "redirect:forebuy?oiid="+oiid;
	}
	/*提交订单
	 * 1. 通过字符串数组获取参数oiid
         为什么这里要用字符串数组试图获取多个oiid，
         而不是int类型仅仅获取一个oiid? 因为根据购物流程环节与表关系，
         结算页面还需要显示在购物车中选中的多条OrderItem数据，
         所以为了兼容从购物车页面跳转过来的需求，要用字符串数组获取多个oiid
2. 准备一个泛型是OrderItem的集合ois
3. 根据前面步骤获取的oiids，从数据库中取出OrderItem对象，并放入ois集合中
4. 累计这些ois的价格总数，赋值在total上
5. 把订单项集合放在session的属性 "ois" 上
6. 把总价格放在 model的属性 "total" 上
7. 服务端跳转到buy.jsp
	 * */
	@RequestMapping("forebuy")
	public String buy( Model model,String[] oiid,HttpSession session){
		User user=(User)session.getAttribute("user");
		Address address=addressService.getDefault(user.getId());
		List<OrderItem> ois = new ArrayList<>();
		float total = 0;

		for (String strid : oiid) {
			int id = Integer.parseInt(strid);
			OrderItem oi= orderItemService.get(id);
			total +=oi.getProduct().getPromotePrice()*oi.getNumber();//总价格
			ois.add(oi);
		}

		session.setAttribute("ois", ois);//把订单项集合放在session的属性 "ois" 上
		model.addAttribute("total", total);
		model.addAttribute("user",user);
		model.addAttribute("address",address);
		return "fore/buy";
	}
	//加入购物车
	@RequestMapping("foreaddCart")
	@ResponseBody
	public String addCart(int pid, int num, Model model,HttpSession session) {
		Product p = productService.get(pid);
		User user =(User)  session.getAttribute("user");
		boolean found = false;
		List<OrderItem> ois = orderItemService.listByUser(user.getId());
		for (OrderItem oi : ois) {
			if(oi.getProduct().getId()==p.getId()){
				oi.setNumber(oi.getNumber()+num);
				orderItemService.update(oi);
				found = true;
				break;
			}
		}
		if(!found){
			OrderItem oi = new OrderItem();
			oi.setUid(user.getId());
			oi.setNumber(num);
			oi.setPid(pid);
			orderItemService.add(oi);
		}
		return "success";
	}
	//查看购物车
	@RequestMapping("forecart")
	public String cart( Model model,HttpSession session) {
		User user =(User)  session.getAttribute("user");
		List<OrderItem> ois = orderItemService.listByUser(user.getId());
		model.addAttribute("ois", ois);
		return "fore/cart";
	}

	//调整订单数量
	@RequestMapping("forechangeOrderItem")
	@ResponseBody
	public String changeOrderItem(Model model,HttpSession session,int pid,int number) {
		User user =(User)  session.getAttribute("user");
		if(null==user)
			return "fail";

		List<OrderItem> ois = orderItemService.listByUser(user.getId());
		for (OrderItem oi : ois) {
			if(oi.getProduct().getId()==pid){
				oi.setNumber(number);
				orderItemService.update(oi);
				break;
			}
		}
		return "success";
	}
	
	//删除订单项
	@RequestMapping("foredeleteOrderItem")
	@ResponseBody
	public String deleteOrderItem(HttpSession session,int oiid) {
		User user =(User)session.getAttribute("user");
		if(null==user)
			return "fail";
		OrderItem oi=orderItemService.get(oiid);
		Product p=productService.get(oi.getPid());
		int stock=p.getStock()+oi.getNumber();//删除订单时,更改库存信息
		p.setStock(stock);
		productService.update(p);
		orderItemService.delete(oiid);
		return "success";
	}

	//生成订单
	@RequestMapping("forecreateOrder")
	public String createOrder(Model model,Order order,HttpSession session) {
		 User user=(User)session.getAttribute("user");
			String orderCode=new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+RandomUtils.nextInt(10000);
			order.setOrderCode(orderCode);
			order.setCreateDate(new Date());
			order.setUid(user.getId());
			order.setStatus(OrderService.waitPay);
			List<OrderItem> ois= (List<OrderItem>) session.getAttribute("ois");
			/*把订单加入到数据库，并且遍历订单项集合，设置每个订单项的oid，更新到数据库
			 * 统计本次订单的总金额
			 * */
			float total =orderService.add(order,ois);
			session.setAttribute("total",total);
            session.removeAttribute("ois");
		return "redirect:gopay?oid="+order.getId() +"&total="+total;
	}

	//支付宝动态支付
	  @RequestMapping("gopay")
	  public String goAlipay(Model model,int oid,float total,HttpSession session,HttpServletRequest request, HttpServletRequest response) throws Exception {
		    Order order=orderService.get(oid);
		    session.setAttribute("order",order);//将订单数据放入session
	        int count=0;
	        List<OrderItem> oiss=orderItemService.listByOid(order.getId());
	        for(OrderItem oi:oiss) {//购买数量
	        	int number=oi.getNumber();
	        	count=number+count;
	        }

	        //获得初始化的AlipayClient
	        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

	        //设置请求参数
	        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
	        alipayRequest.setReturnUrl(AlipayConfig.return_url);
	        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

	        //商户订单号，商户网站订单系统中唯一订单号，必填
	        String out_trade_no = order.getOrderCode();
	        //付款金额，必填
	        float total_amount =total;
	        //订单名称，必填
	        String subject ="鲜花";
	        //商品描述，可空
	        String body = "用户够买鲜花数量：" +count;

	        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。该参数数值不接受小数点， 如 1.5h，可转换为 90m。
	        String timeout_express = "5m";

	        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
	    			+ "\"total_amount\":\""+ total_amount +"\"," 
	    			+ "\"subject\":\""+ subject +"\"," 
	    			+ "\"body\":\""+ body +"\"," 
	    			+ "\"timeout_express\":\""+ timeout_express +"\","
	    			+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

	        //请求
	        String result = alipayClient.pageExecute(alipayRequest).getBody();
	        model.addAttribute("result",result);
	        return "fore/pay";
	    }
	
	//订单支付成功
	@RequestMapping("forepayed")
	public String payed(HttpSession session, Model model,HttpServletRequest request) throws IOException {
		//获取支付宝GET过来反馈信息
				Map<String,String> params = new HashMap<String,String>();
				Map<String,String[]> requestParams = request.getParameterMap();
				for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
					String name = (String) iter.next();
					String[] values = (String[]) requestParams.get(name);
					String valueStr = "";
					for (int i = 0; i < values.length; i++) {
						valueStr = (i == values.length - 1) ? valueStr + values[i]
								: valueStr + values[i] + ",";
					}
					//乱码解决，这段代码在出现乱码时使用
					valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
					params.put(name, valueStr);
				}
				
				try {
					 //调用SDK验证签名
					boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);

					//——请在这里编写您的程序（以下代码仅作参考）——
					if(signVerified) {
//					//商户订单号
//					String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
//				
//					//支付宝交易号
//					String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
//				
//					//付款金额
//					String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
//					
//					//out.println("trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount);
						Order order = (Order) session.getAttribute("order");
						float total=(float) session.getAttribute("total");
						order.setStatus(OrderService.waitDelivery);
						order.setPayDate(new Date());
						orderService.update(order);
						model.addAttribute("o", order);
						model.addAttribute("total",total);
						session.removeAttribute("order");
						session.removeAttribute("total");
						return "fore/payed";
					}else {
						return "fore/fail";
					}
				} catch (AlipayApiException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
	}
	
	
	//查看订单
	@RequestMapping("foreorder")
	public String bought(Model model,HttpSession session) {
		User user =(User)  session.getAttribute("user");
		/*PageHelper.offsetPage(page.getStart(),3);*/
		//查看所有没有被删除的订单
		List<Order> os= orderService.listByStatus(user.getId(),OrderService.delete);
		/*int total=(int)new PageInfo<>(os).getTotal();
    	page.setTotal(total);*/
		orderItemService.fill(os);
		model.addAttribute("os", os);
		/* model.addAttribute("page",page);*/
		return "fore/order";		
	}
	
	//确认收货
	@RequestMapping("foreconfirmPay")
	public String confirmPay( Model model,int oid) {
		Order o = orderService.get(oid);//通过oid获取订单对象
		orderItemService.fill(o);//为订单对象填充订单项
		model.addAttribute("o", o);//把订单对象方法request的属性上"o"上
		return "fore/confirmPay";//服务端跳转
	}
	
	//确认收货成功
	@RequestMapping("foreorderConfirmed")
	public String orderConfirmed( Model model,int oid) {
		Order o = orderService.get(oid);
		o.setStatus(OrderService.waitReview);//修改对象o的状态为等待评价,
		o.setConfirmDate(new Date());//修改确认支付时间
		orderService.update(o);//更新到数据库
		model.addAttribute("o",o);
		return "fore/orderConfirmed";
	}

	//删除订单
	@RequestMapping("foredeleteOrder")
	@ResponseBody
	public String deleteOrder( Model model,int oid){
		Order o = orderService.get(oid);
		o.setStatus(OrderService.delete);
		orderService.update(o);
		return "success";
	}

	//评价
	@RequestMapping("forereview")
	public String review( Model model,int oid,int oiid) {
		Order o = orderService.get(oid);
		orderItemService.fill(o);
		OrderItem oi=orderItemService.get(oiid);
		Product p=productService.get(oi.getPid());
		List<Review> reviews = reviewService.list(p.getId());
		productService.setSaleAndReviewNumber(p);
		model.addAttribute("p", p);
		model.addAttribute("o", o);
		model.addAttribute("oi", oi);
		model.addAttribute("reviews", reviews);
		return "fore/review";
	}

	//提交评价
	@RequestMapping("foredoreview")
	public String doreview( Model model,HttpSession session,@RequestParam("oiid") int oiid,@RequestParam("oid") int oid,@RequestParam("pid") int pid,String content) {
		content = HtmlUtils.htmlEscape(content);
		User user = (User)session.getAttribute("user");
		Review review = new Review();
		if(content.equals(null)) {
			review.setContent(content);
		}else {
			review.setContent("好评");
		}
		review.setPid(pid);
		review.setOid(oid);
		review.setOiid(oiid);
		review.setCreateDate(new Date());
		review.setUid(user.getId());
		reviewService.add(review,oiid);
		List<OrderItem> ois=orderItemService.listByOid(oid);
		int count=orderItemService.getCount(oid);
		if(ois.size()==count) {//验证所有的订单项是否被评论
			Order o = orderService.get(oid);
			o.setStatus(OrderService.finish);
			orderService.update(o);
		}
		return "redirect:forereview?oid="+oid+"&oiid="+oiid+"&showonly=true";
	}
}
