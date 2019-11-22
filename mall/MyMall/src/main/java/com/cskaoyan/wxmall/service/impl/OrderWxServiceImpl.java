package com.cskaoyan.wxmall.service.impl;

import com.cskaoyan.mall.bean.generator.*;
import com.cskaoyan.mall.bean.generator.popularizeModule.Coupon;
import com.cskaoyan.mall.bean.generator.popularizeModule.Groupon;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.mapper.popularizeModuleMapper.CouponMapper;
import com.cskaoyan.mall.mapper.popularizeModuleMapper.GrouponMapper;
import com.cskaoyan.mall.mapper.popularizeModuleMapper.GrouponRulesMapper;
import com.cskaoyan.wxmall.bean.ListOrderBean;
import com.cskaoyan.wxmall.bean.OrderInfoBean;
import com.cskaoyan.wxmall.bean.SubmitOrderBean;
import com.cskaoyan.wxmall.service.OrderWxService;
import com.cskaoyan.wxmall.utils.OrderWxUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA
 *
 * @author zhanghj
 * @date 2019/11/19
 * @time 23:01
 */
@Service
public class OrderWxServiceImpl implements OrderWxService {

    @Autowired
    CartMapper cartMapper;

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    CouponMapper couponMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    GrouponMapper grouponMapper;

    @Autowired
    OrderGoodsMapper orderGoodsMapper;

    @Autowired
    GrouponRulesMapper grouponRulesMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    CommentMapper commentMapper;

    /**
     * 提交订单
     * <p>
     * order_sn不清楚随机生成
     * <p>
     * <p>
     * 积分减免没有找到
     * good_price 取cart中的 price （暂视为单价）
     *
     * @param orderBean
     * @return
     */
    @Override
    public Integer submitOrder(SubmitOrderBean orderBean) {
        Integer cartId = orderBean.getCartId();
        Integer addressId = orderBean.getAddressId();
        Integer couponId = orderBean.getCouponId();
        Cart cart = cartMapper.selectByPrimaryKey(cartId);
        Address address = addressMapper.selectByPrimaryKey(addressId);

        Date date = new Date();
        String format = new SimpleDateFormat("yyyyMMdd").format(date);

        //取出该次下单 购物车中所有商品的信息 并计算总价和数量
        Integer userId = cart.getUserId();
        Boolean checked = cart.getChecked();
        CartExample cartExample = new CartExample();
        cartExample.createCriteria().andUserIdEqualTo(userId).andCheckedEqualTo(checked).andDeletedEqualTo(false);
        List<Cart> carts = cartMapper.selectByExample(cartExample);
        //购物车内商品总价
        BigDecimal money = new BigDecimal(0);
        BigDecimal number = new BigDecimal(0);
        for (Cart compute : carts) {
            number = new BigDecimal(compute.getNumber());
            money.add(number.multiply(compute.getPrice()));
        }
        //新建订单
        Order record = new Order();
        record.setUserId(cart.getUserId());
        record.setMessage(orderBean.getMessage());
        record.setOrderSn(format + UUID.randomUUID());
        record.setOrderStatus((short) 101);
        record.setConsignee(address.getName());
        record.setMobile(address.getMobile());
        record.setAddress(address.getAddress());
        if (orderBean.getMessage() != null) {
            record.setMessage(orderBean.getMessage());
        } else {
            record.setMessage("");
        }
        record.setGoodsPrice(money);

        //不满88加运费
        if (money.doubleValue() < 88) {
            record.setFreightPrice(new BigDecimal(6)); // 缺少
        } else {
            record.setFreightPrice(new BigDecimal(0));
        }

        //计算优惠券
        if (couponId != null) {
            Coupon coupon = couponMapper.queryCouponById(couponId);
            record.setCouponPrice(coupon.getDiscount());
        } else {
            record.setCouponPrice(new BigDecimal(0));
        }

        //缺少用户积分
        record.setIntegralPrice(new BigDecimal(0));

        //是否团购优惠
        Integer grouponRulesId = orderBean.getGrouponRulesId();
        if (grouponRulesId > 0) {
            BigDecimal discount = grouponRulesMapper.selectGrouponRulesByRuleId(orderBean.getGrouponRulesId()).getDiscount();
            record.setGrouponPrice(discount);
        } else {
            record.setGrouponPrice(new BigDecimal(0));
        }

        BigDecimal subtract = money.add(record.getFreightPrice()).subtract(record.getCouponPrice());
        record.setOrderPrice(subtract);

        BigDecimal actual = subtract.subtract(record.getIntegralPrice());
        record.setActualPrice(actual); //缺少用户积分

        record.setPayId(String.valueOf(cartId)); //缺少微信付款id
        //缺少付款时间

        //商品数量
        record.setComments(number.shortValue());
        record.setAddTime(date);
        record.setUpdateTime(date);
        record.setDeleted(false);

        orderMapper.insertSelective(record);
        Integer orderId = orderMapper.getLastInsertId();

        //把所有购物车内商品转移到 order_goods
        for (Cart transfer : carts) {
            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setOrderId(orderId);
            orderGoods.setGoodsId(transfer.getGoodsId());
            orderGoods.setGoodsName(transfer.getGoodsName());
            orderGoods.setGoodsSn(transfer.getGoodsSn());
            orderGoods.setProductId(transfer.getProductId());
            orderGoods.setNumber(transfer.getNumber());
            orderGoods.setPrice(transfer.getPrice());
            orderGoods.setSpecifications(transfer.getSpecifications());
            orderGoods.setPicUrl(transfer.getPicUrl());
            orderGoods.setComment(0);
            Date orderGoodsDate = new Date();
            orderGoods.setAddTime(orderGoodsDate);
            orderGoods.setUpdateTime(orderGoodsDate);
            orderGoods.setDeleted(false);
            orderGoodsMapper.insertSelective(orderGoods);
            //逻辑删除购物车
            transfer.setDeleted(true);
        }
        return orderId;
    }


