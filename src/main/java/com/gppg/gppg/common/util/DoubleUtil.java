package com.gppg.gppg.common.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Created by husheng
 * @on 19-11-28 下午9:23
 * @Version 1.0
 */
public class DoubleUtil implements Serializable {

    private static final long serialVersionUID = -3345205828566485102L;
     // 默认除法运算精度
    private static final Integer DEF_DIV_SCALE = 2;

        /**
 18      * 提供精确的加法运算。
 19      *
 20      * @param value1 被加数
 21      * @param value2 加数
 22      * @return 两个参数的和
 23      */
        public static Double add(Double value1, Double value2) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.add(b2).doubleValue();
        }

        /**
 31      * 提供精确的减法运算。
 32      *
 33      * @param value1 被减数
 34      * @param value2 减数
 35      * @return 两个参数的差
 36      */
        public static double sub(Double value1, Double value2) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.subtract(b2).doubleValue();
        }

    /**
 44      * 提供精确的乘法运算。
 45      *
 46      * @param value1 被乘数
 47      * @param value2 乘数
 48      * @return 两个参数的积
 49      */
        public static Double mul(Double value1, Double value2) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.multiply(b2).doubleValue();
        }

/**
 57      * 提供（相对）精确的除法运算，当发生除不尽的情况时， 精确到小数点以后10位，以后的数字四舍五入。
 58      *
 59      * @param dividend 被除数
 60      * @param divisor  除数
 61      * @return 两个参数的商
 62      */
        public static Double divide(Double dividend, Double divisor) {
        return divide(dividend, divisor, DEF_DIV_SCALE);
        }

    /**
 68      * 提供（相对）精确的除法运算。 当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。
 69      *
 70      * @param dividend 被除数
 71      * @param divisor  除数
 72      * @param scale    表示表示需要精确到小数点以后几位。
 73      * @return 两个参数的商
 74      */
        public static Double divide(Double dividend, Double divisor, Integer scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
            }
        BigDecimal b1 = new BigDecimal(Double.toString(dividend));
        BigDecimal b2 = new BigDecimal(Double.toString(divisor));
        return b1.divide(b2, scale, RoundingMode.HALF_UP).doubleValue();
        }

     /**
 85      * 提供指定数值的（精确）小数位四舍五入处理。
 86      *
 87      * @param value 需要四舍五入的数字
 88      * @param scale 小数点后保留几位
 89      * @return 四舍五入后的结果
 90      */
        public static double round(double value,int scale){
        if(scale<0){
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(value));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one,scale, RoundingMode.HALF_UP).doubleValue();
        }

    public static int compareToZero(double value){

        BigDecimal b = new BigDecimal(Double.toString(value));
        BigDecimal zero = new BigDecimal("0");
        int result = b.compareTo(zero);

        return result;
    }
}