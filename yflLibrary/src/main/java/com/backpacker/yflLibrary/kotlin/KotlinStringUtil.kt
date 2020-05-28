package com.backpacker.yflLibrary.kotlin

import android.content.Context
import android.graphics.Paint
import android.text.Html
import android.text.Spanned
import android.view.View
import android.widget.*


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

    fun getStringWid(m: Context, id: Int): String {
        return m.getResources().getString(id)
    }

    fun setVinOrGone(tv: TextView, com: String?) {
        if (isEmpty(com)) {
            tv.visibility = View.GONE
        } else {
            tv.visibility = View.VISIBLE
        }
    }

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
    fun setTextWithLine(tv: TextView){
        tv.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG or Paint.ANTI_ALIAS_FLAG
    }

    fun setTextViewVOrGWData(tv: TextView, data: String) {
        if (isEmpty(data)) {
            tv.visibility = View.GONE
        } else {
            tv.visibility = View.VISIBLE
            tv.text = data
        }
    }
    fun setTextViewVOrG(tv: View, data: String) {
        if (isEmpty(data)) {
            tv.visibility = View.GONE
        } else {
            tv.visibility = View.VISIBLE
        }
    }
    fun setMoneyDian(data: String) :String{
        var s:CharSequence = data
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
}