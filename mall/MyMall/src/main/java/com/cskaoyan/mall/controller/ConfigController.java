package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.jsonbean.*;
import com.cskaoyan.mall.service.ConfigService;
import com.cskaoyan.mall.utils.RegexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/admin/config")
public class ConfigController {

    @Autowired
    ConfigService configService;

    /**
     * 获取商场配置  (get请求)
     */
    @RequestMapping(value = "mall",method = {GET})
    public BaseReqVo getMallConfig(){
        //调用方法
        Mall mall2 = configService.selectMallConfig();
        //设置结果集
        BaseReqVo<Mall> MallBaseReqVo = new BaseReqVo<>();
        MallBaseReqVo.setErrno(0);
        MallBaseReqVo.setData(mall2);
        MallBaseReqVo.setErrmsg("成功");
        return MallBaseReqVo;
    }

    /**
     * 修改商场配置  (post请求)
     */
    @RequestMapping(value = "mall",method = {POST})
    public BaseReqVo changeMallConfig(@RequestBody Mall mall){
        BaseReqVo<Mall> MallBaseReqVo = new BaseReqVo<>();
        //校验参数
        boolean phoneResult = RegexUtils.isPhoneNumber(mall.getLitemall_mall_phone());
        if(phoneResult == false){
            MallBaseReqVo.setErrno(507);
            return MallBaseReqVo;
        }
        boolean qqResult = RegexUtils.isQQ(mall.getLitemall_mall_qq());
        if(qqResult == false){
            MallBaseReqVo.setErrno(508);
            return MallBaseReqVo;
        }
        //调用方法
        int result = configService.updateMallConfig(mall);
        //设置结果集
        if(result == 1){
            MallBaseReqVo.setErrno(0);
            MallBaseReqVo.setErrmsg("成功");
        }
        return MallBaseReqVo;
    }

    /**
     * 获取运费配置  (get请求)
     */
    @RequestMapping(value = "express",method = {GET})
    public BaseReqVo getExpressConfig(){
        //调用方法
        Express express2 = configService.selectExpressConfig();
        //设置结果集
        BaseReqVo<Express> expressBaseReqVo = new BaseReqVo<>();
        expressBaseReqVo.setErrno(0);
        expressBaseReqVo.setData(express2);
        expressBaseReqVo.setErrmsg("成功");
        return expressBaseReqVo;

    }

    /**
     * 修改运费配置  (post请求)
     */
    @RequestMapping(value = "express",method = {POST})
    public BaseReqVo changeExpreConfig(@RequestBody Express express){
        BaseReqVo<Express> expressBaseReqVo = new BaseReqVo<>();
        //校验参数
        boolean result1 = RegexUtils.isNumber(express.getLitemall_express_freight_min());
        boolean result2 = RegexUtils.isNumber(express.getLitemall_express_freight_value());
        if(result1 == false || result2 == false){
            expressBaseReqVo.setErrno(509);
            return expressBaseReqVo;
        }
        //调用方法
        int result = configService.updateExpressConfig(express);
        //设置结果集
        if(result == 1){
            expressBaseReqVo.setErrno(0);
            expressBaseReqVo.setErrmsg("成功");
        }
        return expressBaseReqVo;
    }

    /**
     * 获取订单配置  (get请求)
     */
    @RequestMapping(value = "order",method = {GET})
    public BaseReqVo getOrderConfig(){
        //调用方法
        ConfigOrder configOrder2 = configService.selectOrderConfig();
        //设置结果集
        BaseReqVo<ConfigOrder> configOrderBaseReqVo = new BaseReqVo<>();
        configOrderBaseReqVo.setErrno(0);
        configOrderBaseReqVo.setData(configOrder2);
        configOrderBaseReqVo.setErrmsg("成功");
        return configOrderBaseReqVo;
    }

    /**
     * 修改订单配置  (post请求)
     */
    @RequestMapping(value = "order",method = {POST})
    public BaseReqVo changeOrderConfig(@RequestBody ConfigOrder configOrder){
        BaseReqVo<ConfigOrder> configOrderBaseReqVo = new BaseReqVo<>();
        //校验参数
        boolean result1 = RegexUtils.isNumber(configOrder.getLitemall_order_unpaid());
        boolean result2 = RegexUtils.isNumber(configOrder.getLitemall_order_unconfirm());
        boolean result3 = RegexUtils.isNumber(configOrder.getLitemall_order_comment());
        if(result1 == false || result2 == false || result3 == false){
            configOrderBaseReqVo.setErrno(510);
            return configOrderBaseReqVo;
        }
        //调用方法
        int result = configService.updateOrderConfig(configOrder);
        //设置结果集
        if(result == 1){
            configOrderBaseReqVo.setErrno(0);
            configOrderBaseReqVo.setErrmsg("成功");
        }
        return configOrderBaseReqVo;
    }

    /**
     * 获取小程序配置  (get请求)
     */
    @RequestMapping(value = "wx",method = {GET})
    public BaseReqVo getWxConfig(){
        //调用方法
        ConfigWx configWx = configService.selectWxConfig();
        //设置结果集
        BaseReqVo<ConfigWx> configWxBaseReqVo = new BaseReqVo<>();
        configWxBaseReqVo.setErrno(0);
        configWxBaseReqVo.setData(configWx);
        configWxBaseReqVo.setErrmsg("成功");
        return configWxBaseReqVo;
    }

    /**
     * 修改小程序配置  (post请求)
     */
    @RequestMapping(value = "wx",method = {POST})
    public BaseReqVo changeWxConfig(@RequestBody ConfigWx configWx){
        BaseReqVo<ConfigWx> configWxBaseReqVo = new BaseReqVo<>();
        //参数校验
        boolean result1 = RegexUtils.isNumber(configWx.getLitemall_wx_catlog_goods());
        boolean result2 = RegexUtils.isNumber(configWx.getLitemall_wx_catlog_list());
        boolean result3 = RegexUtils.isNumber(configWx.getLitemall_wx_index_brand());
        boolean result4 = RegexUtils.isNumber(configWx.getLitemall_wx_index_hot());
        boolean result5 = RegexUtils.isNumber(configWx.getLitemall_wx_index_new());
        boolean result6 = RegexUtils.isNumber(configWx.getLitemall_wx_index_topic());
        if(result1 == false || result2 == false || result3 == false ||
           result4 == false || result5 == false || result6 == false){
            configWxBaseReqVo.setErrno(510);
            return configWxBaseReqVo;
        }
        //调用方法
        int result = configService.updateWxConfig(configWx);
        //设置结果集
        if(result == 1){
            configWxBaseReqVo.setErrno(0);
            configWxBaseReqVo.setErrmsg("成功");
        }
        return configWxBaseReqVo;
    }
}
