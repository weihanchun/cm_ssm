package com.cm.service;

import java.util.List;

import com.cm.pojo.ProductImage;

public interface ProductImageService {
      String type_single="type_single";
      String type_detail="type_detail";
      
      void add(ProductImage pi);
      void delete(int id);
      ProductImage get(int id);
      List<ProductImage> list(int pid,String type);
}
