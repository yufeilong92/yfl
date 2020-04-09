package com.backpacker.yflLibrary.kotlin

import android.content.Context
import android.util.DisplayMetrics

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.backpacker.UtilsLibrary.kotlin
 * @Email : yufeilong92@163.com
 * @Time :2019/7/11 15:05
 * @Purpose :
 */
object AppBarUtil {
    /**
     * 获取屏幕的尺寸信息
     */
    fun obtainScreenWH(mContext: Context) {
        var statusBarHeight = -1
        val resourceId = mContext.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            statusBarHeight = mContext.resources.getDimensionPixelSize(resourceId)
        }

        Constant.statusHeight = statusBarHeight

        val metrics: DisplayMetrics = mContext.resources.displayMetrics
        Constant.screenHeight = metrics.heightPixels
        Constant.screenWidth = metrics.widthPixels

    }

}