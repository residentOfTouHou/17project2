package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.generator.Ad;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface AdMapper {

    List<Ad> queryAd(@Param("sort") String sort,
                     @Param("order") String order,
                     @Param("adName") String name,
                     @Param("adContent") String content);

    void deleteAdById(@Param("adId") Integer id);

    int updateAd(@Param("ad") Ad ad);

    Ad queryUpdatedAd(@Param("updatedId") Integer id);
}
