package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.generator.*;
import com.cskaoyan.mall.bean.jsonbean.CategoryL1Segment;
import com.cskaoyan.mall.bean.jsonbean.CategorySegment;
import com.cskaoyan.mall.bean.jsonbean.RegionSegment;
import com.cskaoyan.mall.mapper.BrandMapper;
import com.cskaoyan.mall.mapper.CategoryMapper;
import com.cskaoyan.mall.mapper.RegionMapper;
import com.cskaoyan.mall.mapper.StorageMapper;
import com.cskaoyan.mall.utils.DateUtils;
import com.cskaoyan.mall.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
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
            List<RegionSegment> regionSegmentsLevelTwo=regionMapper.queryLevelTwoRegion(2,regionLo.getCode());
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
            criteria.andNameLike(map.get("name"));
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

    /**
     * 上传品牌图片
     *
     * 等刘铭接口
     *
     * @return
     */
    @Override
    public Map<String, Object> createImg(HttpServletRequest request, MultipartFile file) throws IOException {
        Map<String,Object> result = new HashMap<>();
        String filename = file.getOriginalFilename();
        ClassPathResource resource = new ClassPathResource("");
        String absolutePath = resource.getFile().getAbsolutePath();
        String finalPathName = absolutePath + UPLOAD_PATH + filename;
        File img = new File(finalPathName);
        if(!img.exists()){
            img.mkdirs();
        }
        file.transferTo(img);

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
        return result;
    }

    /**
     * 上传品牌
     * @param brand
     * @return
     */
    @Override
    public Map<String, Object> addBrand(Brand brand) {
        String date = DateUtils.currentDateToString();
        int code = brandMapper.insertBrand(brand,date);
        Integer id = brandMapper.getLastInsertId();
        Map<String,Object> result = new HashMap<>();
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
        
    }

}
