package com.backpacker.yflLibrary.java;


import android.util.Log;

import com.backpacker.yflLibrary.kotlin.T;
import com.example.UtilsLibrary.R;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: kotlin_androidone
 * @Package com.backpacker.UtilsLibrary
 * @Description: $todo
 * @author: L-BackPacker
 * @date: 2019/3/31 21:30
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
public class TimeUtil {
    public static final int CINT_TIME_SECOND = 1000;
    public static final int CINT_TIME_MINUTE = 60 * 1000;
    public static final int CINT_TIME_HOUR = 3600 * 1000;
    public static final int CINT_TIME_DAY = 24 * 3600 * 1000;

    /**
     * 获取当前时间戳
     *
     * @param date 当时时间
     * @return
     */
    public static String getStringTimeStamp(String date) {
        if (JavaStringUtil.isEmpty(date)) {
            return "";
        }
        String commonDateStr = TimeUtil.getCommonDateStr(date);
        long nowTime = TimeUtil.intervalNow(commonDateStr);
        String result = "";
        long ms = nowTime / 1000;
        BigDecimal decimal = new BigDecimal(ms).setScale(0, BigDecimal.ROUND_HALF_UP);
        //秒数
        long longNow = decimal.longValue();
        long temp = 0;
        if (longNow < 60) {
            result = "刚刚";
        } else if ((temp = longNow / 60) < 60) {
            result = temp + "分前";
        } else if ((temp = temp / 60) < 24) {
            result = temp + "小时前";
        } else if ((temp = temp / 24) < 30) {
            result = temp + "天前";
        } else if ((temp = temp / 30) < 12) {
            result = temp + "月前";
        } else {
            temp = temp / 12;
            result = temp + "年前";
        }
        return result;
    }

    /**
     * 返回yyyy-MM-DD hh:mm:ss
     *
     * @param datestr 处理：2015-12-22 08:49:21.0
     * @return
     */
    public static String getCommonDateStr(String datestr) {
        if (JavaStringUtil.isEmpty(datestr) || datestr.length() <= 19)
            return datestr;
        String tmpStr = datestr.substring(0, 19);
        Date date = strToDate(tmpStr, null);
        if (date == null)
            return tmpStr;
        return getChatTime(date.getTime());
    }

    public static String getChatTime(long time) {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(time);


    }

    /**
     * 字符串转日期
     *
     * @param str 字符串
     * @param def 默认时间，如果转换失败则返回默认时间
     */
    public static Date strToDate(String str, Date def) {
        return strToDate(str, "yyyy-MM-dd HH:mm:ss", def);
    }

