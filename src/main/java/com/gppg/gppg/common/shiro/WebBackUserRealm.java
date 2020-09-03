package com.gppg.gppg.common.shiro;

import com.gppg.gppg.common.entity.BackUserDomain;
import com.gppg.gppg.common.service.BackUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class WebBackUserRealm extends AuthorizingRealm {


    @Autowired
    private BackUserService backUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("执行后台授权逻辑");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行后台认证逻辑");
        String principal = (String)authenticationToken.getPrincipal();
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;

        BackUserDomain hdyh = backUserService.getUserByAccount(usernamePasswordToken.getUsername());

        if(hdyh == null){
            return null;
        }
        return new SimpleAuthenticationInfo(hdyh,hdyh.getPassword(),ByteSource.Util.bytes(hdyh.getSalt()),getName());
    }
}
