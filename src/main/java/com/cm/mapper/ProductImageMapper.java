package com.cm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cm.pojo.ProductImage;

public interface ProductImageMapper {
     void add(ProductImage pi);
     
     void delete(int id);
     
     ProductImage get(int id);
     
     List<ProductImage> list(@Param("pid")int pid,@Param("type")String type);
}
