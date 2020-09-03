package com.gppg.gppg.common.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.Collection;


/**
 * @author JundaZhu
 * 配置shiro多realm
 */
public class UserModularRealmAuthenticator extends ModularRealmAuthenticator {

    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        UserToken userToken = (UserToken)authenticationToken;
        String loginUser = userToken.getLoginUser();
        Collection<Realm> realms = getRealms();
        Collection<Realm> typeRealm = new ArrayList<>();
        for (Realm realm: realms
             ) {
            if(realm.getName().contains(loginUser)){
                typeRealm.add(realm);
            }
        }
        if (typeRealm.size() == 1){

            return doSingleRealmAuthentication(((ArrayList<Realm>) typeRealm).get(0), userToken);

        }
        else{

           return doMultiRealmAuthentication(typeRealm, userToken);

        }
    }
}
