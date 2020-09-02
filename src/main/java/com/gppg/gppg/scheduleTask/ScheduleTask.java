package com.gppg.gppg.scheduleTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @Created by husheng
 * @on 20-3-21 下午3:57
 * @Version 1.0
 * @Description 定时任务
 */
@Component
public class ScheduleTask {
    private final static Logger logger = LoggerFactory.getLogger(ScheduleTask.class);

//    @Autowired
//    private ScheduleService scheduleService;
//
//    @Autowired
//    private CarQueueService carQueueService;
//
//    @Autowired
//    private RedisLock redisLock;
//
//    @Scheduled(cron = "0 45 23 * * ?")
//    public void MarkExpiredRecordInYspc(){
//        logger.info("定时任务-每天23:45清理当天失效记录");
//        int day = scheduleService.queryTimeRemoveExpiredYspcRecord();
//        scheduleService.updateExpiredRecordInYspc(day);
//    }
//
//    @Scheduled(cron = "30 0/15 * * * ?")
//    public void checkIfCrowded(){
//        logger.info("每15分钟查询一次是否拥堵");
//        long expire = 100L;
//
//        //检查任务锁,若其它节点的相同定时任务已经执行，则该节点的任务执行一个空任务，否则设置锁并执行该任务
//        //当前类名+当前方法名
//        String timerName = this.getClass().getName()+Thread.currentThread() .getStackTrace()[1].getMethodName();
//        logger.info(timerName);
//        if(!redisLock.requireLock(timerName,"1",expire)){
//            logger.info("未获取到锁");
//            return;
//        }
//        logger.info("执行checkIfCrowded定时任务");
//        carQueueService.traversalQueue();
//    }

}
