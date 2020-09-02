package com.gppg.gppg.util.WxJwtUtil;

/**
 * @Created by husheng
 * @on 19-11-7 下午1:05
 * @Version 1.0
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public enum JSONUtil {
    ;

    /**
     * 将JSON字符串转为Java对象
     */
    public static <T> T toJavaObject(String result, Class<T> clazz) {
        return JSONObject.toJavaObject(JSONObject.parseObject(result), clazz);
    }

    /**
     * JSON字符串对象解析成java List对象
     */
    public static <T> List<T> toJavaList(String resultList, Class<T> clazz) {
        return JSONArray.parseArray(resultList).toJavaList(clazz);
    }
}
