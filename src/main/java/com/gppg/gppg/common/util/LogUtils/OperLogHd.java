package com.gppg.gppg.common.util.LogUtils;

import java.lang.annotation.*;

/**
 * @Created by husheng
 * @on 19-11-1 下午6:38
 * @Version 1.0
 */

/**
 * @Author: husheng
 * @param：  * @param null
 * @return：
 * @Description: 自定义操作日志注解
 * @Date: 下午6:41 19-11-1
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperLogHd {
    //操作模块
    String operModul() default "";
    //操作类型
    String operType() default "";
    //操作描述
    String operDesc() default "";
}
