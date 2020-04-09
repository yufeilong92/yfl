package com.backpacker.yflLibrary.java;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import com.backpacker.yflLibrary.kotlin.T;
import com.example.UtilsLibrary.R;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author : YFL  is Creating a porject in workerApp_Android
 * @Package cn.ruiye.worker.utils
 * @Email : yufeilong92@163.com
 * @Time :2020/2/26 9:18
 * @Purpose :打开第三方工具
 */
public class OpenOtherMapUtil {
    public final static String BAIDU_PACKAGENAME = "com.baidu.BaiduMap";
    public final static String GAODE_PACKAGENAME = "com.autonavi.minimap";
    public final static String TENCENT_PACKAGENAME = "com.tencent.map";

    public enum MapKind {
        Baidu,
        GaoDe,
        Tengcent;

        public String getMapPackageName(MapKind a) {
            String mapPackagename = "com.baidu.BaiduMap";
            switch (a) {
                case Baidu:
                    mapPackagename = "com.baidu.BaiduMap";
                    break;
                case GaoDe:
                    mapPackagename = "com.autonavi.minimap";
                    break;
                case Tengcent:
                    mapPackagename = "com.tencent.map";
                    break;
            }
            return mapPackagename;
        }
    }

    /**
     * 检查手机上是否安装了指定的软件
     *
     * @param context
     * @param packageName：应用包名
     * @return
     */
    public static boolean isAvilible(Context context, String packageName) {
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }

    /**
     * 腾讯导航
     *
     * @param context
     * @param location
     */
    public static void tencentGuide(Context context, String appName,double[] location) {
        String downloadUri = "http://softroute.map.qq.com/downloadfile?cid=00001";
        String baseUrl = "qqmap://map/";
        String searchPlace = "search?keyword=酒店&bound=39.907293,116.368935,39.914996,116.379321";
        String searchAround = "search?keyword=肯德基&center=39.908491,116.374328&radius=1000";
        String busPlan = "routeplan?type=bus&from=我的家&fromcoord=39.980683,116.302&to=柳巷&tocoord=39.9836,116.3164&policy=2";
        String drivePlan = "routeplan?type=drive&from=&fromcoord=&to=&tocoord=" + location[0] + "," + location[1] + "&policy=1";

        String tencnetUri = baseUrl + drivePlan + "&referer=" + appName;

        if (isAvilible(context, TENCENT_PACKAGENAME)) {
            Intent intent;
            try {
                intent = Intent.parseUri(tencnetUri, 0);
                context.startActivity(intent);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            //直接下载
//            Intent intent;
//            try {
//                intent = Intent.parseUri(downloadUri, 0);
//                context.startActivity(intent);
//            } catch (URISyntaxException e) {
//                e.printStackTrace();
//            }
            //市场下载
            Toast.makeText(context, "您尚未安装腾讯地图", Toast.LENGTH_LONG).show();
            Uri uri = Uri.parse("market://details?id=" + TENCENT_PACKAGENAME);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        }
    }


    /**
     * 百度导航
     *
     * @param context
     * @param location location[0]纬度lat，location[1]经度lon
     */
    public static void baiduGuide(Context context, String appName,double[] location) {
        double[] baiduLoc = GpsUtil.gcj02_To_Bd09(location[0], location[1]);
        if (isAvilible(context, BAIDU_PACKAGENAME)) {//传入指定应用包名
            try {
//intent = Intent.getIntent("intent://map/direction?origin=latlng:34.264642646862,108.95108518068|name:我家&destination=大雁塔&mode=driving®ion=西安&src=yourCompanyName|yourAppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
                Intent intent = Intent.getIntent("intent://map/direction?" +
                        //"origin=latlng:"+"34.264642646862,108.95108518068&" +   //起点  此处不传值默认选择当前位置
                        "destination=latlng:" + baiduLoc[0] + "," + baiduLoc[1] + "|name:我的目的地" +        //终点
                        "&mode=driving" +          //导航路线方式
                        "&region=" +           //
                        "&src=" +
                        appName +
                        "#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
                context.startActivity(intent); //启动调用
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else {//未安装
            //market为路径，id为包名
            //显示手机上所有的market商店
            Toast.makeText(context, "您尚未安装百度地图", Toast.LENGTH_LONG).show();
            Uri uri = Uri.parse("market://details?id=com.baidu.BaiduMap");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        }
    }

    /**
     * 高德导航
     *
     * @param context
     * @param location
     */
    public static void gaodeGuide(Context context,String appName, double[] location) {
        if (isAvilible(context, GAODE_PACKAGENAME)) {
            try {
                Intent intent = Intent.getIntent("androidamap://navi?sourceApplication=" +
                        appName+
                        "&poiname=我的目的地" +
                        "&lat=" + location[0] +
                        "&lon=" + location[1] +
                        "&dev=0");
                context.startActivity(intent);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(context, "您尚未安装高德地图", Toast.LENGTH_LONG).show();
            Uri uri = Uri.parse("market://details?id=com.autonavi.minimap");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        }
    }

    //选择地址

    /**
     *
     * @param mContext
     * @param lat 经度  目的地址
     * @param lng 维度  目的地址
      * @param type 类型 1 百度 2 高德 3 腾讯
     * @param appName 起始位置名字
     */
    public static void selectMapDialog(Context mContext, Double lat, Double lng,  Integer type,String appName) {
        switch(type)
        {
            case 1:
                if (!OpenOtherMapUtil.isAvilible(mContext, OpenOtherMapUtil.BAIDU_PACKAGENAME)) {
                    T.INSTANCE.showToast(mContext, "请安装百度地图");
                }
                double[] array={lat,lng};
                OpenOtherMapUtil.baiduGuide(mContext, appName,array);
                break;
            case 2:
                if (!OpenOtherMapUtil.isAvilible(mContext, OpenOtherMapUtil.GAODE_PACKAGENAME)) {
                    T.INSTANCE.showToast(mContext, "请安装高德地图");
                }
                double[] array1={lat,lng};
                OpenOtherMapUtil.gaodeGuide(mContext,appName, array1);
                break;
            case 3:
                if (!OpenOtherMapUtil.isAvilible(mContext, OpenOtherMapUtil.TENCENT_PACKAGENAME)) {
                    T.INSTANCE.showToast(mContext, "请安装腾讯地图");
                }
                double[] array2={lat,lng};
                OpenOtherMapUtil.tencentGuide(mContext,appName, array2);
                break;
            default:
        }

    }

}
