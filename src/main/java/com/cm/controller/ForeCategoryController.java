package com.cm.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cm.comparator.ProductAllComparator;
import com.cm.comparator.ProductDateComparator;
import com.cm.comparator.ProductPriceComparator;
import com.cm.comparator.ProductReviewComparator;
import com.cm.comparator.ProductSaleCountComparator;
import com.cm.pojo.Category;
import com.cm.pojo.Product;
import com.cm.pojo.ProductImage;
import com.cm.pojo.PropertyValue;
import com.cm.pojo.Review;
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
import com.cm.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("")
public class ForeCategoryController {
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

	@RequestMapping("forehome")
	public String home(Model model) {
		//查询所有分类
		List<Category> cs= categoryService.list();
		//为这些分类填充产品集合
		productService.fill(cs);
		//为这些分类填充推荐产品集合
		productService.fillByRow(cs);
		model.addAttribute("cs", cs);
		return "fore/home";
	}

	//商品详情
	@RequestMapping("foreproduct")
	public String product(Model model,int pid) {
		Product p=productService.get(pid);//获取产品对象
		//产品小图
		List<ProductImage> ps=productImageService.list(pid, ProductImageService.type_single);
		//产品详情图
		List<ProductImage> pd=productImageService.list(pid, ProductImageService.type_detail);
		p.setProductSingleImages(ps);
		p.setProductDetailImages(pd);
		List<PropertyValue> pvs=propertyValueService.list(pid);//获取产品的所有属性值
		List<Review> reviews=reviewService.list(pid);//获取产品对应的所有的评价
		productService.setFirstProductImage(p);//设置产品的销量和评价数量
		model.addAttribute("p",p);
		model.addAttribute("reviews",reviews);
		model.addAttribute("pvs",pvs);
		return "fore/product";
	}
	//查询分类
	@RequestMapping("forecategory")
	public String category(int cid,String sort,Model model) {
		Category c=categoryService.get(cid);
		productService.fill(c);//为分类填充产品
		productService.setSaleAndReviewNumber(c.getProducts());
		if(null!=sort){//为产品排序筛选
			switch(sort){
			case "review"://使用指定的算法排序
				Collections.sort(c.getProducts(),new ProductReviewComparator());
				break;
				
			case "date" :
				Collections.sort(c.getProducts(),new ProductDateComparator());
				break;
				
			case "saleCount" :
				Collections.sort(c.getProducts(),new ProductSaleCountComparator());
				break;
				
			case "price":
				Collections.sort(c.getProducts(),new ProductPriceComparator());
				break;

			case "all":
				Collections.sort(c.getProducts(),new ProductAllComparator());
				break;
			}
		}
		model.addAttribute("c", c);
		return "fore/category";
	}
	
	@RequestMapping("foresearch")
	public String search(Model model,String sort,String keyword,/*Page page,*/HttpServletRequest request) {
		/*page.setCount(8);
		PageHelper.offsetPage(page.getStart(),page.getCount());*/
		List<Product> ps=productService.search(keyword);//根据keyword进行模糊查询，获取满足条件的产品
		/*int total=(int)new PageInfo<>(ps).getTotal();
		page.setTotal(total);
		page.setParam("&keyword="+keyword);*/
		productService.setSaleAndReviewNumber(ps);//为这些产品设置销量和评价数量
		if(null!=sort){//为产品排序筛选
			switch(sort){
			case "review"://使用指定的算法排序
				Collections.sort(ps,new ProductReviewComparator());
				break;
				
			case "date" :
				Collections.sort(ps,new ProductDateComparator());
				break;
				
			case "saleCount" :
				Collections.sort(ps,new ProductSaleCountComparator());
				break;
				
			case "price":
				Collections.sort(ps,new ProductPriceComparator());
				break;

			case "all":
				Collections.sort(ps,new ProductAllComparator());
				break;
			}
		}
		model.addAttribute("ps",ps);
		/*model.addAttribute("page",page);*/
		model.addAttribute("keyword",keyword);
		return "fore/searchResult";
	}
}
