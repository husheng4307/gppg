package com.gppg.gppg.threadpool;

import com.gppg.gppg.constant.ThreadPoolConstants;
import com.gppg.gppg.handler.AsyncExceptionHandler;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置类
 *
 * @author BeanYon
 * 2019.07.23
 */
@Configuration
public class ThreadPoolConfig implements AsyncConfigurer {
    /**
     * 异常处理器实例
     */
    @Autowired
    private AsyncExceptionHandler asyncExceptionHandler;

    /**
     * 线程池配置
     *
     * @return 线程池实例
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(ThreadPoolConstants.CORE_POOL_SIZE);
        executor.setMaxPoolSize(ThreadPoolConstants.MAX_POOL_SIZE);
        executor.setQueueCapacity(ThreadPoolConstants.QUEUE_CAPACITY);
        executor.setKeepAliveSeconds(ThreadPoolConstants.KEEP_ALIVE_SECONDS);
        executor.setThreadNamePrefix(ThreadPoolConstants.THREAD_NAME_PREFIX);
        executor.setWaitForTasksToCompleteOnShutdown(ThreadPoolConstants.WAIT_FOR_TASKS_COMPLETE);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    /**
     * 返回异常处理器实例
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return asyncExceptionHandler;
    }
}
