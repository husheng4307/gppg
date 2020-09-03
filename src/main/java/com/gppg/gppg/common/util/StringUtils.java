package com.gppg.gppg.common.util;

import java.util.UUID;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author BeanYon
 * 2019.07.30
 */
public class StringUtils {
    /**
     * 判断字符串是否为空
     *
     * @param str 要判断的字符串
     * @return 是否为空
     */
    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        }

        return Pattern.matches("^\\s*$", str);
    }

    /**
     * 校验手机号码合法性
     *
     * @param tel 手机号码
     * @return 是否合法
     */
    public static boolean checkTel(String tel) {
        return Pattern.matches("^1\\d{10}$", tel);
    }

    /**
     * 校验密码合法性
     *
     * @param password 密码
     * @return 是否合法
     */
    public static boolean checkPassword(String password) {
        return !isEmpty(password) && password.length() <= 128;
    }

    /**
     * 校验邮箱合法性
     *
     * @param email 邮箱
     * @return 是否合法
     */
    public static boolean checkEmail(String email) {
        return Pattern.matches("^.+@(\\w+\\.)+\\w+$", email);
    }

    /**
     * 获取随机字符串
     *
     * @return 随机字符串
     */
    public static String getRandomString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
