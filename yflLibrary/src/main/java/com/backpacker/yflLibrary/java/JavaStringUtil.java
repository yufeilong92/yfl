package com.backpacker.yflLibrary.java;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.java
 * @Description: $todo
 * @author: L-BackPacker
 * @date: 2019/3/31 21:32
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
public class JavaStringUtil {
    public static boolean isEmpty(String str) {
        if (str == null || str.equals(""))
            return true;
        return false;
    }

    public static String getObjectToStr(View v) {
        if (v instanceof TextView) {
            TextView tv = (TextView) v;
            return tv.getText().toString().trim();
        }
        if (v instanceof EditText) {
            EditText et = (EditText) v;
            return et.getText().toString().trim();
        }
        if (v instanceof Button) {
            Button btn = (Button) v;
            return btn.getText().toString().trim();
        }
        return "";
    }

    public static String getStringWid(Context m, int id) {
        return m.getResources().getString(id);
    }


    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean isEquest(String com, String coms) {
        if (com.equals(coms)) {
            return true;
        }
        return false;

    }

    /***
     * @param name 获取匿名字
     * @param postion
     * @return 小乐888...
     */
    public static String getSubStringName(String name, int postion) {
        if (isEmpty(name)) {
            return "";
        }
        int length = name.length();
        if (length <= postion) {
            return name;
        }
        String substring = name.substring(0, postion);
        return substring.concat("...");

    }
}
