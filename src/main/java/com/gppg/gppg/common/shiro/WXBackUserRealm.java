package com.gppg.gppg.common.shiro;

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
    private IFrontUserService qdyhService;
    @Autowired
    private IBackUserService hdyhService;
    @Autowired
    private IBackRoleService backRoleService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行微信后台授权逻辑");
        SimpleAuthorizationInfo simpleAuthentizationInfo = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        HdyhDomain hdyh = (HdyhDomain)(subject.getPrincipal());
        List<HDJS> hdjsList = backRoleService.getRolesByUid(hdyh.getHdyhId());
        Set<String> bsSet = hdjsList.stream().map(HDJS::getHDJS_BS).collect(Collectors.toSet());
        System.out.println(bsSet.toString());
        simpleAuthentizationInfo.addStringPermissions(bsSet);
        return simpleAuthentizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行微信后台认证逻辑");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;

        HdyhDomain hdyh = hdyhService.getUserByAccount(usernamePasswordToken.getUsername());
        System.out.println(hdyh.toString());
        if(hdyh == null){
            return null;
        }
        return new SimpleAuthenticationInfo(hdyh,hdyh.getHdyhMm(),getName());
    }
}
