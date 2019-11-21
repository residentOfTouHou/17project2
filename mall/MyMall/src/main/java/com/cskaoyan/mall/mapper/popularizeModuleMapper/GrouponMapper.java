package com.cskaoyan.mall.mapper.popularizeModuleMapper;


import com.cskaoyan.mall.bean.generator.Order;
import com.cskaoyan.mall.bean.generator.popularizeModule.Groupon;
import com.cskaoyan.wxmall.bean.GrouponGoodsWxBean;
import com.cskaoyan.wxmall.bean.GrouponWxBean;
import com.cskaoyan.wxmall.bean.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GrouponMapper {
    List<Groupon> getGroupons(@Param("goodsId") Integer goodsId,
                              @Param("sort") String sort,
                              @Param("order") String order);

    List<Groupon> selectAll();




    Groupon hasOrder(@Param("orderId") Integer id);


    List<Groupon> selectGrouponById(Integer id);

    List<GrouponWxBean> queryWxGrouponsList();

    Groupon queryGrouponByUserId(int userId);

    Order queryOrderByOrderId(int orderId);

    List<Integer> queryOrderGoodsByOrderId(int orderId);

    List<GrouponGoodsWxBean> queryGoodsListByGoodIds(@Param("goodsIds") List<Integer> goodsIds);

    Groupon queryGrouponById(int grouponId);

    OrderInfo queryOrderByUserId(int userId);
}
