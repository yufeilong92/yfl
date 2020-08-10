package com.backpacker.yflLibrary.view.dialog

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.WindowManager
import com.backpacker.yflLibrary.java.JavaEditTextUtil
import com.backpacker.yflLibrary.kotlin.*
import com.backpacker.yflLibrary.kotlin.KotlinStringUtil
import com.example.UtilsLibrary.R
import kotlinx.android.synthetic.main.dialog_evaluate.*

/**
 * @Author : YFL  is Creating a porject in DELL
 * @Package cn.ruiye.xiaole.view.dialog
 * @Email : yufeilong92@163.com
 * @Time :2019/9/26 11:55
 * @Purpose :评价对话框
 */
abstract class EvaluateDialog(var mContext: Context,var activity: Activity) : AlertDialog(mContext, R.style.dialog) {

    private var metrics: DisplayMetrics = context.resources.displayMetrics


    init {
        window!!.setWindowAnimations(R.style.popup_animation)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSizeMode()
        window!!.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
        setContentView(R.layout.dialog_evaluate)
        initView()
    }

    abstract fun onSubmitListener(com: String)
    abstract fun onDismissListener()
    abstract fun onShowListener()
    private fun initView() {
        JavaEditTextUtil.showSoftInputFromWindow(activity,et_eva_input)
        btn_eva_submit.setOnClickListener {
            val com = KotlinStringUtil.getObjectToStr(et_eva_input)
            if (KotlinStringUtil.isEmpty(com)) {
                KotlinT.showToast(mContext, "请填写您的精彩内容")
                return@setOnClickListener
            }
            et_eva_input.text=null
            onSubmitListener(com)
        }
    }

    private fun setSizeMode() {
        val params = window!!.attributes
        params.width = metrics.widthPixels
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        window!!.attributes = params
        window!!.setGravity(Gravity.BOTTOM)
    }

    override fun setOnDismissListener(listener: DialogInterface.OnDismissListener?) {
        onDismissListener()
        super.setOnDismissListener(listener)
    }

    override fun setOnShowListener(listener: DialogInterface.OnShowListener?) {
        onShowListener()
        super.setOnShowListener(listener)
    }
}