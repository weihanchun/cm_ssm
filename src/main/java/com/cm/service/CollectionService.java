package com.cm.service;

import java.util.List;

import com.cm.pojo.Collection;

public interface CollectionService {
    public List<Collection> listByUid(int uid);
    public void add(Collection c);
    public void delete(int id);
}
