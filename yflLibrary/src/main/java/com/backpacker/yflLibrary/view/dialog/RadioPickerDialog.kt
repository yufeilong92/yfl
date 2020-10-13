package com.backpacker.yflLibrary.view.dialog

import android.content.Context
import android.graphics.Point
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.appcompat.app.AlertDialog
import com.backpacker.yflLibrary.view.dialog.TimePicker.LoopView
import com.backpacker.yflLibrary.view.dialog.TimePicker.OnItemScrollListener
import com.example.UtilsLibrary.R
import kotlinx.android.synthetic.main.dialog_radio_picker.*

/**
 * @Author : YFL  is Creating a porject in My Application
 * @Package com.example.myapplication.loopview.dialog
 * @Email : yufeilong92@163.com
 * @Time :2020/10/13 14:33
 * @Purpose :单选对话框
 */
class RadioPickerDialog(var mContext: Context) : AlertDialog(mContext, R.style.my_dialog) {
    private var metrics: DisplayMetrics = context.resources.displayMetrics

    init {
        window!!.setWindowAnimations(R.style.popup_animation)
    }

    companion object {
        fun buidler(mContext: Context): Builder {
            return Builder(mContext)
        }
    }

    //百分比
    private var mPercentage: Double = 0.40

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

    private var mSelectItem: String? = null


    private var mContentTvTypeface: Typeface? = null

    private var mOutContentTvTypeface: Typeface? = null

    private lateinit var onRadioPickerListener: (postion: Int, item: String) -> Unit

    private var mItemLists: MutableList<String>? = null


    class Builder(var mContext: Context) {
        val mRadioPicker = RadioPickerDialog(mContext)

        fun setInitData(data: MutableList<String>): Builder {
            mRadioPicker.mItemLists = data
            return this
        }

        fun setSelectItem(com: String): Builder {
            mRadioPicker.mSelectItem = com
            return this
        }


        fun setSelectContentColor(@ColorInt color: Int): Builder {
            mRadioPicker.mContentColor = color
            return this
        }

        fun setSelectOutColor(@ColorInt color: Int): Builder {
            mRadioPicker.mOutContentColor = color
            return this
        }

        fun setLineColor(@ColorInt color: Int): Builder {
            mRadioPicker.mLineColor = color
            return this
        }

        fun setViewPhoneHeightPercentage(mPercentage: Double): Builder {
            mRadioPicker.mPercentage = mPercentage
            return this
        }

        fun setIsLoop(isLoop: Boolean): Builder {
            mRadioPicker.isLoop = isLoop
            return this
        }

        fun setOnRadioPickerListener(onSelectTImePickerHourMin: (postion: Int, item: String) -> Unit): Builder {
            mRadioPicker.onRadioPickerListener = onSelectTImePickerHourMin
            return this
        }

        fun showLine(show: Boolean): Builder {
            mRadioPicker.isShowLine = show
            return this
        }

        fun setOutTvTypeface(type: Typeface): Builder {
            mRadioPicker.mOutContentTvTypeface = type
            return this
        }

        fun setContentTvTypeface(type: Typeface): Builder {
            mRadioPicker.mContentTvTypeface = type
            return this
        }

        fun setShowNumber(number: Int): Builder {
            mRadioPicker.mNumber = number
            return this
        }

        fun show() {
            mRadioPicker.show()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_radio_picker)
        setSizeMode()
        initEvent()
        initListener()
    }

    private fun initEvent() {
        tv_dialog_radio_picker_time.text = "请选择"
        setLoopNumber()
        bindViewData()
        setTvTypeface()
        setContentColor()
        setOutContentColor()
        setLineColor()
        setShowline()
        setIsLoop()
    }

    private fun setLoopNumber() {
        if (mNumber == 0) return
        loop_item_view.setItemsVisibleCount(mNumber)
    }

    private fun bindViewData() {
        if (mItemLists.isNullOrEmpty()) {
            Toast.makeText(mContext, "内容为空", Toast.LENGTH_SHORT).show();
            dismiss()
            return
        }
        var postion: Int = 0
        if (null != mSelectItem && "" != mSelectItem) {
            for ((index, child) in mItemLists!!.withIndex()) {
                if (child == "$mSelectItem") {
                    postion = index
                    break
                }
            }
        }
        loop_item_view.setItems(mItemLists)
        loop_item_view.setInitPosition(postion)
        loop_item_view.setOnItemScrollListener(object : OnItemScrollListener {
            override fun onItemScrollStateChanged(
                loopView: LoopView?,
                currentPassItem: Int,
                oldScrollState: Int,
                scrollState: Int,
                totalScrollY: Int
            ) {
                //滑动停止
                if (scrollState == LoopView.SCROLL_STATE_IDLE) {
                    val selectedItem = loop_item_view.selectedItem
                    tv_dialog_radio_picker_time.text = mItemLists!![selectedItem]
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

    private fun setTvTypeface() {
        mOutContentTvTypeface?.let {
            loop_item_view.setOutContentTypeface(it)
        }

        mContentTvTypeface?.let {
            loop_item_view.setContentTypeface(it)
        }

    }

    private fun setContentColor() {
        if (mContentColor==0)return
        loop_item_view.setCenterTextColor(mContentColor)
    }

    private fun setOutContentColor() {
        if (mOutContentColor==0)return
        loop_item_view.setOuterTextColor(mOutContentColor)
    }

    private fun setLineColor() {
        loop_item_view.setShowDividerLine(isShowLine)
    }

    private fun setShowline() {
        if (mLineColor==0)return
        loop_item_view.setDividerColor(mLineColor)
    }

    private fun setIsLoop() {
        loop_item_view.setLoop(isLoop)
    }

    private fun initListener() {
        tv_dialog_radio_picker_cancle.setOnClickListener {
            dismiss()
        }
        tv_dialog_radio_picker_sure.setOnClickListener {
            sure()
        }
    }

    private fun sure() {
        val selectedItem = loop_item_view.selectedItem
        val com = mItemLists!![selectedItem]
        if (::onRadioPickerListener.isInitialized) {
            onRadioPickerListener(selectedItem, com)
            dismiss()
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

        val layoutParams = rootviewradiopicek.layoutParams
        layoutParams.height = (height * mPercentage).toInt()
        rootviewradiopicek.layoutParams = layoutParams
    }
}