package com.backpacker.yflLibrary.kotlin

import android.os.Environment
import android.os.StatFs
import android.content.pm.PackageManager
import android.content.Context
import android.app.Activity
import android.view.View
import android.view.Window
import android.os.Build
import android.annotation.SuppressLint
import android.content.Intent
import java.net.Inet4Address
import java.net.NetworkInterface
import android.text.TextUtils
import android.telephony.TelephonyManager
import android.net.ConnectivityManager
import android.net.Uri
import com.backpacker.yflLibrary.vo.Constants
import android.util.DisplayMetrics
import com.backpacker.yflLibrary.java.LocaUtil
import java.io.File


/**
 * @Title:  kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.kotlin
 * @Description:    系统工具累
 * @author: L-BackPacker
 * @date:   2019/3/31 22:16
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
object SystemUtil {

    /**
     * 获取系统内部可用空间大小
     *
     * @return available size
     */
    fun getSystemAvailableSize(): Long {
        val root = Environment.getRootDirectory()
        val sf = StatFs(root.path)
        val blockSize = sf.blockSize.toLong()
        val availCount = sf.availableBlocks.toLong()
        return availCount * blockSize
    }

    /**
     * 获取sd卡可用空间大小
     *
     * @return available size
     */
    fun getSDCardAvailableSize(): Long {
        var available: Long = 0
        if (hasSDCard()) {
            val path = Environment.getExternalStorageDirectory()
            val statfs = StatFs(path.path)
            val blocSize = statfs.blockSize.toLong()
            val availaBlock = statfs.availableBlocks.toLong()

            available = availaBlock * blocSize
        } else {
            available = -1
        }
        return available
    }

    /**
     * 是否安装了sdcard
     *
     * @return true表示有，false表示没有
     */
    fun hasSDCard(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    /**
     * 获取版本号
     *
     * @param context
     * @return
     */
    fun getAppSystemVersionName(context: Context): String {
        try {
            return context.packageManager.getPackageInfo(
                context.packageName, 0
            ).versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return ""
    }

    /**
     * 获取版本号
     *
     * @param context
     * @return
     */
    fun getAppSystemVersionCode(context: Context): Int {
        try {
            return context.packageManager.getPackageInfo(
                context.packageName, 0
            ).versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return 0
    }

    /**
     * 获取标题栏高度
     *
     * @param context
     * @return
     */
    fun getTitleBarHeight(context: Activity): Int {
        val contentTop = context.window
            .findViewById<View>(Window.ID_ANDROID_CONTENT).top
        return contentTop - getStatusBarHeight(context)
    }

    /**
     * 获取通知栏高度
     *
     * @param context
     * @return
     */
    fun getStatusBarHeight(context: Context): Int {
        var x = 0
        var statusBarHeight = 0
        try {
            val c = Class.forName("com.android.internal.R\$dimen")
            val obj = c.newInstance()
            val field = c.getField("status_bar_height")
            x = Integer.parseInt(field.get(obj).toString())
            statusBarHeight = context.resources.getDimensionPixelSize(x)
        } catch (e1: Exception) {
            e1.printStackTrace()
        }

        return statusBarHeight
    }

    /**
     * 获取手机名称
     *
     * @return
     */
    fun getMobileName(): String {
        return Build.MANUFACTURER + " " + Build.MODEL
    }

    /*
     * 版本控制部分
     */

    /**
     * 是否大于2.2版本及以上
     *
     * @return
     */
    fun hasFroyo(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO
    }

    /**
     * 是否为2.3版本及以上
     *
     * @return
     */
    fun hasGingerbread(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD
    }

    /**
     * 是否为3.0版本及以上
     *
     * @return
     */
    fun hasHoneycomb(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB
    }

    /**
     * 是否为3.1版本及以上
     *
     * @return
     */
    fun hasHoneycombMR1(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1
    }

    /**
     * 是否4.1版本及以上
     *
     * @return
     */
    fun hasJellyBean(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
    }

    /**
     * 获取系统版本号
     *
     * @return
     */
    fun getOsVersion(): String {
        val osversion: String
        val osversion_int = getOsVersionInt()
        osversion = osversion_int.toString() + ""
        return osversion

    }

    /**
     * 获取系统版本号
     *
     * @return
     */
    fun getOsVersionInt(): Int {
        return Build.VERSION.SDK_INT

    }

    /**
     * 获取ip地址
     *
     * @return
     */
    @SuppressLint("LongLogTag")
    fun getHostIp(): String {
        try {
            val en = NetworkInterface
                .getNetworkInterfaces()
            while (en.hasMoreElements()) {
                val intf = en.nextElement()
                val enumIpAddr = intf
                    .inetAddresses
                while (enumIpAddr.hasMoreElements()) {
                    val inetAddress = enumIpAddr.nextElement()
                    if (!inetAddress.isLoopbackAddress && inetAddress is Inet4Address) {
                        if (!inetAddress.getHostAddress().toString()
                                .equals("null") && inetAddress.getHostAddress() != null
                        ) {
                            return inetAddress.getHostAddress().toString()
                                .trim()
                        }
                    }
                }
            }
        } catch (ex: Exception) {
            println("WifiPreference IpAddress$ex")
        }

        return ""
    }

    /**
     * 获取imei值
     *
     * @param context
     * @return
     */
    fun getPhoneImei(context: Context): String {
        val mTelephonyMgr = context
            .applicationContext.getSystemService(
            Context.TELEPHONY_SERVICE
        ) as TelephonyManager
        @SuppressLint("MissingPermission")
        val phoneImei = mTelephonyMgr.deviceId
        println("IMEI is : $phoneImei")
        return if (TextUtils.isEmpty(phoneImei)) "" else phoneImei
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    fun getMobileSystemVersion(): String {
        return android.os.Build.VERSION.RELEASE
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    fun getMobileSystemModel(): String {
        return android.os.Build.MODEL
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    fun getMobileDeviceBrand(): String {
        return android.os.Build.BRAND
    }

    /**
     * 获取手机IMEI(需要“android.permission.READ_PHONE_STATE”权限)
     *
     * @return 手机IMEI
     */
    @SuppressLint("MissingPermission")
    fun getMobileIMEI(ctx: Context): String? {
        try {
            val tm = ctx.getSystemService(Activity.TELEPHONY_SERVICE) as TelephonyManager
            return tm.deviceId
        } catch (e: Exception) {
            return ""
        }
    }

    /**
     * 获取手机IMEI(需要“android.permission.READ_PHONE_STATE”权限)
     *
     * @return 手机IMEI
     */
    @SuppressLint("MissingPermission")
    fun getMobileIMSI(ctx: Context): String? {
        try {
            val tm = ctx.getSystemService(Activity.TELEPHONY_SERVICE) as TelephonyManager
            return tm.subscriberId

        } catch (e: Exception) {
            return ""
        }
    }

    /**
     * 获取分辨率
     *
     * @return
     */
    fun getMobiledp(context: Context): String {
        var metrics = DisplayMetrics()
        metrics = context.applicationContext.resources.displayMetrics
        val width = metrics.widthPixels
        val height = metrics.heightPixels
        val with = width.toString()
        val heights = height.toString()
        return "$with*$heights"
    }

    /**
     * 获取dpi
     *
     * @return
     */
    fun getMobiledpi(context: Context): String {
        var metrics = DisplayMetrics()
        metrics = context.applicationContext.resources.displayMetrics
        return metrics.densityDpi.toString()
    }

    /**
     * 获取用户网络
     */

    fun getNetWorkStatus(context: Context): Int {
        var netWorkType = Constants.NETWORK_CLASS_UNKNOWN
        //检测API是不是小于21，因为到了API21之后getNetworkInfo(int networkType)方法被弃用
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {

            //获得ConnectivityManager对象
            val connMgr =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            //获取ConnectivityManager对象对应的NetworkInfo对象
            //获取WIFI连接的信息
            val wifiNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            //获取移动数据连接的信息
            val dataNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            if (wifiNetworkInfo != null && dataNetworkInfo != null && wifiNetworkInfo.isConnected && dataNetworkInfo.isConnected) { //WIFI已连接,移动数据已连接
                netWorkType = Constants.NETWORK_WIFI
            } else if (wifiNetworkInfo != null && wifiNetworkInfo.isConnected) { //WIFI已连接,移动数据已断开
                netWorkType = Constants.NETWORK_WIFI
            } else if (dataNetworkInfo != null && dataNetworkInfo.isConnected) { //WIFI已断开,移动数据已连接
                netWorkType = Constants.NETWORK_CLASS_4_G
            } else { //WIFI已断开,移动数据已断开
                netWorkType = Constants.NETWORK_CLASS_UNKNOWN
            }
        } else {
            //这里的就不写了，前面有写，大同小异
            println("API level 大于21")
            //获得ConnectivityManager对象
            val connMgr =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            //获取所有网络连接的信息
            val networks = connMgr.allNetworks
            //通过循环将网络信息逐个取出来.
            var isNetWork = false
            var isWift = false
            for (i in networks.indices) {
                //获取ConnectivityManager对象对应的NetworkInfo对象
                val networkInfo = connMgr.getNetworkInfo(networks[i])
                if (networkInfo != null && networkInfo.isConnected) {
                    isNetWork = true
                }
                if (networkInfo != null && networkInfo.typeName.equals("WIFI", ignoreCase = true)) {
                    isWift = true
                }
            }
            if (isNetWork) {
                if (isWift) {
                    netWorkType = Constants.NETWORK_WIFI
                } else
                    netWorkType = Constants.NETWORK_CLASS_4_G
            } else {
                netWorkType = Constants.NETWORK_CLASS_UNKNOWN
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

        return netWorkType
    }

    /**
     * 跳转到权限设置界面
     */
    fun getAppDetailSettingIntent(context: Activity) {

        // vivo 点击设置图标>加速白名单>我的app
        //      点击软件管理>软件管理权限>软件>我的app>信任该软件
        var appIntent = context.packageManager.getLaunchIntentForPackage("com.iqoo.secure")
        if (appIntent != null) {
            context.startActivity(appIntent)

            return
        }

        // oppo 点击设置图标>应用权限管理>按应用程序管理>我的app>我信任该应用
        //      点击权限隐私>自启动管理>我的app
        appIntent = context.packageManager.getLaunchIntentForPackage("com.oppo.safe")
        if (appIntent != null) {
            context.startActivity(appIntent)

            return
        }

        val intent = Intent()
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        if (Build.VERSION.SDK_INT >= 9) {
            intent.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
            intent.data = (Uri.fromParts("package", context.packageName, null))
        } else if (Build.VERSION.SDK_INT <= 8) {
            intent.action = Intent.ACTION_VIEW
            intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails")
            intent.putExtra("com.android.settings.ApplicationPkgName", context.packageName)
        }
        context.startActivity(intent)
    }

    /**
     * 清除app缓存
     */
    fun clearAppCache(context: Context) {
        // 清除数据缓存
        clearCacheFolder(context.filesDir, System.currentTimeMillis())
        clearCacheFolder(context.cacheDir, System.currentTimeMillis())
        // 2.2版本才有将应用缓存转移到sd卡的功能
        if (LocaUtil.isMethodsCompat(Build.VERSION_CODES.FROYO)) {
            clearCacheFolder(
                LocaUtil.getExternalCacheDir(context),
                System.currentTimeMillis()
            )
        }
    }

    /**
     * 清除缓存目录
     *
     * @param dir     目录
     * @param curTime 当前系统时间
     * @return
     */
    private fun clearCacheFolder(dir: File?, curTime: Long): Int {
        var deletedFiles = 0
        if (dir != null && dir.isDirectory) {
            try {
                for (child in dir.listFiles()) {
                    if (child.isDirectory) {
                        deletedFiles += clearCacheFolder(child, curTime)
                    }
                    if (child.lastModified() < curTime) {
                        if (child.delete()) {
                            deletedFiles++
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        return deletedFiles
    }
}