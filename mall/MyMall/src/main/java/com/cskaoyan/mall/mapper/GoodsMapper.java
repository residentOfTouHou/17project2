package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.generator.Goods;
import com.cskaoyan.mall.bean.generator.GoodsAlter;
import com.cskaoyan.mall.bean.generator.GoodsExample;
import com.cskaoyan.mall.bean.jsonbean.GoodsQueryParameters;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {
    long countByExample(GoodsExample example);

    int deleteByExample(GoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    List<Goods> selectByExampleWithBLOBs(GoodsExample example);

    List<Goods> selectByExample(GoodsExample example);

    Goods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByExampleWithBLOBs(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByExample(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);

    List<GoodsAlter> queryGoodsAndSort(GoodsQueryParameters goodsQueryParameters);

    int insertGoodAlter(GoodsAlter goodsAlter);

    GoodsAlter queryGoodsById(int id);

    int updateGoodAlter(GoodsAlter goods);

    Goods queryGoodsByGoodsSn(String goodsSn);

    Goods queryGoodsByName(String name);

    Goods queryGoodsBySnAndName(GoodsAlter goods);
}