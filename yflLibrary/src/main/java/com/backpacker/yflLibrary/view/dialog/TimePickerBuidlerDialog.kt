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
import androidx.annotation.ColorInt
import androidx.appcompat.app.AlertDialog
import com.backpacker.yflLibrary.view.dialog.TimePicker.LoopView
import com.backpacker.yflLibrary.view.dialog.TimePicker.OnItemScrollListener
import com.example.UtilsLibrary.R
import kotlinx.android.synthetic.main.dialog_time_picker.*
import java.util.ArrayList

/**
 * @Author : YFL  is Creating a porject in My Application
 * @Package com.example.myapplication.loopview.dialog
 * @Email : yufeilong92@163.com
 * @Time :2020/10/13 12:52
 * @Purpose :
 */
class TimePickerBuidlerDialog(var mContext: Context) : AlertDialog(mContext, R.style.my_dialog) {
    private var metrics: DisplayMetrics = context.resources.displayMetrics

    init {
        window!!.setWindowAnimations(R.style.popup_animation)
    }

    companion object {
        fun buidler(mContext: Context): Builder {
            return Builder(mContext)
        }
    }

    public lateinit var onSelectTImePickerNoHourMin: (hour: String, min: String) -> Unit

    private var mHourList: MutableList<String>? = null
    private var mMinuteList: MutableList<String>? = null

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

    //设置item 字体大小
    private var mTextItemSize = 0.0F


    private var mSelectHour: Int = 0
    private var mSelectMin: Int = 0

    private var mLinkageHour: String? = null
    private var mLinkageMin: String? = null

    private var mContentTvTypeface: Typeface? = null

    private var mOutContentTvTypeface: Typeface? = null

    //是否显示顶层line
    private var mShowTopLine = true

    //顶层line 颜色
    private var mTopLineColor: Int = 0


    class Builder(var mContext: Context) {
        val timePicker = TimePickerBuidlerDialog(mContext)
        fun setSelectTime(hour: Int, min: Int): Builder {
            timePicker.mSelectHour = hour
            timePicker.mSelectMin = min
            return this
        }

        fun setSelectContentColor(@ColorInt color: Int): Builder {
            timePicker.mContentColor = color
            return this
        }

        fun setContentSize(size: Float): Builder {
            timePicker.mTextItemSize=size
            return this
        }

        fun setSelectOutColor(@ColorInt color: Int): Builder {
            timePicker.mOutContentColor = color
            return this
        }

        fun setLineColor(@ColorInt color: Int): Builder {
            timePicker.mLineColor = color
            return this
        }

        fun setLablerSize(size: Float): Builder {
            timePicker.mLablerSize = size
            return this
        }

        fun setLablerColor(@ColorInt color: Int): Builder {
            timePicker.mLaberColor = color
            return this
        }

        fun setViewPhoneHeightPercentage(mPercentage: Double): Builder {
            timePicker.mPercentage = mPercentage
            return this
        }

        fun setIsLoop(isLoop: Boolean): Builder {
            timePicker.isLoop = isLoop
            return this
        }

        fun setLinkage(isLinkage: Boolean): Builder {
            timePicker.mIsLinkAge = isLinkage
            return this
        }

        fun setOnTimePickerListener(onSelectTImePickerHourMin: (hour: String, min: String) -> Unit): Builder {
            timePicker.onSelectTImePickerNoHourMin = onSelectTImePickerHourMin
            return this
        }

        fun showLine(show: Boolean): Builder {
            timePicker.isShowLine = show
            return this
        }

        fun setShowNumber(number: Int): Builder {
            timePicker.mNumber = number
            return this
        }

        fun showTopLine(show: Boolean): Builder {
            timePicker.mShowTopLine = show
            return this
        }

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
        setContentView(R.layout.dialog_time_picker)
        setSizeMode()
        initEvent()
        initListener()

    }

    private fun initEvent() {
        tv_dialog_time_picker_h_time.text = "请选择"
        setLoopNumber()
        setTopLineColor()
        setTopShow()
        gmSetViewData(3, loop_h_hour, mSelectHour)
        gmSetViewData(4, loop_h_min, mSelectMin)
        setTvTypeface()
        setTvSize()
        setContentColor()
        setOutContentColor()
        setLineColor()
        setLablerUnitColor()
        setLablerUnitSize()
        setShowline()
        setIsLoop()
        initLinkAge()
    }

    private fun setTvSize() {
        if (mTextItemSize == 0.0F) return
        loop_h_hour.setTextSize(mTextItemSize)
        loop_h_min.setTextSize(mTextItemSize)

    }

