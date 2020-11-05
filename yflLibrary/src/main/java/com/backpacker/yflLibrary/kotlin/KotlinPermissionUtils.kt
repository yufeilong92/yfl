package com.backpacker.yflLibrary.kotlin

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.permissionx.guolindev.PermissionX
import com.yanzhenjie.permission.AndPermission


object KotlinPermissionUtils {
    /**
     * 请求权限
     */
    fun showPermissionX(
        mContext: FragmentActivity, titleContent: String,
        permissionStr: MutableList<String>, onGrantedListener: () -> Unit
    ) {
        PermissionX.init(mContext)
            .permissions(permissionStr)
            .setDialogTintColor(Color.parseColor("#008577"), Color.parseColor("#83e8dd"))
            .onExplainRequestReason { scope, deniedList ->
                var message: String
                if (KotlinStringUtil.isEmpty(titleContent)) {
                    message = titleContent
                } else
                    message =  "小乐到家需要以下权限才能继续"
                scope.showRequestReasonDialog(deniedList, message, "允许", "拒绝")
            }
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    onGrantedListener()
                } else {
                    Toast.makeText(mContext, "您拒绝改:$deniedList,部分功能无法使用", Toast.LENGTH_SHORT).show()
                }
            }
    }

}