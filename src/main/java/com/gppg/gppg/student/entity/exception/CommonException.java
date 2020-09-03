package com.gppg.gppg.student.entity.exception;


import com.gppg.gppg.common.entity.response.ResponseType;

/**
* @Dessciption: 业务中的CommonException，将异常信息 与 controller层中的返回结果 绑定在一起，方便实现业务代码的流程控制
* @author: husheng
* @date: 2020/6/6 19:34
*/
public class CommonException extends RuntimeException{
    private String message;
    private ResponseType type;

    public CommonException() {
        super();
    }

    public CommonException(ResponseType responseType) {
        super(responseType.getMessage());
        this.type = responseType;
    }

    public CommonException(ResponseType responseType, String message) {
        super(message);
        this.type = responseType;
        this.message = message;
    }


    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseType getType() {
        return type;
    }

    public void setType(ResponseType type) {
        this.type = type;
    }
}
