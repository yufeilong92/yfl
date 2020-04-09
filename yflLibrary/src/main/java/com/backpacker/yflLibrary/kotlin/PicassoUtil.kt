package com.backpacker.yflLibrary.kotlin

import android.content.Context
import android.widget.ImageView
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.graphics.Shader.TileMode
import android.graphics.*
import com.backpacker.yflLibrary.view.RoundTransform
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.zzzh.akhalteke.utils.GlideUtil


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
}