package com.cskaoyan.wxmall.service;

import com.cskaoyan.wxmall.bean.SubmitOrderBean;

/**
 * Created by IntelliJ IDEA
 *
 * @author zhanghj
 * @date 2019/11/19
 * @time 23:03
 */
public interface OrderWxService {

    Integer submitOrder(SubmitOrderBean orderBean);

}
