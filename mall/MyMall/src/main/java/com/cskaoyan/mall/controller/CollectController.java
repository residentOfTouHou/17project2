package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.generator.Collect;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.bean.jsonbean.CollectData;
import com.cskaoyan.mall.bean.jsonbean.PageSplit;
import com.cskaoyan.mall.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/collect/")
public class CollectController {
    @Autowired
    private CollectService collectService;
    @RequestMapping("/list")
    public BaseReqVo list(PageSplit pageSplit){
        CollectData data = new CollectData();
        BaseReqVo baseReqVo = new BaseReqVo();
        Integer userId = pageSplit.getUserId();
        Integer valueId = pageSplit.getValueId();
        if (userId==null && valueId == null){
            Map<String, Object> allCollect = collectService.findAll(pageSplit);
            Long total = (Long) allCollect.get("total");
            List<Collect> items = (List<Collect>) allCollect.get("collects");

            data.setTotal(total);
            data.setItems(items);
        }else {
            Map<String, Object> collectByCondition = collectService.findCollectByCondition(pageSplit);
            Long total = (Long) collectByCondition.get("total");
            List<Collect> items = (List<Collect>) collectByCondition.get("collects");

            data.setTotal(total);
            data.setItems(items);
        }


        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setData(data);
        return baseReqVo;
    }
}
