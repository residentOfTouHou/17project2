package com.cskaoyan.mall.service;


import com.cskaoyan.mall.bean.generator.Storage;

import java.util.List;

public interface StorageService {

    List<Storage> getStoragesOrderBy(String sort, String order, String name, String key);

    int updateStorage(Storage storage);

    int deleteStorage(Storage storage);

    int insertStorage(Storage storage);
}
