/**
 *
 */
package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.generator.SystemPermission;
import com.cskaoyan.mall.mapper.SystemPermissionMapper;
import com.cskaoyan.mall.service.SystemPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemPermissionServiceImpl implements SystemPermissionService {

    @Autowired
    SystemPermissionMapper systemPermissionMapper;


    //总共三层数据 一对多对多  分解两次 一对多 一对多
    @Override
    public List<SystemPermission> getAllPermissions() {
        List<SystemPermission> systemPermissions = systemPermissionMapper.selectPermissionByParentId("system");
        for (SystemPermission systemPermission : systemPermissions) {
            systemPermission.setChildren(systemPermissionMapper.selectPermissionByParentId(systemPermission.getId()));
            for (SystemPermission child : systemPermission.getChildren()) {
                child.setChildren(systemPermissionMapper.selectPermissionByParentId(child.getId()));
            }
        }
        return systemPermissions;
    }
}
