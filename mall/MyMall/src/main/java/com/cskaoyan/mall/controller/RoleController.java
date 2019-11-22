/**
 *
 */
package com.cskaoyan.mall.controller;


import com.cskaoyan.mall.bean.generator.Role;
import com.cskaoyan.mall.bean.generator.SystemPermission;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.bean.jsonbean.reqVo.PermissionReqVo;
import com.cskaoyan.mall.bean.jsonbean.reqVo.RoleReqVo;
import com.cskaoyan.mall.bean.jsonbean.reqVo.RoleResVo;
import com.cskaoyan.mall.service.RoleService;
import com.cskaoyan.mall.service.SystemPermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RequestMapping("/admin/role")
@RestController
public class RoleController {

    @Autowired
    RoleService roleService;

    @Autowired
    SystemPermissionService systemPermissionService;

    @RequestMapping("options")
    public BaseReqVo getRoles(){

        List<Role> roles = roleService.getRoles();
        ArrayList<RoleResVo> roleResVos = new ArrayList<>();
        for (Role role : roles) {
            roleResVos.add(new RoleResVo(role.getId(),role.getName()));
        }

        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setData(roleResVos);
        baseReqVo.setErrmsg("success");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    @RequestMapping("list")
    public BaseReqVo roleList(@Param("page")int page, @Param("limit")int limit,
                              @Param("sort")String sort, @Param("order")String order,
                              String name){
        PageHelper.startPage(page, limit);
        List<Role> roles = roleService.getRolesOrderBy(sort,order,name);
        PageInfo<Role> rolePageInfo = new PageInfo<>(roles);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",rolePageInfo.getTotal());
        map.put("items",rolePageInfo.getList());
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setData(map);
        baseReqVo.setErrmsg("success");
        baseReqVo.setErrno(0);
        return baseReqVo;

    }

    @RequestMapping("create")
    public BaseReqVo create(@RequestBody RoleReqVo roleReqVo){
        Date date = new Date();
        Role role = new Role();
        role.setAddTime(date);
        role.setDeleted(false);
        role.setDesc(roleReqVo.getDesc());
        role.setEnabled(true);
        role.setUpdateTime(date);
        role.setName(roleReqVo.getName());
        int insert = roleService.insertRole(role);
        BaseReqVo baseReqVo = new BaseReqVo();
        if(insert == 1){
            baseReqVo.setErrmsg("创建角色成功");
            baseReqVo.setErrno(0);
            baseReqVo.setData(role);
            return baseReqVo;
        }else {
            baseReqVo.setErrno(502);
            baseReqVo.setErrmsg("创建失败");
            return baseReqVo;
        }
    }

    @RequestMapping("update")
    public BaseReqVo update(@RequestBody Role role){
        role.setUpdateTime(new Date());
        int update = roleService.updateRole(role);
        BaseReqVo baseReqVo = new BaseReqVo();
        if(update == 1){
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("success");
        }else{
            baseReqVo.setErrno(502);
            baseReqVo.setErrmsg("failed");
        }
        return baseReqVo;
    }

    @RequestMapping("delete")
    public BaseReqVo delete(@RequestBody Role role){
        int delete = roleService.deleteRole(role);
        BaseReqVo baseReqVo = new BaseReqVo();
        if(delete == 1){
            baseReqVo.setErrmsg("success");
            baseReqVo.setErrno(0);
        }else {
            baseReqVo.setErrno(500);
            baseReqVo.setErrmsg("failed");
        }
        return baseReqVo;
    }

    @RequestMapping(value = "permissions", method = RequestMethod.GET)
    public BaseReqVo permission(@Param("roleId")int roleId){
        List<String> permissions = roleService.getPermissionByRoleId(roleId);
        BaseReqVo baseReqVo = new BaseReqVo();
        HashMap<String, Object> map = new HashMap<>();
        map.put("assignedPermissions",permissions);
        //获取全部permission
        List<SystemPermission> systemPermissions = systemPermissionService.getAllPermissions();
        map.put("systemPermissions",systemPermissions);
        baseReqVo.setData(map);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    @RequestMapping(value = "permissions" ,method = RequestMethod.POST)
    @Transactional
    public BaseReqVo permission2(@RequestBody PermissionReqVo permissionReqVo){
        BaseReqVo baseReqVo = new BaseReqVo();
        //更新permission
        roleService.deletePermissionByRoleId(permissionReqVo.getRoleId());
        if(permissionReqVo.getPermissions() != null) {
            for (String permission : permissionReqVo.getPermissions()) {
                if (roleService.insertPermissionByRoleId(permissionReqVo.getRoleId(), permission) != 1) {
                    baseReqVo.setErrmsg("failed");
                    baseReqVo.setErrno(500);
                    return baseReqVo;
                }
            }
        }
        //更新role表
        Role role = new Role();
        role.setId(permissionReqVo.getRoleId());
        role.setUpdateTime(new Date());
        roleService.updateRole(role);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }
}
