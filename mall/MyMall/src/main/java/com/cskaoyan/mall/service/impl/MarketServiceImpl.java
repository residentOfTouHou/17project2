package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.generator.*;
import com.cskaoyan.mall.bean.jsonbean.CategoryL1Segment;
import com.cskaoyan.mall.bean.jsonbean.CategorySegment;
import com.cskaoyan.mall.bean.jsonbean.RegionSegment;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.service.MarketService;
import com.cskaoyan.mall.utils.DateUtils;
import com.cskaoyan.mall.utils.MoneyUtils;
import com.cskaoyan.mall.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

/**
 * Created by IntelliJ IDEA
 *
 * @author zhanghj
 * @date 2019/11/15
 * @time 20:15
 */
@Service
public class MarketServiceImpl implements MarketService {
    private static final String UPLOAD_PATH=File.separator+"wx"+File.separator+"storage"+File.separator+"fetch"+File.separator;

    @Autowired
    RegionMapper regionMapper;

    @Autowired
    BrandMapper brandMapper;

    @Autowired
    StorageMapper storageMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrderGoodsMapper orderGoodsMapper;

    @Autowired
    IssueMapper issueMapper;

    @Autowired
    KeywordMapper keywordMapper;

    /**
     * 查询出行政区域
     * {
     * 	"errno": 0,
     * 	"data": [{
     * 		"id": 1,            id
     * 		"name": "北京市",    name
     * 		"type": 1,          type
     * 		"code": 11,         code
     * 		"children": [{
     * 			"id": 32,
     * 			"name": "市辖区",
     * 			"type": 2,
     * 			"code": 1101,
     * 			"children": [{
     * 				"id": 376,
     * 				"name": "东城区",
     * 				"type": 3,
     * 				"code": 110101
     *             }, {
     * 				"id": 377,
     * 				"name": "西城区",
     * 				"type": 3,
     * 				"code": 110102
     *            }, {
     * 				"id": 378,
     * 				"name": "朝阳区",
     * 				"type": 3,
     * 				"code": 110105
     *            }, {
     * 				"id": 379,
     * 				"name": "丰台区",
     * 				"type": 3,
     * 				"code": 110106
     *            }, {
     * 				"id": 380,
     * 				"name": "石景山区",
     * 				"type": 3,
     * 				"code": 110107
     *            }]
     *        }]
     *    }],
     * 	"errmsg": "成功"
     * }
     */
    @Override
    public List<RegionSegment> queryRegion() {
        //查出所有type=1 的一级区域
        RegionExample regionExample = new RegionExample();
        regionExample.createCriteria().andTypeEqualTo((byte) 1);
        List<Region> regionsLevelOne = regionMapper.selectByExample(regionExample);

        //封装返回的bean
        List<RegionSegment> regionSegments = new ArrayList<>();
        for (Region regionLo : regionsLevelOne) {
            RegionSegment regionSegment = new RegionSegment();
            regionSegment.setId(regionLo.getId());
            regionSegment.setCode(regionLo.getCode());
            regionSegment.setName(regionLo.getName());
            regionSegment.setType(1);

            //查询 type=2 且 code前缀为当前code 的当前二级区域 依次查询三级区域
            List<RegionSegment> regionSegmentsLevelTwo=regionMapper.queryLevelTwoRegion(2,regionLo.getCode(),
                    regionLo.getCode()*100, regionLo.getCode()*100+100);
            regionSegment.setChildren(regionSegmentsLevelTwo);
            regionSegments.add(regionSegment);
        }
        return regionSegments;
    }

