package com.cskaoyan.mall.service;


import com.cskaoyan.mall.bean.generator.Ad;

import java.util.List;

public interface AdService {

    List<Ad> queryAd(int page,int limit,String sort,String order,String name,String content);

    void deleteAd(Integer id);

    int updateAd(Ad ad);

    Ad queryAdById(int adId);
}
