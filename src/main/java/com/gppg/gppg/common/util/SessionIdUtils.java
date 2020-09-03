package com.gppg.gppg.common.util;//package site.beanyon.apt.common.util;
//
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.subject.Subject;
//import org.springframework.beans.factory.annotation.Autowired;
//import site.beanyon.apt.common.entity.front.QDYH;
//import site.beanyon.apt.lhbb.entity.DzDomain;
//import site.beanyon.apt.lhbb.entity.SjDomain;
//import site.beanyon.apt.lhbb.service.DzService;
//import site.beanyon.apt.lhbb.service.SjService;
//
///**
// * @Created by husheng
// * @on 19-9-19 下午4:05
// * @Version 1.0
// */
//public class SessionIdUtils {
//
//    @Autowired
//    static
//    SjService sjService;
//
//    @Autowired
//    static
//    DzService dzService;
//
//    public static Object getQDYHTypeAndId(){
//        Subject subject = SecurityUtils.getSubject();
//        QDYH principal = (QDYH)subject.getPrincipal();
//        Integer TypeId = principal.getQDYH_LX();
//        Integer id = principal.getQDYH_LX_ID();
//
//        switch (TypeId){
//            case 1:
//                DzDomain dz = dzService.getDzById(id);
//                return dz;
//            case 2:
//                SjDomain sj = sjService.getSjById(id);
//                return sj;
//            default:
//                throw new IllegalStateException("Unexpected value: " + TypeId);
//        }
//    }
//}
