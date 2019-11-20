package com.cskaoyan.wxmall.controller;
import com.cskaoyan.mall.bean.generator.Brand;
import com.cskaoyan.mall.bean.generator.popularizeModule.Topic;
import com.cskaoyan.mall.bean.generator.popularizeModule.Ad;
import com.cskaoyan.mall.bean.generator.popularizeModule.Coupon;
import com.cskaoyan.mall.bean.generator.popularizeModule.Groupon;
import com.cskaoyan.mall.bean.generator.Goods;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.mapper.popularizeModuleMapper.GrouponMapper;
import com.cskaoyan.mall.service.BrandService;
import com.cskaoyan.mall.service.popularizeModuleService.AdService;
import com.cskaoyan.mall.service.popularizeModuleService.CouponService;
import com.cskaoyan.mall.service.popularizeModuleService.TopicService;
import com.cskaoyan.wxmall.bean.BaseResVo.DataBean;
import com.cskaoyan.wxmall.bean.BaseResVo.DataBean.ChannelBean;
import java.util.ArrayList;
import java.util.List;
import com.cskaoyan.mall.service.GoodsService;
import com.cskaoyan.wxmall.bean.GrouponWxBean;
import com.cskaoyan.wxmall.service.GrouponWxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/wx/home")
public class HomeController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CouponService couponService;

    @Autowired
    private GrouponWxService grouponWxService;

    @Autowired
    private AdService adService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private TopicService topicService;

    @RequestMapping("index")
    public BaseReqVo inedx(){
//        封装返回前端数据
        BaseReqVo baseResVo = new BaseReqVo();
        baseResVo.setErrno(0);
        DataBean dataBean = new DataBean();
        List<Goods> newGoodsList = goodsService.queryNewGoods();
        dataBean.setNewGoodsList(newGoodsList);

        List<Coupon> couponList = couponService.queryCoupons();
        dataBean.setCouponList(couponList);

        List<ChannelBean> channels = new ArrayList<>();
        ChannelBean channelBean = new ChannelBean();
        dataBean.setChannel(channels);

        List<GrouponWxBean> grouponList = grouponWxService.queryWxGrouponsList(1,5);
        dataBean.setGrouponList(grouponList);

        List<Ad> banner = adService.getAllAd();
        dataBean.setBanner(banner);

        List<Brand> brandList = brandService.getAllBrands();
        dataBean.setBrandList(brandList);

        List<Goods> hotGoodsList = goodsService.queryHotGoods();
        dataBean.setHotGoodsList(hotGoodsList);

        List<Topic> topicList = topicService.getAllTopics();
        dataBean.setTopicList(topicList);

        ArrayList<Goods> floorGoodsList = new ArrayList<>();
        dataBean.setFloorGoodsList(floorGoodsList);


        baseResVo.setData(dataBean);
        baseResVo.setErrmsg("");
        return baseResVo;
    }
}
