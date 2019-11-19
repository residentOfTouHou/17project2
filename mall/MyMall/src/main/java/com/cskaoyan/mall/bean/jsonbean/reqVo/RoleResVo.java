/**
 *
 */
package com.cskaoyan.mall.bean.jsonbean.reqVo;

import lombok.Data;

@Data
public class RoleResVo {
    private String label;

    private int value;

    public RoleResVo() {
    }

    public RoleResVo(int value, String label) {
        this.label = label;
        this.value = value;
    }
}
