package com.cm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.mapper.AddressMapper;
import com.cm.pojo.Address;
import com.cm.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressMapper addressMapper;
	
	@Override
	public List<Address> listById(int uid) {
		return addressMapper.list(uid);
	}

	@Override
	public void add(Address address) {
		addressMapper.add(address);
	}

	@Override
	public void delete(int id) {
		addressMapper.delete(id);
	}

	@Override
	public Address get(int id) {
		return addressMapper.get(id);
	}

	@Override
	public void update(Address address) {
		addressMapper.update(address);
	}

	@Override
	public Address getDefault(int uid) {
		return addressMapper.getDefault(uid);
	}
	
	@Override
	public void defaultAddress(int aid,int uid) {
		Address a=getDefault(uid);
		if(a!=null) {
		   a.setIsdefault("N");
		   update(a);
		}
		Address address=get(aid);
		address.setIsdefault("Y");
        update(address);
	}

	

}
