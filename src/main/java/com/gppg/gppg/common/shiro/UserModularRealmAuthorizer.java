package com.gppg.gppg.common.shiro;


import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Collection;


/**
 * 多realm授权管理
 */

public class UserModularRealmAuthorizer extends ModularRealmAuthorizer {
    @Override
    public boolean isPermitted(PrincipalCollection principals, String permission) {

        Collection<Realm> realmList = getRealms();
        for (Realm realm: realmList
             ) {
            if(realm.getName().contains("WebBack")){
                return ((WebBackUserRealm)realm).isPermitted(principals,permission);
            }
            if(realm.getName().contains("WebFront")){
                return ((WebFrontUserRealm)realm).isPermitted(principals,permission);
            }
            if(realm.getName().contains("WXFront")){
                return((WXFrontUserRealm)realm).isPermitted(principals,permission);
            }
            if(realm.getName().contains("WXBack")){
                return((WXBackUserRealm)realm).isPermitted(principals,permission);
            }

        }
        return super.isPermitted(principals, permission);
    }
}
