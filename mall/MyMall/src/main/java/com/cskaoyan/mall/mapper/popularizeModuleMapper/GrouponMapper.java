package com.cskaoyan.mall.mapper.popularizeModuleMapper;


import com.cskaoyan.mall.bean.generator.Order;
import com.cskaoyan.mall.bean.generator.popularizeModule.Groupon;
import com.cskaoyan.wxmall.bean.GrouponWxBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GrouponMapper {
    List<Groupon> getGroupons(@Param("goodsId") Integer goodsId,
                              @Param("sort") String sort,
                              @Param("order") String order);

    List<Groupon> selectAll();

<<<<<<< HEAD


    Groupon hasOrder(@Param("orderId") Integer id);


=======
>>>>>>> 8bc4fd1c417389b07eb00356ff61c9582a557720
    List<Groupon> selectGrouponById(Integer id);

    List<GrouponWxBean> queryWxGrouponsList();

}
