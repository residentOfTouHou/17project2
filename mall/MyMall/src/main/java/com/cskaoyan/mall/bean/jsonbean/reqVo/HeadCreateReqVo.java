/**
 *
 */
package com.cskaoyan.mall.bean.jsonbean.reqVo;

import lombok.Data;

@Data
public class HeadCreateReqVo {
    private int id;

    private String key;

    private String name;

    private String type;

    private long size;

    private String url;

    private String addTime;

    private String updateTime;

}
