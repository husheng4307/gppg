package com.gppg.gppg.handler;

import com.gppg.gppg.entity.response.HttpResponse;
import com.gppg.gppg.entity.response.ResponseType;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Web API 全局异常处理器
 *
 * @author BeanYon
 * 2019.07.25
 */
@ControllerAdvice
public class WebApiGlobalExceptionHandler {
    /**
     * 空指针
     *
     * @param e 异常实例
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public HttpResponse handleNullPointerException(NullPointerException e) {
        e.printStackTrace();
        return new HttpResponse(ResponseType.UNKNOWN, null);
    }

    /**
     * 必需参数未传递
     *
     * @param e 异常实例
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public HttpResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        e.printStackTrace();
        return new HttpResponse(ResponseType.MISSING_REQUIRED_PARAM, null);
    }

    /**
     * 请求不支持（比如该用Get却用了Post）
     *
     * @param e 异常实例
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public HttpResponse handleMissingServletRequestParameterException(HttpRequestMethodNotSupportedException e) {
        e.printStackTrace();
        return new HttpResponse(ResponseType.ILLEGAL_REQUEST_METHOD, null);
    }

    /**
     * 捕获并处理未知异常
     *
     * @param e 异常实例
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public HttpResponse handleException(Exception e) {
        e.printStackTrace();
        return new HttpResponse(ResponseType.UNKNOWN, null);
    }
}
