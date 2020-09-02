package com.gppg.gppg.util;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author HanJing
 * The type Json result.
 */
public class JsonResult implements Serializable {


    //返回码 非0即失败
    private int code;


    private String msg;

    //返回的数据
    private Map<String, Object> data;

    /**
     * Instantiates a new Json result.
     */
    public JsonResult(){

    };

    /**
     * Instantiates a new Json result.
     *
     * @param code the code
     * @param msg  the msg
     * @param data the data
     */
    public JsonResult(int code, String msg, Map<String, Object> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * Success string.
     *
     * @return the string
     */
    public static String success() {
        return success(new HashMap(0));
    }

    /**
     * Success string.
     *
     * @param data the data
     * @return the string
     */
    public static String success(Map<String, Object> data) {
        return JSON.toJSONString(new JsonResult(0, "解析成功", data));
    }

    /**
     * Failed string.
     *
     * @return the string
     */
    public static String failed() {
        return failed("解析失败");
    }

    /**
     * Failed string.
     *
     * @param msg the msg
     * @return the string
     */
    public static String failed(String msg) {
        return failed(-1, msg);
    }

    /**
     * Failed string.
     *
     * @param code the code
     * @param msg  the msg
     * @return the string
     */
    public static String failed(int code, String msg) {
        return  JSON.toJSONString(new JsonResult(code, msg, new HashMap(0)));
    }

}
