package com.cm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.mapper.AdminMapper;
import com.cm.pojo.Admin;
import com.cm.service.AdminService;
@Service
public class AdminServiceImpl implements AdminService{
  @Autowired
  AdminMapper adminMapper;
	/*
	@Override
	public List<Admin> list() {
		return null;
	}*/

	@Override
	public void add(Admin user) {
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Admin get(int id) {
		return null;
	}

	@Override
	public void update(Admin user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Admin getByNameAndPassword(String name, String password) {
		return adminMapper.selectByNameAndPassWord(name, password);
	}

}
