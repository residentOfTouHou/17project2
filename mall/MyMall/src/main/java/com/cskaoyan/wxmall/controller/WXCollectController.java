package com.cskaoyan.wxmall.controller;


import com.cskaoyan.mall.bean.generator.Collect;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.bean.jsonbean.CollectData;
import com.cskaoyan.mall.bean.jsonbean.PageSplit;
import com.cskaoyan.mall.service.CollectService;
import com.cskaoyan.wxmall.bean.WXCollectData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/collect/")
public class WXCollectController {
    @Autowired
    private CollectService collectService;

    @RequestMapping("/list")
    public BaseReqVo list(PageSplit pageSplit) {
        WXCollectData data = new WXCollectData();
        BaseReqVo baseReqVo = new BaseReqVo();
        Integer userId = pageSplit.getUserId();
        Integer valueId = pageSplit.getValueId();
        Byte type = pageSplit.getType();
        if (userId == null && valueId == null&& type==null) {
            Map<String, Object> allCollect = collectService.findAll(pageSplit);
//            Long total = (Long) allCollect.get("total");
            List<Collect> collectList = (List<Collect>) allCollect.get("collects");

//            data.setTotal(total);
            data.setCollectList(collectList);
        } else {
            Map<String, Object> collectByCondition = collectService.findCollectByCon(pageSplit);
            Long totalPages = (Long) collectByCondition.get("totalPages");
            List<Collect> collectList = (List<Collect>) collectByCondition.get("collects");

            data.setTotalPages(totalPages);
            data.setCollectList(collectList);
        }


        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setData(data);
        return baseReqVo;
    }

    @RequestMapping("/addordelete")
    public BaseReqVo addordelete(Byte type, Integer valueId) {
        BaseReqVo baseReqVo = new BaseReqVo();

        Map<String, String> data = new HashMap<>();
        List<Collect> collects = collectService.findCollectByCondition(type, valueId);

        if (collects.isEmpty()) {
            int i = collectService.addCollection(type, valueId);
            data.put("type", "add");
        } else {
            int j = collectService.deleteCollection(type, valueId);
            data.put("type", "delete");
        }
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setData(data);

        return baseReqVo;
    }
}
