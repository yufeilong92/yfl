package com.backpacker.yflLibrary.kotlin

import java.text.SimpleDateFormat
import java.util.*

/**
 * @Title:  kotlin_androidone
 * @Package com.example.myutils
 * @Description:    时间工具累
 * @author: L-BackPacker
 * @date:   2019/3/31 20:46
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
object KotlinDateUtil {
    private val PATTERN_YMDHMS: String = "yyyy-MM-dd HH:mm:ss"
    private val PATTERN_HMS: String = "yyyy-MM-dd HH:mm:ss"
    private val PATTERN_YMD: String = "yyyy-MM-dd"
    private val PATTERN_YMDHMS_CHINESE: String = "yyyy年MM月dd日 HH时mm分ss秒"
    private val PATTERN_HMS_CHINESE: String = "yyyy年MM月dd日 HH时mm分ss秒"
    private val PATTERN_YMD_CHINESE: String = "yyyy年MM月dd日"

    /**
     * 获取年月日时分秒
     */
    fun getNewDateTime(): String {
        val smf = SimpleDateFormat(PATTERN_YMDHMS)
        return smf.format(Date())
    }

    /**
     * 获取年月日时分秒
     */
    fun getNewDateTimeWithChinese(): String {
        val smf = SimpleDateFormat(PATTERN_YMDHMS_CHINESE)
        return smf.format(Date())
    }

    /**
     * 获取年月日
     */
    fun getNewDate(): String {
        val sdf = SimpleDateFormat(PATTERN_YMD)
        return sdf.format(Date())
    }

    /**
     * 获取年月日
     */
    fun getNewDateWithChinese(): String {
        val sdf = SimpleDateFormat(PATTERN_YMD_CHINESE)
        return sdf.format(Date())
    }

    /**
     * 时分秒
     */
    fun getNewTime(): String {
        val sdf = SimpleDateFormat(PATTERN_HMS)
        return sdf.format(Date())
    }

    /**
     * 时分秒
     */
    fun getNewTimeWithChinese(): String {
        val sdf = SimpleDateFormat(PATTERN_HMS_CHINESE)
        return sdf.format(Date())
    }

    fun getDate(time: String?): String {
        if (KotlinStringUtil.isEmpty(time)) return getNewDateTime()
        val sdf = SimpleDateFormat(PATTERN_YMDHMS)
        return sdf.format(time)
    }
    fun getDateChinese(time: String?): String {
        if (KotlinStringUtil.isEmpty(time)) return getNewDateTime()
        val sdf = SimpleDateFormat(PATTERN_YMDHMS_CHINESE)
        return sdf.format(time)
    }
    fun gettTime(time: String?): String {
        if (KotlinStringUtil.isEmpty(time)) return getNewTime()
        val sdf = SimpleDateFormat(PATTERN_HMS)
        return sdf.format(time)
    }

    fun gettTimeChinese(time: String?): String {
        if (KotlinStringUtil.isEmpty(time)) return getNewTimeWithChinese()
        val sdf = SimpleDateFormat(PATTERN_HMS_CHINESE)
        return sdf.format(time)
    }

    fun gettDateChinese(time: String?): String {
        if (KotlinStringUtil.isEmpty(time)) return getNewDateWithChinese()
        val sdf = SimpleDateFormat(PATTERN_YMD_CHINESE)
        return sdf.format(time)
    }

    fun gettDate(time: String?): String {
        if (KotlinStringUtil.isEmpty(time)) return getNewDate()
        val sdf = SimpleDateFormat(PATTERN_YMD)
        return sdf.format(time)
    }


}