    /**
     * 字符串转日期
     *
     * @param str 字符串
     * @param def 默认时间，如果转换失败则返回默认时间
     */
    public static Date strToDate(String str, String formatstr, Date def) {
        if (JavaStringUtil.isEmpty(str))
            return def;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formatstr);
            return sdf.parse(str);
        } catch (Exception e) {
            return def;
        }
    }


    /**
     * 计算当前时间-提供的时间间隔
     *
     * @param str
     * @return
     */
    public static long intervalNow(String str) {
        return intervalNow(strToDate(str, null));
    }

    /**
     * 计算当前时间-提供的时间间隔
     *
     * @param date
     * @return
     */
    public static long intervalNow(Date date) {
        if (date == null)
            return (new Date()).getTime();
        return (new Date()).getTime() - date.getTime();
    }

    //截取年月日
    public static String getYMDT(String str) {
        String time = "";
        if (!JavaStringUtil.isEmpty(str)) {
            int x = str.indexOf(" ");
            if (x == -1)
                return str;
            time = str.substring(0, x);
        }
        return time;
    }

    /**
     * 字符传转换成long
     *
     * @param time
     * @return
     */
    public static long getTimeWString(String time) {
        if (JavaStringUtil.isEmpty(time)) {
            return 0;
        }
        Date date = strToDate(time, null);
        if (date == null)
            return 0;
        return date.getTime();
    }

    //毫秒换成00:00:00
    public static String getCountTimeByLong(long finishTime) {
        int totalTime = (int) (finishTime / 1000);//秒
        int hour = 0, minute = 0, second = 0;

        if (3600 <= totalTime) {
            hour = totalTime / 3600;
            totalTime = totalTime - 3600 * hour;
        }
        if (60 <= totalTime) {
            minute = totalTime / 60;
            totalTime = totalTime - 60 * minute;
        }
        if (0 <= totalTime) {
            second = totalTime;
        }
        StringBuilder sb = new StringBuilder();

        if (hour < 10) {
            sb.append("0").append(hour).append(":");
        } else {
            sb.append(hour).append(":");
        }
        if (minute < 10) {
            sb.append("0").append(minute).append(":");
        } else {
            sb.append(minute).append(":");
        }
        if (second < 10) {
            sb.append("0").append(second);
        } else {
            sb.append(second);
        }
        return sb.toString();

    }

    //秒换成00:00:00
    public static String getCountTimeByLongOne(int totalTime) {
//        int totalTime = (int) (finishTime / 1000);//秒
        int hour = 0, minute = 0, second = 0;

        if (3600 <= totalTime) {
            hour = totalTime / 3600;
            totalTime = totalTime - 3600 * hour;
        }
        if (60 <= totalTime) {
            minute = totalTime / 60;
            totalTime = totalTime - 60 * minute;
        }
        if (0 <= totalTime) {
            second = totalTime;
        }
        StringBuilder sb = new StringBuilder();

        if (hour < 10) {
            sb.append("0").append(hour).append(":");
        } else {
            sb.append(hour).append(":");
        }
        if (minute < 10) {
            sb.append("0").append(minute).append(":");
        } else {
            sb.append(minute).append(":");
        }
        if (second < 10) {
            sb.append("0").append(second);
        } else {
            sb.append(second);
        }
        return sb.toString();

    }

    /**
     * 日期转为字符串
     *
     * @param date 如果为空，返回当前时间
     * @return
     */
    public static String dateToString(Date date) {
        if (date == null)
            date = new Date();
        return dateToString(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 日期转为字符串
     *
     * @param date 如果为空，返回当前时间
     * @return
     */
    public static String dateToStringOne(Date date) {
        if (date == null)
            date = new Date();
        return dateToString(date, "yyyy/MM/dd HH:mm:ss");
    }

    /**
     * 日期转为字符串
     *
     * @param date 如果为空，返回当前时间
     * @return
     */
    public static String dateToStringYMD(Date date) {
        if (date == null)
            date = new Date();
        return dateToString(date, "yyyy-MM-dd");
    }

    /**
     * 日期转为字符串
     *
     * @param date         如果为空，返回当前时间
     * @param formatstring 如果为空，则默认格式yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String dateToString(Date date, String formatstring) {
        if (formatstring == null || formatstring.equals(""))
            formatstring = "yyyy-MM-dd HH:mm:ss";
        if (date == null)
            date = new Date();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formatstring);
            return sdf.format(date);
        } catch (Exception e) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(date);
        }
    }

    public static long getTime() {
        return new Date().getTime();
    }

    /**
     * 是否超过一天
     *
     * @param str
     * @return
     */
    //一天间隔时间（毫秒）
    public static long time = 86400000;

    public static boolean isMore(String str) {
        long l1 = intervalNow(strToDate(str, null));
        long l = l1 - time;
        if (l > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 是否超过一天
     *
     * @param str
     * @return
     */
    public static long hourtime = 3600000;

    public static boolean isMoreHour(String str) {
        long l1 = intervalNow(strToDate(str, null));
        long l = l1 - hourtime;
        if (l > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 返回两个时间的间隔(取绝对值)，单位ms
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long interval(Date date1, Date date2) {
        if (date1 == null && date2 == null)
            return 0;
        if (date1 == null)
            return date2.getTime();
        if (date2 == null)
            return date1.getTime();
        return Math.abs(date1.getTime() - date2.getTime());
    }

    /**
     * @param date
     * @param formatstr
     * @return 字符串转化
     */
    public static String longToStr(long date, String formatstr) {
        return dateToString(new Date(date), formatstr);
    }

    /**
     * @param time
     * @return 月日
     */
    public static String getTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日 HH:mm");
        return format.format(new Date(time));
    }

    /**
     * @param time
     * @return 月日
     */
    public static String getTimes(long time) {
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
        return format.format(new Date(time));
    }

    /***
     *
     * @param time
     * @return 小时
     */
    public static String getHourAndMin(long time) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(new Date(time));
    }

    //截取年
    public static int getYearTime(String str) {
        int time = 0;
        if (!JavaStringUtil.isEmpty(str)) {
            int x = str.indexOf("-");
            try {
                time = Integer.parseInt(str.substring(0, x));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return time;
    }

    /***
     * 获取年
     * @return
     */
    public static String getCurrentYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        return sdf.format(date);
    }

    /**
     * 判断时间是不是今天
     *
     * @param date
     * @return 是返回true，不是返回false
     */
    public static boolean isNow(Date date) {
        //当前时间
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        //获取今天的日期
        String nowDay = sf.format(now);
        //对比的时间
        String day = sf.format(date);
        return day.equals(nowDay);
    }

    /**
     * 用于显示时间
     */
    public static final String TODAY = "今天";
    public static final String YESTERDAY = "昨天";
    public static final String TOMORROW = "明天";
    public static final String BEFORE_YESTERDAY = "前天";
    public static final String AFTER_TOMORROW = "后天";

    /**
     * 获取对应时间戳转换的今天、明天。。。。的日期 * @param time * @return
     */
    public static String getToday(String time) {
        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);

        Calendar cal = Calendar.getInstance();

        Date date = strToDate(time, "yyyy-MM-dd HH:mm:ss", new Date());
        cal.setTime(date);

        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            return showDateDetail(diffDay, time);

        }
        return time;
    }

    /**
     * 将日期差显示为今天、明天或者星期 * @param diffDay * @param time * @return
     */
    private static String showDateDetail(int diffDay, String time) {
        switch (diffDay) {
            case 0:
                return TODAY;
            case 1:
                return TOMORROW;
            case 2:
                return AFTER_TOMORROW;
            case -1:
                return YESTERDAY;
            case -2:
                return BEFORE_YESTERDAY;
            default:
                return getWeek(time);
        }
    }

    /**
     * 计算周几
     */
    public static String getWeek(String data) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        long lcc = Long.valueOf(data);
        int i = Integer.parseInt(data);
        String times = sdr.format(new Date(i * 1000L));
        Date date = null;
        int mydate = 0;
        String week = "";
        try {
            date = sdr.parse(times);
            Calendar cd = Calendar.getInstance();
            cd.setTime(date);
            mydate = cd.get(Calendar.DAY_OF_WEEK);
            // 获取指定日期转换成星期几
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (mydate == 1) {
            week = "星期日";
        } else if (mydate == 2) {
            week = "星期一";
        } else if (mydate == 3) {
            week = "星期二";
        } else if (mydate == 4) {
            week = "星期三";
        } else if (mydate == 5) {
            week = "星期四";
        } else if (mydate == 6) {
            week = "星期五";
        } else if (mydate == 7) {
            week = "星期六";
        }
        return week;
    }

    public static String getFormatTime(String time, DateFormatUtil type) {
        if (JavaStringUtil.isEmpty(time)) {
            return new Date().toString();
        }
        String isFormattType = type.getValue();
        SimpleDateFormat sdf = new SimpleDateFormat(DateFormatUtil.YMDHMS.getValue());
        Date date=null;
        try {
            date=sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat format = new SimpleDateFormat(isFormattType);
        return format.format(new Date(date.getTime()));

    }

    /***
     * 时间格式
     */
    public static enum DateFormatUtil {
        YMDHMS("yyyy-MM-dd HH:mm:ss"), YMD("yyyy-MM-dd"),
        HMS("HH:mm:ss"), MD("MM-dd"), HM("HH:mm");
        String value;

        DateFormatUtil(String yy) {
            this.value = yy;
        }

        public String getValue() {
            return value;
        }
    }

    /***
     * @param time 时间
     * @param type 格式类型
     * @param isFormat 是否格式化
     *
     * @return 今天 ，明天， 后天   时间
     */
    public static String getDifDay(String time, DateFormatUtil type, Boolean isFormat) {
        String content = "";
        if (JavaStringUtil.isEmpty(time)) {
            return content;
        }
        String isFormattType = "yyyy-MM-dd HH:mm:ss";
        if (isFormat) {
            isFormattType = type.getValue();
        }
        SimpleDateFormat sdf = new SimpleDateFormat(isFormattType);
        Calendar nowData = Calendar.getInstance();
        try {
            Date parse = sdf.parse(time);
            time = sdf.format(parse);
            if (parse == null) {
                content = "";
            } else {
                Calendar date = Calendar.getInstance();
                date.setTime(parse);

                //判断同年，判断同月 判断同日
                int nowDataYear = nowData.get(Calendar.YEAR);
                int year = date.get(Calendar.YEAR);
                int difYear = nowDataYear - year;
                //刷选的月数
                int monthZone = date.get(Calendar.MONTH);
                //当前的月数
                int nowmonthZone = nowData.get(Calendar.MONTH);

                //筛选天数
                int dayZone = date.get(Calendar.DAY_OF_MONTH);
                //当前天数
                int nowDataDayZone = nowData.get(Calendar.DAY_OF_MONTH);
                switch (difYear) {
                    case 1://前年
                        switch (monthZone) {//当前月数
                            case 11:
                                switch (nowmonthZone) {//刷选月数
                                    case 0://刷选为一月
                                        //当前天数的最后一天
                                        date.set(Calendar.DAY_OF_MONTH, nowData.getActualMaximum(Calendar.DAY_OF_MONTH));
                                        int beforMaxDay = date.get(Calendar.DAY_OF_MONTH);
                                        int difNowDay = beforMaxDay - dayZone;
                                        switch (difNowDay) {
                                            case 0://该月最后一天
                                                switch (nowDataDayZone) {
                                                    case 1:
                                                        content = "昨天";
                                                        break;
                                                    case 2:
                                                        content = "前天";
                                                        break;
                                                    default:
                                                        content = time;
                                                        break;
                                                }
                                                break;
                                            case 1:
                                                switch (nowDataDayZone) {
                                                    case 1:
                                                        content = "前天";
                                                        break;
                                                    default:
                                                        content = time;
                                                        break;
                                                }

                                                break;
                                            default:
                                                content = time;
                                                break;
                                        }

                                        break;
                                    default:
                                        content = time;
                                        break;
                                }
                                break;
                            default:
                                content = time;
                                break;
                        }


                        break;
                    case 0:
                        int differenceMonthZone = nowmonthZone - monthZone;


                        switch (differenceMonthZone) {
                            case 1://上个月
                                Calendar instanceZone = Calendar.getInstance();
                                instanceZone.set(Calendar.MONTH, nowmonthZone - 1);
                                instanceZone.set(Calendar.DAY_OF_MONTH, instanceZone.getActualMaximum(Calendar.DAY_OF_MONTH));
                                int beforMaxDayZon = instanceZone.get(Calendar.DAY_OF_MONTH);
                                //判断选择时间距离月底几天
                                int difBeforDay = beforMaxDayZon - dayZone;
                                switch (difBeforDay) {
                                    case 0:
                                        switch (nowDataDayZone) {
                                            case 1:
                                                content = "昨天";
                                                break;
                                            case 2:
                                                content = "前天";
                                                break;
                                            default:
                                                content = time;
                                        }
                                        break;
                                    case 1:
                                        switch (nowDataDayZone) {
                                            case 1:
                                                content = "前天";
                                                break;
                                            default:
                                                content = time;
                                        }
                                        break;
                                    default:
                                        content = time;
                                        break;
                                }
                                break;
                            case 0:
                                //判断同日
                                int i = nowDataDayZone - dayZone;
                                switch (i) {
                                    case -2:
                                        content = "后天";
                                        break;
                                    case -1:
                                        content = "明天";
                                        break;
                                    case 0:
                                        content = "今天";
                                        break;
                                    case 1:
                                        content = "昨天";
                                        break;
                                    case 2:
                                        content = "前天";
                                        break;
                                    default:
                                        content = time;
                                        break;
                                }
                                break;
                            case -1://明月
                                //1 获取上月
                                //获取当月最后一天
                                nowData.set(Calendar.DAY_OF_MONTH, nowData.getActualMaximum(Calendar.DAY_OF_MONTH));
                                int nowMaxDayZone = nowData.get(Calendar.DAY_OF_MONTH);
                                int difmaxZone = nowMaxDayZone - nowDataDayZone;
                                switch (difmaxZone) {
                                    case 0:
                                        content = "今天";
                                        break;
                                    case 1:
                                        content = "明天";
                                        break;
                                    case 2:
                                        content = "后天";
                                        break;
                                    default:
                                        content = time;
                                        break;
                                }
                                break;
                            default:
                                content = time;
                                break;

                        }
                        break;
                    case -1://明年
                        //判断刷选月数是否是第十二个月
                        switch (monthZone) {
                            case 0:
                                //当前月数如果等一,不等于一差的太多
                                switch (nowmonthZone) {
                                    case 11:
                                        //获取上年最后一月的天数
                                        nowData.set(Calendar.DAY_OF_MONTH, date.getActualMaximum(Calendar.DAY_OF_MONTH));
                                        int beforMaxDay = nowData.get(Calendar.DAY_OF_MONTH);
                                        int difBeforDay = beforMaxDay - nowDataDayZone;
                                        switch (difBeforDay) {
                                            case 0://最后一天
                                                switch (dayZone) {
                                                    case 1:
                                                        content = "明天";
                                                        break;
                                                    case 2:
                                                        content = "后天";
                                                        break;
                                                    default:
                                                        content = time;
                                                        break;
                                                }
                                                break;
                                            case 1://最后第2天
                                                switch (dayZone) {
                                                    case 1:
                                                        content = "后天";
                                                        break;
                                                    default:
                                                        content = time;
                                                        break;
                                                }
                                                break;
                                            default:
                                                content = time;
                                                break;
                                        }
                                        break;
                                    default:
                                        content = time;
                                        break;
                                }
                                break;
                            default:
                                content = time;
                                break;
                        }
                        break;
                    default:
                        content = time;
                        break;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return content;

    }

}
