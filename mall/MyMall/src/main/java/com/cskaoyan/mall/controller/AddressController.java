package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.generator.Address;
import com.cskaoyan.mall.bean.jsonbean.AllAddressData;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.bean.jsonbean.PageSplit;
import com.cskaoyan.mall.service.AddressService;
import com.cskaoyan.mall.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/address")
public class AddressController {
    @Autowired
    private AddressService addressService;
@RequestMapping("/list")
    public BaseReqVo list(PageSplit pageSplit) {
        AllAddressData data = new AllAddressData();
        BaseReqVo baseReqVo = new BaseReqVo();
        String name = pageSplit.getName();
        Integer userId = pageSplit.getUserId();
        if (StringUtil.isBlank(name) && userId ==null) {
            Map<String, Object> addressServiceAll = addressService.findAll(pageSplit);
            Long total = (Long) addressServiceAll.get("total");
            List<Address> items = (List<Address>) addressServiceAll.get("addresses");

            data.setTotal(total);
            data.setItems(items);
        } else {
            Map<String, Object> addressByCondition = addressService.findAddressByCondition(pageSplit);
            Long total = (Long) addressByCondition.get("total");
            List<Address> items = (List<Address>) addressByCondition.get("addresses");

            data.setTotal(total);
            data.setItems(items);
        }


        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setData(data);
        return baseReqVo;
    }
}