    /**
     * 查询品牌商
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> queryBrand(Map<String, String> map) {
        Integer page = Integer.valueOf(map.get("page"));
        Integer limit = Integer.valueOf(map.get("limit"));
        PageHelper.startPage(page,limit);
        BrandExample brandExample = new BrandExample();
        BrandExample.Criteria criteria = brandExample.createCriteria();
        if(!StringUtils.isEmpty(map.get("id"))){
            criteria.andIdEqualTo(Integer.valueOf(map.get("id")));
        }
        if(!StringUtils.isEmpty(map.get("name"))){
            criteria.andNameLike("%"+map.get("name")+"%");
        }
        criteria.andDeletedEqualTo(false);
        Map<String,Object> result = new HashMap<>();
        List<Brand> items = brandMapper.selectByExample(brandExample);
        result.put("items",items);
        PageInfo<Brand> brandPageInfo = new PageInfo<>(items);
        long total = brandPageInfo.getTotal();
        result.put("total",total);
        return result;
    }

//    /**
//     * 上传品牌图片
//     *
//     * 等刘铭接口
//     *
//     * @return
//     */
//    @Override
//    public Map<String, Object> createImg(HttpServletRequest request, MultipartFile file) throws IOException {
//        Map<String,Object> result = new HashMap<>();
//        String filename = file.getOriginalFilename();
//        ClassPathResource resource = new ClassPathResource("");
//        String absolutePath = resource.getFile().getAbsolutePath();
//        String finalPathName = absolutePath + UPLOAD_PATH + filename;
//        File img = new File(finalPathName);
//        if(!img.exists()){
//            img.mkdirs();
//        }
//        file.transferTo(img);
//        Storage storage = new Storage();
//        storage.setKey(filename);
//        storage.
//        storageMapper.insertSelective()
//        result.put("id",);
//        result.put("key",filename);
//        result.put("name",filename);
//        result.put("size",file.getSize());
//        result.put("url",);
//        result.put("addTime",DateUtils.currentDateToString());
//        result.put("updateTime",DateUtils.currentDateToString());
//        return result;
//    }

    /**
     * 上传品牌
     * @param brand
     * @return
     */
    @Override
    public Map<String, Object> addBrand(Brand brand) {
        String date = DateUtils.currentDateToString();
        Map<String,Object> result = new HashMap<>();
        if(brand.getPicUrl()==null){
            result.put("err","noPic");
            return result;
        }
        if(!MoneyUtils.judgeTwoBigDecimal(brand.getFloorPrice())){
            result.put("err","noNumber");
            return result;
        }
        int code = brandMapper.insertBrand(brand,date);
        Integer id = brandMapper.getLastInsertId();
        result.put("id",id);
        result.put("name",brand.getName());
        result.put("desc",brand.getDesc());
        result.put("floorPrice",brand.getFloorPrice());
        result.put("addTime",date);
        result.put("updateTime",date);
        return result;
    }

    /**
     * 更新品牌
     * @param brand
     * @return
     */
    @Override
    public Brand updateBrand(Brand brand) {
        if(!MoneyUtils.judgeTwoBigDecimal(brand.getFloorPrice())){
            return null;
        }
        brand.setUpdateTime(new Date());
        brand.setSortOrder(null);
        brand.setAddTime(null);
        brand.setDeleted(null);
        brandMapper.updateByPrimaryKeySelective(brand);
        return brand;
    }

