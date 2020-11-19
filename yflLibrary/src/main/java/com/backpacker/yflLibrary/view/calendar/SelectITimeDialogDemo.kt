package com.backpacker.yflLibrary.view.calendar


import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity

import android.view.WindowManager
import com.example.UtilsLibrary.R
import kotlinx.android.synthetic.main.dialog_select_time.*
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import java.util.*
/**
 * @Author : YFL  is Creating a porject in del
 * @Package :
 * @Email : yufeilong92@163.com
 * @Time :2020/8/27 15:40
 * @Purpose :选择区域时间对话框
 */
abstract class SelectITimeDialogDemo(var mContext: Context) : AlertDialog(mContext, R.style.dialog) {

    private var metrics: DisplayMetrics = context.resources.displayMetrics


    init {
        window!!.setWindowAnimations(R.style.popup_animation)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSizeMode()
        setContentView(R.layout.dialog_select_time)
        initView()
    }

    abstract fun showSelectTime(startTime: Date?, endTime: Date?)

    private var mStartTime: Date? = null
    private var mEndTime: Date? = null
    private fun initView() {
        val calenderMin = Calendar.getInstance()
        val calenderNow = Calendar.getInstance()
        calenderMin.set(2018, 0, 1)
        val start = calenderMin.time
        calendar_view.init(start, DateTime.now(DateTimeZone.UTC).plusDays(2).toDate())
            .inMode(CalendarPickerView.SelectionMode.RANGE)
            .withSelectedDate(calenderNow.time)

        calendar_view.setOnDateResolvedListener(object : CalendarPickerView.OnDateResolvedListener {
            override fun onMinDateResolved(date: Date?) {
                mStartTime = date
            }

            override fun onMaxDateResolved(date: Date?) {
                mEndTime = date
            }

            override fun onDblclickResolved(date: Date?) {
                mStartTime = date
                mEndTime = date
            }
        })
        tv_select_time_sure.setOnClickListener {
            dismiss()
            showSelectTime(mStartTime, mEndTime)
        }
        iv_recharge_close.setOnClickListener {
            dismiss()
        }
    }

    private fun setSizeMode() {
        val params = window!!.attributes
        params.width = metrics.widthPixels
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        window!!.attributes = params
        window!!.setGravity(Gravity.BOTTOM)
    }
}