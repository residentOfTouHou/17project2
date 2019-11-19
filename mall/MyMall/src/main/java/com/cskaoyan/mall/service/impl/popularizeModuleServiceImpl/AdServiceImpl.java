package com.cskaoyan.mall.service.impl.popularizeModuleServiceImpl;


import com.cskaoyan.mall.bean.generator.popularizeModule.Ad;
import com.cskaoyan.mall.mapper.popularizeModuleMapper.AdMapper;
import com.cskaoyan.mall.service.popularizeModuleService.AdService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    AdMapper adMapper;

    @Override
    public List<Ad> queryAd(int page, int limit, String sort, String order, String name, String content) {

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

    @Override
    public Ad createAd(Ad ad) {
        int flag = adMapper.createAd(ad);
        int adId = ad.getId();
        if(flag == 1){
           return adMapper.queryAdById(adId);
        }
        return null;
    }

    @Override
    public List<Ad> getAllAd() {
        return adMapper.getAll();
    }
}
