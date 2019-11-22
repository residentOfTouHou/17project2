package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.generator.Footprint;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.bean.jsonbean.FootprintData;
import com.cskaoyan.mall.bean.jsonbean.PageSplit;
import com.cskaoyan.mall.service.FootprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/footprint")
public class FootprintController {
    @Autowired
    private FootprintService footprintService;
    @RequestMapping("/list")
    public BaseReqVo list(PageSplit pageSplit){
        FootprintData data = new FootprintData();
        BaseReqVo baseReqVo = new BaseReqVo();
        Integer userId = pageSplit.getUserId();
        Integer goodsId = pageSplit.getGoodsId();
        if (userId==null && goodsId == null){
            Map<String, Object> allFootprint = footprintService.findAll(pageSplit);
            Long total = (Long) allFootprint.get("total");
            List<Footprint> items = (List<Footprint>) allFootprint.get("footprints");

            data.setTotal(total);
            data.setItems(items);
        }else {
            Map<String, Object> footprintByCondition = footprintService.findFootprintByCondition(pageSplit);
            Long total = (Long) footprintByCondition.get("total");
            List<Footprint> items = (List<Footprint>) footprintByCondition.get("footprints");

            data.setTotal(total);
            data.setItems(items);
        }


        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setData(data);
        return baseReqVo;
    }
}
