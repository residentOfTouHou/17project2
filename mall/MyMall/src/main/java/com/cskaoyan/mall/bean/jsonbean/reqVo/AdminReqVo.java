/**
 *
 */
package com.cskaoyan.mall.bean.jsonbean.reqVo;

import lombok.Data;

import java.util.List;

@Data
public class AdminReqVo {
    private int id;

    private String username;

    private String password;

    private String avatar;

    private List<Integer> roleIds;
}
