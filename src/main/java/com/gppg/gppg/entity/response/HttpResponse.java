package com.gppg.gppg.entity.response;

/**
 * 封装统一的HTTP请求响应模版
 *
 * @author BeanYon
 * 2019.07.25
 */
public class HttpResponse {
    /**
     * 是否请求成功
     */
    private boolean result;
    /**
     * 响应码
     */
    private int code;
    /**
     * 响应描述
     */
    private String message;
    /**
     * 返回到客户端的数据
     */
    private Object data;

    public HttpResponse() {
    }

    public HttpResponse(ResponseType resultType, Object data) {
        this.result = resultType.getResult();
        this.code = resultType.getCode();
        this.message = resultType.getMessage();
        this.data = data;
    }

    public void setHttpResponse(ResponseType resultType, Object data){
        this.result = resultType.getResult();
        this.code = resultType.getCode();
        this.message = resultType.getMessage();
        this.data = data;
    }

    public HttpResponse(boolean result, int code, String message, Object data) {
        this.result = result;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
