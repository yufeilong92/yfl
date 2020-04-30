package com.backpacker.yflLibrary.kotlin

import android.content.Context
import android.graphics.*
import android.widget.ImageView
import com.backpacker.yflLibrary.view.RoundTransform
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation


/**
 * @Author : YFL  is Creating a porject in xiaole
 * @Package com.backpacker.yflLibrary.kotlin
 * @Email : yufeilong92@163.com
 * @Time :2019/11/22 14:49
 * @Purpose :图片工具
 */
object PicassoUtil {
    fun loadQuadRangleImager(context: Context,imageView: ImageView,path:String){
        if (KotlinStringUtil.isEmpty(path)) {
            imageView.setImageResource(com.example.UtilsLibrary.R.mipmap.ic_default_img)
            return
        }


        Picasso.get()
            .load(path)
            .transform(RoundTransform(10))
            .placeholder(com.example.UtilsLibrary.R.mipmap.ic_default_img)
            .error(com.example.UtilsLibrary.R.mipmap.ic_default_img)
            .fit()
            .tag("img")
            .centerCrop()
            .into(imageView)
    }
    fun loadImager(imageView: ImageView, path: String) {
        if (KotlinStringUtil.isEmpty(path)) {
            imageView.setImageResource(com.example.UtilsLibrary.R.mipmap.ic_default_img)
            return
        }
        Picasso.get()
            .load(path)
            .placeholder(com.example.UtilsLibrary.R.mipmap.ic_default_img)
            .error(com.example.UtilsLibrary.R.mipmap.ic_default_img)
            .fit()
            .centerCrop()
            .tag("img")
            .into(imageView)
    }
    /***
     * @param data
     *  加载圆角图片  不带剧中
     * @return
     */
    fun loadQuadRangleImagerWitOutScay(context: Context, imageView: ImageView, path: String) {
        if (KotlinStringUtil.isEmpty(path)) {
            imageView.setImageResource(com.example.UtilsLibrary.R.mipmap.ic_default_img)
            return
        }


        Picasso.get()
            .load(path)
            .transform(RoundTransform(10))
            .placeholder(com.example.UtilsLibrary.R.mipmap.ic_default_img)
            .error(com.example.UtilsLibrary.R.mipmap.ic_default_img)
            .fit()
            .tag("img")
            .into(imageView)
    }
    /***
     * @param data
     *  加载圆角图片  不带剧中
     * @return
     */
    fun loadCircleImager(imageView: ImageView, path: String) {
        if (KotlinStringUtil.isEmpty(path)) {
            imageView.setImageResource(com.example.UtilsLibrary.R.mipmap.ic_default_img)
            return
        }


        Picasso.get()
            .load(path)
            .transform(CircleTransform())
            .placeholder(com.example.UtilsLibrary.R.mipmap.ic_default_img)
            .error(com.example.UtilsLibrary.R.mipmap.ic_default_img)
            .fit()
            .tag("img")
            .into(imageView)
    }

    internal class CircleTransform : Transformation {
        override fun key(): String {
            return "circle"
        }

        override fun transform(source: Bitmap?): Bitmap {
            return source?.let {
                val size = Math.min(it.width, it.height)
                val x = (it.width - size) / 2
                val y = (it.height - size) / 2
                val createBitmap = Bitmap.createBitmap(it, x, y, size, size)
                if (createBitmap != it)
                    it.recycle()
                val   bitmap = Bitmap.createBitmap(size, size, it.config)
                val canvas = Canvas(bitmap)
                val paint = Paint()
                val shader =
                    BitmapShader(createBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
                paint.setShader(shader)
                paint.isAntiAlias = true
                val r = size / 2f
                canvas.drawCircle(r, r, r, paint)
                createBitmap.recycle()
                bitmap

            }!!
        }


    }
}