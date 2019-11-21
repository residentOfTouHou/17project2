/**
 *
 */
package com.cskaoyan.wxmall.controller;

import com.cskaoyan.mall.bean.generator.Region;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.service.RegionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/wx/region")
public class RegionController {
    @Autowired
    RegionService regionService;

    @RequestMapping("list")
    public BaseReqVo list(@Param("pid") int pid){
        List<Region> regions = regionService.getRegionsByPid(pid);
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setData(regions);
        baseReqVo.setErrmsg("success");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }
}
