package com.cskaoyan.wxmall.service;

import com.cskaoyan.wxmall.bean.GrouponWxBean;

import java.util.List;

public interface GrouponWxService {

    List<GrouponWxBean> queryWxGrouponsList(Integer page, Integer size);
}