    /**
     * 订单列表
     *
     * @param showType
     * @param page
     * @param size
     * @return
     */
    @Override
    public Map<String, Object> listOrder(Integer showType, Integer page, Integer size) {
        List<ListOrderBean> result = new ArrayList<>();
        PageHelper.startPage(page, size);
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andDeletedEqualTo(false).andCommentsNotEqualTo((short) 0);
        if (showType != 0) {
            int code = OrderWxUtils.typeToStatusCode(showType);
            if (code != 401) {
                criteria.andOrderStatusEqualTo((short) code);
            } else {
                ArrayList<Short> values = new ArrayList<>();
                values.add((short) 401);
                values.add((short) 402);
                criteria.andOrderStatusIn(values);
            }
        }
        List<Order> orders = orderMapper.selectByExample(orderExample);
        for (Order order : orders) {
            ListOrderBean resultBean = new ListOrderBean();
            String statusText = OrderWxUtils.getStatusText(Integer.valueOf(order.getOrderStatus()));
            resultBean.setOrderStatusTest(statusText);
            Groupon token = grouponMapper.hasOrder(order.getId());
            if (token == null) {
                resultBean.setGroupin(false);
            } else {
                resultBean.setGroupin(true);
            }
            resultBean.setOrderSn(order.getOrderSn());
            resultBean.setActuralPrice(order.getActualPrice());
            //goodsList
            OrderGoodsExample orderGoodsExample = new OrderGoodsExample();
            orderGoodsExample.createCriteria().andOrderIdEqualTo(order.getId());
            List<OrderGoods> orderGoods = orderGoodsMapper.selectByExample(orderGoodsExample);
            resultBean.setGoodsList(orderGoods);
            resultBean.setId(order.getId());
            //handleOption
            Map<String, Boolean> handleOption = OrderWxUtils.handleOption(order);
            resultBean.setHandleOption(handleOption);
            result.add(resultBean);
        }
        PageInfo<Order> orderPageInfo = new PageInfo<>(orders);
        long count = orderPageInfo.getTotal();
        int totalPages = (int) Math.ceil(count / size);
        Map<String, Object> map = new HashMap<>();
        map.put("data", result);
        map.put("count", count);
        map.put("totalPages", totalPages);
        return map;
    }

