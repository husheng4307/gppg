package com.gppg.gppg.common.handler;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 异步任务全局异常处理器
 *
 * @author BeanYon
 * 2019.07.24
 */
@Component
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    /**
     * 异常处理方法
     *
     * @param throwable 异常
     * @param method    抛出异常的方法
     * @param objects   方法参数
     */
    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
        System.out.println("------我是Async无返回方法的异常处理方法---------");
        System.out.println(throwable.toString());
        System.out.println(method.getName());
        System.out.println((String) objects[0]);
    }
}
