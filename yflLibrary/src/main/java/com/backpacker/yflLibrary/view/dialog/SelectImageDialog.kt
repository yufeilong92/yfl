package com.backpacker.yflLibrary.view.dialog


import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import com.example.UtilsLibrary.R
import kotlinx.android.synthetic.main.dialog_select_image.*

abstract class SelectImageDialog(var mContext: Context) : AlertDialog(mContext, R.style.dialog), View.OnClickListener {

    private var metrics: DisplayMetrics = context.resources.displayMetrics


    init {
        window!!.setWindowAnimations(R.style.popup_animation)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSizeMode()
        setContentView(R.layout.dialog_select_image)

        initView()
    }

    private fun initView() {
        dialog_select_image_takePrice.setOnClickListener(this)
        dialog_select_image_fromPhoto.setOnClickListener(this)
        dialog_select_image_cancel.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            dialog_select_image_takePrice -> {
                onTakePrice()
                dismiss()
            }
            dialog_select_image_fromPhoto -> {
                onFromPhoto()
                dismiss()
            }
            dialog_select_image_cancel -> dismiss()
        }
    }

    abstract fun onTakePrice()
    abstract fun onFromPhoto()

    private fun setSizeMode() {
        val params = window!!.attributes
        params.width = metrics.widthPixels
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        window!!.attributes = params
        window!!.setGravity(Gravity.BOTTOM)
    }
}