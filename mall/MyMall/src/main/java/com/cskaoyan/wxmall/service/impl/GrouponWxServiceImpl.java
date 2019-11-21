package com.cskaoyan.wxmall.service.impl;

import com.cskaoyan.mall.mapper.popularizeModuleMapper.GrouponMapper;
import com.cskaoyan.wxmall.bean.GrouponWxBean;
import com.cskaoyan.wxmall.service.GrouponWxService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class GrouponWxServiceImpl implements GrouponWxService {

    @Autowired
    GrouponMapper grouponMapper;

    public List<GrouponWxBean> queryWxGrouponsList(Integer page,Integer size){
        PageHelper.startPage(page,size);
        List<GrouponWxBean> grouponWxBeanList = grouponMapper.queryWxGrouponsList();
        for (GrouponWxBean grouponWxBean : grouponWxBeanList) {
            BigDecimal dicount = grouponWxBean.getGroupon_price();
            BigDecimal retailPrice = grouponWxBean.getGoods().getRetailPrice();
            BigDecimal grouponPrice = retailPrice.subtract(dicount);
            grouponWxBean.setGroupon_price(grouponPrice);
        }
        return grouponWxBeanList;
    }
}
