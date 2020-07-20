package com.backpacker.yflLibrary.kotlin

import com.backpacker.yflLibrary.java.JavaStringUtil
import com.backpacker.yflLibrary.java.JavaTimeUtil
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


    /**
     * 返回yyyy-MM-DD hh:mm:ss
     *
     * @param datestr 处理：2015-12-22 08:49:21.0
     * @return
     */
    fun getCommonDateStr(datestr: String): String? {
        if (KotlinStringUtil.isEmpty(datestr) || datestr.length <= 19)
            return datestr
        val tmpStr = datestr.substring(0, 19)
        val date = strToDate(tmpStr, null) ?: return tmpStr
        return getChatTime(date.time)
    }

    fun getChatTime(time: Long): String {
        val result = ""
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return sdf.format(time)


    }

    /**
     * 字符串转日期
     *
     * @param str 字符串
     * @param def 默认时间，如果转换失败则返回默认时间
     */
    fun strToDate(str: String?, def: Date?): Date? {
        return strToDate(str, "yyyy-MM-dd HH:mm:ss", def)
    }

    /**
     * 字符串转日期
     *
     * @param str 字符串
     * @param def 默认时间，如果转换失败则返回默认时间
     */
    fun strToDate(str: String?, formatstr: String, def: Date?): Date? {
        if (KotlinStringUtil.isEmpty(str))
            return def
        try {
            val sdf = SimpleDateFormat(formatstr)
            return sdf.parse(str)
        } catch (e: Exception) {
            return def
        }

    }


    /**
     * 计算当前时间-提供的时间间隔
     *
     * @param str
     * @return
     */
    fun intervalNow(str: String?): Long {
        return intervalNow(strToDate(str, null))
    }

    /**
     * 计算当前时间-提供的时间间隔
     *
     * @param date
     * @return
     */
    fun intervalNow(date: Date?): Long {
        return if (date == null) Date().time else Date().time - date.time
    }

    //截取年月日
    fun getYMDT(str: String): String {
        var time = ""
        if (!KotlinStringUtil.isEmpty(str)) {
            val x = str.indexOf(" ")
            if (x == -1)
                return str
            time = str.substring(0, x)
        }
        return time
    }

    /**
     * 字符传转换成long
     * @param time
     * @return
     */
    fun getTimeWString(time: String): Long {
        if (KotlinStringUtil.isEmpty(time)) {
            return 0
        }
        val date = strToDate(time, null) ?: return 0
        return date.time
    }

    /**
     * 字符传转换成long
     *
     * @param time
     * @return
     */
    fun getTimeWString(time: String?, def: String?): Long {
        if (JavaStringUtil.isEmpty(time)) {
            return 0
        }
        val date = JavaTimeUtil.strToDate(time, def, null) ?: return 0
        return date.time
    }

    fun getNewAfaterLastDay(time: String): String? {

        val oldTime = getTimeWString(time,"yyyy-MM-dd")
        val instance = Calendar.getInstance()
        val s = "${instance.get(Calendar.YEAR)}-${instance.get(Calendar.MONTH)+1}-${instance.get(Calendar.DATE)}"
        val now = JavaTimeUtil.getTimeWString(
            s,"yyyy-MM-dd"
        )
        if (oldTime == now) {
            return "今天"
        }
        val after = now + JavaTimeUtil.time
        if (oldTime == after)
            return "明天"
        val last = now + (2 * JavaTimeUtil.time)
        if (oldTime==last)
            return "后天"

        return ""
    }
}