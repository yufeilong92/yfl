package com.backpacker.yflLibrary.java;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.backpacker.yflLibrary.vo.Constants;
import com.example.UtilsLibrary.R;

import java.lang.reflect.Field;

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
public class SystemUtil {
    /**
     * 获取通知栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int x = 0, statusBarHeight = 0;
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 获取标题栏高度
     *
     * @param context
     * @return
     */
    public static int getTitleBarHeight(Activity context) {
        int contentTop = context.getWindow()
                .findViewById(Window.ID_ANDROID_CONTENT).getTop();
        return contentTop - getStatusBarHeight(context);
    }

    public static int getNetWorkStatus(Context context) {
        int netWorkType = Constants.NETWORK_CLASS_UNKNOWN;
        //检测API是不是小于21，因为到了API21之后getNetworkInfo(int networkType)方法被弃用
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {

            //获得ConnectivityManager对象
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            //获取ConnectivityManager对象对应的NetworkInfo对象
            //获取WIFI连接的信息
            NetworkInfo wifiNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            //获取移动数据连接的信息
            NetworkInfo dataNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (wifiNetworkInfo != null && dataNetworkInfo != null && wifiNetworkInfo.isConnected() && dataNetworkInfo.isConnected()) {//WIFI已连接,移动数据已连接
                netWorkType = Constants.NETWORK_WIFI;
            } else if (wifiNetworkInfo != null && wifiNetworkInfo.isConnected()) {//WIFI已连接,移动数据已断开
                netWorkType = Constants.NETWORK_WIFI;
            } else if (dataNetworkInfo != null && dataNetworkInfo.isConnected()) {//WIFI已断开,移动数据已连接
                netWorkType = Constants.NETWORK_CLASS_4_G;
            } else {//WIFI已断开,移动数据已断开
                netWorkType = Constants.NETWORK_CLASS_UNKNOWN;
            }
        } else {
            //这里的就不写了，前面有写，大同小异
            System.out.println("API level 大于21");
            //获得ConnectivityManager对象
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            //获取所有网络连接的信息
            Network[] networks = connMgr.getAllNetworks();
            //通过循环将网络信息逐个取出来.
            boolean isNetWork = false;
            boolean isWift = false;
            for (int i = 0; i < networks.length; i++) {
                //获取ConnectivityManager对象对应的NetworkInfo对象
                NetworkInfo networkInfo = connMgr.getNetworkInfo(networks[i]);
                if (networkInfo != null && networkInfo.isConnected()) {
                    isNetWork = true;
                }
                if (networkInfo != null && networkInfo.getTypeName().equalsIgnoreCase("WIFI")) {
                    isWift = true;
                }
            }
            if (isNetWork) {
                if (isWift) {
                    netWorkType = Constants.NETWORK_WIFI;
                } else
                    netWorkType = Constants.NETWORK_CLASS_4_G;
            } else {
                netWorkType = Constants.NETWORK_CLASS_UNKNOWN;
            }
        }
    /*    ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            int type = networkInfo.getType();
            if (type == ConnectivityManager.TYPE_WIFI) {
                netWorkType = Constants.NETWORK_WIFI;
            } else if (type == ConnectivityManager.TYPE_MOBILE) {
                netWorkType = getNetWorkClass(context);
            }
        }*/

        return netWorkType;
    }
    /**
     * 但是当我们没在AndroidManifest.xml中设置其debug属性时:
     * 使用Eclipse运行这种方式打包时其debug属性为true,使用Eclipse导出这种方式打包时其debug属性为法false.
     * 在使用ant打包时，其值就取决于ant的打包参数是release还是debug.
     * 因此在AndroidMainifest.xml中最好不设置android:debuggable属性置，而是由打包方式来决定其值.
     *
     * @param context
     * @return
     */
    public static boolean isApkDebugable(Context context) {
        try {
            ApplicationInfo info= context.getApplicationInfo();
            return (info.flags&ApplicationInfo.FLAG_DEBUGGABLE)!=0;
        } catch (Exception e) {
        }
        return false;
    }
    /**
     * 获取状态栏高度
     * @return
     */
    public static int getStatusBarsHeight(Context mContext) {
        int result = 0;
        int resourceId =mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result =mContext.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取控件左上顶点Y坐标
     *
     * @param view
     * @return
     */
    public static int getCoordinateY(View view) {
        int[] coordinate = new int[2];
        view.getLocationOnScreen(coordinate);
        return coordinate[1];
    }
}
