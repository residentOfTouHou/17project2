package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.generator.Ad;
import com.cskaoyan.mall.bean.generator.AdExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdMapper {
    long countByExample(AdExample example);

    int deleteByExample(AdExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Ad record);

    int insertSelective(Ad record);

    List<Ad> selectByExample(AdExample example);

    Ad selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Ad record, @Param("example") AdExample example);

    int updateByExample(@Param("record") Ad record, @Param("example") AdExample example);

    int updateByPrimaryKeySelective(Ad record);

    int updateByPrimaryKey(Ad record);

    List<Ad> queryAd(@Param("sort") String sort,
                    @Param("order") String order,
                    @Param("adName") String name,
                    @Param("adContent") String content);

    void deleteAdById(@Param("adId") Integer id);

    int updateAd(@Param("ad") Ad ad);

    Ad queryUpdatedAd(@Param("updatedId") Integer id);
}