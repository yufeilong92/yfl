package com.backpacker.yflLibrary.kotlin

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView

/**
 * @Title:  tsyc
 * @Package com.backpacker.UtilsLibrary.kotlin
 * @Description:    View工具类型
 * @author: L-BackPacker
 * @date:   2019/7/6 13:47
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
object KotlinSetUtils {
    fun setImagerSrc(im: ImageView, id: Int) {
        im.setImageResource(id)
    }


    fun setTextString(tv: TextView, str: String) {
        if (KotlinStringUtil.isEmpty(str)) return
        tv.text = str
    }

    fun setTextBraground(tv: TextView, id: Int) {
        tv.setBackgroundColor(id)
    }

    fun setLayoutBragroundColor(v: View, id: Int) {
        when (v) {
            is LinearLayout -> {
                (v as LinearLayout).setBackgroundColor(id)
            }
            is RelativeLayout ->{
                (v as RelativeLayout).setBackgroundColor(id)
            }
        }
    }
    fun setLayoutBaraground(v: View,id: Int){
        when (v) {
            is LinearLayout -> {
                (v as LinearLayout).setBackgroundResource(id)
            }
            is RelativeLayout ->{
                (v as RelativeLayout).setBackgroundResource(id)
            }
        }
    }

}
