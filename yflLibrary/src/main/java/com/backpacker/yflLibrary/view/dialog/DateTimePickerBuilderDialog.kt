package com.backpacker.yflLibrary.view.dialog

import android.content.Context
import android.graphics.Point
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.appcompat.app.AlertDialog
import com.backpacker.yflLibrary.view.dialog.TimePicker.LoopView
import com.backpacker.yflLibrary.view.dialog.TimePicker.OnItemScrollListener
import com.example.UtilsLibrary.R
import kotlinx.android.synthetic.main.dialog_date_time_picker.*
import kotlinx.android.synthetic.main.dialog_time_picker.*
import java.lang.NumberFormatException
import java.util.ArrayList

/**
 * @Author : YFL  is Creating a porject in My Application
 * @Package com.example.myapplication.loopview
 * @Email : yufeilong92@163.com
 * @Time :2020/10/12 16:09
 * @Purpose :对话框
 */
public class DateTimePickerBuilderDialog(var mContext: Context) :
    AlertDialog(mContext, R.style.my_dialog) {
    private var metrics: DisplayMetrics = context.resources.displayMetrics

    init {
        window!!.setWindowAnimations(R.style.popup_animation)
    }

    public lateinit var onSelectTImePickerNoHourMin: (year: String, month: String, day: String) -> Unit
    public lateinit var onSelectTImePickerHourMin: (year: String, month: String, day: String, hour: String, min: String) -> Unit

    private var mYearList: MutableList<String>? = null
    private var mMonthList: MutableList<String>? = null
    private var mDayList: MutableList<String>? = null
    private var mHourList: MutableList<String>? = null
    private var mMinuteList: MutableList<String>? = null

    //中间字体格式
    private var mContentTvTypeface: Typeface? = null

    //其它字体格式
    private var mOutContentTvTypeface: Typeface? = null

    //是否显示时分
    private var isShowHourMin = false

    //中间文字颜色
    private var mContentColor: Int = 0

    //其他颜色
    private var mOutContentColor: Int = 0

    //线颜色
    private var mLineColor: Int = 0

    //是否显示线
    private var isShowLine: Boolean = true

    //是否循环
    private var isLoop: Boolean = true

    //开始时间
    private var mStartYear: Int = 0

    //结束时间
    private var mEndYear: Int = 0

    //百分比
    private var mPercentage: Double = 0.40

    //是否联动
    private var mIsLinkAge: Boolean = false

    //单位颜色
    private var mLaberColor: Int = 0

    //单位大小
    private var mLablerSize: Float = 0.0f

    //显示数量
    private var mNumber: Int = 0

    //设置item 字体大小
    private var mTextItemSize = 0.0F

    //回现数据
    private var mSelectYear: Int = 0
    private var mSelectMonth: Int = 0
    private var mSelectDay: Int = 0
    private var mSelectHour: Int = 0
    private var mSelectMin: Int = 0

    //联动数据
    private var mLinkageYear: String? = null
    private var mLinkageMonth: String? = null
    private var mLinkageDay: String? = null
    private var mLinkageHour: String? = null
    private var mLinkageMin: String? = null
    //iTem 线的左右距离
    private var mItemLineSpace: Int = 0

    //是否显示头顶颜色
    private var mShowTopLine = true

    //颜色
    private var mTopLineColor = 0

    companion object {
        fun buidler(mContext: Context): Builder {
            return Builder(mContext)
        }
    }

    class Builder(var mContext: Context) {
        val timePicker = DateTimePickerBuilderDialog(mContext)

        /***
         * @param year  开始时间年限
         * @return
         */
        fun setStartTime(year: Int): Builder {
            timePicker.mStartYear = year
            return this
        }
        /***
         * @param space item 线左右间距
         * @return
         */
        fun setItemLineSpace(space: Int): Builder {
            timePicker.mItemLineSpace = space
            return this
        }

        /***
         * @param year  结束时间年限
         * @return
         */
        fun setEndTime(year: Int): Builder {
            timePicker.mEndYear = year
            return this
        }

        /***
         * @param year 选中年
         * @param month 选中月
         * @param day 选中日
         * @param hour 选中小时
         * @param min 选中分钟
         * @return
         */
        fun setSelectTime(year: Int, month: Int, day: Int, hour: Int, min: Int): Builder {
            timePicker.mSelectYear = year
            timePicker.mSelectMonth = month
            timePicker.mSelectDay = day
            timePicker.mSelectHour = hour
            timePicker.mSelectMin = min
            return this
        }

        /***
         * @param year 选中年
         * @param month 选中月
         * @param day 选中日
         * @return
         */
        fun setSelectTime(year: Int, month: Int, day: Int): Builder {
            timePicker.mSelectYear = year
            timePicker.mSelectMonth = month
            timePicker.mSelectDay = day
            timePicker.mSelectHour = 0
            timePicker.mSelectMin = 0
            return this
        }

        /***
         * @param show 是否显示时分布局
         * @return
         */
        fun setIsShowHourMin(show: Boolean): Builder {
            timePicker.isShowHourMin = show
            return this
        }

        /***
         * @param color 选中中间的颜色
         * @return
         */
        fun setSelectContentColor(@ColorInt color: Int): Builder {
            timePicker.mContentColor = color
            return this
        }

        /***
         * @param color 其它颜色
         * @return
         */

        fun setSelectOutColor(@ColorInt color: Int): Builder {
            timePicker.mOutContentColor = color
            return this
        }

        /***
         * @param size item字体大小
         * @return
         */
        fun setItemSize(size: Float): Builder {
            timePicker.mTextItemSize = size
            return this
        }

        /***
         * @param color 中间上下颜色
         * @return
         */

        fun setLineColor(@ColorInt color: Int): Builder {
            timePicker.mLineColor = color
            return this
        }

        /***
         * @param show 中间上下颜色是否显示
         * @return
         */
        fun showLine(show: Boolean): Builder {
            timePicker.isShowLine = show
            return this
        }

        /***
         * @param number 每行显示数量
         * @return
         */

        fun setShowNumber(number: Int): Builder {
            timePicker.mNumber = number
            return this
        }

        /***
         * @param size 单位大小
         * @return
         */
        fun setLablerSize(size: Float): Builder {
            timePicker.mLablerSize = size
            return this
        }

        /***
         * @param color 单位颜色
         * @return
         */
        fun setLablerColor(@ColorInt color: Int): Builder {
            timePicker.mLaberColor = color
            return this
        }

        /***
         * @param mPercentage 布局占整个屏幕的百分比
         * @return
         */
        fun setViewPhoneHeightPercentage(mPercentage: Double): Builder {
            timePicker.mPercentage = mPercentage
            return this
        }

        /***
         * @param isLoop 是否循环
         * @return
         */
        fun setIsLoop(isLoop: Boolean): Builder {
            timePicker.isLoop = isLoop
            return this
        }

        /***
         * @param isLinkage 是否联动
         * @return
         */
        fun setLinkage(isLinkage: Boolean): Builder {
            timePicker.mIsLinkAge = isLinkage
            return this
        }

        /***
         * @param onSelectTImePickerNoHourMin 监听回到
         * @return
         */
        fun setOnTimePickerListener(onSelectTImePickerNoHourMin: (year: String, month: String, day: String) -> Unit): Builder {
            timePicker.onSelectTImePickerNoHourMin = onSelectTImePickerNoHourMin
            return this
        }

        /***
         * @param onSelectTImePickerNoHourMin 监听回到
         * @return
         */
        fun setOnTimePickerListener(onSelectTImePickerHourMin: (year: String, month: String, day: String, hour: String, min: String) -> Unit): Builder {
            timePicker.onSelectTImePickerHourMin = onSelectTImePickerHourMin
            return this
        }

        /***
         * @param type 其它字体类型
         * @return
         */

        fun setOutTvTypeface(type: Typeface): Builder {
            timePicker.mOutContentTvTypeface = type
            return this
        }

        /***
         * @param type 中间字体类型
         * @return
         */
        fun setContentTvTypeface(type: Typeface): Builder {
            timePicker.mContentTvTypeface = type
            return this
        }

        /***
         * @param show 是否显示顶层线
         * @return
         */
        fun showTopLine(show: Boolean): Builder {
            timePicker.mShowTopLine = show
            return this
        }

        /***
         * @param color 顶层线颜色
         * @return
         */
        fun setTopLineColor(@ColorInt color: Int): Builder {
            timePicker.mTopLineColor = color
            return this
        }

        fun show() {
            timePicker.show()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_date_time_picker)
        setSizeMode()
        initEvent()
        initListener()
    }

    private fun initEvent() {
        tv_dialog_time_picker_time.text = "请选择"
        setTopLineColor()
        setItemLineSpace()
        setTopShow()
        gmSetViewData(0, loop_year, mSelectYear)
        gmSetViewData(1, loop_month, mSelectMonth)
        gmSetViewData(2, loop_day, mSelectDay)
        if (isShowHourMin) {
            gmSetViewData(3, loop_hour, mSelectHour)
            gmSetViewData(4, loop_min, mSelectMin)
        }
        setLoopViewShow(loop_hour, isShowHourMin)
        setLoopViewShow(loop_min, isShowHourMin)
        setTvShow(tv_dialog_time_picker_hour, isShowHourMin)
        setTvShow(tv_dialog_time_picker_min, isShowHourMin)
        setTvTypeface()
        setTvSize()
        setLoopNumber()
        setContentColor()
        setOutContentColor()
        setLineColor()
        setLablerUnitColor()
        setLablerUnitSize()
        setShowline()
        setIsLoop()
        initLinkAge()
    }
    private fun setItemLineSpace() {
        if (mItemLineSpace == 0) return
        loop_year.setItemLineSpace(mItemLineSpace)
        loop_month.setItemLineSpace(mItemLineSpace)
        loop_day.setItemLineSpace(mItemLineSpace)
        if (!isShowHourMin) return
        loop_hour.setItemLineSpace(mItemLineSpace)
        loop_min.setItemLineSpace(mItemLineSpace)
    }
    private fun setTvSize() {
        if (mTextItemSize == 0.0F) return
        loop_year.setTextSize(mTextItemSize)
        loop_month.setTextSize(mTextItemSize)
        loop_day.setTextSize(mTextItemSize)
        if (!isShowHourMin) return
        loop_hour.setTextSize(mTextItemSize)
        loop_min.setTextSize(mTextItemSize)

    }

    private fun setTopShow() {
        view_line_one.visibility = if (mShowTopLine) View.VISIBLE else View.GONE
    }

    private fun setTopLineColor() {
        if (mTopLineColor == 0) return
        view_line_one.setBackgroundColor(mTopLineColor)
    }

    private fun setTvTypeface() {
        mOutContentTvTypeface?.let {
            loop_year.setOutContentTypeface(it)
            loop_month.setOutContentTypeface(it)
            loop_day.setOutContentTypeface(it)
            if (!isShowHourMin) return
            loop_hour.setOutContentTypeface(it)
            loop_min.setOutContentTypeface(it)
        }

        mContentTvTypeface?.let {
            loop_year.setContentTypeface(it)
            loop_month.setContentTypeface(it)
            loop_day.setContentTypeface(it)
            if (!isShowHourMin) return
            loop_hour.setContentTypeface(it)
            loop_min.setContentTypeface(it)
        }

    }

    private fun setLoopViewShow(loopView: LoopView, show: Boolean) {
        loopView.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun setTvShow(tv: TextView, show: Boolean) {
        tv.visibility = if (show) View.VISIBLE else View.GONE

    }

    private fun setLablerUnitColor() {
        if (mLaberColor == 0) return
        tv_dialog_time_picker_year.setTextColor(mLaberColor)
        tv_dialog_time_picker_month.setTextColor(mLaberColor)
        tv_dialog_time_picker_day.setTextColor(mLaberColor)
        if (!isShowHourMin) return
        tv_dialog_time_picker_hour.setTextColor(mLaberColor)
        tv_dialog_time_picker_min.setTextColor(mLaberColor)
    }

    private fun setLablerUnitSize() {
        if (mLablerSize == 0.0f) return
        tv_dialog_time_picker_year.textSize = mLablerSize
        tv_dialog_time_picker_month.textSize = mLablerSize
        tv_dialog_time_picker_day.textSize = mLablerSize
        if (!isShowHourMin) return
        tv_dialog_time_picker_hour.textSize = mLablerSize
        tv_dialog_time_picker_min.textSize = mLablerSize
    }

    private fun setLoopNumber() {
        if (mNumber == 0) return
        loop_year.setItemsVisibleCount(mNumber)
        loop_month.setItemsVisibleCount(mNumber)
        loop_day.setItemsVisibleCount(mNumber)
        if (!isShowHourMin) return
        loop_hour.setItemsVisibleCount(mNumber)
        loop_min.setItemsVisibleCount(mNumber)
    }

    private fun initLinkAge() {
        if (!mYearList.isNullOrEmpty()) {
            val selectedItem = loop_year.selectedItem
            mLinkageYear = mYearList!![selectedItem]
        }
        if (!mMonthList.isNullOrEmpty()) {
            val selectedItem = loop_month.selectedItem
            mLinkageMonth = mMonthList!![selectedItem]
        }

        if (!mDayList.isNullOrEmpty()) {
            val selectedItem = loop_day.selectedItem
            mLinkageDay = mDayList!![selectedItem]
        }
        if (!mHourList.isNullOrEmpty()) {
            val selectedItem = loop_hour.selectedItem
            mLinkageHour = mHourList!![selectedItem]
        }
        if (!mMinuteList.isNullOrEmpty()) {
            val selectedItem = loop_min.selectedItem
            mLinkageMin = mMinuteList!![selectedItem]
        }
    }

    private fun setContentColor() {
        if (mContentColor == 0) return
        loop_year.setCenterTextColor(mContentColor)
        loop_month.setCenterTextColor(mContentColor)
        loop_day.setCenterTextColor(mContentColor)
        if (!isShowHourMin) return
        loop_hour.setCenterTextColor(mContentColor)
        loop_min.setCenterTextColor(mContentColor)
    }

    private fun setOutContentColor() {
        if (mOutContentColor == 0) return
        loop_year.setOuterTextColor(mOutContentColor)
        loop_month.setOuterTextColor(mOutContentColor)
        loop_day.setOuterTextColor(mOutContentColor)
        if (!isShowHourMin) return
        loop_hour.setOuterTextColor(mOutContentColor)
        loop_min.setOuterTextColor(mOutContentColor)
    }

    private fun setShowline() {
        loop_year.setShowDividerLine(isShowLine)
        loop_month.setShowDividerLine(isShowLine)
        loop_day.setShowDividerLine(isShowLine)
        if (!isShowHourMin) return
        loop_hour.setShowDividerLine(isShowLine)
        loop_min.setShowDividerLine(isShowLine)
    }

    private fun setLineColor() {
        if (mLineColor == 0) return
        loop_year.setDividerColor(mLineColor)
        loop_month.setDividerColor(mLineColor)
        loop_day.setDividerColor(mLineColor)
        if (!isShowHourMin) return
        loop_hour.setDividerColor(mLineColor)
        loop_min.setDividerColor(mLineColor)
    }

    private fun setIsLoop() {
        loop_year.setLoop(isLoop)
        loop_month.setLoop(isLoop)
        loop_day.setLoop(isLoop)
        if (!isShowHourMin) return
        loop_hour.setLoop(isLoop)
        loop_min.setLoop(isLoop)
    }


    private fun initListener() {
        tv_dialog_time_picker_cancle.setOnClickListener {
            dismiss()
        }
        tv_dialog_time_picker_sure.setOnClickListener {
            sure()
        }
    }

    private fun sure() {
        val selectedItem = loop_year.selectedItem
        val selectedItem1 = loop_month.selectedItem
        val selectedItem2 = loop_day.selectedItem
        if (isShowHourMin) {
            if (mYearList.isNullOrEmpty() || mMonthList.isNullOrEmpty() || mDayList.isNullOrEmpty() || mHourList.isNullOrEmpty() || mMinuteList.isNullOrEmpty())
                return
            val selectedItem3 = loop_hour.selectedItem
            val selectedItem4 = loop_min.selectedItem
            if (::onSelectTImePickerHourMin.isInitialized) {

                onSelectTImePickerHourMin(
                    mYearList!![selectedItem],
                    mMonthList!![selectedItem1],
                    mDayList!![selectedItem2],
                    mHourList!![selectedItem3],
                    mMinuteList!![selectedItem4]
                )
                dismiss()
            }

        } else {
            if (mYearList.isNullOrEmpty() || mMonthList.isNullOrEmpty() || mDayList.isNullOrEmpty())
                return
            if (::onSelectTImePickerNoHourMin.isInitialized) {
                onSelectTImePickerNoHourMin(
                    mYearList!![selectedItem],
                    mMonthList!![selectedItem1],
                    mDayList!![selectedItem2]
                )
                dismiss()
            }
        }


    }


    /***
     *
     * @param type 0 年 1 月 2 天 3 时 4 分
     * @param loopView
     * @param select 选中的时间
     * @return
     */

    private fun gmSetViewData(type: Int, loopView: LoopView, select: Int) {
        var postion = 0
        when (type) {
            0 -> {//年
                if (mYearList.isNullOrEmpty())
                    mYearList = ArrayList<String>()
                else
                    mYearList?.clear()
                if (mStartYear > mEndYear) {
                    throw NumberFormatException("开始时间$mStartYear 大于 结束$mEndYear")
                }
                for (i in mStartYear..mEndYear) {
                    mYearList?.add("${fillZero(i)}")
                }
                if (!mYearList.isNullOrEmpty()) {
                    for ((index, child) in mYearList!!.withIndex()) {
                        if (child == "${fillZero(select)}") {
                            postion = index
                            break
                        }
                    }
                }
                loopView.setItems(mYearList)
            }
            1 -> {//月
                if (mMonthList.isNullOrEmpty()) {
                    mMonthList = ArrayList<String>()
                } else
                    mMonthList?.clear()

                for (i in 1..12) {
                    mMonthList?.add("${fillZero(i)}")
                }
                if (!mMonthList.isNullOrEmpty()) {
                    for ((index, child) in mMonthList!!.withIndex()) {
                        if (child == "${fillZero(select)}") {
                            postion = index
                            break
                        }
                    }
                }

                loopView.setItems(mMonthList)
            }
            2 -> {//日
                if (mDayList.isNullOrEmpty()) {
                    mDayList = ArrayList<String>()
                } else
                    mDayList?.clear()
                for (i in 1..31) {
                    mDayList?.add("${fillZero(i)}")
                }
                if (!mDayList.isNullOrEmpty()) {
                    for ((index, child) in mDayList!!.withIndex()) {
                        if (child == "${fillZero(select)}") {
                            postion = index
                            break
                        }
                    }
                }

                loopView.setItems(mDayList)
            }
            3 -> {//时
                if (mHourList.isNullOrEmpty()) {

                    mHourList = ArrayList<String>()
                } else {
                    mHourList?.clear()
                }
                for (i in 0..23) {
                    mHourList?.add("${fillZero(i)}")
                }
                if (!mHourList.isNullOrEmpty()) {
                    for ((index, child) in mHourList!!.withIndex()) {
                        if (child == "${fillZero(select)}") {
                            postion = index
                            break
                        }
                    }
                }

                loopView.setItems(mHourList)
            }
            4 -> {//分
                if (mMinuteList.isNullOrEmpty()) {
                    mMinuteList = ArrayList<String>()
                } else
                    mMinuteList?.clear()
                for (i in 0..59) {
                    mMinuteList?.add("${fillZero(i)}")
                }
                if (!mMinuteList.isNullOrEmpty()) {
                    for ((index, child) in mMinuteList!!.withIndex()) {
                        if (child == "${fillZero(select)}") {
                            postion = index
                            break
                        }
                    }
                }

                loopView.setItems(mMinuteList)
            }
        }
        loopView.setInitPosition(postion)
        loopView.setOnItemScrollListener(object : OnItemScrollListener {
            override fun onItemScrollStateChanged(
                loopView: LoopView?,
                currentPassItem: Int,
                oldScrollState: Int,
                scrollState: Int,
                totalScrollY: Int
            ) {
                //滑动停止
                if (scrollState == LoopView.SCROLL_STATE_IDLE) {
                    bindViewData()
                    if (mIsLinkAge)
                        setIsLinkage(type)
                }
            }

            override fun onItemScrolling(
                loopView: LoopView?,
                currentPassItem: Int,
                scrollState: Int,
                totalScrollY: Int
            ) {
            }

        })
    }

    /***
     * @param type 0 年 1 月 2 天 3 时 4 分
     * @return
     */
    private fun setIsLinkage(type: Int) {
        when (type) {
            0 -> {
                val selectedItem = loop_year.selectedItem
                val com = mYearList!![selectedItem]
                if (com != mLinkageYear) {
                    setCustiomPostion(0, true, 0, true, 0, true, 0, true)
                }
            }
            1 -> {
                val selectedItem = loop_month.selectedItem
                val com = mMonthList!![selectedItem]
                if (com != mLinkageMonth) {
                    setCustiomPostion(0, false, 0, true, 0, true, 0, true)
                }

            }
            2 -> {
                val selectedItem = loop_day.selectedItem
                val com = mDayList!![selectedItem]
                if (com != mLinkageDay) {
                    setCustiomPostion(0, false, 0, false, 0, true, 0, true)
                }
            }
            3 -> {
                val selectedItem = loop_hour.selectedItem
                val com = mHourList!![selectedItem]
                if (com != mLinkageHour) {
                    setCustiomPostion(0, false, 0, false, 0, false, 0, true)
                }
            }
            4 -> {
            }
            else -> {
            }
        }

    }

    /***
     * @param month 月份索引
     * @param day    天数索引
     * @param hour  小时索引
     * @param min  分钟索引
     * @return
     */
    private fun setCustiomPostion(
        month: Int,
        isChangerMonth: Boolean,
        day: Int,
        isChangerDay: Boolean,
        hour: Int,
        isChangerHour: Boolean,
        min: Int,
        isChangerMin: Boolean
    ) {
        if (isChangerMonth)
            loop_month.setCurrentPosition(month)
        if (isChangerDay)
            loop_day.setCurrentPosition(day)
        if (isChangerHour)
            loop_hour.setCurrentPosition(hour)
        if (isChangerMin)
            loop_min.setCurrentPosition(min)
    }


    private fun bindViewData() {
        val selectedItem = loop_year.selectedItem
        val selectedItem1 = loop_month.selectedItem
        val selectedItem2 = loop_day.selectedItem
        if (isShowHourMin) {
            if (mYearList.isNullOrEmpty() || mMonthList.isNullOrEmpty() || mDayList.isNullOrEmpty() || mHourList.isNullOrEmpty() || mMinuteList.isNullOrEmpty())
                return
            val selectedItem3 = loop_hour.selectedItem
            val selectedItem4 = loop_min.selectedItem
            tv_dialog_time_picker_time.text =
                "${mYearList!![selectedItem]}-${mMonthList!![selectedItem1]}-${mDayList!![selectedItem2]}  ${mHourList!![selectedItem3]}:${mMinuteList!![selectedItem4]}"

        } else {
            if (mYearList.isNullOrEmpty() || mMonthList.isNullOrEmpty() || mDayList.isNullOrEmpty())
                return
            tv_dialog_time_picker_time.text =
                "${mYearList!![selectedItem]}-${mMonthList!![selectedItem1]}-${mDayList!![selectedItem2]}"
        }
    }


    private fun setSizeMode() {
        val params = window!!.attributes
        params.width = metrics.widthPixels
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        window!!.attributes = params
        window!!.setGravity(Gravity.BOTTOM)
        val windowManager = mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val defaultDisplay = windowManager.defaultDisplay
        var height: Int
        if (Build.VERSION.SDK_INT < 17) {
            height = defaultDisplay.height
        } else {
            val size = Point()
            defaultDisplay.getRealSize(size)
            height = size.y
        }

        val layoutParams = rootviewtimepicek.layoutParams
        layoutParams.height = (height * mPercentage).toInt()
        rootviewtimepicek.layoutParams = layoutParams
    }

    private fun fillZero(number: Int): String {
        return if (number < 10) "0$number" else "" + number
    }
}