package com.gppg.gppg.common.service;//package site.beanyon.apt.common.service;
//
//import com.baomidou.mybatisplus.extension.service.IService;
//import site.beanyon.apt.common.entity.VO.TokenDTO;
//import site.beanyon.apt.common.entity.WxyhDomain;
//import site.beanyon.apt.common.entity.response.HttpResponse;
//
///**
// * @Created by husheng
// * @on 19-11-7 下午12:26
// * @Version 1.0
// */
//public interface WxyhService extends IService<WxyhDomain> {
//
//
//    /**
//     * 微信小程序用户登录，完整流程可参考下面官方地址，本例中是按此流程开发
//     * https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/login.html
//     * 1 . 我们的微信小程序端传入code。
//     * 2 . 调用微信code2session接口获取openid和session_key
//     * 3 . 根据openid和session_key自定义登录态(TokenDTO)
//     * 4 . 返回自定义登录态(TokenDTO)给小程序端。
//     * 5 . 我们的小程序端调用其他需要认证的api，请在header的Authorization里面携带 token信息
//     *
//     *
//     * @param response
//     * @param code 小程序端 调用 wx.login 获取到的code,用于调用 微信code2session接口
//     * @param rawData
//     * @return TokenDTO 返回后端 自定义登录态 token  基于JWT实现
//     */
//    public HttpResponse wxUserLogin(HttpResponse response, String code, String rawData);
//
//
//}
