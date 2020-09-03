package com.gppg.gppg.common.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
* @Dessciption: Shiro登录token，重写支持登录多个realm里的token
* @author: husheng
* @date: 2020/9/3 16:43
*/
public class UserToken extends UsernamePasswordToken {
    private String loginUser;

    public UserToken(final String username, final String password, String loginUser){
        super(username,password);
        this.loginUser = loginUser;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }
}
