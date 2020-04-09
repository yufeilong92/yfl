package com.backpacker.yflLibrary.kotlin

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 * @Title:  kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.kotlin
 * @Description:    $todo$
 * @author: L-BackPacker
 * @date:   2019/3/31 23:12
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
object ImagerUtil {
    fun saveImage(context: Context, inputstream: ByteArray): String {
        val bitmap = BitmapFactory.decodeByteArray(inputstream, 0, inputstream.size)
        val path = context.cacheDir.absolutePath
        val file = File(path)
        if (!file.exists()) {
            file.mkdirs();
        }
        val name: String = "" + System.currentTimeMillis() + ".jpg"
        val fileOne = File(file, name)
        try {
            val fileOutputStream = FileOutputStream(fileOne)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
            fileOutputStream.flush()
            fileOutputStream.close()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return file.absolutePath

    }

    // 这里是根据质量压缩.可以吧图片压缩到100K以下,然后保存到本地文件
    fun compressAndSave(context: Context, image: Bitmap?): String {
        var image = image
        var path = ""
        try {
            val baos = ByteArrayOutputStream()
            image!!.compress(Bitmap.CompressFormat.JPEG, 100, baos)// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
            var options = 100
            while (baos.toByteArray().size / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
                baos.reset()// 重置baos即清空baos
                image.compress(Bitmap.CompressFormat.JPEG, options, baos)// 这里压缩options%，把压缩后的数据存放到baos中
                options -= 10// 每次都减少10
            }
            path = saveImg(context, baos)

            if (image != null && image.isRecycled) {
                image.recycle()
                image = null
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return path
    }

    //图片保存到本地，--返回图片的路径
    private fun saveImg(context: Context, bos: ByteArrayOutputStream): String {
        var fos: FileOutputStream? = null
//        var path:String=""
//        var path = getImgpath(context,System.currentTimeMillis().toString())
        var path = context.externalCacheDir!!.absolutePath + File.separator + "/" + System.currentTimeMillis() + ".png"
        val file = File(path)
        try {
//            val filePath = context.externalCacheDir.absolutePath+File.separator+"image"
//            val file01 = File(filePath)
//            if(!file01.exists()){
//                file01.mkdirs()
//            }
//            val file02 = File(file01,System.currentTimeMillis().toString()+".png")
//            if(!file02.exists()){
//                file02.createNewFile()
//            }
            if (!file.exists()) {
                file.createNewFile()
            }
            path = file.toString()
            fos = FileOutputStream(file)
            fos.write(bos.toByteArray())
            fos.flush()
        } catch (e: IOException) {
            e.printStackTrace()
            path = ""
        } finally {
            try {
                fos?.close()
                bos?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        return path

    }

    fun getDefaultPath(context: Context): String {
        var path = context.externalCacheDir!!.absolutePath + File.separator + "/"
        val file = File(path)
        try {
//            val filePath = context.externalCacheDir.absolutePath+File.separator+"image"
//            val file01 = File(filePath)
//            if(!file01.exists()){
//                file01.mkdirs()
//            }
//            val file02 = File(file01,System.currentTimeMillis().toString()+".png")
//            if(!file02.exists()){
//                file02.createNewFile()
//            }
            if (!file.exists()) {
                file.createNewFile()
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
        return path
    }

}