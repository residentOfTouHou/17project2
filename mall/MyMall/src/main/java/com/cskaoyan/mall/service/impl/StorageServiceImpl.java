/**
 *
 */
package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.generator.Storage;
import com.cskaoyan.mall.bean.generator.StorageExample;
import com.cskaoyan.mall.mapper.StorageMapper;
import com.cskaoyan.mall.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    StorageMapper storageMapper;


    @Override
    public List<Storage> getStoragesOrderBy(String sort, String order, String name, String key) {
        StorageExample storageExample = new StorageExample();
        if(key != null && name != null) {
            storageExample.createCriteria().andNameLike("%" + name + "%").andKeyLike("%" + key + "%");
        }else if(key != null && name == null){
            storageExample.createCriteria().andKeyLike("%" + key + "%");
        }else if(key == null && name != null){
            storageExample.createCriteria().andNameLike("%" + name + "%");

        }else if(key == null && name == null){}
        storageExample.setOrderByClause(sort + " " + order);
        return storageMapper.selectByExample(storageExample);
    }

    @Override
    public int updateStorage(Storage storage) {
        return  storageMapper.updateByPrimaryKey(storage);
    }

    @Override
    public int deleteStorage(Storage storage) {
        return storageMapper.deleteByPrimaryKey(storage.getId());
    }

    @Override
    public int insertStorage(Storage storage) {
        return storageMapper.insert(storage);
    }
}
