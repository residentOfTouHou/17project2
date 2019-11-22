/**
 *
 */
package com.cskaoyan.wxmall.bean;

import lombok.Data;

@Data
public class WxLoginReqVo {
    private String code;

    private UserInfo userInfo;
}
