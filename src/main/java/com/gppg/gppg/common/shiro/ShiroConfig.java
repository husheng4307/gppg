package com.gppg.gppg.common.shiro;


import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * 创建factoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> filterMap = new HashMap<>();
        filterMap.put("/back/js/**","anon");
        filterMap.put("/back/css/**","anon");
        filterMap.put("/back/img/**","anon");
        filterMap.put("/back/hdyh/login","anon");
        filterMap.put("/back/hdyh/bindHdyh","anon");
        filterMap.put("/back/hdyh/wxLogin","anon");
 //       filterMap.put("/back/index.html","perms[user:superadmin]");
        filterMap.put("/back/**","authc");
        shiroFilterFactoryBean.setLoginUrl("/back/login.html");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }
    /**
     * 创建 securityManager
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setAuthenticator(modularRealmAuthenticator());
        defaultWebSecurityManager.setAuthorizer(modularRealmAuthorizer());
        defaultWebSecurityManager.setCacheManager(redisCacheManager());
        defaultWebSecurityManager.setSessionManager(sessionManager());
        List<Realm> realms = new ArrayList<>();
        realms.add(getBackUserRealm());
        realms.add(getFrontUserRealm());
        realms.add(getWXBackUserRealm());
        realms.add(getWXFrontUserRealm());
        defaultWebSecurityManager.setRealms(realms);
        return defaultWebSecurityManager;
    }
    @Bean(name = "frontUserRealm")
    public WebFrontUserRealm getFrontUserRealm(){
        WebFrontUserRealm frontUserRealm = new WebFrontUserRealm();
        frontUserRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return frontUserRealm;
    }

    @Bean(name = "wxfrontUserRealm")
    public WXFrontUserRealm getWXFrontUserRealm(){
        WXFrontUserRealm wxfrontUserRealm = new WXFrontUserRealm();
        return wxfrontUserRealm;
    }

    @Bean(name = "wxbackUserRealm")
    public WXBackUserRealm getWXBackUserRealm(){
        WXBackUserRealm wxbackUserRealm = new WXBackUserRealm();
        return wxbackUserRealm;
    }

    @Bean(name = "sessionManager")
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        // 设置session过期时间一天
        sessionManager.setSessionDAO(redisSessionDAO());
        sessionManager.setGlobalSessionTimeout(86400000L);
        /*sessionManager.setSessionIdCookieEnabled(true);
        SimpleCookie cookie = new SimpleCookie("SHIROJSESSIONID");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(1000*60*60*24);
        sessionManager.setSessionIdCookie(cookie);*/
        return sessionManager;
    }

    @Bean(name = "backUserRealm")
    public WebBackUserRealm getBackUserRealm(){
        WebBackUserRealm backUserRealm = new WebBackUserRealm();
        backUserRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return backUserRealm;
    }

    /**
     * 多Realm
     * 配置使用重写的ModularRealmAuthenticator
     */
    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator(){
        UserModularRealmAuthenticator modularRealmAuthenticator = new UserModularRealmAuthenticator();
        //登陆成功的匹配策略
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return modularRealmAuthenticator;
    }
    /**
     * 系统自带的Realm管理，主要针对多realm 授权
     */
    @Bean
    public ModularRealmAuthorizer modularRealmAuthorizer() {
        //自己重写的ModularRealmAuthorizer
        UserModularRealmAuthorizer modularRealmAuthorizer = new UserModularRealmAuthorizer();
        return modularRealmAuthorizer;
    }


    /**
     * 配置密码比较器
     */
    @Bean("credentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();


        //如果密码加密,可以打开下面配置
        //加密算法的名称
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        //配置加密的次数
        hashedCredentialsMatcher.setHashIterations(2);
        //是否存储为16进制
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);

        return hashedCredentialsMatcher;
    }


    /**
     * 整合redis
     */

  //  @Bean
    public RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("119.45.222.202");
        redisManager.setPort(6379);
        redisManager.setExpire(86400);
        redisManager.setPassword("redispassword");
        redisManager.setTimeout(1000);
        return  redisManager;
   }
//
//  //  @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }
//
// //  @Bean
   public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
   }
}
