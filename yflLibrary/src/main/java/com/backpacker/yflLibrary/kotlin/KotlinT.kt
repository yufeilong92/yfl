package com.backpacker.yflLibrary.kotlin

import android.content.Context
import android.os.Handler
import android.widget.Toast



/**
 * @Title:  kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.kotlin
 * @Description:    $todo$
 * @author: L-BackPacker
 * @date:   2019/3/31 23:53
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
object KotlinT {
    private var mToast: Toast? = null
    private val mhandler = Handler()
    private val r = Runnable { mToast!!.cancel() }

    fun showToast(context: Context, text: String) {
        mhandler.removeCallbacks(r)
        if (null != mToast) {
            mToast!!.setText(text)
        } else {
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
        }
        mhandler.postDelayed(r, 2000)
        mToast!!.show()
    }

    fun showToast(context: Context, strId: Int) {
        showToast(context, context.getString(strId))
    }



}