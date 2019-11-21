package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.generator.Footprint;
import com.cskaoyan.mall.bean.generator.FootprintExample;
import java.util.List;
import java.util.Map;

import com.cskaoyan.wxmall.bean.FootPrintBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface FootprintMapper {
    long countByExample(FootprintExample example);

    int deleteByExample(FootprintExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Footprint record);

    int insertSelective(Footprint record);

    List<Footprint> selectByExample(FootprintExample example);

    Footprint selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Footprint record, @Param("example") FootprintExample example);

    int updateByExample(@Param("record") Footprint record, @Param("example") FootprintExample example);

    int updateByPrimaryKeySelective(Footprint record);

    int updateByPrimaryKey(Footprint record);

    int updateUpdateTimeByGoodsId(Footprint footprint);

    List<FootPrintBean> selectFootprintBy(Integer id);
}