package com.cskaoyan.wxmall.service;

import com.cskaoyan.wxmall.bean.ListOrderBean;
import com.cskaoyan.wxmall.bean.SubmitOrderBean;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 *
 * @author zhanghj
 * @date 2019/11/19
 * @time 23:03
 */
public interface OrderWxService {

    Integer submitOrder(SubmitOrderBean orderBean);

    List<ListOrderBean> listOrder(Integer showType, Integer page, Integer size);

    Map<String, Object> detailOrder(Integer id);

    void cancelOrder(Integer id);

}
