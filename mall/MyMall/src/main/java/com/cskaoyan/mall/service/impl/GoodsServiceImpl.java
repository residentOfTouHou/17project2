package com.cskaoyan.mall.service.impl;

import java.util.*;

import com.cskaoyan.mall.bean.generator.*;
import com.cskaoyan.mall.bean.generator.popularizeModule.Groupon;
import com.cskaoyan.mall.bean.generator.popularizeModule.GrouponRules;
import com.cskaoyan.mall.bean.jsonbean.GoodsData;
import com.cskaoyan.mall.bean.jsonbean.GoodsQueryParameters;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.mapper.popularizeModuleMapper.GrouponMapper;
import com.cskaoyan.mall.mapper.popularizeModuleMapper.GrouponRulesMapper;
import com.cskaoyan.mall.service.GoodsService;
import com.cskaoyan.mall.utils.DateUtils;
import com.cskaoyan.wxmall.bean.SpecificationListBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 杨盛
 * @date 2019/11/15 19:36
 */

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;

    @Autowired
    IssueMapper issueMapper;

    @Autowired
    GoodsAttributeMapper goodsAttributeMapper;

    @Autowired
    GoodsProductMapper goodsProductMapper;

    @Autowired
    GrouponRulesMapper grouponRulesMapper;

    @Autowired
    GrouponMapper grouponMapper;

    @Autowired
    CollectMapper collectMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    BrandMapper brandMapper;

    @Autowired
    FootprintMapper footprintMapper;

    @Autowired
    SearchHistoryMapper searchHistoryMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public GoodsData queryGoods(GoodsQueryParameters goodsQueryParameters) {
        PageHelper.startPage(goodsQueryParameters.getPage(), goodsQueryParameters.getLimit());
        if (goodsQueryParameters.getName() != null) {
            goodsQueryParameters.setName("%" + goodsQueryParameters.getName() + "%");
        }
        List<GoodsAlter> goodsAlters = goodsMapper.queryGoodsAndSort(goodsQueryParameters);
        PageInfo<GoodsAlter> goodsPageInfo = new PageInfo<>(goodsAlters);
        long total = goodsPageInfo.getTotal();
        GoodsData goodsData = new GoodsData();
        goodsData.setTotal(total);
        goodsData.setItems(goodsAlters);
        return goodsData;
    }

    @Override
    public int insertGoods(GoodsAlter goods) {
        int insert = goodsMapper.insertGoodAlter(goods);
        return insert;
    }

    @Override
    public GoodsAlter queryGoodsById(int id) {
        GoodsAlter goodsAlter = goodsMapper.queryGoodsById(id);
        return goodsAlter;
    }

    @Override
    public int updateGoods(GoodsAlter goods) {
        int update = goodsMapper.updateGoodAlter(goods);
        return update;
    }

    @Override
    public int deleteGoods(Integer id) {
        GoodsExample example = new GoodsExample();
        int delete = goodsMapper.deleteByPrimaryKey(id);
        return delete;
    }

    @Override
    public List<Goods> findAll() {
        GoodsExample example = new GoodsExample();
        List<Goods> goodsList = goodsMapper.selectByExample(example);
        return goodsList;
    }

    @Override
    public Goods queryGoodsByGoodsSn(String goodsSn) {
        Goods goods = goodsMapper.queryGoodsByGoodsSn(goodsSn);
        return goods;
    }

    @Override
    public Goods queryGoodsByName(String name) {
        Goods goods = goodsMapper.queryGoodsByName(name);
        return goods;
    }

    @Override
    public Goods queryGoodsBySnAndName(GoodsAlter goods) {
        Goods queryGoods = goodsMapper.queryGoodsBySnAndName(goods);
        return queryGoods;
    }

    @Override
    public int queryGoodsCount() {
        return findAll().size();
    }

    @Override
    public List<Goods> queryNewGoods() {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIsNewEqualTo(true);
        return goodsMapper.selectByExample(goodsExample);
    }

    @Override
    public List<Goods> queryHotGoods() {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIsHotEqualTo(true);
        return goodsMapper.selectByExample(goodsExample);
    }

    @Override
    public List<Goods> queryGoodsList(Integer categoryId, Integer page, Integer size) {
        //分页
        PageHelper.startPage(page, size);

        List<Goods> goodsList = goodsMapper.selectGoodsByCategoryId(categoryId);
        return goodsList;
    }

    /**
     * 获取商品详细信息
     *
     * @param id 商品id
     * @return 结果集data
     */
    @Override
    public HashMap<String, Object> queryGoodsDetail(Integer id, Integer userId) {
        //获取商品规格 specificationList
        List<GoodsSpecification> valueList = goodsSpecificationMapper.selectByGoodsId(id);
        //获取团购 groupon
        GrouponRules grouponRules = grouponRulesMapper.selectByGoodsId(id);
        List<Groupon> groupon = null;
        if (grouponRules != null) {
            Integer grouponId = grouponRules.getId();
            groupon = grouponMapper.selectGrouponById(grouponId);
        }
        //获取问题 issue
        IssueExample issueExample = new IssueExample();
        List<Issue> issue = issueMapper.selectByExample(issueExample);
        //获取 userHasCollect
        Integer userHasCollect = collectMapper.selectUserIdByGoodsIdAndType(id);
        //获取商品信息 info
        Goods info = goodsMapper.selectByPrimaryKey(id);
        //获取 shareImage
        String shareImage = info.getShareUrl();
        //获取评论 comment {data,count} 注意：里面还有一层对象 需要套一下
        List<Comment> comments = commentMapper.selectCommentsByGoodsIdAndType(id);
        //获取属性 attribute
        List<GoodsAttribute> attribute = goodsAttributeMapper.selectByGoodsId(id);
        //获取品牌 brand
        Integer brandId = info.getBrandId();
        Brand brand = brandMapper.selectByPrimaryKey(brandId);
        //获取产品 productList
        List<GoodsProduct> productList = goodsProductMapper.selectByGoodsId(id);
        //封装Date spec和comment需要套娃
        HashMap<String,Object> map = new HashMap();
        HashMap<String,Object> commentMap = new HashMap();
        //封装specificationList
        Set<String> nameSet = new HashSet<>();
        for (GoodsSpecification goodsSpecification : valueList) {
            String name = goodsSpecification.getSpecification();
            nameSet.add(name);
        }
        List<GoodsSpecification> goodsSpecificationList = new ArrayList<>();
        for (String s : nameSet) {
            List<GoodsSpecification> goodsSpecificationList2 = new ArrayList<>();
            GoodsSpecification goodsSpecification = new GoodsSpecification();
            goodsSpecification.setName(s);
            for (GoodsSpecification specification : valueList) {
                if (s.equals(specification.getSpecification())) {
                    goodsSpecificationList2.add(specification);
                }
            }
            goodsSpecification.setValueList(goodsSpecificationList2);
            goodsSpecificationList.add(goodsSpecification);
        }
        //封装commentMap
        commentMap.put("data", comments);
        commentMap.put("count", comments.size());
        //封装Date
        map.put("specificationList", goodsSpecificationList);
        map.put("groupon", groupon);
        map.put("issue", issue);
        map.put("userHasCollect", userHasCollect);
        map.put("shareImage", shareImage);
        map.put("comment", commentMap);
        map.put("attribute", attribute);
        map.put("brand", brand);
        map.put("productList", productList);
        map.put("info", info);
        //记录足迹 没有登录则不记录足迹
        if (userId != 0) {
            Footprint footprint = new Footprint();
            //userId后为当前用户Id
            footprint.setUserId(userId);
            footprint.setGoodsId(id);
            footprint.setAddTime(new Date());
            footprint.setUpdateTime(new Date());
            footprint.setDeleted(false);
            footprintMapper.insertSelective(footprint);
        }
        return map;
    }

    /**
     * 查看商品详情页关联商品（大家都在看）
     */
    @Override
    public HashMap<String, Object> queryGoodsRelated(Integer id) {
        Integer categoryId = goodsMapper.selectByPrimaryKey(id).getCategoryId();
        List<Goods> goodsList = goodsMapper.selectGoodsByCategoryId(categoryId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("goodsList", goodsList);
        return map;
    }

    @Override
    public int updateNumberById(GoodsProduct goodsProduct) {
        int update = goodsProductMapper.updateByPrimaryKeySelective(goodsProduct);
        return update;
    }
    /**
     * 模糊查询并分页
     * @param keyword
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Goods> queryGoodsListByName(String keyword, Integer page, Integer size,String sort,String order) {
        PageHelper.startPage(page,size);
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.setOrderByClause(sort + " " + order);

        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andNameLike("%" + keyword + "%");
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        return goodsList;
    }


    @Override
    public int insertKeyWord(String keyword) {
        SearchHistory searchHistory = new SearchHistory();
        //searchHistory.setId();
        searchHistory.setUserId(1);
        searchHistory.setKeyword(keyword);
        searchHistory.setFrom("wx");
        searchHistory.setAddTime(new Date());
        searchHistory.setUpdateTime(new Date());
        searchHistory.setDeleted(false);

        return searchHistoryMapper.insert(searchHistory);
    }

    @Override
    public List<Goods> selectAllGoods(String keyword, Boolean isNew, Boolean isHot, int page, int size, String sort, String order, int categoryId) {
        PageHelper.startPage(page,size);

        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        if(keyword != null) {
            criteria.andNameLike("%" + keyword + "%");
        }
        if(isNew != null) {
            criteria.andIsNewEqualTo(isNew);
        }
        if(isHot != null) {
            criteria.andIsHotEqualTo(isHot);
        }
        if(categoryId != 0) {
            criteria.andCategoryIdEqualTo(categoryId);
        }
        if (sort != null && order != null) {
            String orderClause = sort + " " + order;
            goodsExample.setOrderByClause(orderClause);
        }
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        return goodsList;
    }

    @Override
    public List<Category> queryAllCategory() {
        return categoryMapper.selectByExample(new CategoryExample());
    }

}
