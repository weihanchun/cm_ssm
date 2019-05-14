package com.cm.mapper;

import java.util.List;

import com.cm.pojo.Address;

public interface AddressMapper {
     public List<Address> list(int uid);
	
     public void add(Address address);

     public void delete(int id);
     
     public Address get(int id);
     
     public void update(Address address);
     
     public Address getDefault(int uid);
}
