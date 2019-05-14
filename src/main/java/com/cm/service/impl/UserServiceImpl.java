package com.cm.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.cm.mapper.UserMapper;
import com.cm.pojo.User;
import com.cm.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper userMapper;

	@Override
	public List<User> list() {
		return userMapper.list();
	}

	@Override
	public void add(User user) {
		userMapper.add(user);
	}

	@Override
	public void delete(int id) {
		userMapper.delete(id);
	}

	@Override
	public User get(int id) {
		return userMapper.get(id);
	}


	@Override
	public boolean isExist(String name) {
		User user=userMapper.selectByName(name);
		if(user!=null) {
			return true;
		}
		return false;
	}

	@Override
	public User getByNameAndPassword(String name, String password) {
        User user=userMapper.selectByNameAndPassWord(name, password);
        if(null==user) 
        	return null;
		return user;
	}

	/**
	 * 返回盐，随机数
	 */
	public String getRandomSalt(){
		return UUID.randomUUID().toString().toUpperCase();
	}

	/**
	 * 将资源md5j加密
	 */
	public String md5(String src){
		return DigestUtils.md5DigestAsHex(src.getBytes());
	}
	/**
	 *将密码和盐放在一起进行最终加密返回加密后的密码
	 */
	public String getEncrpytedPassword(String src,String salt){
		String s1=md5(src);
		String s2=md5(salt);
		String s3=md5(s1+s2);
		for(int i=0;i<5;i++){
			s3=md5(s3);
		}
		return s3;
	}

	@Override
	public User getByName(String name) {
		return userMapper.selectByName(name);
	}

	@Override
	public int update(User user) {
		return userMapper.update(user);
	}

	@Override
	public User getByPhone(String phone) {
		return userMapper.selectByPhone(phone);
	}

	@Override
	public boolean PhoneisExist(String phone) {
		User user=userMapper.selectByPhone(phone);
		if(user!=null) {
			return true;
		}
		return false;
	}
}
