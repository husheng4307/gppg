package com.gppg.gppg.common.handler;

import com.gppg.gppg.common.entity.response.HttpResponse;
import com.gppg.gppg.common.entity.response.ResponseType;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 数据库全局异常处理器
 *
 * @author BeanYon
 * 2019.07.30
 */
@ControllerAdvice
public class DatabaseGlobalExceptionHandler {
    /**
     * 重复添加具有唯一约束的字段
     *
     * @param e 异常实例
     */
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    public HttpResponse handleNullPointerException(DuplicateKeyException e) {
        e.printStackTrace();
        return new HttpResponse(ResponseType.FAILED, 1);
    }

    /**
     * 未遵守数据完整性约束，通常是由于给外键字段写入不正确的数据
     *
     * @param e 异常实例
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseBody
    public HttpResponse handleNullPointerException(SQLIntegrityConstraintViolationException e) {
        e.printStackTrace();
        return new HttpResponse(ResponseType.DATA_EXIST, 2);
    }
}