    /**
     * 删除品牌
     * @param brand
     * @return
     */
    @Override
    public int deleteBrand(Brand brand) {
        brand.setAddTime(null);
        brand.setUpdateTime(null);
        brand.setSortOrder(null);
        brand.setDeleted(true);
        BrandExample brandExample = new BrandExample();
        brandExample.createCriteria().andIdEqualTo(brand.getId());
        try {
            brandMapper.updateByExampleSelective(brand,brandExample);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    /**
     * 显示所有商品类别
     * @return
     */
    @Override
    public List<CategorySegment> getCategoryList() {
        List<CategorySegment> categorySegmentsList = null;
        try {
            categorySegmentsList=categoryMapper.selectAllCategory();
        }catch (Exception e){
            e.printStackTrace();
        }
        return categorySegmentsList;
    }

    /**
     * 查询所有L1级商品类目
     * @return
     */
    @Override
    public List<CategoryL1Segment> getCategoryL1() {
        List<CategoryL1Segment> categorySegmentsList = new ArrayList<>();
        try {
            CategoryExample example = new CategoryExample();
            example.setOrderByClause("id asc");
            example.createCriteria().andLevelEqualTo("L1");
            List<Category> categories = categoryMapper.selectByExample(example);
            for (Category category : categories) {
                CategoryL1Segment segment = new CategoryL1Segment();
                segment.setValue(category.getId());
                segment.setLabel(category.getName());
                categorySegmentsList.add(segment);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return categorySegmentsList;
    }

    /**
     * 添加类目
     * @param category
     * @return
     */
    @Override
    public Category addCategory(Category category) {
        Date date = new Date();
        category.setAddTime(date);
        category.setUpdateTime(date);
        category.setDeleted(false);
        categoryMapper.insertSelective(category);
        int id = categoryMapper.getLastInsertId();
        category.setId(id);
        return category;
    }

    /**
     * 根据三种情况更新类目
     * @param categorySegment
     */
    @Override
    public void updateCategory(CategorySegment categorySegment) {
        Category category = new Category();
        category.setId(categorySegment.getId());
        category.setName(categorySegment.getName());
        category.setKeywords(categorySegment.getKeywords());
        category.setDesc(categorySegment.getDesc());
        category.setIconUrl(categorySegment.getIconUrl());
        category.setPicUrl(categorySegment.getPicUrl());

        if("L1".equals(categorySegment.getLevel())){
            categoryMapper.updateByPrimaryKeySelective(category);
        }
        //L2
        if(categorySegment.getChildren()==null){ //二级内部调整
            category.setLevel(categorySegment.getLevel());
            category.setPid(categorySegment.getPid());
            categoryMapper.updateByPrimaryKeySelective(category);
        }else{ //一级变换为二级
            category.setLevel(categorySegment.getLevel());
            category.setPid(categorySegment.getPid());
            categoryMapper.updateByPrimaryKeySelective(category);
            for (Category segmentChild : categorySegment.getChildren()) {
                segmentChild.setDeleted(true); //所属全部逻辑删除
                categoryMapper.updateByPrimaryKeySelective(segmentChild);
            }
        }
    }

    /**
     * 删除类目
     * @param categorySegment
     */
    @Override
    public void deleteCategory(CategorySegment categorySegment) {
        Category category = new Category();
        category.setId(categorySegment.getId());
        category.setDeleted(true);
        categoryMapper.updateByPrimaryKeySelective(category);
        for (Category child : categorySegment.getChildren()) {
            category.setId(child.getId());
            categoryMapper.updateByPrimaryKeySelective(category);
        }
    }

    /**
     * 查询订单信息
     *
     * @param page
     * @param limit
     * @param orderStatusArray
     * @param sort
     * @param order
     * @param userId
     * @param orderSn
     * @return
     */
    @Override
    public Map<String, Object> getOrderList(int page, int limit, Integer[] orderStatusArray,
                  String sort, String order, Integer userId, String orderSn) {
        PageHelper.startPage(page,limit);
        OrderExample orderExample = new OrderExample();
        orderExample.setOrderByClause(sort+" "+order);
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        if(orderStatusArray!=null&&orderStatusArray.length>0){
            List<Short> list = new ArrayList<>();
            for (Integer integer : orderStatusArray) {
                list.add(Short.valueOf(String.valueOf(integer)));
            }
            criteria.andOrderStatusIn(list);
        }
        if(userId!=null){
            criteria.andUserIdEqualTo(userId);
        }
        if(orderSn!=null){
            criteria.andOrderSnEqualTo(orderSn);
        }
        Map<String,Object> result = new HashMap<>();
        List<Order> items = orderMapper.selectByExample(orderExample);
        result.put("items",items);
        PageInfo<Order> orderPageInfo = new PageInfo<>(items);
        long total = orderPageInfo.getTotal();
        result.put("total",total);
        return result;
    }

    /**
     * 获取订单详情
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> getOrderDetail(Integer id) {
        Order order = orderMapper.selectByPrimaryKey(id);
        User user = userMapper.selectByPrimaryKey(order.getUserId());
        OrderGoodsExample orderGoodsExample = new OrderGoodsExample();
        orderGoodsExample.createCriteria().andOrderIdEqualTo(id);
        List<OrderGoods> orderGoods = orderGoodsMapper.selectByExample(orderGoodsExample);
        Map<String,Object> map = new HashMap<>();
        map.put("orderGoods",orderGoods);
        map.put("user",user);
        map.put("order",order);
        return map;
    }

    /**
     * 订单发货
     * @param orderId
     * @param shipChannel
     * @param shipSn
     */
    @Override
    public void shipOrder(Integer orderId, String shipChannel, String shipSn) {
        Order order = new Order();
        order.setId(orderId);
        order.setShipChannel(shipChannel);
        order.setShipSn(shipSn);
        order.setShipTime(new Date());
        order.setOrderStatus((short) 301);
        orderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 系统取消订单
     * @param orderId
     */
    @Override
    public void refundOrder(Integer orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setOrderStatus((short) 203);
        orderMapper.updateByPrimaryKeySelective(order);
    }


    /**
     * 查询问题
     * @param page
     * @param limit
     * @param question
     * @param sort
     * @param order
     * @return
     */
    @Override
    public Map<String, Object> getIssueList(Integer page, Integer limit, String question, String sort, String order) {
        PageHelper.startPage(page,limit);
        IssueExample issueExample = new IssueExample();
        issueExample.setOrderByClause(sort+" "+order);
        IssueExample.Criteria criteria = issueExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        if(question!=null){
            criteria.andQuestionLike("%"+question+"%");
        }
        List<Issue> issues = issueMapper.selectByExample(issueExample);
        PageInfo<Issue> issuePageInfo = new PageInfo<>(issues);
        long total = issuePageInfo.getTotal();
        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("items",issues);
        return map;
    }

    /**
     * 更新通用问题
     * @param issue
     * @return
     */
    @Override
    public Issue updateIssue(Issue issue) {
        issue.setUpdateTime(new Date());
        issueMapper.updateByPrimaryKeySelective(issue);
        return issue;
    }

    /**
     * 删除问题
     * @param issue
     */
    @Override
    public void deleteIssue(Issue issue) {
        Issue segment = new Issue();
        segment.setId(issue.getId());
        segment.setDeleted(true);
        issueMapper.updateByPrimaryKeySelective(segment);
    }

    /**
     * 获取关键词
     * @param page
     * @param limit
     * @param url
     * @param keyword
     * @param sort
     * @param order
     * @return
     */
    @Override
    public Map<String, Object> getKeywords(Integer page, Integer limit, String url, String keyword, String sort, String order) {
        PageHelper.startPage(page,limit);
        KeywordExample keywordExample = new KeywordExample();
        keywordExample.setOrderByClause(sort+" "+order);
        KeywordExample.Criteria criteria = keywordExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        if(url!=null){
            criteria.andUrlLike("%"+url+"%");
        }
        if(keyword!=null){
            criteria.andKeywordLike("%"+keyword+"%");
        }
        List<Keyword> items = keywordMapper.selectByExample(keywordExample);
        PageInfo<Keyword> keywordPageInfo = new PageInfo<>(items);
        long total = keywordPageInfo.getTotal();
        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("items",items);
        return map;
    }

    /**
     * 更新关键词
     *
     * @param keyword
     * @return
     */
    @Override
    public Keyword updateKeyword(Keyword keyword) {
        keyword.setUpdateTime(new Date());
        keywordMapper.updateByPrimaryKeySelective(keyword);
        return keyword;
    }

    /**
     * 删除关键词
     * @param keyword
     */
    @Override
    public void deleteKeyword(Keyword keyword) {
        Keyword segment = new Keyword();
        segment.setId(keyword.getId());
        segment.setDeleted(true);
        keywordMapper.updateByPrimaryKeySelective(segment);
    }

    /**
     * 添加关键词
     * @param keyword
     * @return
     */
    @Override
    public Keyword addKeyword(Keyword keyword) {
        Date date = new Date();
        keyword.setAddTime(date);
        keyword.setUpdateTime(date);
        keyword.setDeleted(false);
        keyword.setSortOrder(100);
        keywordMapper.insert(keyword);
        Integer id = keywordMapper.getLastInsertId();
        keyword.setId(id);
        return keyword;
    }

}
