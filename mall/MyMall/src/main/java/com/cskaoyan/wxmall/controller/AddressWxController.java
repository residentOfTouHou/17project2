package com.cskaoyan.wxmall.controller;

import com.cskaoyan.mall.bean.generator.Address;
import com.cskaoyan.mall.bean.generator.User;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.mapper.AddressMapper;
import com.cskaoyan.mall.service.AddressService;
import com.cskaoyan.wxmall.bean.AddressBean;
import com.cskaoyan.wxmall.utils.UserIdUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/address")
public class AddressWxController {

    @Autowired
    AddressService addressService;

    /**
     * 收货地址列表查询
     */
    @RequestMapping("list")
    public BaseReqVo GetAddressList(){
        //获取userId
        Integer userId = UserIdUtils.getCurrentUserId();
        List<AddressBean> addressList = addressService.selectAddressList(userId);
        return BaseReqVo.ok(addressList);
    }

    /**
     * 地址详情
     * @param id 地址id
     */
    @RequestMapping("detail")
    public BaseReqVo GetAddress(Integer id){
        Address address =addressService.selectAddressById(id);
        return BaseReqVo.ok(address);
    }

    /**
     * 保存收货地址（修改）
     */
    @RequestMapping("save")
    public BaseReqVo SaveAddress(@RequestBody Address address){
        Integer userId = UserIdUtils.getCurrentUserId();
        if(userId!=0){
            address.setUserId(userId);
        }
        address.setUpdateTime(new Date());

        int result = addressService.updateAddress(address);
        if(result == 1){
            return BaseReqVo.ok(address.getId());
        }
        else {
            return BaseReqVo.fail(500);
        }
    }

    /**
     * 删除收货地址
     */
    @RequestMapping("delete")
    public BaseReqVo deleteAddress(@RequestBody Map map){
        Integer id = (Integer) map.get("id");
        int result = addressService.deleteAddressById(id);
        if(result == 1){
            return BaseReqVo.ok();
        }else {
            return BaseReqVo.fail(500);
        }
    }
}
