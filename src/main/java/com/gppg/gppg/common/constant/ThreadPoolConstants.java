package com.gppg.gppg.common.constant;

/**
 * 持有线程池中涉及到的常量
 *
 * @author BeanYon
 * 2019.07.24
 */
public class ThreadPoolConstants {
    /**
     * 核心线程数量，可用处理器的两倍
     */
    public final static int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() + 1;
    /**
     * 最大线程数量
     */
    public final static int MAX_POOL_SIZE = CORE_POOL_SIZE * 2;
    /**
     * 任务队列容量
     */
    public final static int QUEUE_CAPACITY = 200;
    /**
     * 线程保活时间（s）
     */
    public final static int KEEP_ALIVE_SECONDS = 60;
    /**
     * 线程池关闭前是否等待所有线程执行完毕
     */
    public final static boolean WAIT_FOR_TASKS_COMPLETE = true;
    /**
     * 线程名称前缀
     */
    public final static String THREAD_NAME_PREFIX = "APT-Async-";
}