    /**
     * 获取订单详情
     *
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> detailOrder(Integer id) {
        Map<String, Object> result = new HashMap<>();

        //orderInfo
        OrderInfoBean orderInfo = new OrderInfoBean();
        Order order = orderMapper.selectByPrimaryKey(id);
        User user = userMapper.selectByPrimaryKey(order.getUserId());
        orderInfo.setConsignee(user.getUsername());
        orderInfo.setAddress(order.getAddress());
        orderInfo.setAddTime(order.getAddTime());
        orderInfo.setOrderSn(order.getOrderSn());
        orderInfo.setActualPrice(order.getActualPrice());
        orderInfo.setMobile(user.getMobile());
        orderInfo.setOrderStatusTest(OrderWxUtils.getStatusText(Integer.valueOf(order.getOrderStatus())));
        orderInfo.setGoodsPrice(order.getGoodsPrice());
        orderInfo.setCouponPrice(order.getCouponPrice());
        orderInfo.setId(order.getId());
        orderInfo.setFreightPrice(order.getFreightPrice());
        orderInfo.setHandleOption(OrderWxUtils.handleOption(order));

        //orderGoods
        OrderGoodsExample orderGoodsExample = new OrderGoodsExample();
        orderGoodsExample.createCriteria().andDeletedEqualTo(false).andOrderIdEqualTo(order.getId());
        List<OrderGoods> orderGoods = orderGoodsMapper.selectByExample(orderGoodsExample);

        result.put("orderInfo", orderInfo);
        result.put("orderGoods", orderGoods);
        return result;
    }

    /**
     * 取消订单
     *
     * @param orderId
     */
    @Override
    public void cancelOrder(Integer orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setOrderStatus((short) 102);
        orderMapper.updateByPrimaryKeySelective(order);

        OrderGoods orderGoods = new OrderGoods();
        orderGoods.setDeleted(true);
        OrderGoodsExample orderGoodsExample = new OrderGoodsExample();
        orderGoodsExample.createCriteria().andOrderIdEqualTo(orderId);
        orderGoodsMapper.updateByExampleSelective(orderGoods, orderGoodsExample);
    }

    @Override
    public int getOrderNumber() {
        return orderMapper.selectByExample(new OrderExample()).size();
    }

    /**
     * 退款取消订单
     *
     * @param orderId
     */
    @Override
    public void refundOrder(Integer orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setOrderStatus((short) 202);
        orderMapper.updateByPrimaryKeySelective(order);

        OrderGoods orderGoods = new OrderGoods();
        orderGoods.setDeleted(true);
        OrderGoodsExample orderGoodsExample = new OrderGoodsExample();
        orderGoodsExample.createCriteria().andOrderIdEqualTo(orderId);
        orderGoodsMapper.updateByExampleSelective(orderGoods, orderGoodsExample);
    }

    /**
     * 删除订单
     *
     * @param orderId
     */
    @Override
    public void deleteOrder(Integer orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setDeleted(true);
        orderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 确认订单
     *
     * @param orderId
     */
    @Override
    public void confirmOrder(Integer orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setOrderStatus((short) 401);
        orderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 订单付款
     *
     * @param orderId
     */
    @Override
    public void prepayOrder(Integer orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setOrderStatus((short) 201);
        orderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 获得订单商品
     *
     * @param orderId
     * @param goodsId
     */
    @Override
    public OrderGoods goodsOrder(Integer orderId, Integer goodsId) {
        OrderGoodsExample example = new OrderGoodsExample();
        example.createCriteria().andOrderIdEqualTo(orderId).andGoodsIdEqualTo(goodsId);
        List<OrderGoods> orderGoods = orderGoodsMapper.selectByExample(example);
        OrderGoods result = null;
        if (orderGoods != null && orderGoods.size() > 0) {
            result = orderGoods.get(0);
        }
        return result;
    }

    /**
     * 评论订单
     *
     * @param orderGoodsId
     * @param comment
     */
    @Override
    public void commentOrder(Integer orderGoodsId, Comment comment) {
        OrderGoods orderGoods = orderGoodsMapper.selectByPrimaryKey(orderGoodsId);
        Order order = new Order();
        order.setId(orderGoods.getOrderId());
        order.setComments((short) 0);
        orderMapper.updateByPrimaryKeySelective(order);
        comment.setValueId(0);
        comment.setType((byte) 3);
        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        comment.setUserId(principal.getId());
        Date date = new Date();
        comment.setAddTime(date);
        comment.setUpdateTime(date);
        comment.setDeleted(false);
        commentMapper.insertSelective(comment);
    }
}
