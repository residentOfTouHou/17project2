package com.cskaoyan.mall.service;


import com.cskaoyan.mall.bean.generator.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRoles();

    List<Role> getRolesOrderBy(String sort, String order, String name);

    int insertRole(Role role);

    int updateRole(Role role);

    int deleteRole(Role role);

    List<String> getPermissionByRoleId(int roleId);

    int insertPermissionByRoleId(int roleId, String permission);

    int deletePermissionByRoleId(Integer roleId);
}
