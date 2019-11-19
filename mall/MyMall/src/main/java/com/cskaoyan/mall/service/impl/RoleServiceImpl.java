/**
 *
 */
package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.generator.Role;
import com.cskaoyan.mall.bean.generator.RoleExample;
import com.cskaoyan.mall.mapper.Role2PermissionMapper;
import com.cskaoyan.mall.mapper.RoleMapper;
import com.cskaoyan.mall.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    Role2PermissionMapper role2PermissionMapper;

    @Override
    public List<Role> getRoles() {
        return roleMapper.selectAll();
    }

    @Override
    public List<Role> getRolesOrderBy(String sort, String order, String name) {
        RoleExample roleExample = new RoleExample();
        if(!"".equals(name) && name != null) {
            roleExample.createCriteria().andNameLike("%" + name + "%");
        }
        roleExample.setOrderByClause(sort + " " + order);
        return roleMapper.selectByExample(roleExample);
    }

    @Override
    public int insertRole(Role role) {
        return roleMapper.insert(role);
    }

    @Override
    public int updateRole(Role role) {
        return roleMapper.updateByPrimaryKeySelective(role);

    }

    @Override
    public int deleteRole(Role role) {
        return  roleMapper.deleteByPrimaryKey(role.getId());
    }

    @Override
    public List<String> getPermissionByRoleId(int roleId) {
        return role2PermissionMapper.selectPermissionByRoleID(roleId);
    }

    @Override
    public int insertPermissionByRoleId(int roleId, String permission) {
        return role2PermissionMapper.insert(roleId,permission);
    }

    @Override
    public int deletePermissionByRoleId(Integer roleId) {
        return role2PermissionMapper.deleteByRoleId(roleId);
    }
}
