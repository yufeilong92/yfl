package com.backpacker.yflLibrary.kotlin

import android.text.TextUtils
import android.util.Log


/**
 * @Title:  kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.kotlin
 * @Description:    $todo$
 * @author: L-BackPacker
 * @date:   2019/3/31 23:51
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
object LogUtil {

    val VERBOSE = 1
    val DEBUG = 2
    val INFO = 3
    val WARN = 4
    val ERROR = 5
    val NOTHING = 6
    //修改其等级不打log
    val LEVEL = VERBOSE
    val SEPARATOR = ","

    fun v(message: String) {
        if (LEVEL <= VERBOSE) {
            val stackTraceElement = Thread.currentThread().stackTrace[3]
            val tag = getDefaultTag(stackTraceElement)
            Log.v(tag, getLogInfo(stackTraceElement) + message)
        }
    }

    fun v(tag: String, message: String) {
        var tag = tag
        if (LEVEL <= VERBOSE) {
            val stackTraceElement = Thread.currentThread().stackTrace[3]
            if (TextUtils.isEmpty(tag)) {
                tag = getDefaultTag(stackTraceElement)
            }
            Log.v(tag, getLogInfo(stackTraceElement) + message)
        }
    }

    fun d(message: String) {
        if (LEVEL <= DEBUG) {
            val stackTraceElement = Thread.currentThread().stackTrace[3]
            val tag = getDefaultTag(stackTraceElement)
            Log.d(tag, getLogInfo(stackTraceElement) + message)
        }
    }

    fun d(tag: String, message: String) {
        var tag = tag
        if (LEVEL <= DEBUG) {
            val stackTraceElement = Thread.currentThread().stackTrace[3]
            if (TextUtils.isEmpty(tag)) {
                tag = getDefaultTag(stackTraceElement)
            }
            Log.d(tag, getLogInfo(stackTraceElement) + message)
        }
    }

    fun i(message: String) {
        if (LEVEL <= INFO) {
            val stackTraceElement = Thread.currentThread().stackTrace[3]
            val tag = getDefaultTag(stackTraceElement)
            Log.i(tag, getLogInfo(stackTraceElement) + message)
        }
    }

    fun i(tag: String, message: String) {
        var tag = tag
        if (LEVEL <= INFO) {
            val stackTraceElement = Thread.currentThread().stackTrace[3]
            if (TextUtils.isEmpty(tag)) {
                tag = getDefaultTag(stackTraceElement)
            }
            Log.i(tag, getLogInfo(stackTraceElement) + message)
        }
    }

    fun w(message: String) {
        if (LEVEL <= WARN) {
            val stackTraceElement = Thread.currentThread().stackTrace[3]
            val tag = getDefaultTag(stackTraceElement)
            Log.w(tag, getLogInfo(stackTraceElement) + message)
        }
    }

    fun w(tag: String, message: String) {
        var tag = tag
        if (LEVEL <= WARN) {
            val stackTraceElement = Thread.currentThread().stackTrace[3]
            if (TextUtils.isEmpty(tag)) {
                tag = getDefaultTag(stackTraceElement)
            }
            Log.w(tag, getLogInfo(stackTraceElement) + message)
        }
    }

    fun e(message: String) {
        if (LEVEL <= ERROR) {
            val stackTraceElement = Thread.currentThread().stackTrace[3]
            val tag = getDefaultTag(stackTraceElement)
            Log.e(tag, getLogInfo(stackTraceElement) + message)
        }
    }

    fun e(tag: String, message: String) {
        var tag = tag
        if (LEVEL <= ERROR) {
            val stackTraceElement = Thread.currentThread().stackTrace[3]
            if (TextUtils.isEmpty(tag)) {
                tag = getDefaultTag(stackTraceElement)
            }
            Log.e(tag, getLogInfo(stackTraceElement) + message)
        }
    }

    /**
     * 获取默认的TAG名称.
     * 比如在MainActivity.java中调用了日志输出.
     * 则TAG为MainActivity
     */
    fun getDefaultTag(stackTraceElement: StackTraceElement): String {
        val fileName = stackTraceElement.fileName
        val stringArray = fileName.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return stringArray[0]
    }

    /**
     * 输出日志所包含的信息
     */
    fun getLogInfo(stackTraceElement: StackTraceElement): String {
        val logInfoStringBuilder = StringBuilder()
        // 获取线程名
        val threadName = Thread.currentThread().name
        // 获取线程ID
        val threadID = Thread.currentThread().id
        // 获取文件名.即xxx.java
        val fileName = stackTraceElement.fileName
        // 获取类名.即包名+类名
        val className = stackTraceElement.className
        // 获取方法名称
        val methodName = stackTraceElement.methodName
        // 获取生日输出行数
        val lineNumber = stackTraceElement.lineNumber

        logInfoStringBuilder.append("[ ")
        //        logInfoStringBuilder.append("threadID=" + threadID).append(SEPARATOR);
        //        logInfoStringBuilder.append("threadName=" + threadName).append(SEPARATOR);
        logInfoStringBuilder.append("fileName=$fileName").append(SEPARATOR)
        //        logInfoStringBuilder.append("className=" + className).append(SEPARATOR);
        logInfoStringBuilder.append("methodName=$methodName").append(SEPARATOR)
        logInfoStringBuilder.append("lineNumber=$lineNumber")
        logInfoStringBuilder.append(" ] ")
        return logInfoStringBuilder.toString()
    }
}