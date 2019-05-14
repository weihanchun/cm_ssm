package com.cm.service;

import java.util.List;

import com.cm.pojo.Admin;

public interface AdminService {
	//public List<Admin> list();

	public void add(Admin user);

	public void delete(int id);

	public Admin get(int id);

	public void update(Admin user);
	/*//判断用户名是否存在
	public boolean isExist(String name);*/
	//登录时判断信息是否正确
    public Admin getByNameAndPassword(String name,String password);	
}
