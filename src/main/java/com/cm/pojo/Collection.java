package com.cm.pojo;

public class Collection {
    private int id;
    private int uid;
    private int pid;
    /*非数据库字段*/
    private Product product;
    private String flowerLanguage;
    
	public String getFlowerLanguage() {
		return flowerLanguage;
	}
	public void setFlowerLanguage(String flowerLanguage) {
		this.flowerLanguage = flowerLanguage;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
}
