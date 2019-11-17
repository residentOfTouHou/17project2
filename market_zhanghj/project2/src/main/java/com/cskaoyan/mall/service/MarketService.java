package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.generator.Brand;
import com.cskaoyan.mall.bean.generator.Category;
import com.cskaoyan.mall.bean.jsonbean.CategoryL1Segment;
import com.cskaoyan.mall.bean.jsonbean.CategorySegment;
import com.cskaoyan.mall.bean.jsonbean.RegionSegment;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 *
 * @author zhanghj
 * @date 2019/11/15
 * @time 19:48
 */
public interface MarketService {
    List<RegionSegment> queryRegion();

    Map<String, Object> queryBrand(Map<String, String> map);

    Map<String, Object> addBrand(Brand brand);

    Map<String, Object> createImg(HttpServletRequest request, MultipartFile file) throws IOException;

    Brand updateBrand(Brand brand);

    int deleteBrand(Brand brand);

    List<CategorySegment> getCategoryList();

    List<CategoryL1Segment> getCategoryL1();

    Category addCategory(Category category);

    void updateCategory(CategorySegment categorySegment);

    void deleteCategory(CategorySegment categorySegment);

}
