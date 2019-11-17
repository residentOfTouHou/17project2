package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.generator.Ad;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.service.AdService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/ad")
public class AdController {

    @Autowired
    AdService adService;

    @RequestMapping("list")
    public BaseReqVo queryAdList(int page, int limit, String sort, String order, String name, String content){

        List<Ad> ads = adService.queryAd(page,limit,sort,order,name,content);
        PageInfo<Ad> adPageInfo = new PageInfo<>(ads);
        long total = adPageInfo.getTotal();

        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(0);
        Map<String,Object> adsMap = new HashMap<>();
        adsMap.put("total",total);
        adsMap.put("items",ads);
        baseReqVo.setData(adsMap);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    @RequestMapping("delete")
    public BaseReqVo delete(@RequestBody Ad ad){
        int id = ad.getId();
        adService.deleteAd(id);

        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    @RequestMapping("update")
    public BaseReqVo update(@RequestBody Ad ad){
        BaseReqVo baseReqVo = new BaseReqVo();
        int id = ad.getId();
        int flag = adService.updateAd(ad);
        if(flag == 1){
            Ad updatedAd = adService.queryAdById(id);
            baseReqVo.setErrno(0);
            baseReqVo.setData(updatedAd);
            baseReqVo.setErrmsg("成功");
        }
        return baseReqVo;
    }

    @RequestMapping("create")
    public BaseReqVo create(){
        BaseReqVo baseReqVo = new BaseReqVo();
        return baseReqVo;
    };
}
