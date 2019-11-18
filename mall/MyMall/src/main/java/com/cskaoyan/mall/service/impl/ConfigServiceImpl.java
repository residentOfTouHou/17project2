package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.generator.System;
import com.cskaoyan.mall.bean.generator.SystemExample;
import com.cskaoyan.mall.bean.jsonbean.ConfigOrder;
import com.cskaoyan.mall.bean.jsonbean.ConfigWx;
import com.cskaoyan.mall.bean.jsonbean.Express;
import com.cskaoyan.mall.bean.jsonbean.Mall;
import com.cskaoyan.mall.mapper.SystemMapper;
import com.cskaoyan.mall.service.ConfigService;

import com.cskaoyan.mall.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    SystemMapper systemMapper;

    /**
     * 查找商品配置
     */
    @Override
    public Mall selectMallConfig() {
        List<System> systems = systemMapper.selectByExample(new SystemExample());
        Mall mall = new Mall();
        for (System system : systems) {
            String keyName = system.getKeyName();
            String keyValue = system.getKeyValue();

            if("cskaoyan_mall_mall_phone".equals(keyName)){
                mall.setLitemall_mall_phone(keyValue);
            }else if("cskaoyan_mall_mall_address".equals(keyName)){
                mall.setLitemall_mall_address(keyValue);
            }else if("cskaoyan_mall_mall_name".equals(keyName)){
                mall.setLitemall_mall_name(keyValue);
            }else if("cskaoyan_mall_mall_qq".equals(keyName)){
                mall.setLitemall_mall_qq(keyValue);
            }
        }
        return mall;
    }

    /**
     * 修改商品配置
     */
    @Override
    public int updateMallConfig(Mall mall) {
        //获取keyValue
        String mall_phone = mall.getLitemall_mall_phone();
        String mall_address = mall.getLitemall_mall_address();
        String mall_name = mall.getLitemall_mall_name();
        String mall_qq = mall.getLitemall_mall_qq();
        //调用工具类 获取当前时间
        String update_time = DateUtils.currentDateToString();

        //调用方法修改
        int result1 = systemMapper.updateKeyValueByKeyName("cskaoyan_mall_mall_address",mall_address,update_time);
        int result2 = systemMapper.updateKeyValueByKeyName("cskaoyan_mall_mall_phone",mall_phone,update_time);
        int result3 = systemMapper.updateKeyValueByKeyName("cskaoyan_mall_mall_name",mall_name,update_time);
        int result4 = systemMapper.updateKeyValueByKeyName("cskaoyan_mall_mall_qq",mall_qq,update_time);

        //如果都修改成功 返回1 否则返回0
        if(result1 == 1 && result2 == 1 && result3 == 1 && result4 == 1){
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * 查找运费配置
     */
    @Override
    public Express selectExpressConfig() {
        List<System> systems = systemMapper.selectByExample(new SystemExample());
        Express express = new Express();
        for (System system : systems) {
            String keyName = system.getKeyName();
            String keyValue = system.getKeyValue();

            if("cskaoyan_mall_express_freight_min".equals(keyName)){
                express.setLitemall_express_freight_min(keyValue);
            }else if("cskaoyan_mall_express_freight_value".equals(keyName)){
                express.setLitemall_express_freight_value(keyValue);
            }
        }
        return express;
    }

    /**
     * 修改运费配置
     */
    @Override
    public int updateExpressConfig(Express express) {
        //获取keyValue
        String express_freight_min = express.getLitemall_express_freight_min();
        String express_freight_value = express.getLitemall_express_freight_value();

        //调用工具类 获取当前时间
        String update_time = DateUtils.currentDateToString();

        //调用方法修改
        int result1 = systemMapper.updateKeyValueByKeyName("cskaoyan_mall_express_freight_min",express_freight_min,update_time);
        int result2 = systemMapper.updateKeyValueByKeyName("cskaoyan_mall_express_freight_value",express_freight_value,update_time);

        //如果都修改成功 返回1 否则返回0
        if(result1 == 1 && result2 == 1){
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * 查找订单配置
     */
    @Override
    public ConfigOrder selectOrderConfig() {
        List<System> systems = systemMapper.selectByExample(new SystemExample());
        ConfigOrder configOrder = new ConfigOrder();
        for (System system : systems) {
            String keyName = system.getKeyName();
            String keyValue = system.getKeyValue();

            if("cskaoyan_mall_order_comment".equals(keyName)){
                configOrder.setLitemall_order_comment(keyValue);
            }else if("cskaoyan_mall_order_unconfirm".equals(keyName)){
                configOrder.setLitemall_order_unconfirm(keyValue);
            }else if("cskaoyan_mall_order_unpaid".equals(keyName)){
                configOrder.setLitemall_order_unpaid(keyValue);
            }
        }
        return configOrder;
    }

    /**
     * 修改订单配置
     */
    @Override
    public int updateOrderConfig(ConfigOrder configOrder) {
        //获取keyValue
        String order_comment = configOrder.getLitemall_order_comment();
        String order_unconfirm = configOrder.getLitemall_order_unconfirm();
        String order_unpaid = configOrder.getLitemall_order_unpaid();
        //调用工具类 获取当前时间
        String update_time = DateUtils.currentDateToString();

        //调用方法修改
        int result1 = systemMapper.updateKeyValueByKeyName("cskaoyan_mall_order_comment",order_comment,update_time);
        int result2 = systemMapper.updateKeyValueByKeyName("cskaoyan_mall_order_unconfirm",order_unconfirm,update_time);
        int result3 = systemMapper.updateKeyValueByKeyName("cskaoyan_mall_order_unpaid",order_unpaid,update_time);

        //如果都修改成功 返回1 否则返回0
        if(result1 == 1 && result2 == 1 && result3 == 1){
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * 查找小程序配置
     */
    @Override
    public ConfigWx selectWxConfig() {
        List<System> systems = systemMapper.selectByExample(new SystemExample());
        ConfigWx configWx = new ConfigWx();
        for (System system : systems) {
            String keyName = system.getKeyName();
            String keyValue = system.getKeyValue();

            if("cskaoyan_mall_wx_catlog_goods".equals(keyName)){
                configWx.setLitemall_wx_catlog_goods(keyValue);
            }else if("cskaoyan_mall_wx_catlog_list".equals(keyName)){
                configWx.setLitemall_wx_catlog_list(keyValue);
            }else if("cskaoyan_mall_wx_index_brand".equals(keyName)){
                configWx.setLitemall_wx_index_brand(keyValue);
            }else if("cskaoyan_mall_wx_index_hot".equals(keyName)){
                configWx.setLitemall_wx_index_hot(keyValue);
            }else if("cskaoyan_mall_wx_index_new".equals(keyName)){
                configWx.setLitemall_wx_index_new(keyValue);
            }else if("cskaoyan_mall_wx_index_topic".equals(keyName)){
                configWx.setLitemall_wx_index_topic(keyValue);
            }else if("cskaoyan_mall_wx_share".equals(keyName)){
                configWx.setLitemall_wx_share(keyValue);
            }
        }
        return configWx;
    }

    /**
     * 修改小程序配置
     */
    @Override
    public int updateWxConfig(ConfigWx configWx) {
        //获取keyValue
        String wx_catlog_goods = configWx.getLitemall_wx_catlog_goods();
        String wx_catlog_list = configWx.getLitemall_wx_catlog_list();
        String wx_index_brand = configWx.getLitemall_wx_index_brand();
        String wx_index_hot = configWx.getLitemall_wx_index_hot();
        String wx_index_new = configWx.getLitemall_wx_index_new();
        String wx_index_topic = configWx.getLitemall_wx_index_topic();
        String wx_share = configWx.getLitemall_wx_share();
        //调用工具类 获取当前时间
        String update_time = DateUtils.currentDateToString();

        //调用方法修改
        int result1 = systemMapper.updateKeyValueByKeyName("cskaoyan_mall_wx_catlog_goods",wx_catlog_goods,update_time);
        int result2 = systemMapper.updateKeyValueByKeyName("cskaoyan_mall_wx_catlog_list",wx_catlog_list,update_time);
        int result3 = systemMapper.updateKeyValueByKeyName("cskaoyan_mall_wx_index_brand",wx_index_brand,update_time);
        int result4 = systemMapper.updateKeyValueByKeyName("cskaoyan_mall_wx_index_hot",wx_index_hot,update_time);
        int result5 = systemMapper.updateKeyValueByKeyName("cskaoyan_mall_wx_index_new",wx_index_new,update_time);
        int result6 = systemMapper.updateKeyValueByKeyName("cskaoyan_mall_wx_index_topic",wx_index_topic,update_time);
        int result7 = systemMapper.updateKeyValueByKeyName("cskaoyan_mall_wx_share",wx_share,update_time);

        //如果都修改成功 返回1 否则返回0
        if(result1 == 1 && result2 == 1 && result3 == 1 && result4 == 1 &&
            result5 == 1  && result6 == 1 && result7 == 1){
            return 1;
        }else {
            return 0;
        }
    }


}
