/**
 *
 */
package com.cskaoyan.mall.bean.jsonbean.reqVo;

import lombok.Data;

@Data
public class PermissionReqVo {
    private int roleId;

    private String[] permissions;
}
