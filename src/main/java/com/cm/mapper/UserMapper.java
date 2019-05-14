package com.cm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cm.pojo.User;

public interface UserMapper {
	     public List<User> list();
		
	     public void add(User user);

	     public void delete(int id);
	     
	     public User get(int id);
	     
	     public int update(User user);
	     
	     public User selectByName(String name);

	     public User selectByPhone(String phone);

	     public User selectByNameAndPassWord(@Param("name")String name,@Param("password")String password);
}
