package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.generator.Address;
import com.cskaoyan.mall.bean.generator.AddressExample;
import com.cskaoyan.mall.bean.generator.Region;
import com.cskaoyan.mall.bean.generator.RegionExample;
import com.cskaoyan.mall.bean.jsonbean.PageSplit;
import com.cskaoyan.mall.mapper.AddressMapper;
import com.cskaoyan.mall.mapper.RegionMapper;
import com.cskaoyan.mall.service.AddressService;
import com.cskaoyan.mall.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private RegionMapper regionMapper;

    @Override
    public Map<String, Object> findAll(PageSplit pageSplit) {


//        分页
        PageHelper.startPage(pageSplit.getPage(), pageSplit.getLimit());
        String name = pageSplit.getName();
        Integer userId = pageSplit.getUserId();
        AddressExample addressExample = new AddressExample();

//排序方式
        addressExample.setOrderByClause(pageSplit.getSort() + " " + pageSplit.getOrder());


        List<Address> addresses = addressMapper.selectByExample(addressExample);
        for (Address address : addresses) {
            Region areaInfo = findAddressInfo(address.getAreaId());
            Region cityInfo = findAddressInfo(address.getCityId());
            String area = areaInfo.getName();
            String city = cityInfo.getName();


            RegionExample regionExample3 = new RegionExample();
//
//
//            RegionExample.Criteria criteria2 = regionExample2.createCriteria();
            RegionExample.Criteria criteria3 = regionExample3.createCriteria();
//
//
//           criteria2.andPidEqualTo(address.getCityId());
            criteria3.andIdEqualTo(address.getProvinceId());
//
//
//            List<Region> regions2 = regionMapper.selectByExample(regionExample2);
            List<Region> regions3 = regionMapper.selectByExample(regionExample3);
            address.setArea(area);
            address.setCity(city);
            address.setProvince(regions3.get(0).getName());
        }
        PageInfo<Address> addressPageInfo = new PageInfo<>(addresses);
        long total = addressPageInfo.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("addresses", addresses);
        return map;

    }

    @Override
    public Map<String, Object> findAddressByCondition(PageSplit pageSplit) {
        //        分页
        PageHelper.startPage(pageSplit.getPage(), pageSplit.getLimit());
        String name = pageSplit.getName();
        Integer userId = pageSplit.getUserId();
        AddressExample addressExample = new AddressExample();
        AddressExample.Criteria criteria = addressExample.createCriteria();
        addressExample.setOrderByClause(pageSplit.getSort() + " " + pageSplit.getOrder());

//        条件查询
//        List<Address> addresses = addressMapper.selectAddressBycondition("%" + name + "%", userId);

        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        if (!StringUtil.isBlank(name)) {
            criteria.andNameLike("%" + name + "%");
        }

        List<Address> addresses = addressMapper.selectByExample(addressExample);
        for (Address address : addresses) {
            Region areaInfo = findAddressInfo(address.getAreaId());
            Region cityInfo = findAddressInfo(address.getCityId());
            String area = areaInfo.getName();
            String city = cityInfo.getName();


            RegionExample regionExample3 = new RegionExample();
//
//
//            RegionExample.Criteria criteria2 = regionExample2.createCriteria();
            RegionExample.Criteria criteria3 = regionExample3.createCriteria();
//
//
//           criteria2.andPidEqualTo(address.getCityId());
            criteria3.andIdEqualTo(address.getProvinceId());
//
//
//            List<Region> regions2 = regionMapper.selectByExample(regionExample2);
            List<Region> regions3 = regionMapper.selectByExample(regionExample3);
            address.setArea(area);
            address.setCity(city);
            address.setProvince(regions3.get(0).getName());
        }

        PageInfo<Address> addressPageInfo = new PageInfo<>(addresses);

        long total = addressPageInfo.getTotal();

        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("addresses", addresses);
        return map;


    }

    Region findAddressInfo(int id) {
        RegionExample regionExample = new RegionExample();
        /*RegionExample.Criteria criteria = regionExample.createCriteria();
        criteria.andIdEqualTo(id);
        List<Region> regions = regionMapper.selectByExample(regionExample);*/
        Region region = regionMapper.selectByPrimaryKey(id);
        return region;

    }
}
