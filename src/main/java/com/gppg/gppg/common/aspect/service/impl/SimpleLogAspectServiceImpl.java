package com.gppg.gppg.common.aspect.service.impl;//package site.beanyon.apt.common.aspect.service.impl;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//import site.beanyon.apt.common.aspect.service.IAspectService;
//
///**
// * 日志切面服务，实现日志切面所需要的业务功能
// *
// * @author BeanYon
// * 2019.07.24
// */
//@Service
//public class SimpleLogAspectServiceImpl implements IAspectService {
//    /**
//     * 获取日志工具实例
//     */
//    private Logger logger = LoggerFactory.getLogger(SimpleLogAspectServiceImpl.class);
//
//    /**
//     * 日志切面的前置处理
//     *
//     * @param process 切点实例
//     */
//    @Override
//    public void preExecute(ProceedingJoinPoint process) {
//        logger.info("切面方法开始执行...");
//
//        // 获取被切入的类名和方法名
//        MethodSignature methodSignature = (MethodSignature) process.getSignature();
//        Class declaringType = methodSignature.getDeclaringType();
//        String className = declaringType.getName();
//        String methodName = methodSignature.getMethod().getName();
//
//        // 获取被切入的方法的参数
//        Object[] args = process.getArgs();
//        String argsStr = "";
//        for (Object obj : args) {
//            argsStr += obj.toString() + ",";
//        }
//
//        logger.info("Class: " + className + ",method: " + methodName + ",args: " + argsStr);
//    }
//
//    /**
//     * 后置处理
//     *
//     * @param result 被切入方法的执行结果
//     */
//    @Override
//    public void postExecute(Object result) {
//        if (result != null) {
//            logger.info("result: " + result.toString());
//        }
//        logger.info("切面方法执行完成...");
//    }
//
//    /**
//     * 异常处理
//     *
//     * @param throwable 异常实例
//     */
//    @Override
//    public void exceptionExecute(Throwable throwable) {
////        throwable.printStackTrace();
//        logger.error("异常...");
//    }
//}
