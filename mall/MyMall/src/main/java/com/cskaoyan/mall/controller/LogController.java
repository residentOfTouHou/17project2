/**
 *
 */
package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.generator.Log;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.service.LogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RequestMapping("/admin/log")
@RestController
public class LogController {

    @Autowired
    LogService logService;

    @RequestMapping("list")
    @RequiresPermissions("admin:log:list")
    public BaseReqVo logList(@Param("page")int page, @Param("limit")int limit,
                             @Param("sort")String sort, @Param("order")String order,
                             String name){
        PageHelper.startPage(page, limit);
        List<Log> logs = logService.getLogsOrderBy(sort,order,name);
        PageInfo<Log> logPageInfo = new PageInfo<>(logs);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",logPageInfo.getTotal());
        map.put("items",logPageInfo.getList());
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setData(map);
        baseReqVo.setErrmsg("success");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }
}
