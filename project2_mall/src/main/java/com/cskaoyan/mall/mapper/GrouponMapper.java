package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.generator.Groupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GrouponMapper {
    List<Groupon> getGroupons(@Param("goodsId") Integer goodsId,
                              @Param("sort") String sort,
                              @Param("order") String order);
}
