package com.backpacker.yflLibrary.kotlin

import android.content.Context
import android.view.Gravity
import com.backpacker.yflLibrary.view.dialog.GmDialog
import com.example.UtilsLibrary.R
import kotlinx.android.synthetic.main.dialog_gm_dialog.*

/**
 * @Author : YFL  is Creating a porject in My Application
 * @Package com.backpacker.yflLibrary.kotlin
 * @Email : yufeilong92@163.com
 * @Time :2020/8/10 11:08
 * @Purpose :对话框
 */
object KotlinDialogUtil {

    fun showDialogTitle(
        context: Context,
        title: String?,
        content: String?,
        sure: String?,
        canle: String?,
        canable: Boolean,
        cannerlistener: () -> Unit,
        sureList: () -> Unit
    ) {
        val dialog = object : GmDialog(context, R.layout.dialog_gm_dialog, Gravity.CENTER) {
            override fun initViewData() {
                if (!KotlinStringUtil.isEmpty(title))
                    tv_gm_dialog_item_title.text = title
                if (!KotlinStringUtil.isEmpty(content))
                    tv_gm_dialog_item_content.text = content
                KotlinStringUtil.setGmVinOrGoneWithCom(btn_gm_dialog_left,canle)
                KotlinStringUtil.setGmVinOrGoneWithCom(btn_gm_dialog_right,sure)
                btn_gm_dialog_left.setOnClickListener {
                    dismiss()
                    cannerlistener()
                }
                btn_gm_dialog_right.setOnClickListener {
                    dismiss()
                    sureList()
                }

            }
        }
        dialog.setCancelable(canable)
        dialog.show()
    }

}