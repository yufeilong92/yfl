package com.backpacker.yflLibrary.kotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.provider.Settings
import androidx.annotation.RequiresPermission
import com.example.UtilsLibrary.R
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * @Author : YFL  is Creating a porject in basehttps
 * @Package com.backpacker.yflLibrary.kotlin
 * @Email : yufeilong92@163.com
 * @Time :2019/11/23 17:16
 * @Purpose :工具累
 */
object KotlinIntentUtil {
    /**
     * 发送短信
     * @param phoneNumber 手机号码
     * @param message 短信内容
     */
    @JvmStatic
    fun sendSMS(context: Context, phoneNumber: String = "", message: String = "") {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:$phoneNumber"))
        intent.putExtra("sms_body", message)
        context.startActivity(intent)
    }

    /**
     * 打开系统设置界面
     */
    @JvmStatic
    fun launchSystemSetting(context: Context) {
        context.startActivity(Intent(Settings.ACTION_SETTINGS))
    }

    /**
     * 打开wifi设置界面
     */
    @JvmStatic
    fun launchWifiSetting(context: Context) {
        context.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
    }

    /**
     * 打开拨号面板
     */
    @JvmStatic
    fun launchDialPage(context: Context) {
        context.startActivity(Intent(Intent.ACTION_DIAL))
    }

    /**
     * 直接拨打电话
     */
    @JvmStatic
    @RequiresPermission(android.Manifest.permission.CALL_PHONE)
    fun callPhone(context: Context, phoneNumber: String) {
        context.startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNumber")))
    }

    /**
     * 调用浏览器并打开一个网页
     * 为了避免手机上面没有安装浏览器引发崩溃，应弹出选择面板
     * @param url : 网页地址
     * @param browserListener : 是否成功打开浏览器选择面板的监听
     */
    @JvmStatic
    @JvmOverloads
    fun launchBrowse(
        context: Context,
        url: String,
        title: String,
        browserListener: ((isSuccess: Boolean) -> Unit)? = null
    ) {
        val intentWeb = with(Intent(Intent.ACTION_VIEW)) {
            addCategory(Intent.CATEGORY_BROWSABLE)
            data = Uri.parse(url)
            this
        }
        if (intentWeb.resolveActivity(context.packageManager) != null) {
            browserListener?.invoke(true)
            context.startActivity(Intent.createChooser(intentWeb, title))
        } else {
            //手机上没有安装浏览器
            browserListener?.invoke(false)
        }
    }


    /**
     * 启动系统相机
     */
    @JvmStatic
    fun launchCamera(activity: Activity, requestCode: Int) {
        activity.startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE), requestCode)
    }

    /***
     * 跳转到选择文件
     */
    fun startSelectFile(mContext: Activity, reqeustCode: Int) {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "*/*"
        }
        mContext.startActivityForResult(intent, reqeustCode)
    }
   /***
    * 获取文件选择内容
    */
    @Throws(IOException::class)
     fun readTextFromUri(mActivity: Activity, uri: Uri): String {
        val stringBuilder = StringBuilder()
        mActivity.contentResolver.openInputStream(uri)?.use { inputStream ->
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