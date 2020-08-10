package com.backpacker.yflLibrary.view.dialog

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import com.example.UtilsLibrary.R
import kotlinx.android.synthetic.main.cammer_dialog.*

/**
 * @Title:  tsyc
 * @Package com.example.tsyc.customView
 * @Description:    $todo$
 * @author: L-BackPacker
 * @date:   2019/7/5 9:10
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
abstract class SelectCammerDialog(var mContext: Context) : AlertDialog(mContext, R.style.my_dialog) {
    private var metrics: DisplayMetrics = mContext.resources.displayMetrics

    init {
        window!!.setWindowAnimations(R.style.popup_animation)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSizeMode()
        setContentView(R.layout.cammer_dialog)
        ininOnClick()
    }

    abstract fun onTakePrice()
    abstract fun onFromPhoto()
    fun ininOnClick() {
        dialog_play_cammer.setOnClickListener {
            onTakePrice()
            dismiss()
        }
        dialog_from_phone.setOnClickListener {
            onFromPhoto()
            dismiss()
        }
        dialog_canner.setOnClickListener {
            dismiss()
        }

    }

    private fun setSizeMode() {
        val params = window!!.attributes
        params.width = metrics.widthPixels
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        window!!.attributes = params
        window!!.setGravity(Gravity.BOTTOM)
    }
}