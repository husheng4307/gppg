package com.gppg.gppg.common.shiro;

import com.gppg.gppg.common.entity.BackUserDomain;
import com.gppg.gppg.common.service.BackUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WXBackUserRealm  extends AuthorizingRealm {

    @Autowired
    private BackUserService backUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行微信后台授权逻辑");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行微信后台认证逻辑");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;

        BackUserDomain hdyh = backUserService.getUserByAccount(usernamePasswordToken.getUsername());
        System.out.println(hdyh.toString());
        if(hdyh == null){
            return null;
        }
        return new SimpleAuthenticationInfo(hdyh,hdyh.getPassword(),getName());
    }
}
