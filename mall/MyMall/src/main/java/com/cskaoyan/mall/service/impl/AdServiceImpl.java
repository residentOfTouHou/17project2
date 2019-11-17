package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.generator.Ad;
import com.cskaoyan.mall.mapper.AdMapper;
import com.cskaoyan.mall.service.AdService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    AdMapper adMapper;

    @Override
    public List<Ad> queryAd(int page,int limit,String sort,String order,String name,String content) {

        PageHelper.startPage(page,limit);
        if(name!=null) {
            name = "%" + name + "%";
        }
        if(content!=null) {
            content = "%" + content + "%";
        }

        List<Ad> ads = adMapper.queryAd(sort,order,name,content);
        return ads;
    }

    @Override
    public void deleteAd(Integer id) {
        adMapper.deleteAdById(id);
    }

    @Override
    public int updateAd(Ad ad) {
        int flag = adMapper.updateAd(ad);
        return flag;
    }

    @Override
    public Ad queryAdById(int adId) {
        Ad ad = adMapper.queryUpdatedAd(adId);
        return ad;
    }
}
