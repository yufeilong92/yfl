package com.backpacker.yflLibrary.kotlin

import android.app.Activity
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import java.util.*

/**
 * @Author : YFL  is Creating a porject in basehttps
 * @Package com.backpacker.yflLibrary.kotlin
 * @Email : yufeilong92@163.com
 * @Time :2019/11/23 16:47
 * @Purpose :软键盘开关工具类
 */
object KotlinKeyBoardUtils {
    /**
     * 打开软键盘
     *
     * @param mEditText
     * @param mContext
     */
    fun openKeybord(mEditText: View, mContext: Context) {
        val imm = mContext
            .getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN)
        imm.toggleSoftInput(
            InputMethodManager.SHOW_FORCED,
            InputMethodManager.HIDE_IMPLICIT_ONLY
        )
    }

    /**
     * 关闭软键盘
     *
     * @param mEditText
     * @param mContext
     */
    fun closeKeybord(mEditText: View, mContext: Context) {
        val imm = mContext.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mEditText.windowToken, 0)
    } /**
     * 关闭软键盘
     *
     * @param mEditText
     * @param mContext
     */
    fun closeKeybord( mContext: Activity) {
        val imm = mContext.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mContext.currentFocus!!.windowToken, 0)
    }

    /**
     * des:隐藏软键盘,这种方式参数为activity
     *
     * @param activity
     */
    fun hideInputForce(activity: Activity?) {
        if (activity == null || activity.currentFocus == null)
            return
        (activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(
                activity.currentFocus!!
                    .windowToken, InputMethodManager.HIDE_NOT_ALWAYS
            )
    }

    /**
     * 打开键盘
     */
    fun showInput(context: Context, view: View) {
        val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm != null) {
            view.requestFocus()
            imm!!.showSoftInput(view, 0)
        }
    }

    /**
     * 打卡软键盘
     *
     * @param mEditText 输入框
     * @param mContext  上下文
     */
    fun openKeybord500(mEditText: EditText?, mContext: Context) {

        //必须要等UI绘制完成之后，打开软键盘的代码才能生效，所以要设置一个延时
        val timer = Timer()
        timer.schedule(object : TimerTask() {
           override fun run() {
                val imm = mContext
                    .getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN)
                imm.toggleSoftInput(
                    InputMethodManager.SHOW_FORCED,
                    InputMethodManager.HIDE_IMPLICIT_ONLY
                )
            }
        }, 500)
    }
}