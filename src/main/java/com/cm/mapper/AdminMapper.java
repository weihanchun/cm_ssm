package com.cm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cm.pojo.Admin;

public interface AdminMapper {
	public List<Admin> list();
	
    public void add(Admin admin);

    public void delete(int id);
    
    public Admin get(int id);
    
    public void update(Admin admin);
    
    public Admin selectByName(String name);

    public Admin selectByNameAndPassWord(@Param("name")String name,@Param("password")String password);
}
