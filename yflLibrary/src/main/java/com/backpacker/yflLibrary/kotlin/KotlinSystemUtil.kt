package com.backpacker.yflLibrary.kotlin

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Point
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.StatFs
import android.telephony.TelephonyManager
import android.text.TextUtils
import android.util.DisplayMetrics
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.backpacker.yflLibrary.vo.Constants
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.math.BigDecimal
import java.net.Inet4Address
import java.net.NetworkInterface


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
object KotlinSystemUtil {
    /***
     * @param mContext
     * @return
     */
    data class WithHeight(val with: Int, val height: Int)

    fun getPhoneScreenWithHeight(mContext: Context): WithHeight {
        val windowManager = mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val defaultDisplay = windowManager.defaultDisplay
        var height: Int
        var with: Int
        if (Build.VERSION.SDK_INT < 17) {
            height = defaultDisplay.height
            with = defaultDisplay.width
        } else {
            val size = Point()
            defaultDisplay.getRealSize(size)
            height = size.y
            with = size.x
        }
        return WithHeight(with, height)
    }


    /**
     * 获取版本号
     * 也可使用 BuildConfig.VERSION_NAME 替换
     *
     * @param context 上下文
     * @return 版本号
     */
    fun getVersionName(context: Context): String? {
        val packageManager = context.packageManager
        val packageName = context.packageName
        try {
            val packageInfo: PackageInfo = packageManager.getPackageInfo(packageName, 0)
            return packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return "1.0.0"
    }

    /**
     * 获取版本code
     * 也可使用 BuildConfig.VERSION_CODE 替换
     *
     * @param context 上下文
     * @return 版本code
     */
    fun getVersionCode(context: Context): Int {
        val packageManager = context.packageManager
        val packageName = context.packageName
        try {
            val packageInfo: PackageInfo = packageManager.getPackageInfo(packageName, 0)
            return packageInfo.versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return 1
    }

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

    /**
     * 获取应用程序名称
     */
    fun getAppName(context: Context): String? {
        try {
            val packageManager = context.packageManager
            val packageInfo = packageManager.getPackageInfo(
                context.packageName, 0
            )
            val labelRes = packageInfo.applicationInfo.labelRes
            return context.resources.getString(labelRes)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return null
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
        return try {
            Build.VERSION.SDK_INT
        } catch (e: Exception) {
            0
        }

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
        return try {
            Build.VERSION.RELEASE
        } catch (e: Exception) {
            "0.0.0"
        }
    }


    /**
     * 获取手机型号
     *
     * @param context  上下文
     * @return   String
     */
    fun getMobileModel(context: Context?): String? {
        return try {
            Build.MODEL
        } catch (e: java.lang.Exception) {
            "未知"
        }
    }

    /**
     * 获取手机品牌
     *获取手机厂商
     * @param context  上下文
     * @return  String
     */
    fun getMobileBrand(context: Context?): String? {
        return try {
            Build.BRAND
        } catch (e: java.lang.Exception) {
            "未知"
        }
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
     * 但是当我们没在AndroidManifest.xml中设置其debug属性时:
     * 使用Eclipse运行这种方式打包时其debug属性为true,使用Eclipse导出这种方式打包时其debug属性为法false.
     * 在使用ant打包时，其值就取决于ant的打包参数是release还是debug.
     * 因此在AndroidMainifest.xml中最好不设置android:debuggable属性置，而是由打包方式来决定其值.
     *
     * @param context
     * @return
     */
    fun isApkDebugable(context: Context): Boolean {
        try {
            val info = context.applicationInfo
            return info.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
        } catch (e: java.lang.Exception) {
        }
        return false
    }

    /**
     * 获取控件左上顶点Y坐标
     *
     * @param view
     * @return
     */
    fun getCoordinateY(view: View): Int {
        val coordinate = IntArray(2)
        view.getLocationOnScreen(coordinate)
        return coordinate[1]
    }

    /**
     * 计算缓存的大小,
     *
     * @return
     */
    fun getCacheSize(context: Context): String? {
        var fileSize: Long = 0
        var cacheSize: String? = "0KB"
        val filesDir =
            context.applicationContext.filesDir // /data/data/package_name/files
        val cacheDir = context.cacheDir // /data/data/package_name/cache
        fileSize += getDirSize(filesDir)
        fileSize += getDirSize(cacheDir)
        // 2.2版本才有将应用缓存转移到sd卡的功能
        if (isMethodsCompat(Build.VERSION_CODES.FROYO)) {
            val externalCacheDir =
                getExternalCacheDir(context) // "<sdcard>/Android/data/<package_name>/cache/"
            fileSize += getDirSize(externalCacheDir)
        }
        if (fileSize > 0) cacheSize = formatFileSize(fileSize)
        return cacheSize
    }

    /**
     * 获取目录文件大小
     *
     * @param dir
     * @return
     */
    fun getDirSize(dir: File?): Long {
        if (dir == null) {
            return 0
        }
        if (!dir.isDirectory) {
            return 0
        }
        var dirSize: Long = 0
        val files = dir.listFiles()
        for (file in files) {
            if (file.isFile) {
                dirSize += file.length()
            } else if (file.isDirectory) {
                dirSize += file.length()
                dirSize += getDirSize(file) // 递归调用继续统计
            }
        }
        return dirSize
    }

    /**
     * 将二进制长度转换成文件大小
     *
     * @param length
     * @return
     */
    fun formatFileSize(length: Long): String? {
        var result: String? = null
        var sub_string = 0
        if (length >= 1073741824) {
            sub_string = (length.toFloat() / 1073741824).toString().indexOf(
                "."
            )
            result = ((length.toFloat() / 1073741824).toString() + "000").substring(
                0,
                sub_string + 3
            ) + "GB"
        } else if (length >= 1048576) {
            sub_string = (length.toFloat() / 1048576).toString().indexOf(".")
            result = ((length.toFloat() / 1048576).toString() + "000").substring(
                0,
                sub_string + 3
            ) + "MB"
        } else if (length >= 1024) {
            sub_string = (length.toFloat() / 1024).toString().indexOf(".")
            result = ((length.toFloat() / 1024).toString() + "000").substring(
                0,
                sub_string + 3
            ) + "KB"
        } else if (length < 1024) result = java.lang.Long.toString(length) + "B"
        return result
    }


    /**
     * 获取状态栏高度
     * @return
     */
    fun getStatusBarsHeight(mContext: Context): Int {
        var result = 0
        val resourceId =
            mContext.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = mContext.resources.getDimensionPixelSize(resourceId)
        }
        return result
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
        if (isMethodsCompat(Build.VERSION_CODES.FROYO)) {
            clearCacheFolder(
                getExternalCacheDir(context),
                System.currentTimeMillis()
            )
        }
    }

    fun getExternalCacheDir(context: Context): File? {
        // return context.getExternalCacheDir(); API level 8
        // e.g. "<sdcard>/Android/data/<package_name>/cache/"
        var dir = context.externalCacheDir
        if (null == dir) dir = context.cacheDir
        return dir
    }

    /**
     * 判断当前版本是否兼容目标版本的方法
     *
     * @param VersionCode
     * @return
     */
    fun isMethodsCompat(VersionCode: Int): Boolean {
        val currentVersion = Build.VERSION.SDK_INT
        return currentVersion >= VersionCode
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

    /**
     * 获取缓存大小
     * @param context
     * @return
     * @throws Exception
     */
    @Throws(java.lang.Exception::class)
    fun getTotalCacheSize(context: Context): String? {
        var cacheSize = getFolderSize(context.cacheDir)
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            cacheSize += getFolderSize(context.externalCacheDir!!)
        }
        return getFormatSize(cacheSize.toDouble())
    }

    // 获取文件大小
    //Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
    //Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
    @Throws(java.lang.Exception::class)
    fun getFolderSize(file: File): Long {
        var size: Long = 0
        try {
            val fileList = file.listFiles()
            for (i in fileList.indices) {
                // 如果下面还有文件
                size = if (fileList[i].isDirectory) {
                    size + getFolderSize(fileList[i])
                } else {
                    size + fileList[i].length()
                }
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return size
    }

    /**
     * 格式化单位
     * @param size
     * @return
     */
    fun getFormatSize(size: Double): String? {
        val kiloByte = size / 1024
        if (kiloByte < 1) {
//            return size + "Byte";
            return "0K"
        }
        val megaByte = kiloByte / 1024
        if (megaByte < 1) {
            val result1 = BigDecimal.valueOf(kiloByte)
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                .toPlainString().toString() + "K"
        }
        val gigaByte = megaByte / 1024
        if (gigaByte < 1) {
            val result2 = BigDecimal.valueOf(megaByte)
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                .toPlainString().toString() + "M"
        }
        val teraBytes = gigaByte / 1024
        if (teraBytes < 1) {
            val result3 = BigDecimal.valueOf(gigaByte)
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                .toPlainString().toString() + "GB"
        }
        val result4 = BigDecimal.valueOf(teraBytes)
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
            .toString() + "TB"
    }

    @Throws(IOException::class)
    private fun readTextFromUri(activity: Activity, uri: Uri): String {
        val stringBuilder = StringBuilder()
        activity.contentResolver.openInputStream(uri)?.use { inputStream ->
            BufferedReader(InputStreamReader(inputStream)).use { reader ->
                var line: String? = reader.readLine()
                while (line != null) {
                    stringBuilder.append(line)
                    stringBuilder.append(";")
                    line = reader.readLine()
                }
            }
        }
        return stringBuilder.toString()
    }
}