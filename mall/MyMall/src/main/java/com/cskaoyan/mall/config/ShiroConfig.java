/**
 *
 */
package com.cskaoyan.mall.config;


import com.cskaoyan.mall.shiro.AdminRealm;
import com.cskaoyan.mall.shiro.CustomRealmAuthenticator;
import com.cskaoyan.mall.shiro.CustomSessionManager;
import com.cskaoyan.mall.shiro.WxRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig{

    /**
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //需要配置的成员变量 1、securityManager  2、loginUrl 重定向
        //3、filterChainDefinitionMap map为linkedmap 执行顺序
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //shiroFilterFactoryBean.setLoginUrl("");
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//        filterChainDefinitionMap.put("/wx/home/index","authc");
        filterChainDefinitionMap.put("/admin/auth/login","anon");
        //后台除了登录其余都需要认证
        //filterChainDefinitionMap.put("/wx/coupon/**","authc");
        filterChainDefinitionMap.put("/admin/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(AdminRealm adminRealm, WxRealm wxRealm,
                                                     CustomSessionManager sessionManager,
                                                     CustomRealmAuthenticator authenticator
                                                     ){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //1、realms 2、sessionmanager  3、authenticator
        ArrayList<Realm> realms = new ArrayList<>();
        realms.add(wxRealm);
        realms.add(adminRealm);
        defaultWebSecurityManager.setRealms(realms);

        defaultWebSecurityManager.setSessionManager(sessionManager);
        defaultWebSecurityManager.setAuthenticator(authenticator);
        return defaultWebSecurityManager;
    }

    /**声明式鉴权注册
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public CustomRealmAuthenticator customRealmAuthenticator(AdminRealm adminRealm,WxRealm wxRealm){
        CustomRealmAuthenticator customRealmAuthenticator = new CustomRealmAuthenticator();
        ArrayList<Realm> realms = new ArrayList<>();
        realms.add(wxRealm);
        realms.add(adminRealm);
        customRealmAuthenticator.setRealms(realms);
        return customRealmAuthenticator;
    }
}
