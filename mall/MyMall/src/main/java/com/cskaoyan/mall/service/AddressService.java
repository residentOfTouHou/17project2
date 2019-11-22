package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.generator.Address;
import com.cskaoyan.mall.bean.jsonbean.PageSplit;
import com.cskaoyan.wxmall.bean.AddressBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface AddressService {
    @Transactional
    Map<String, Object> findAll(PageSplit pageSplit);

    @Transactional
    Map<String, Object> findAddressByCondition(PageSplit pageSplit);

    List<AddressBean> selectAddressList(Integer userId);

    Address selectAddressById(Integer id);

    int updateAddress(Address address);

    int deleteAddressById(Integer id);

    Address isNameExit(String name);

    void insertAddress(Address address);

    void setAllDefaultFalse();

}
