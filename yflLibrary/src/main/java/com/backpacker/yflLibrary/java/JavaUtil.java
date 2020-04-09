package com.backpacker.yflLibrary.java;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.java
 * @Description: todo
 * @author: L-BackPacker
 * @date: 2019/3/31 22:26
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
public class JavaUtil {
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int dp2px(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5);
    }

    public static int dp2px(Context context, float dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5);
    }

    /**
     * 改变颜色
     *
     * @param str1 要查询的字符串
     * @param str2 要该变颜色字符串
     * @return
     */
    public static Spanned repaceStr(String str1, String str2, String color) {

        if (color == null || TextUtils.isEmpty(color)) {
            color = "#fb595b";
        }
        String s = str1.replaceAll(str2, "<font color='" + color + "'><normal>" + str2 + "</normal></font>");
        Spanned spanned = Html.fromHtml(s);

        return spanned;

    }

    /**
     * 判断某个界面是否在前台
     *
     * @param context   Context
     * @param className 界面的类名
     * @return 是否在前台显示
     */
    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static boolean isForeground(Context context, String className) {
        if (context == null || TextUtils.isEmpty(className))
            return false;
        ActivityManager am = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
//    boolean flag=false;
        for (ActivityManager.RunningTaskInfo taskInfo : list) {
            if (taskInfo.topActivity.getShortClassName().contains(className)) { // 说明它已经启动了
//        flag = true;
                return true;
            }
        }
        return false;
    }

    /**
     * 返回当前的应用是否处于前台显示状态
     *
     * @param $packageName
     * @return
     */
    public static boolean isTopActivity(Context mContent, String $packageName) {
        //_context是一个保存的上下文
        ActivityManager __am = (ActivityManager) mContent.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> __list = __am.getRunningAppProcesses();
        if (__list.size() == 0) return false;
        for (ActivityManager.RunningAppProcessInfo __process : __list) {
            Log.d("===", Integer.toString(__process.importance));
            Log.d("===", __process.processName);
            if (__process.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND &&
                    __process.processName.equals($packageName)) {
                return true;
            }
        }
        return false;
    }

    public static List<String> getValuesArray(Context mContext, Integer id) {
        String[] stringArray = mContext.getResources().getStringArray(id);
        List<String> titles = new ArrayList<>();
        if (stringArray == null || stringArray.length == 0) return titles;
        for (int i = 0; i < stringArray.length; i++) {
            titles.add(stringArray[i]);
        }
        return titles;
    }
}
