package com.gppg.gppg.common.entity.response;

/**
 * 枚举 Web API 响应时可能出现的结果
 *
 * @author BeanYon
 * 2019.07.25
 */
public enum ResponseType {
    // Web API
    SUCCESS(true, 1000, "操作成功"),
    FAILED(false, 1001, "操作失败"),
    ILLEGAL_TEL(false, 1002, "手机号不合法"),
    ILLEGAL_PASSWORD(false, 1003, "密码不合法，请提供散列后的密码"),
    ILLEGAL_ACCOUNT(false, 1004, "账号不存在"),
    PASSWORD_NOT_MATCH(false, 1005, "密码错误"),
    ILLEGAL_NAME(false, 1006, "用户名不能为空"),
    ILLEGAL_CREATE_USER(false, 1007, "创建者id不合法"),
    MISSING_REQUIRED_PARAM(false, 1008, "缺失必要参数"),
    ILLEGAL_EMAIL(false, 1009, "邮箱不合法"),
    ILLEGAL_REQUEST_METHOD(false, 1010, "Request Method不合法"),
    NOTHING_TO_UPDATE(false, 1011, "没有要更新的内容"),
    NOTEXIST(false,1012,"不存在"),
    ILLEGAL_DELETE(false,1013,"删除失败"),
    ILLEGAL_INPUT(false,1014,"必要信息不完整"),

    //FAILED_SCZLZERO_JJTH
    // 数据库
    DATA_EXIST(false, 2001, "重复添加具有唯一约束的数据"),
    // 未知
    UNKNOWN(false, 3000, "服务器出现未知错误"),
    // 权限
    UNAUTH(false, 4001, "权限不足"),
    NO_LOGIN(false, 4002, "尚未登录"),
    //对接
    NOT_EXIST_BBXX(false,5001,"未报备"),
    SUCCESS_QUERY_BBXX(true,5002,"已报备"),
    SUCCESS_QUERY_BBXX_IMPERFECT(true,5003,"已报备，报备信息不完整");
    /**
     * 是否请求成功
     */
    private boolean result;
    /**
     * 异常标识码
     */
    private int code;
    /**
     * 异常信息
     */
    private String message;

    private ResponseType(boolean result, int code, String message) {
        this.result = result;
        this.code = code;
        this.message = message;
    }

    public boolean getResult() {
        return result;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
