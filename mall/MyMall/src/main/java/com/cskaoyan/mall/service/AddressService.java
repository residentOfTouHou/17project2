package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.jsonbean.PageSplit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

public interface AddressService {
    @Transactional
    Map<String, Object> findAll(PageSplit pageSplit);

    @Transactional
    Map<String, Object> findAddressByCondition(PageSplit pageSplit);

}