    private fun setTopShow() {
        view_line_h_one.visibility = if (mShowTopLine) View.VISIBLE else View.GONE
    }

    private fun setTopLineColor() {
        if (mTopLineColor == 0) return
        view_line_h_one.setBackgroundColor(mTopLineColor)
    }

    private fun setShowline() {
        loop_h_hour.setShowDividerLine(isShowLine)
        loop_h_min.setShowDividerLine(isShowLine)
    }

    private fun setLoopNumber() {
        if (mNumber == 0) return
        loop_h_hour.setItemsVisibleCount(mNumber)
        loop_h_min.setItemsVisibleCount(mNumber)
    }

    private fun setTvTypeface() {
        mOutContentTvTypeface?.let {

            loop_h_hour.setOutContentTypeface(it)
            loop_h_min.setOutContentTypeface(it)
        }

        mContentTvTypeface?.let {
            loop_h_hour.setContentTypeface(it)
            loop_h_min.setContentTypeface(it)
        }
    }

    private fun setLablerUnitColor() {
        if (mLaberColor == 0) return
        tv_dialog_time_picker_h_hour.setTextColor(mLaberColor)
        tv_dialog_time_picker_h_min.setTextColor(mLaberColor)
    }

    private fun setLablerUnitSize() {
        if (mLablerSize == 0.0f) return

        tv_dialog_time_picker_h_hour.textSize = mLablerSize
        tv_dialog_time_picker_h_min.textSize = mLablerSize
    }

    private fun setContentColor() {
        if (mContentColor == 0) return

        loop_h_hour.setCenterTextColor(mContentColor)
        loop_h_min.setCenterTextColor(mContentColor)
    }

    private fun setOutContentColor() {
        if (mOutContentColor == 0) return

        loop_h_hour.setOuterTextColor(mOutContentColor)
        loop_h_min.setOuterTextColor(mOutContentColor)
    }

    private fun setLineColor() {
        if (mLineColor == 0) return
        loop_h_hour.setDividerColor(mLineColor)
        loop_h_min.setDividerColor(mLineColor)
    }

    private fun setIsLoop() {
        loop_h_hour.setLoop(isLoop)
        loop_h_min.setLoop(isLoop)
    }

    private fun initLinkAge() {
        if (!mHourList.isNullOrEmpty()) {
            val selectedItem = loop_h_hour.selectedItem
            mLinkageHour = mHourList!![selectedItem]
        }
        if (!mMinuteList.isNullOrEmpty()) {
            val selectedItem = loop_h_min.selectedItem
            mLinkageMin = mMinuteList!![selectedItem]
        }
    }

    private fun initListener() {
        tv_dialog_time_picker_h_cancle.setOnClickListener {
            dismiss()
        }
        tv_dialog_time_picker_h_sure.setOnClickListener {
            sure()
        }
    }

    private fun sure() {
        if (mHourList.isNullOrEmpty() || mMinuteList.isNullOrEmpty())
            return
        val selectedItem3 = loop_h_hour.selectedItem
        val selectedItem4 = loop_h_min.selectedItem
        if (::onSelectTImePickerNoHourMin.isInitialized) {
            onSelectTImePickerNoHourMin(
                mHourList!![selectedItem3],
                mMinuteList!![selectedItem4]
            )
            dismiss()
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

    private fun bindViewData() {
        if (mHourList.isNullOrEmpty() || mMinuteList.isNullOrEmpty())
            return
        val selectedItem3 = loop_h_hour.selectedItem
        val selectedItem4 = loop_h_min.selectedItem
        tv_dialog_time_picker_h_time.text =
            "${mHourList!![selectedItem3]}:${mMinuteList!![selectedItem4]}"
    }

    /***
     * @param type 0 年 1 月 2 天 3 时 4 分
     * @return
     */
    private fun setIsLinkage(type: Int) {
        when (type) {
            3 -> {
                val selectedItem = loop_h_hour.selectedItem
                val com = mHourList!![selectedItem]
                if (com != mLinkageHour) {
                    setCustiomPostion(0, true)
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
        min: Int,
        isChangerMin: Boolean
    ) {

        if (isChangerMin)
            loop_h_min.setCurrentPosition(min)
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

        val layoutParams = rootviewtimepicek_h.layoutParams
        layoutParams.height = (height * mPercentage).toInt()
        rootviewtimepicek_h.layoutParams = layoutParams
    }

    private fun fillZero(number: Int): String {
        return if (number < 10) "0$number" else "" + number
    }

}