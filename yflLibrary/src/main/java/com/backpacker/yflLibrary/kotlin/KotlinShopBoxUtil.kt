package com.backpacker.yflLibrary.kotlin

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Point
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.*
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.annotation.NonNull

/**
 * @Author : YFL  is Creating a porject in My Application
 * @Package com.backpacker.yflLibrary.kotlin
 * @Email : yufeilong92@163.com
 * @Time :2020/4/13 13:50
 * @Purpose :商城购物筐
 */
object KotlinShopBoxUtil {
    /**
     * @param activity
     * @param id 加载过程动画图片
     * @param start 开始view
     * @param ent  结束view
     */
    fun setShopAnim(
        activity: Activity,
        @DrawableRes id: Int,
        start: View,
        ent: View,
        startListener: () -> Unit,
        endListener: () -> Unit
    ) {
        val start_location = IntArray(2)
        start.getLocationInWindow(start_location)

        val img = ImageView(activity!!)
        img.setImageBitmap(getAddDrawBitMap(activity, id))

        var anim_mask_layout: ViewGroup? = null
        anim_mask_layout = createAnimLayout(activity)
        // 把动画小球添加到动画层
        anim_mask_layout.addView(img)

        val view = addViewToAnimLayout(img, start_location)
        // 这是用来存储动画结束位置的X、Y坐标
        val end_location = IntArray(2)
        // rl_gouwuche是小球运动的终点 一般是购物车图标
        ent.getLocationInWindow(end_location)
        // 计算位移
        val point = getScreenSize(activity)
        val endView = IntArray(2)
        ent.getLocationOnScreen(endView)
        val i = endView[0]
        val with = point!!.x
        val endt = i - with
        val endX = endt + ent.width  // 动画位移的X坐标
        val endY = end_location[1] - start_location[1] // 动画位移的y坐标
        val translateAnimationX = TranslateAnimation(0f, endX.toFloat(), 0f, 0f)
        translateAnimationX.interpolator = LinearInterpolator()
        translateAnimationX.repeatCount = 0 // 动画重复执行的次数
        translateAnimationX.fillAfter = true

        val translateAnimationY = TranslateAnimation(0f, 0f, 0f, endY.toFloat())
        translateAnimationY.interpolator = AccelerateInterpolator()
        translateAnimationY.repeatCount = 0 // 动画重复执行的次数
        translateAnimationX.fillAfter = true

        val set = AnimationSet(false)
        set.fillAfter = false
        set.addAnimation(translateAnimationY)
        set.addAnimation(translateAnimationX)
        set.duration = 800 // 动画的执行时间
        view.startAnimation(set)
        // 动画监听事件
        set.setAnimationListener(object : Animation.AnimationListener {
            // 动画的开始
            override fun onAnimationStart(animation: Animation) {
                img.visibility = View.VISIBLE
                startListener()
            }

            override fun onAnimationRepeat(animation: Animation) {

            }

            // 动画的结束
            override fun onAnimationEnd(animation: Animation) {
                img.visibility = View.GONE
                endListener()

            }
        })

    }

    /**
     * 创建控件的背景
     */
    private fun convertViewToBitmap(view: View): Bitmap {
        view.measure(
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        )
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
        view.buildDrawingCache()
        return view.drawingCache
    }

    /**
     *  创建动画的父布局
     */
    private fun createAnimLayout(activity: Activity): ViewGroup {
        val rootView = activity!!.window.decorView as ViewGroup
        val animLayout = LinearLayout(activity!!)
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        animLayout.layoutParams = lp
        animLayout.id = Integer.MAX_VALUE
        animLayout.setBackgroundResource(android.R.color.transparent)
        rootView.addView(animLayout)
        return animLayout
    }

    /**
     * 添加动画
     * @param view  动画过程中的view
     * @param location  开始位置
     */
    private fun addViewToAnimLayout(view: View, location: IntArray): View {
        val x = location[0]
        val y = location[1]
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        lp.leftMargin = x
        lp.topMargin = y
        view.setLayoutParams(lp)
        return view
    }

    /**
     * 获取屏幕宽高
     */
    private fun getScreenSize(context: Context): Point? {
        val wm =
            context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val out = Point()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            display.getSize(out)
        } else {
            val width = display.width
            val height = display.height
            out[width] = height
        }
        return out
    }

    /**
     *  创建加载动画过程中的view
     *  @param id 动画背景id
     */
  private  fun getAddDrawBitMap(con: Context, @DrawableRes id: Int): Bitmap {
        val iv = ImageView(con)
        // 运动的控件，样式可以自定义
        iv.setImageResource(id)
        return convertViewToBitmap(iv)
    }
}