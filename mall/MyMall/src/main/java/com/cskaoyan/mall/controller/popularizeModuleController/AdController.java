package com.cskaoyan.mall.controller.popularizeModuleController;


import com.cskaoyan.mall.bean.generator.popularizeModule.Ad;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.service.popularizeModuleService.AdService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
    @RequiresPermissions("admin:ad:list")

    public BaseReqVo queryAdList(int page, int limit, String sort, String order, String name, String content){

        List<Ad> ads = adService.queryAd(page,limit,sort,order,name,content);
        PageInfo<Ad> adPageInfo = new PageInfo<>(ads);
        long total = adPageInfo.getTotal();

        Map<String,Object> adsMap = new HashMap<>();
        adsMap.put("total",total);
        adsMap.put("items",ads);
        return new BaseReqVo(adsMap,"成功",0);
    }

    @RequestMapping("delete")
    @RequiresPermissions("admin:ad:delete")
    public BaseReqVo delete(@RequestBody Ad ad){
        int id = ad.getId();
        adService.deleteAd(id);

        return new BaseReqVo("成功",0);
    }

    @RequestMapping("update")
    @RequiresPermissions("admin:ad:update")
    public BaseReqVo update(@RequestBody Ad ad){
        BaseReqVo baseReqVo = null;
        int id = ad.getId();
        int flag = adService.updateAd(ad);
        if(flag == 1){
            Ad updatedAd = adService.queryAdById(id);
            baseReqVo = new BaseReqVo(updatedAd,"成功",0);
        }
        return baseReqVo;
    }

    @RequestMapping("create")
    @RequiresPermissions("admin:ad:create")

    public BaseReqVo create(@RequestBody Ad ad){
        Ad insertedAd = adService.createAd(ad);
        return new BaseReqVo(insertedAd,"成功",0);
    }
}
