package com.backpacker.yflLibrary.kotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import com.yanzhenjie.permission.FileProvider
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.backpacker.UtilsLibrary.kotlin
 * @Email : yufeilong92@163.com
 * @Time :2019/7/5 10:21
 * @Purpose :
 */
object KotlinTakePhotoUtils {

    /**
     * 拍照
     */
    @Throws(IOException::class)
    fun takePhoto(mActivity: Activity, flag: Int): Uri? {
        //指定拍照intent
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        var imageUri: Uri? = null
        if (takePictureIntent.resolveActivity(mActivity.packageManager) != null) {
            val sdcardState = Environment.getExternalStorageState()
            var outputImage: File? = null
            if (Environment.MEDIA_MOUNTED.equals(sdcardState)) {
                outputImage = createImageFile(mActivity)
            } else {
                Toast.makeText(mActivity.applicationContext, "内存异常", Toast.LENGTH_SHORT).show()
            }
            try {
                if (outputImage!!.exists()) {
                    outputImage!!.delete()
                }
                outputImage!!.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            if (outputImage != null) {
//                imageUri = Uri.fromFile(outputImage)
                imageUri = FileProvider.getUriForFile(mActivity, mActivity.packageName+".FileProvider", outputImage)
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                mActivity.startActivityForResult(takePictureIntent, flag)
            }
        }

        return imageUri
    }


    @Throws(IOException::class)
    fun createImageFile(mActivity: Activity): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_$timeStamp"//创建以时间命名的文件名称
        val storageDir = getOwnCacheDirectory(mActivity, "yugong" + File.separator + "takePhone")//创建保存的路径
        val image = File(storageDir.path, "$imageFileName.jpg")
        if (!image.exists()) {
            try {
                //在指定的文件夹中创建文件
                image.createNewFile()
            } catch (e: Exception) {
            }

        }

        return image
    }


    /**
     * 根据目录创建文件夹
     * @param context
     * @param cacheDir
     * @return
     */
    fun getOwnCacheDirectory(context: Context, cacheDir: String): File {
        var appCacheDir: File? = null
        //判断sd卡正常挂载并且拥有权限的时候创建文件
        if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState() && hasExternalStoragePermission(context)) {
            appCacheDir = File(Environment.getExternalStorageDirectory(), cacheDir)
        }
        if (appCacheDir == null || !appCacheDir!!.exists() && !appCacheDir!!.mkdirs()) {
            appCacheDir = context.cacheDir
        }
        return appCacheDir!!
    }


    /**
     * 检查是否有权限
     * @param context
     * @return
     */
    private fun hasExternalStoragePermission(context: Context): Boolean {
        val perm = context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE")
        return perm == 0
    }
}