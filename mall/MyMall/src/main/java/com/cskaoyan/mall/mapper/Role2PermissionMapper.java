package com.cskaoyan.mall.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Role2PermissionMapper {
    List<String> selectPermissionByRoleID(int roleId);

    int insert(@Param("roleId") int roleId, @Param("permission") String permission);

    int deleteByRoleId(Integer roleId);
}
