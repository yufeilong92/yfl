package com.backpacker.yflLibrary.java;

import java.security.MessageDigest;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.java
 * @Description: todo
 * @author: L-BackPacker
 * @date: 2019/3/31 23:23
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
public class MD5 {
    public static String getMD5String(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4',
                '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            //获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            //使用指定的字节更新摘要
            mdInst.update(btInput);
            //获得密文
            byte[] md = mdInst.digest();
            //把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
