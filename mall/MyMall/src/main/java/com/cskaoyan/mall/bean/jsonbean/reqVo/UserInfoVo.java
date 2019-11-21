/**
 *
 */
package com.cskaoyan.mall.bean.jsonbean.reqVo;

import lombok.Data;

@Data
public class UserInfoVo {

    private String nickName;

    private String avatarUrl;

    private String username;

    private String password;

    private String code;

    private String wxCode;

    private String mobile;

    public UserInfoVo() {
    }

    public UserInfoVo(String nickName, String avatarUrl) {
        this.nickName = nickName;
        this.avatarUrl = avatarUrl;
    }
}
