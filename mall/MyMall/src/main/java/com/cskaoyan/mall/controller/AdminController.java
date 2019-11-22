/**
 *
 */
package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.generator.Admin;
import com.cskaoyan.mall.bean.jsonbean.BaseReqVo;
import com.cskaoyan.mall.bean.jsonbean.reqVo.AdminReqVo;
import com.cskaoyan.mall.service.AdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/admin/admin")
public class AdminController {

    @Autowired
    AdminService adminService;



    @RequestMapping("list")
    @RequiresPermissions("admin:admin:list")
    public BaseReqVo adminList(@Param("page")int page, @Param("limit")int limit,
                               @Param("sort")String sort, @Param("order")String order,
                               String username){
        PageHelper.startPage(page, limit);
        List<Admin> admins = adminService.getAllAdminOderBy(sort,order,username);
//        List<AdminResVo> adminResVos = new ArrayList<>();
//        for (Admin admin : admins) {
//            AdminResVo adminResVo = new AdminResVo(admin.getId(), admin.getUsername(), admin.getPassword(), admin.getLastLoginIp(), admin.getLastLoginTime(),
//                    admin.getAvatar(), admin.getAddTime(), admin.getUpdateTime(), admin.getDeleted(), );
//            adminResVos.add(adminResVo);
//        }
        PageInfo<Admin> adminPageInfo = new PageInfo<>(admins);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",adminPageInfo.getTotal());
        map.put("items",adminPageInfo.getList());
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setData(map);
        baseReqVo.setErrmsg("success");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }


    @RequestMapping("create")
    @RequiresPermissions("admin:admin:create")
    public BaseReqVo create(@RequestBody AdminReqVo adminReqVo){
        BaseReqVo baseReqVo = new BaseReqVo();

        Date date = new Date();
        String username = adminReqVo.getUsername();
        String password = adminReqVo.getPassword();
        //验证用户名与密码格式
        if(username.length() < 6 || password.length() < 6){
            baseReqVo.setErrno(500);
            baseReqVo.setErrmsg("用户名或密码长度不够");
            return baseReqVo;
        }

        Admin admin = new Admin();
        admin.setRoleIds(adminReqVo.getRoleIds());
        admin.setAvatar(adminReqVo.getAvatar());
        admin.setUsername(adminReqVo.getUsername());
        admin.setPassword(adminReqVo.getPassword());
        admin.setAddTime(date);
        admin.setUpdateTime(date);
        admin.setDeleted(false);
        int insert = adminService.addAdmin(admin);
        if(insert == 1){
            baseReqVo.setData(admin);
            baseReqVo.setErrmsg("success");
            baseReqVo.setErrno(0);
        }else{
            baseReqVo.setErrmsg("failed");
            baseReqVo.setErrno(500);
        }
        return baseReqVo;
    }

    @RequestMapping("update")
    @RequiresPermissions("admin:admin:update")
    public BaseReqVo updateAdmin(@RequestBody AdminReqVo adminReqVo){
        BaseReqVo baseReqVo = new BaseReqVo();
        String username = adminReqVo.getUsername();
        String password = adminReqVo.getPassword();
        //验证用户名与密码格式
        if(username.length() < 6 || password.length() < 6){
            baseReqVo.setErrno(500);
            baseReqVo.setErrmsg("用户名或密码长度不够");
            return baseReqVo;
        }
        Date date = new Date();
        Admin admin = new Admin();
        admin.setRoleIds(adminReqVo.getRoleIds());
        admin.setAvatar(adminReqVo.getAvatar());
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setId(adminReqVo.getId());
        admin.setUpdateTime(date);
        admin.setDeleted(false);
        int update = adminService.updateAdminById(admin);
        if(update == 1){
            baseReqVo.setErrno(0);
            baseReqVo.setData(adminReqVo);
            baseReqVo.setErrmsg("modify success");
        }else{
            baseReqVo.setErrno(502);
            baseReqVo.setErrmsg("modify failed");
        }
        return baseReqVo;
    }

    @RequestMapping("delete")
    @RequiresPermissions("admin:admin:delete")
    public BaseReqVo deleteAdmin(@RequestBody Admin admin, HttpServletRequest request){
        BaseReqVo baseReqVo = new BaseReqVo();
        int delete = adminService.deleteAdminById(admin.getId());
        if(delete == 1){
            //删除头像
            String avatar = admin.getAvatar();
            String head = "http://" + request.getServerName() //服务器地址
                    + ":"
                    + request.getServerPort()           //端口号
                    + request.getContextPath();
            String s = avatar.replaceAll(head, "");
            String realPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static";
            realPath += s;
            File file = new File(realPath);
            file.deleteOnExit();
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("success");
        }else{
            baseReqVo.setErrno(502);
            baseReqVo.setErrmsg("failed");
        }
        return baseReqVo;
    }


}
