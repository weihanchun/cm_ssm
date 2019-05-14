package com.cm.service;

import java.util.List;

import com.cm.pojo.User;

public interface UserService {
	public List<User> list();

	public void add(User user);

	public void delete(int id);

	public User get(int id);
    
	public int update(User user);
	
	public User getByName(String name);
	
	public User getByPhone(String phone);
	//判断用户名是否存在
	public boolean isExist(String name);
	//判断手机号码是否存在
	public boolean PhoneisExist(String phone);
	//登录时判断信息是否正确
    public User getByNameAndPassword(String name,String password);
    //生成盐
    public String getRandomSalt();
    //盐加密   
    public String getEncrpytedPassword(String src,String salt);
}
