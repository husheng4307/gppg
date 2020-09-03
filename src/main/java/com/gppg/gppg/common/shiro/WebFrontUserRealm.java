package com.gppg.gppg.common.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class WebFrontUserRealm extends AuthorizingRealm {


    @Autowired
    private IFrontUserService qdyhService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行前台授权逻辑");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行前台认证逻辑");
        UserToken usernamePasswordToken = (UserToken) authenticationToken;
        QDYH qdyh = qdyhService.getUserByAccount(usernamePasswordToken.getUsername());
        if(qdyh == null){
            return null;
        }
        return new SimpleAuthenticationInfo(qdyh,qdyh.getQDYH_MM(), ByteSource.Util.bytes(qdyh.getQDYH_YAN()),getName());
    }
}
