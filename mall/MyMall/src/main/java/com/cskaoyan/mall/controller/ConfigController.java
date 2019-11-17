package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.jsonbean.*;
import com.cskaoyan.mall.service.ConfigService;
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
        //调用方法
        int result = configService.updateMallConfig(mall);
        //设置结果集
        BaseReqVo<Mall> MallBaseReqVo = new BaseReqVo<>();
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
        //调用方法
        int result = configService.updateExpressConfig(express);
        //设置结果集
        BaseReqVo<Express> expressBaseReqVo = new BaseReqVo<>();
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
        //调用方法
        int result = configService.updateOrderConfig(configOrder);
        //设置结果集
        BaseReqVo<ConfigOrder> configOrderBaseReqVo = new BaseReqVo<>();
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
        //调用方法
        int result = configService.updateWxConfig(configWx);
        //设置结果集
        BaseReqVo<ConfigWx> configWxBaseReqVo = new BaseReqVo<>();
        if(result == 1){
            configWxBaseReqVo.setErrno(0);
            configWxBaseReqVo.setErrmsg("成功");
        }
        return configWxBaseReqVo;
    }
}
