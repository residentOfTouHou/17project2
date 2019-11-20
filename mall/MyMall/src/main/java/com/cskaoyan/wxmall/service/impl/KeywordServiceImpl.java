package com.cskaoyan.wxmall.service.impl;

import com.cskaoyan.mall.bean.generator.Keyword;
import com.cskaoyan.mall.bean.generator.KeywordExample;
import com.cskaoyan.mall.mapper.KeywordMapper;
import com.cskaoyan.wxmall.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordServiceImpl implements KeywordService {
    @Autowired
    private KeywordMapper keywordMapper;

    @Override
    public List<Keyword> findKeyWordHot(Boolean isHot) {
        KeywordExample keywordExample = new KeywordExample();
        KeywordExample.Criteria criteria = keywordExample.createCriteria();
        criteria.andIsHotEqualTo(isHot);
        List<Keyword> keywords = keywordMapper.selectByExample(keywordExample);
        return keywords;
    }

    @Override
    public List<Keyword> findKeyWordDefault(Boolean isDefault) {
        KeywordExample keywordExample = new KeywordExample();
        KeywordExample.Criteria criteria = keywordExample.createCriteria();
        criteria.andIsDefaultEqualTo(isDefault);
        List<Keyword> keywords = keywordMapper.selectByExample(keywordExample);
        return keywords;
    }
}
