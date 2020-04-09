package com.backpacker.yflLibrary.kotlin

import android.content.Context
import android.net.ConnectivityManager

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.backpacker.UtilsLibrary.kotlin
 * @Email : yufeilong92@163.com
 * @Time :2019/7/5 17:57
 * @Purpose :
 */
class NetWork {
    fun getNetworkType(context: Context): Int {
        var netType = 0
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo ?: return netType
        val nType = networkInfo.type
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            val extraInfo:String = networkInfo.extraInfo
            if (extraInfo != null && "" == extraInfo) {
                if(extraInfo == "cmnet") {
                    netType = NETTYPE_CMNET
                } else {
                    netType = NETTYPE_CMWAP
                }
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = NETTYPE_WIFI
        }
        return netType
    }

    companion object {

        /**
         * 检测网络是否可用
         *
         * @return
         */
        fun isNetworkConnected(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val ni = cm.activeNetworkInfo
            return ni != null && ni.isConnectedOrConnecting
        }

        /**
         * 获取当前网络类型
         *
         * @return 0：没有网络 1：WIFI网络 2：WAP网络 3：NET网络
         */

        val NETTYPE_WIFI = 0x01
        val NETTYPE_CMWAP = 0x02
        val NETTYPE_CMNET = 0x03
    }
}