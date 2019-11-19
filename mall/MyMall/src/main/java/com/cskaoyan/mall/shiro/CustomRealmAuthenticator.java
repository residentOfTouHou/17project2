/**
 *
 */
package com.cskaoyan.mall.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;


public class CustomRealmAuthenticator extends ModularRealmAuthenticator {
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
         this.assertRealmsConfigured();
        Collection<Realm> originalRealms = this.getRealms();
        CustomToken token = (CustomToken) authenticationToken;
        String type = token.getType();
        ArrayList<Realm> realms = new ArrayList<>();
        for (Realm originalRealm : originalRealms) {
            if(originalRealm.getName().toLowerCase().contains(type)){
                realms.add(originalRealm);
            }
        }
        return realms.size() == 1 ? this.doSingleRealmAuthentication((Realm)realms.iterator().next(), authenticationToken) : this.doMultiRealmAuthentication(realms, authenticationToken);
    }
}