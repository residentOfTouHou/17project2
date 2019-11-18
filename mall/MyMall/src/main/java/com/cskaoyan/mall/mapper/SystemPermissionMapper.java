package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.generator.SystemPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemPermissionMapper {
    List<SystemPermission> selectPermissionByParentId(@Param("parentId") String parentId);
}
