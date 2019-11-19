/**
 *
 */
package com.cskaoyan.mall.bean.generator;

import lombok.Data;

import java.util.List;

@Data
public class SystemPermission {
    private String id;

    private String label;

    private String api;

    private String parentId;

    private List<SystemPermission> children;
}
