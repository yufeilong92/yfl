package com.backpacker.yflLibrary.kotlin

import android.content.Context
import android.widget.ImageView
import android.graphics.*
import androidx.recyclerview.widget.RecyclerView
import com.backpacker.yflLibrary.view.RoundedCornersTransformation
import com.example.UtilsLibrary.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import java.io.File


/**
 * @Author : YFL  is Creating a porject in xiaole
 * @Package com.backpacker.yflLibrary.kotlin
 * @Email : yufeilong92@163.com
 * @Time :2019/11/22 14:49
 * @Purpose :图片工具
 */
object KotlinPicassoUtil {
    private val PucassiTag="img"
    fun loadImager(imageView: ImageView, path: String?) {
        if (KotlinStringUtil.isEmpty(path)) {
            imageView.setImageResource(R.mipmap.ic_default_img)
            return
        }
        Picasso.get()
            .load(path)
            .placeholder(R.mipmap.ic_default_img)
            .error(R.mipmap.ic_default_img)
            .fit()
            .tag(PucassiTag)
            .into(imageView)
    }

    fun loadBImager(imageView: ImageView, path: String?) {
        if (KotlinStringUtil.isEmpty(path)) {
            imageView.setImageResource(R.mipmap.ic_default_img)
            return
        }
        Picasso.get()
            .load(File(path))
            .placeholder(R.mipmap.ic_default_img)
            .error(R.mipmap.ic_default_img)
            .fit()
            .tag(PucassiTag)
            .into(imageView)
    }

    /**
     * 加载部分圆角图片
     */
    fun loadQuadRangleHalfTypeImager(
        imageView: ImageView,
        path: String, radius: Int,
        type: RoundedCornersTransformation.CornerType
    ) {
        if (KotlinStringUtil.isEmpty(path)) {
            imageView.setImageResource(R.mipmap.ic_default_img)
            return
        }

        Picasso.get()
            .load(path)
            .placeholder(R.mipmap.ic_default_img)
            .error(R.mipmap.ic_default_img)
            .fit()
            .transform(RoundedCornersTransformation(radius, 0, type))
            .tag(PucassiTag)
            .into(imageView)
    }
    /**
     * 加载部分圆角图片
     */
    fun loadQuadBRangleHalfTypeImager(
        imageView: ImageView,
        path: String, radius: Int,
        type: RoundedCornersTransformation.CornerType
    ) {
        if (KotlinStringUtil.isEmpty(path)) {
            imageView.setImageResource(R.mipmap.ic_default_img)
            return
        }

        Picasso.get()
            .load(File(path))
            .placeholder(R.mipmap.ic_default_img)
            .error(R.mipmap.ic_default_img)
            .fit()
            .transform(RoundedCornersTransformation(radius, 0, type))
            .tag(PucassiTag)
            .into(imageView)
    }

    /***
     * @param data
     *  加载圆角图片  不带剧中
     * @return
     */
    fun loadCircleImager(imageView: ImageView,  path: String?) {
        if (KotlinStringUtil.isEmpty(path)) {
            imageView.setImageResource(R.mipmap.ic_default_img)
            return
        }

        Picasso.get()
            .load(path)
            .placeholder(R.mipmap.ic_default_img)
            .error(R.mipmap.ic_default_img)
            .fit()
            .transform(CircleTransform())
            .tag(PucassiTag)
            .into(imageView)
    }
    /***
     * @param data
     *  加载圆角图片  不带剧中
     * @return
     */
    fun loadBCircleImager(com:Context,imageView: ImageView,  path: String?) {
        if (KotlinStringUtil.isEmpty(path)) {
            imageView.setImageResource(R.mipmap.ic_default_img)
            return
        }


        Picasso.get()
            .load(File(path))
            .placeholder(R.mipmap.ic_default_img)
            .error(R.mipmap.ic_default_img)
            .fit()
            .transform(CircleTransform())
            .tag(PucassiTag)
            .into(imageView)
    }

    internal class CircleTransform : Transformation {
        override fun key(): String {
            return "circle"
        }

        override fun transform(source: Bitmap?): Bitmap {
            return source?.let {
                val size = Math.min(source.width, source.height)
                val width = (source.width - size) / 2
                val height = (source.height - size) / 2
                val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
                val canvas = Canvas(bitmap)
                val paint = Paint()
                val shader =
                    BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
                if (width != 0 || height != 0) {
                    // source isn't square, move viewport to center
                    val matrix = Matrix()
                    matrix.setTranslate(-width.toFloat(), -height.toFloat())
                    shader.setLocalMatrix(matrix)
                }
                paint.shader = shader
                paint.isAntiAlias = true
                val r = size / 2f
                canvas.drawCircle(r, r, r, paint)
                source.recycle()
                bitmap

            }!!
        }


    }
    fun setPauseTagAndResueTag(rlv: RecyclerView){
        rlv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Picasso.get().resumeTag(PucassiTag)
                } else {
                    Picasso.get().pauseTag(PucassiTag)
                }
            }
        })
    }
}