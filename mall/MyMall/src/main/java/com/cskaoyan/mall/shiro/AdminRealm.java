/**
 *
 */
package com.cskaoyan.mall.shiro;

import com.cskaoyan.mall.bean.generator.Admin;
import com.cskaoyan.mall.service.AdminService;
import com.cskaoyan.mall.service.RoleService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class AdminRealm extends AuthorizingRealm {
    @Autowired
    AdminService adminService;

    @Autowired
    RoleService roleService;
    /**认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        CustomToken token = (CustomToken) authenticationToken;
        String username = token.getUsername();
        Admin admin = adminService.getAdminByName(username);
        return new SimpleAuthenticationInfo(admin,admin.getPassword(),getName());
    }


    /**授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Admin admin = (Admin) principalCollection.getPrimaryPrincipal();
        List<Integer> roleIds = admin.getRoleIds();
        HashSet<String> permissions = new HashSet<>();
        for (Integer roleId : roleIds) {
            for (String s : roleService.getPermissionByRoleId(roleId)) {
                permissions.add(s);
            }
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }


}
