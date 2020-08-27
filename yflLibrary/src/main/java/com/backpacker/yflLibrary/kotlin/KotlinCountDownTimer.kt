package com.backpacker.yflLibrary.kotlin

import android.content.Context
import android.os.CountDownTimer
import android.widget.Button
import androidx.annotation.ColorInt
import com.example.UtilsLibrary.R

/**
 * @Author : YFL  is Creating a porject in My Application
 * @Package com.backpacker.yflLibrary.kotlin
 * @Email : yufeilong92@163.com
 * @Time :2020/8/27 16:56
 * @Purpose :倒计时时间
 */
object KotlinCountDownTimer {

    private var mContext: Context? = null

    //默认总时长60s
    private var mAllCount: Long = 60000

    //间隔时间
    private var mFutureCount: Long = 1000

    //到时计时按钮
    private var mButton: Button? = null

    /**
     * 默认字体颜色
     */
    private var mDefaultColor = -1

    /**
     * 结束字体颜色
     */
    private var mFinishColor = -1

    /**
     * 默认背景颜色
     */
    private var mDefaultBgColor = -1

    /**
     * 结束背景颜色
     */
    private var mFinishBgColor = -1

    private lateinit var onFinishListener: () -> Unit
    private val mCountDownTimer = object : CountDownTimer(mAllCount, mFutureCount) {
        override fun onFinish() {
            mButton?.let { btn ->
                btn.setText("重新发送")
                btn.isEnabled = true
                mContext?.let {
                    btn.setTextColor(it.resources.getColor(mFinishColor))
                    btn.setBackgroundResource(mFinishBgColor)
                }
            }
            if (::onFinishListener.isInitialized) {
                onFinishListener.invoke()
            }
        }

        override fun onTick(p0: Long) {
            val kNumber = KotlinUtil.getKNumber(4, p0.toInt(), 100)
            mButton?.let {
                it.setText("重发（$kNumber）")
            }
        }
    }

    /***
     * @param mContext
     * @param finisColor 结束颜色
     * @param defaultColor 默认颜色
     * @param btn  按钮
     * @return
     */
    fun startTime(
        mContext: Context,
        defaultColor: Int,
        defaultBgColor: Int,
        finishColor: Int,
        finishBgColor: Int,
        btn: Button, onFinishListener: () -> Unit
    ) {
        this.mContext = mContext
        this.mDefaultColor = defaultColor
        this.mFinishColor = finishColor
        this.mDefaultBgColor = defaultBgColor
        this.mFinishBgColor = finishBgColor
        this.mButton = btn
        this.onFinishListener = onFinishListener
        mCountDownTimer.start()
        mButton?.let {
            it.isEnabled = false
            it.setTextColor(mContext.resources.getColor(mDefaultColor))
            it.setBackgroundResource(mDefaultBgColor)
        }
    }

    fun onStop() {
        mCountDownTimer.cancel()
    }
}