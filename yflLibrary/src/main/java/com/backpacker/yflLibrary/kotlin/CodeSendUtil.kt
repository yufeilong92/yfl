package com.backpacker.yflLibrary.kotlin

import android.R
import android.content.Context
import android.os.Handler
import android.widget.Button


/**
 * @Title:  kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.kotlin
 * @Description:    $todo$
 * @author: L-BackPacker
 * @date:   2019/3/31 23:34
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
class CodeSendUtil {
    var recLen = 60
    private var mContext: Context? = null
    var handler = Handler()
    private var util: CodeSendUtil? = null
    private var button: Button? = null

    var runnable: Runnable = object : Runnable {
        override fun run() {
            recLen--
            if (recLen == 0) {
                button!!.text = "重新发送"
                button!!.isEnabled = true
                button!!.setTextColor(mContext!!.resources.getColor(R.color.holo_red_dark))
                recLen = 60
                return
            }
            button!!.setTextColor(mContext!!.resources.getColor(R.color.darker_gray))
            button!!.text = "重发($recLen)"
            handler.postDelayed(this, 1000)
        }
    }

    fun startTime(context: Context, button: Button) {
        this.button = button
        this.mContext = context
        handler.postDelayed(runnable, 1000)
    }

    fun getInstance(): CodeSendUtil {
        if (util == null)
            util = CodeSendUtil()
        return util as CodeSendUtil
    }

    fun stop() {
        handler.postDelayed(null, 1000)
    }
}