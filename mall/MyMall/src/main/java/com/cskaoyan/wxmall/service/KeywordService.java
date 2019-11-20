package com.cskaoyan.wxmall.service;

import com.cskaoyan.mall.bean.generator.Keyword;

import java.util.List;

public interface  KeywordService {
    List<Keyword> findKeyWordHot(Boolean isHot);

    List<Keyword> findKeyWordDefault(Boolean isDefault);
}
