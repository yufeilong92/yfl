package com.backpacker.yflLibrary.kotlin

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.*
import androidx.annotation.Nullable


/**
 * @Title:  kotlin_androidone
 * @Package com.backpacker.UtilsLibrary
 * @Description:    字符串工具类
 * @author: L-BackPacker
 * @date:   2019/3/31 21:14
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
object KotlinStringUtil {

    fun isEmpty(str: String?): Boolean {
        if (str == null || str == "")
            return true
        return false
    }
    /***
     * 获取布局数据
     * @param v
     * @return
     */
    fun getObjectToStr(v: View): String {
        if (v is TextView) {
            val tv = v as TextView
            return tv.text.toString().trim()
        }
        if (v is EditText) {
            val et = v as EditText
            return et.text.toString().trim()
        }
        if (v is Button) {
            val btn = v as Button
            return btn.getText().toString().trim()
        }
        if (v is CheckBox) {
            val ch = v as CheckBox
            return ch.text.toString().trim()
        }
        if (v is RadioButton) {
            val rb = v as RadioButton
            return rb.text.toString().trim()
        }
        return ""
    }
   /***
    * 设置布局显示
    * @return
    */
    fun setViewStr(v: View, com: String?) {
        if (v is TextView) {
            val tv = v as TextView
            tv.text = com
        }
        if (v is EditText) {
            val et = v as EditText
            et.setText(com)
        }
        if (v is Button) {
            val btn = v as Button
            btn.setText(com)
        }
        if (v is CheckBox) {
            val ch = v as CheckBox
            ch.setText(com)
        }
        if (v is RadioButton) {
            val rb = v as RadioButton
            rb.text = com
        }
    }

    fun getStringWid(m: Context, id: Int): String {
        return m.getResources().getString(id)
    }
    /***
     * 设置1111xxxxx
     * @param com
     * @return
     */
    fun setTextXXXX(com: String): String? {
        if (isEmpty(com)) return ""
        if (com.length <= 12) {
            return com
        }
        val subSequence = com.subSequence(0, 4).toString()
        val charSequence = com.subSequence(com.length - 4, com.length).toString()
        return "$subSequence****$charSequence"


    }

    fun setTextYMoeny(tv: TextView) {
        tv.text = Html.fromHtml("&yen")
    }

    fun getTextYMoney(): Spanned {
        return Html.fromHtml("&yen")
    }

    /***
     * @param tv
     * 设置下滑线
     */
    fun setTextWithLine(tv: TextView) {
        tv.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG or Paint.ANTI_ALIAS_FLAG
    }
     /***
      * @param data 设置小数点
      * @return
      */
    fun setMoneyDian(data: String): String {
        var s: CharSequence = data
        val digits = 2
        //删除“.”后面超过2位后的数据
        if (s.toString().contains(".")) {
            if (s.length - 1 - s.indexOf(".") > digits) {
                s = s.subSequence(0, s.indexOf(".") + digits + 1)
            }
        }
        //如果"."在起始位置,则起始位置自动补0
        //如果"."在起始位置,则起始位置自动补0
        if (s.toString().trim({ it <= ' ' }).substring(0) == ".") {
            s = "0$s"
        }

        //如果起始位置为0,且第二位跟的不是".",则无法后续输入

        //如果起始位置为0,且第二位跟的不是".",则无法后续输入
        if (s.toString().startsWith("0")
            && s.toString().trim({ it <= ' ' }).length > 1
        ) {
            if (s.toString().substring(1, 2) != ".") {
                return ""
            }
        }
        return s.toString()
    }

    /***
     *设置隐藏
     * @param view
     * @param com
     * @return
     */
    fun setGmVinOrGone(view: View, com: String?) {
        if (isEmpty(com)) {
            view.visibility = View.GONE
        } else {
            view.visibility = View.VISIBLE
        }
    }

    /***
     * 内容为空隐藏 否则显示
     * @param view
     * @param com
     * @return
     */
    fun setGmVinOrGoneWithCom(view: View, com: String?) {
        if (isEmpty(com)) {
            view.visibility = View.GONE
        } else {
            view.visibility = View.VISIBLE
            setViewStr(view, com)
        }
    }

    interface SpannableStringListener {
        fun item()
    }

    /***
     * 设置SpannabkeString 监听事件
     * @param tv 显示空间
     * @param data 内容
     * @param start 标注颜色开始的位置
     * @param end 标注颜色结束的位置
     * @param color 标注颜色
     * @param listener 标注监听
     * @return
     */
    fun spangStringLintener(
        tv: TextView,
        data: String,
        start: Int,
        end: Int,
        @Nullable
        color: String?,
        listener: SpannableStringListener
    ) {
        val spannableString = SpannableString(data)
        val clickableSpan = MyClickableSpan(listener)
        spannableString.setSpan(
            clickableSpan,
            start,
            end,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        val colorSpan = ForegroundColorSpan(Color.parseColor(color ?: "#0BC467"))
        spannableString.setSpan(
            colorSpan, start,
            end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        tv.movementMethod = LinkMovementMethod.getInstance()
        tv.text = spannableString

    }

    internal class MyClickableSpan(var listener: SpannableStringListener?) : ClickableSpan() {
        override fun onClick(p0: View) {
            if (listener != null) {
                listener!!.item()
            }
        }

        override fun updateDrawState(ds: TextPaint) {
            ds.isUnderlineText = false
        }


    }
}