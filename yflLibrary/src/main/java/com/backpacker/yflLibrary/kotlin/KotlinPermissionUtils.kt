package com.backpacker.yflLibrary.kotlin

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import com.yanzhenjie.permission.AndPermission


object KotlinPermissionUtils {

    /**
     * 权限提示
     */
    fun showPermission(
        mContext: Activity, titleContent: String,
        permissionStr: Array<String>
        , onGrantedListener: () -> Unit
    ) {
        AndPermission.with(mContext).runtime().permission(*permissionStr)
            .onGranted { permissions ->
                onGrantedListener()
            }
            .onDenied { permissions ->
                val builder = AlertDialog.Builder(mContext)
                builder.setTitle("权限提示")
                builder.setMessage(titleContent) //提示内容
                builder.setPositiveButton("确定")
                { dialog: DialogInterface, which: Int ->
                    KotlinSystemUtil.getAppDetailSettingIntent(
                        mContext
                    )
                }
                builder.setNegativeButton("取消") { dialog: DialogInterface, which: Int -> }
                val dialog = builder.create()
                dialog.show()
            }
            .start()
    }

    /**
     * 权限提示
     */
    fun showPermission(
        mContext: Activity, titleName: String,
        titleContent: String,
        permissionStr: Array<String>
        , onGrantedListener: () -> Unit
    ) {
        AndPermission.with(mContext).runtime().permission(*permissionStr)
            .onGranted { permissions ->
                onGrantedListener()
            }
            .onDenied { permissions ->
                val builder = AlertDialog.Builder(mContext)
                builder.setTitle(titleName)
                builder.setMessage(titleContent) //提示内容
                builder.setPositiveButton("确定")
                { dialog: DialogInterface, which: Int ->
                    KotlinSystemUtil.getAppDetailSettingIntent(
                        mContext
                    )
                }
                builder.setNegativeButton("取消") { dialog: DialogInterface, which: Int -> }
                val dialog = builder.create()
                dialog.show()
            }
            .start()
    }


}