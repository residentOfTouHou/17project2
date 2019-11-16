package com.cskaoyan.mall.bean.jsonbean;

import com.cskaoyan.mall.bean.generator.User;
import lombok.Data;

import java.util.List;

@Data
public class AllUserData {
    private Long total;

    private List<User> items;
}
