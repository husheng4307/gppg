package com.gppg.gppg.common.shiro;

import com.gppg.gppg.common.entity.FrontUserDomain;
import com.gppg.gppg.common.service.FrontUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class WXFrontUserRealm extends AuthorizingRealm {
    @Autowired
    private FrontUserService frontUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行微信前台授权逻辑");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行微信前台认证逻辑");
        UserToken usernamePasswordToken = (UserToken) authenticationToken;
        FrontUserDomain qdyh = frontUserService.getUserByAccount(usernamePasswordToken.getUsername());
        if(qdyh == null){
            return null;
        }
        return new SimpleAuthenticationInfo(qdyh,qdyh.getPassword(),getName());
    }
}
