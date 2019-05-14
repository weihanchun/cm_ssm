package com.cm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cm.pojo.PropertyValue;

public interface PropertyValueMapper {
      List<PropertyValue> list(int pid);
      
      void update(PropertyValue p);
      
      PropertyValue get(@Param("pid")int pid,@Param("ptid")int ptid);
      
      void add(PropertyValue p);
}
