/**
 *
 */
package com.cskaoyan.mall.bean.jsonbean.reqVo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AdminResVo {
    private Integer id;

    private String username;

    private String password;

    private String lastLoginIp;

    private Date lastLoginTime;

    private String avatar;

    private Date addTime;

    private Date updateTime;

    private Boolean deleted;

    private List<Integer> roleIds;

    public AdminResVo() {
    }

    public AdminResVo(Integer id, String username, String password, String lastLoginIp, Date lastLoginTime, String avatar, Date addTime, Date updateTime, Boolean deleted, List<Integer> roleIds) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.lastLoginIp = lastLoginIp;
        this.lastLoginTime = lastLoginTime;
        this.avatar = avatar;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.deleted = deleted;
        this.roleIds = roleIds;
    }
}
