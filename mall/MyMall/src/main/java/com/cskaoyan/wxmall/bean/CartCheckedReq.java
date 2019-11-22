package com.cskaoyan.wxmall.bean;

import lombok.Data;

import java.util.List;

/**
 * @author 杨盛
 * @date 2019/11/20 20:04
 */

@Data
public class CartCheckedReq {

    List<Integer> productIds;

    boolean isChecked;

    public List<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
    }

    public boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean checked) {
        isChecked = checked;
    }
}
