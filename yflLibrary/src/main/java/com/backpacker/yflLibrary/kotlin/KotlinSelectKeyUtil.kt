package com.backpacker.yflLibrary.kotlin

import android.app.Activity
import android.graphics.Color
import android.view.Gravity


/**
 * @Author : YFL  is Creating a porject in xiaole
 * @Package cn.ruiye.xiaole.utils
 * @Email : yufeilong92@163.com
 * @Time :2019/9/24 9:33
 * @Purpose :选择狂
 */
object KotlinSelectKeyUtil {
  /*     fun <T> showSelectKey(
        activity: Activity,
        items: MutableList<T>, item: T?,
        selectClick: (postion: Int, com: T) -> Unit
    ) {
        val picker = SinglePicker(activity, items)
        picker.window.setGravity(Gravity.BOTTOM)
        picker.setCanLoop(false) //不禁用循环
        picker.setTopBackgroundColor(-0x111112)
        picker.setTopHeight(50)
        picker.setTopLineColor(-0xcc4a1b)
        picker.setTopLineHeight(1)
        picker.setTitleText("请选择")
        picker.setTitleTextColor(-0x666667)
        picker.setTitleTextSize(12)
        picker.setCancelTextColor(-0xcc4a1b)
        picker.setCancelTextSize(13)
        picker.setSubmitTextColor(-0xcc4a1b)
        picker.setSubmitTextSize(13)
        picker.setSelectedTextColor(-0xcc4a1b)
        picker.setUnSelectedTextColor(-0x666667)
        val config = LineConfig()
        config.color = Color.TRANSPARENT //线颜色
        config.alpha = 120 //线透明度
        //        config.setRatio(1);//线比率
        picker.setLineConfig(config)
        picker.setItemWidth(200)
        picker.setBackgroundColor(-0x1e1e1f)
        //picker.setSelectedItem(isChinese ? "处女座" : "Virgo");
        //        picker.setSelectedIndex(7);
        if (null != item && "" != item)
            picker.setSelectedItem(item)
        picker.setOnItemPickListener { i, com ->
            selectClick(i, com)
        }
        picker.show()
    }
        /*
    * 年月日选择
    * */
    fun onYearMonthDayPicker(
        activity: Activity,
        year: Int,
        month: Int,
        day: Int,
        onSelectTime: (year: String, month: String, day: String) -> Unit
    ) {
        val data = Calendar.getInstance()
        var mYear = year
        var mMonth = month
        var mDay = day
        if (mYear == 0) {
            mYear = data.get(Calendar.YEAR)
            mMonth = data.get(Calendar.MONTH)+1
            mDay = data.get(Calendar.DAY_OF_MONTH)
        }

        val picker = DatePicker(activity)
        picker.window.setGravity(Gravity.BOTTOM)
        picker.setTopPadding(15)
        picker.setRangeStart(2016, 1, 1)
        picker.setRangeEnd(2111, 1, 30)
        picker.setSelectedItem(mYear, mMonth, mDay)
        picker.setWeightEnable(true)
        picker.setCanLoop(false)
        picker.setCanLinkage(true)
        picker.setLineColor(Color.BLACK)
        picker.setOnDatePickListener(object : DatePicker.OnYearMonthDayPickListener {
            override fun onDatePicked(
                year: String,
                month: String,
                day: String
            ) {
                onSelectTime(year, month, day)
            }
        })
        picker.setOnWheelListener(object : DatePicker.OnWheelListener {
            override fun onYearWheeled(index: Int, year: String) {
                picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay())
            }

            override fun onMonthWheeled(index: Int, month: String) {
                picker.setTitleText(
                    picker.getSelectedYear()
                        .toString() + "-" + month + "-" + picker.getSelectedDay()
                )
            }

            override fun onDayWheeled(index: Int, day: String) {
                picker.setTitleText(
                    picker.getSelectedYear()
                        .toString() + "-" + picker.getSelectedMonth() + "-" + day
                )
            }
        })
        picker.show()
    }
    */
}