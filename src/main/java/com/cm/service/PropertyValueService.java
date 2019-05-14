package com.cm.service;

import java.util.List;

import com.cm.pojo.Product;
import com.cm.pojo.PropertyValue;

public interface PropertyValueService {
        void init(Product p);
        void update(PropertyValue pv);
        
        PropertyValue get(int ptid,int pid);
        List<PropertyValue> list(int pid);
        //void fill(Product p);
}
