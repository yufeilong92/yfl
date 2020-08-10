package com.backpacker.yflLibrary.kotlin

import com.backpacker.yflLibrary.java.JavaStringUtil
import com.backpacker.yflLibrary.java.JavaTimeUtil
import java.text.ParseException
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
    const val WEEKDAYS = 7
    private var formatBuilder: SimpleDateFormat? = null
    var WEEK = arrayOf("周日", "周一", "周二", "周三", "周四", "周五", "周六")
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

    /**
     * 比较当前时间和服务器返回时间大小
     *
     * @param start
     * @param end
     * @return
     */
    fun compareDate(start: String, end: String): Boolean {
        val df = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        return try {
            val now = df.parse(start)
            val end = df.parse(end)
            now.before(end)
        } catch (e: ParseException) {
            e.printStackTrace()
            false
        }
    }

    fun generateTime(time: Long): String {
        val totalSeconds = (time / 1000).toInt()
        val seconds = totalSeconds % 60
        val minutes = totalSeconds / 60 % 60
        val hours = totalSeconds / 3600
        return if (hours > 0) String.format("%02d:%02d:%02d", hours, minutes, seconds) else String.format("%02d:%02d", minutes, seconds)
    }

    /**
     * 根据long毫秒数，获得时分秒
     */
    fun getDateFormatByLong(time: Long): String {
        val totalSeconds = (time / 1000).toInt()
        val seconds = totalSeconds % 60
        val minutes = totalSeconds / 60 % 60
        val hours = totalSeconds / 3600
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }


    /**
     * 以友好的方式显示时间
     * @param time
     * *
     * @return
     */
    fun getFriendlyTime(time: Date): String {
        //获取time距离当前的秒数
        val ct = ((System.currentTimeMillis() - time.time) / 1000).toInt()
        if (ct == 0) {
            return "刚刚"
        }
        if (ct in 1..59) {
            return ct.toString() + "秒前"
        }
        if (ct in 60..3599) {
            return Math.max(ct / 60, 1).toString() + "分钟前"
        }
        if (ct in 3600..86399)
            return (ct / 3600).toString() + "小时前"
        if (ct in 86400..2591999) { //86400 * 30
            val day = ct / 86400
            return day.toString() + "天前"
        }
        if (ct in 2592000..31103999) { //86400 * 30
            return (ct / 2592000).toString() + "月前"
        }
        return (ct / 31104000).toString() + "年前"
    }

    /**
     * 获取当前时间为本月的第几周
     *
     * @return WeekOfMonth
     */
    fun getWeekOfMonth(): Int {
        val calendar = Calendar.getInstance()
        val week = calendar[Calendar.WEEK_OF_MONTH]
        return week - 1
    }

    /**
     * 获取当前时间为本周的第几天
     *
     * @return DayOfWeek
     */
    fun getDayOfWeek(): Int {
        val calendar = Calendar.getInstance()
        var day = calendar[Calendar.DAY_OF_WEEK]
        day = if (day == 1) {
            7
        } else {
            day - 1
        }
        return day
    }

    /**
     * UTM转换成日期描述，如三周前，上午，昨天等
     *
     * @param milliseconds milliseconds
     * @param isShowWeek 是否采用周的形式显示  true 显示为3周前，false 则显示为时间格式mm-dd
     * @return 如三周前，上午，昨天等
     */
    fun getTimeDesc(milliseconds: Long, isShowWeek: Boolean): String? {
        val sb = StringBuffer()
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliseconds
        val hour = calendar[Calendar.HOUR_OF_DAY].toLong()
        calendar.timeInMillis = System.currentTimeMillis()
        val hourNow = calendar[Calendar.HOUR_OF_DAY].toLong()
        val datetime = System.currentTimeMillis() - milliseconds
        val day =
            Math.floor(datetime / 24 / 60 / 60 / 1000.0f.toDouble())
                .toLong() + if (hourNow < hour) 1 else 0 // 天前
        if (day <= 7) { // 一周内
            if (day == 0L) { // 今天
                if (hour <= 4) {
                    sb.append(" 凌晨 ")
                } else if (hour in 5..6) {
                    sb.append(" 早上 ")
                } else if (hour in 7..11) {
                    sb.append(" 上午 ")
                } else if (hour in 12..13) {
                    sb.append(" 中午 ")
                } else if (hour in 14..18) {
                    sb.append(" 下午 ")
                } else if (hour in 19..19) {
                    sb.append(" 傍晚 ")
                } else if (hour in 20..24) {
                    sb.append(" 晚上 ")
                } else {
                    sb.append("今天 ")
                }
            } else if (day == 1L) { // 昨天
                sb.append(" 昨天 ")
            } else if (day == 2L) { // 前天
                sb.append(" 前天 ")
            } else {
                sb.append(" " + DateToWeek(milliseconds).toString() + " ")
            }
        } else { // 一周之前
            if (isShowWeek) {
                sb.append((if (day % 7 == 0L) day / 7 else day / 7 + 1).toString() + "周前")
            } else {
                formatBuilder = SimpleDateFormat("MM-dd")
                val time: String = formatBuilder!!.format(milliseconds)
                sb.append(time)
            }
        }
        return sb.toString()
    }


    /**
     * UTM转换成带描述的日期
     *
     * @param milliseconds milliseconds
     * @return UTM转换成带描述的日期
     */
    fun getDisplayTimeAndDesc(milliseconds: Long): String? {
        formatBuilder = SimpleDateFormat("HH:mm")
        val time = formatBuilder!!.format(milliseconds)
        val sb = StringBuffer()
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliseconds
        val hour = calendar[Calendar.HOUR_OF_DAY].toLong()
        val datetime = System.currentTimeMillis() - milliseconds
        val day =
            Math.ceil(datetime / 24 / 60 / 60 / 1000.0f.toDouble()).toLong() // 天前
        if (day <= 7) { // 一周内
            if (day == 0L) { // 今天
                if (hour <= 4) {
                    sb.append(" 凌晨 $time")
                } else if (hour in 5..6) {
                    sb.append(" 早上 $time")
                } else if (hour in 7..11) {
                    sb.append(" 上午 $time")
                } else if (hour in 12..13) {
                    sb.append(" 中午 $time")
                } else if (hour in 14..18) {
                    sb.append(" 下午 $time")
                } else if (hour in 19..19) {
                    sb.append(" 傍晚 $time")
                } else if (hour in 20..24) {
                    sb.append(" 晚上 $time")
                } else {
                    sb.append("今天 $time")
                }
            } else if (day == 1L) { // 昨天
                sb.append("昨天 $time")
            } else if (day == 2L) { // 前天
                sb.append("前天 $time")
            } else {
                sb.append(DateToWeek(milliseconds).toString() + time)
            }
        } else { // 一周之前
            sb.append((day % 7).toString() + "周前")
        }
        return sb.toString()
    }
    /**
     * 日期变量转成对应的星期字符串
     *
     * @param milliseconds data
     * @return 日期变量转成对应的星期字符串
     */
    fun DateToWeek(milliseconds: Long): String? {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliseconds
        val dayIndex = calendar[Calendar.DAY_OF_WEEK]
        return if (dayIndex < 1 || dayIndex > WEEKDAYS) {
            null
        } else WEEK.get(dayIndex - 1)
    }
}