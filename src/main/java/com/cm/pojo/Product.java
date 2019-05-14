package com.cm.pojo;

import java.util.Date;
import java.util.List;

public class Product {
    private int id;
    private String name;
    private String isdelete;
    private String subTitle;
    private float originalPrice;
    private float promotePrice;
    private int stock;
    private int cid;
    private Date createDate;
    
    /*非数据库字段*/
    private Category category;

    private ProductImage firstProductImage;
    
    //单个产品图片集合
    private List<ProductImage> productSingleImages;
    //详情产品图片集合
    private List<ProductImage> productDetailImages;
    //销量
    private int saleCount;
    //累计评价
    private int reviewCount;
    
    //花语
    private String flowerLanguage;
    
    private List<PropertyValue> pvs;
    
	public List<PropertyValue> getPvs() {
		return pvs;
	}

	public void setPvs(List<PropertyValue> pvs) {
		this.pvs = pvs;
	}

	public String getFlowerLanguage() {
		return flowerLanguage;
	}

	public void setFlowerLanguage(String flowerLanguage) {
		this.flowerLanguage = flowerLanguage;
	}

	public List<ProductImage> getProductSingleImages() {
		return productSingleImages;
	}

	public void setProductSingleImages(List<ProductImage> productSingleImages) {
		this.productSingleImages = productSingleImages;
	}

	public List<ProductImage> getProductDetailImages() {
		return productDetailImages;
	}

	public void setProductDetailImages(List<ProductImage> productDetailImages) {
		this.productDetailImages = productDetailImages;
	}

	public int getSaleCount() {
		return saleCount;
	}

	public void setSaleCount(int saleCount) {
		this.saleCount = saleCount;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	public ProductImage getFirstProductImage() {
		return firstProductImage;
	}

	public void setFirstProductImage(ProductImage firstProductImage) {
		this.firstProductImage = firstProductImage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public float getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(float originalPrice) {
		this.originalPrice = originalPrice;
	}

	public float getPromotePrice() {
		return promotePrice;
	}

	public void setPromotePrice(float promotePrice) {
		this.promotePrice = promotePrice;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}
    
}
