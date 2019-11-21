package com.cskaoyan.wxmall.controller;

import com.cskaoyan.mall.bean.generator.Brand;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.service.BrandService;
import com.cskaoyan.wxmall.bean.BrandData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 杨盛
 * @date 2019/11/19 22:30
 */

@RestController
@RequestMapping("wx/brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    /**
     * 品牌列表
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("list")
    public BaseReqVo list(int page, int size) {
        BrandData brandData = brandService.queryBrandsWx(page, size);
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(0);
        baseReqVo.setData(brandData);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 品牌详情
     * @param id
     * @return
     */
    @RequestMapping("detail")
    public BaseReqVo detail(int id) {
        Brand brand = brandService.queryBrandById(id);
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(0);
        Map<String, Object> brandMap = new HashMap<>();
        brandMap.put("brand", brand);
        baseReqVo.setData(brandMap);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

}
