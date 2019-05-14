package com.cm.service;

import java.util.List;

import com.cm.pojo.Address;

public interface AddressService {
	public List<Address> listById(int uid);
	
    public void add(Address address);

    public void delete(int id);
    
    public Address get(int id);

    public Address getDefault(int uid);
    
    public void update(Address address);
    
    public void defaultAddress(int aid,int uid);
}
