package com.backpacker.yflLibrary.kotlin

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

/**
 * @Title:  kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.kotlin
 * @Description:    $todo$
 * @author: L-BackPacker
 * @date:   2019/3/31 23:14
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
class KotlinAppManager {
    companion object {

        private var activityStack: Stack<Activity>? = null
        private var mInstance: KotlinAppManager? = null

        /**
         * 单例模式实例
         */
        val kotlinAppManager: KotlinAppManager
            get() {
                if (mInstance == null) {
                    mInstance = KotlinAppManager()
                }
                return mInstance!!
            }
    }
    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity) {
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack!!.add(activity)

    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    fun currentActivity(): Activity {
        return activityStack!!.lastElement()
    }

    /**
     * 获取当前activity的个数
     * @return
     */
    fun activityCount(): Int {
        return if (activityStack == null) {
            0
        } else activityStack!!.size
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishActivity() {
        var activitys: Activity? = activityStack!!.lastElement()
        if (activitys != null) {
            activitys.finish()
            activitys = null
        }
    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(activity: Activity?) {
        var activitys = activity
        if (activitys != null) {
            activityStack!!.remove(activitys)
            activitys.finish()
            activitys = null
        }
    }

    /**
     * 结束指定类名的Activity
     */
    fun finishActivity(cls: Class<*>) {
        for (activity in activityStack!!) {
            if (activity.javaClass == cls) {
                finishActivity(activity)
                return
            }
        }
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        var i = 0
        val size = activityStack!!.size
        while (i < size) {
            if (null != activityStack!![i]) {
                activityStack!![i].finish()
            }
            i++
        }
        activityStack!!.clear()
    }

    /**
     * 退出应用程序
     */
    @SuppressLint("NewApi")
    fun AppExit(context: Context) {
        try {
            finishAllActivity()
            val activityMgr = context
                .getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            activityMgr.restartPackage(context.packageName)
            System.exit(0)
        } catch (e: Exception) {
        }

    }


}