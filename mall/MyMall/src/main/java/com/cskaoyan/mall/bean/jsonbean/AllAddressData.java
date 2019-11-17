package com.cskaoyan.mall.bean.jsonbean;

import com.cskaoyan.mall.bean.generator.Address;
import com.cskaoyan.mall.bean.generator.User;
import lombok.Data;

import java.util.List;

@Data
public class AllAddressData {
    private Long total;

    private List<Address> items;
}
