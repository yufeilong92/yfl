package com.backpacker.yflLibrary.kotlin


import com.backpacker.yflLibrary.java.TimeUtil
import java.text.SimpleDateFormat
import java.util.*



/**
 * @Title:  kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.kotlin
 * @Description:    时间工具累
 * @author: L-BackPacke
 * @date:   2019/3/31 22:08
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
object KotlinTimeUtil {

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



    fun getNewAfaterLastDay(time: String): String? {

        val oldTime = TimeUtil.getTimeWString(time,"yyyy-MM-dd")
        val instance = Calendar.getInstance()
        val s = "${instance.get(Calendar.YEAR)}-${instance.get(Calendar.MONTH)+1}-${instance.get(Calendar.DATE)}"
        val now = TimeUtil.getTimeWString(
            s,"yyyy-MM-dd"
        )
        if (oldTime == now) {
            return "今天"
        }
        val after = now + TimeUtil.time
        if (oldTime == after)
            return "明天"
        val last = now + (2 * TimeUtil.time)
        if (oldTime==last)
            return "后天"

        return ""
    }
}