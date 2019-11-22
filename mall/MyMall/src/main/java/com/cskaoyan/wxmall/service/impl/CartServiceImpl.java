package com.cskaoyan.wxmall.service.impl;

import com.cskaoyan.mall.bean.generator.*;
import com.cskaoyan.mall.bean.generator.popularizeModule.Ad;
import com.cskaoyan.mall.bean.generator.popularizeModule.Coupon;
import com.cskaoyan.mall.bean.generator.popularizeModule.GrouponRules;
import com.cskaoyan.mall.mapper.AddressMapper;
import com.cskaoyan.mall.mapper.CartMapper;
import com.cskaoyan.mall.mapper.RegionMapper;
import com.cskaoyan.mall.mapper.popularizeModuleMapper.CouponMapper;
import com.cskaoyan.mall.mapper.popularizeModuleMapper.GrouponRulesMapper;
import com.cskaoyan.wxmall.bean.CartCheckoutReq;
import com.cskaoyan.wxmall.bean.CartTotal;
import com.cskaoyan.wxmall.bean.CartCheckedReq;
import com.cskaoyan.wxmall.service.CartService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 杨盛
 * @date 2019/11/20 11:22
 */

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartMapper cartMapper;

    @Autowired
    RegionMapper regionMapper;

    @Autowired
    CouponMapper couponMapper;

    @Autowired
    GrouponRulesMapper grouponRulesMapper;

    @Autowired
    AddressMapper addressMapper;

    @Override
    public long queryTotalNumber(User principal) {
        CartExample example = new CartExample();
        example.createCriteria().andUserIdEqualTo(principal.getId()).andDeletedEqualTo(false);
        List<Cart> carts = cartMapper.selectByExample(example);
        long total = 0;
        for (Cart cart : carts) {
            total += cart.getNumber();
        }
        return total;
    }

    @Override
    public int insertCart(User principal, GoodsAlter goods, GoodsProduct goodsProduct, int number) {
        Cart cart = new Cart();
        CartExample example = new CartExample();
        example.createCriteria().andUserIdEqualTo(principal.getId()).andDeletedEqualTo(false);
        List<Cart> carts = cartMapper.selectByExample(example);
        for (Cart cart1 : carts) {
            // 合并
            if (cart1.getProductId() == goodsProduct.getId()) {
                number += cart1.getNumber();
                cartMapper.deleteByPrimaryKey(cart1.getId());
            }
        }
        int insert = cartMapper.insertCart(principal, goods, goodsProduct, cart, number);
        return insert;
    }

    @Override
    public Map<String, Object> queryCarts() {
        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        CartExample example = new CartExample();
        example.createCriteria().andUserIdEqualTo(principal.getId()).andDeletedEqualTo(false);
        List<Cart> carts = cartMapper.selectByExample(example);
        CartTotal cartTotal = new CartTotal();
        long total = queryTotalNumber(principal);
        CartExample example1 = new CartExample();
        example1.createCriteria().andUserIdEqualTo(principal.getId()).andCheckedEqualTo(true).andDeletedEqualTo(false);
        List<Cart> carts1 = cartMapper.selectByExample(example1);
        double goodsAmount = 0.0;
        double checkedGoodsAmount = 0.0;
        int checkedGoodsCount = 0;
        for (Cart cart : carts) {
            goodsAmount += cart.getPrice().doubleValue() * cart.getNumber();
        }
        for (Cart cart : carts1) {
            checkedGoodsAmount += cart.getPrice().doubleValue() * cart.getNumber();
            checkedGoodsCount += cart.getNumber();
        }
        cartTotal.setGoodsTotal((int) total);
        cartTotal.setCheckedGoodsCount(checkedGoodsCount);
        cartTotal.setGoodsAmount(goodsAmount);
        cartTotal.setCheckedGoodsAmount(checkedGoodsAmount);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("cartTotal", cartTotal);
        dataMap.put("cartList", carts);
        return dataMap;
    }

    @Override
    public int updateCheckeds(CartCheckedReq checkedReq) {
        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        List<Integer> productIds = checkedReq.getProductIds();
        int update = 0;
        for (Integer productId : productIds) {
            Cart cart = new Cart();
            cart.setUserId(principal.getId());
            cart.setProductId(productId);
            cart.setChecked(checkedReq.getIsChecked());
            update += cartMapper.updateByUser(cart);
        }
        return update;
    }

    @Override
    public int updateCart(int id, int number) {
        Cart record = new Cart();
        record.setId(id);
        record.setNumber((short) number);
        int update = cartMapper.updateById(record);
        return update;
    }

    @Override
    public int deleteById(List<Integer> productIds) {
        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        CartExample example = new CartExample();
        int delete = 0;
        for (Integer productId : productIds) {
            example.createCriteria().andUserIdEqualTo(principal.getId()).andProductIdEqualTo(productId).andDeletedEqualTo(false);
            delete += cartMapper.deleteByExample(example);
        }
        return delete;
    }

    @Override
    public int fastAddCart(User principal, GoodsAlter goods, GoodsProduct goodsProduct, int number) {
        Cart cart = new Cart();
        CartExample example = new CartExample();
        example.createCriteria().andUserIdEqualTo(principal.getId()).andProductIdEqualTo(goodsProduct.getId()).andDeletedEqualTo(false);
        List<Cart> carts = cartMapper.selectByExample(example);
        if (carts != null) {
            for (Cart cart1 : carts) {
                // 更新
                cart.setId(cart1.getId());
            }
        }
        cart.setNumber((short) number);
        cartMapper.updateById(cart);
        return cart.getId();
    }

    /**
     * {
     * "errno": 0,
     * "data": {
     * "grouponPrice": 0,
     * "grouponRulesId": 0,
     * "checkedAddress": {
     * "id": 94,
     * "name": "123",
     * "userId": 1,
     * "provinceId": 1,
     * "cityId": 32,
     * "areaId": 379,
     * "address": "123",
     * "mobile": "18888888888",
     * "isDefault": false,
     * "addTime": "2019-11-19 22:20:11",
     * "updateTime": "2019-11-20 07:51:07",
     * "deleted": false
     * },
     * "actualPrice": 4168.00,
     * "orderTotalPrice": 4168.00,
     * "couponPrice": 10.00,
     * "availableCouponLength": 1, // 是否过期
     * "couponId": 139,
     * "freightPrice": 0,
     * 运费 "checkedGoodsList": [{
     * "id": 1335,
     * "userId": 1,
     * "goodsId": 1181181,
     * "goodsSn": "2333",
     * "goodsName": "奥力给",
     * "productId": 753,
     * "price": 180.00,
     * "number": 1,
     * "specifications": ["标准"],
     * "checked": true,
     * "picUrl": "http://192.168.2.100:8081/wx/storage/fetch/kaqncpkrxjoohuiwx925.jpg",
     * "addTime": "2019-11-21 01:27:41",
     * "updateTime": "2019-11-21 01:51:39",
     * "deleted": false
     * }, {
     * "id": 1337,
     * "userId": 1,
     * "goodsId": 1181174,
     * "goodsSn": "124354303",
     * "goodsName": "男西装裤",
     * "productId": 756,
     * "price": 1999.00,
     * "number": 1,
     * "specifications": ["31"],
     * "checked": true,
     * "picUrl": "http://192.168.2.100:8081/wx/storage/fetch/9glgghlrfaolsz58jg9l.jpg",
     * "addTime": "2019-11-21 01:50:56",
     * "updateTime": "2019-11-21 01:51:39",
     * "deleted": false
     * }, {
     * "id": 1338,
     * "userId": 1,
     * "goodsId": 1181174,
     * "goodsSn": "124354303",
     * "goodsName": "男西装裤",
     * "productId": 757,
     * "price": 1999.00,
     * "number": 1,
     * "specifications": ["32"],
     * "checked": true,
     * "picUrl": "http://192.168.2.100:8081/wx/storage/fetch/9glgghlrfaolsz58jg9l.jpg",
     * "addTime": "2019-11-21 01:51:33",
     * "updateTime": "2019-11-21 01:51:39",
     * "deleted": false
     * }],
     * "goodsTotalPrice": 4178.00,
     * "addressId": 94* 	},
     * "errmsg": "成功"
     * }
     *
     * @param cartCheckoutReq
     * @return
     */
    @Override
    public Map<String, Object> checkoutOrder(CartCheckoutReq cartCheckoutReq) {
        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        CartExample cartExample = new CartExample();
        cartExample.createCriteria().andUserIdEqualTo(principal.getId()).andCheckedEqualTo(true).andDeletedEqualTo(false);
        List<Cart> carts = cartMapper.selectByExample(cartExample);

        // 团购
        int grouponRulesId = cartCheckoutReq.getGrouponRulesId();
        double grouponAmount = 0.0;
        GrouponRules grouponRules = new GrouponRules();
        if (grouponRulesId != 0) {
            grouponRules = grouponRulesMapper.queryGrouponRulesById(grouponRulesId);
            grouponAmount = grouponRules.getDiscount().doubleValue();
        }

        // 选中商品金额
        double goodsAmount = 0.0;
        for (Cart cart : carts) {
            // 商品最初价格
            goodsAmount += cart.getPrice().doubleValue() * cart.getNumber();
            //  团购优惠价格
            if (cart.getGoodsId() == grouponRules.getGoodsId()) {
                goodsAmount = grouponRules.getDiscount().doubleValue() * cart.getNumber();
            }
        }

        //  邮费
        double postage = 0.0;
        int addressId = cartCheckoutReq.getAddressId();
        if (addressId == 0) {
            AddressExample example = new AddressExample();
            example.createCriteria().andUserIdEqualTo(principal.getId()).andIsDefaultEqualTo(true);
            List<Address> addresses = addressMapper.selectByExample(example);
            addressId = addresses.get(0).getId();
        }
        Address address = addressMapper.selectByPrimaryKey(addressId);
        if (goodsAmount < 88) {
            int ProvinceId = address.getProvinceId();
            if (ProvinceId == 5 || ProvinceId == 26 || ProvinceId == 31) {
                //  偏远地区
                postage = 30.0;
            } else {
                // 其他地区
                postage = 10.0;
            }
        }

        // 优惠券
        int couponId = cartCheckoutReq.getCouponId();
        Coupon coupon = couponMapper.queryCouponById(couponId);
        double couponAmount = 0.0;
        if (couponId > 0 && coupon.getStatus() == 0) {
            couponAmount = coupon.getDiscount().doubleValue();
        }

        // 订单费用
        double orderTotalPrice = goodsAmount + postage - couponAmount;
        // 最终实际费用
        double actualPrice = orderTotalPrice - grouponAmount;

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("grouponPrice", grouponAmount);
        resultMap.put("grouponRulesId", grouponRulesId);
        resultMap.put("checkedAddress", address);
        resultMap.put("actualPrice", actualPrice);
        resultMap.put("orderTotalPrice", orderTotalPrice);
        resultMap.put("couponPrice", couponAmount);
        if (couponId > 0 && coupon.getStatus() == 0) {
            resultMap.put("availableCouponLength", coupon.getStatus());
        }
        resultMap.put("couponId", couponId);
        resultMap.put("freightPrice", postage);
        resultMap.put("checkedGoodsList", carts);
        resultMap.put("goodsTotalPrice", goodsAmount);
        resultMap.put("addressId", addressId);

        return resultMap;
    }

}
