package com.backpacker.yflLibrary.java;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.java
 * @Description: 数学工具累
 * @author: L-BackPacker
 * @date: 2019/3/31 23:29
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
public class ArithUtil {
    /**
     * 提供精确加法计算的add方法
     *
     * @param value1 被加数
     * @param value2 加数
     * @return 两个参数的和
     */
    public static double add(double value1, double value2) {
        BigDecimal b1 =BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确加法计算的add方法
     *
     * @param value1  被加数
     * @param value2  加数
     * @param postion 保留数据
     * @return 两个参数的和
     */
    public static double add(double value1, double value2, int postion) {
        BigDecimal b1 =BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        return b1.add(b2).setScale(postion, BigDecimal.ROUND_DOWN).doubleValue();
    }

    public static double strToDouble(String com) {
        if (JavaStringUtil.isEmpty(com)) {
            return 0.0;
        }
        BigDecimal bd = new BigDecimal(com);
        Double d = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return d;
    }

    /**
     * 提供精确减法运算的sub方法
     *
     * @param value1 被减数
     * @param value2 减数
     * @return 两个参数的差
     */
    public static double sub(double value1, double value2) {
        BigDecimal b1 =BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确减法运算的sub方法
     *
     * @param value1  被减数
     * @param value2  减数
     * @param postion 保留数据
     * @return 两个参数的差
     */
    public static double sub(double value1, double value2, int postion) {
        BigDecimal b1 =BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        return b1.subtract(b2).setScale(postion, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     * 提供精确乘法运算的mul方法
     *
     * @param value1 被乘数
     * @param value2 乘数
     * @return 两个参数的积
     */
    public static double mul(double value1, double value2) {
        BigDecimal b1 =BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供精确乘法运算的mul方法
     *
     * @param value1 被乘数
     * @param value2 乘数
     * @return 两个参数的积
     */
    public static double mul(double value1, double value2,Integer positon) {
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 =  BigDecimal.valueOf(value2);
        return b1.multiply(b2).setScale(positon, BigDecimal.ROUND_DOWN).doubleValue();
    }

    public static double fourFive(double value1, Integer number) {
        BigDecimal b1 = BigDecimal.valueOf(value1);
        return b1.setScale(number, BigDecimal.ROUND_DOWN).doubleValue();

    }

    public static float toFloat(double value1, Integer number) {
        BigDecimal decimal =  BigDecimal.valueOf(value1);
        return decimal.setScale(number, BigDecimal.ROUND_DOWN).floatValue();

    }

    /**
     * 提供精确的除法运算方法div
     *
     * @param value1 被除数
     * @param value2 除数
     * @param scale  精确范围
     * @return 两个参数的商
     * @throws IllegalAccessException
     */
    public static double div(double value1, double value2, int scale) throws IllegalAccessException {
        if (value1 == 0 || value2 == 0) return 0;
        //如果精确范围小于0，抛出异常信息
        if (scale < 0) {
            throw new IllegalAccessException("精确度不能小于0");
        }
        BigDecimal b1 =BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        //默认保留两位会有错误，这里设置保留小数点后4位
        return b1.divide(b2, scale, BigDecimal.ROUND_DOWN).doubleValue();
    }

    /**
     * 提供精确的除法运算方法div
     *
     * @param value1 被除数
     * @param value2 除数
     * @param scale  精确范围
     * @return 两个参数的商
     * @throws IllegalAccessException
     */
    public static float divF(double value1, double value2, int scale) throws IllegalAccessException {
        if (value1 == 0 || value2 == 0) return 0;
        //如果精确范围小于0，抛出异常信息
        if (scale < 0) {
            throw new IllegalAccessException("精确度不能小于0");
        }
        BigDecimal b1 =BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        //默认保留两位会有错误，这里设置保留小数点后4位
        return  b1.divide(b2, scale, BigDecimal.ROUND_DOWN).floatValue();
    }

    public static String divNumber(double value1, double value2, int scale) {
        if (value1 == 0 || value2 == 0) return "0";
        double div = 0;
        try {
            div = div(value1, value2, scale);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return String.valueOf((int) (div * 100));
    }
}
