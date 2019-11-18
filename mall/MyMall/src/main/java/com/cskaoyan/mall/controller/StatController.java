package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.bean.jsonbean.StatGoods;
import com.cskaoyan.mall.bean.jsonbean.StatOrder;
import com.cskaoyan.mall.bean.jsonbean.StatUser;
import com.cskaoyan.mall.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/stat")
public class StatController {

    @Autowired
    StatService statService;

    /**
     * 用户统计
     * @return
     */
    @RequestMapping("user")
    public BaseReqVo getUserStat(){
        //查找并封装参数
        Map<String,Object> map = new HashMap<>();
        String[] columns = {"day","users"};
        List<StatUser> rows = statService.selectStatUser();
        map.put("columns",columns);
        map.put("rows",rows);
        //设置并返回结果集
        BaseReqVo<Map> mapBaseReqVo = new BaseReqVo<>();
        mapBaseReqVo.setErrno(0);
        mapBaseReqVo.setData(map);
        mapBaseReqVo.setErrmsg("成功");
        return mapBaseReqVo;
    }

    /**
     * 订单统计
     */
    @RequestMapping("order")
    public BaseReqVo getOrderStat(){
        //查找参数
        String[] columns = {"day", "orders", "customers", "amount", "pcr"};
        List<StatOrder> rows = statService.selectStatOrder();
        //封装参数
        Map<String,Object> map = new HashMap<>();
        map.put("columns",columns);
        map.put("rows",rows);
        //设置结果集
        BaseReqVo<Map> mapBaseReqVo = new BaseReqVo<>();
        mapBaseReqVo.setErrno(0);
        mapBaseReqVo.setData(map);
        mapBaseReqVo.setErrmsg("成功");
        return mapBaseReqVo;
    }

    /**
     * 商品统计
     */
    @RequestMapping("goods")
    public BaseReqVo getGoodsStat(){
        //查找参数
        String[] columns = {"day","orders","products","amount"};
        List<StatGoods> rows = statService.selectStatGoods();
        //封装参数
        Map<String,Object> map = new HashMap<>();
        map.put("columns",columns);
        map.put("rows",rows);
        //设置结果集
        BaseReqVo<Map> mapBaseReqVo = new BaseReqVo<>();
        mapBaseReqVo.setErrno(0);
        mapBaseReqVo.setData(map);
        mapBaseReqVo.setErrmsg("成功");
        return mapBaseReqVo;
    }
}
