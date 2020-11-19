package com.backpacker.yflLibrary.view

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.UtilsLibrary.R

/**
 * @Author : YFL  is Creating a porject in My Application
 * @Package com.backpacker.yflLibrary.view
 * @Email : yufeilong92@163.com
 * @Time :2020/6/4 14:03
 * @Purpose :自定标题栏
 */
class TitleCustomBar : ConstraintLayout {


    lateinit var ivlifelistener: () -> Unit
    lateinit var tvlifelistener: () -> Unit


    lateinit var tvRightlistener: () -> Unit


    lateinit var ivRightlistener: () -> Unit


    constructor(
        context: Context,
        attributeSet: AttributeSet
    ) : super(context, attributeSet) {
        init(context, attributeSet)
    }

    private fun init(context: Context, attributeSet: AttributeSet) {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_title_custom_bar, this)
        val iv_life = view.findViewById<ImageView>(R.id.gm_iv_title_custom_back)
        val tv_life = view.findViewById<TextView>(R.id.gm_tv_custom_life_title)
        val viewtitlebg = view.findViewById<View>(R.id.gm_view_my_title_bg)
        val tv_Content = view.findViewById<TextView>(R.id.activityid)
        val iv_right = view.findViewById<ImageView>(R.id.gm_iv_custom_title_righit)
        val tv_right = view.findViewById<TextView>(R.id.gm_tv_custom_right_title)
        val mybar = view.findViewById<MyBarView>(R.id.my_custom_bar)

        attributeSet.let {
            val a = context.obtainStyledAttributes(attributeSet, R.styleable.TitleCustomBar)
            val barbg = a.getColor(R.styleable.TitleCustomBar_custombarBg, Color.TRANSPARENT)
            val topbarbg = a.getColor(R.styleable.TitleCustomBar_customTopbarBg, Color.TRANSPARENT)
            val drawable = a.getResourceId(R.styleable.TitleCustomBar_custombgDrawable, 0)
            val topdrawable = a.getResourceId(R.styleable.TitleCustomBar_custombgTopDrawable, 0)
            if (drawable == 0) {
                viewtitlebg.setBackgroundColor(barbg)
            } else {
                viewtitlebg.setBackgroundResource(drawable)
            }
            if (topdrawable == 0) {
                mybar.setBackgroundColor(topbarbg)
            } else {
                mybar.setBackgroundResource(topdrawable)
            }


            val life = a.getResourceId(R.styleable.TitleCustomBar_customIvLifeRes, 0)
            if (life != 0) {
                iv_life.setImageResource(life)
            }

            val right = a.getResourceId(R.styleable.TitleCustomBar_customIvRightRes, 0)
            if (right != 0) {
                iv_right.setImageResource(right)
            }

            val lifeComstring = a.getString(R.styleable.TitleCustomBar_customLifeText)
            val life_Size = a.getDimension(R.styleable.TitleCustomBar_customLifeSize, 14F)
            val life_Color = a.getColor(R.styleable.TitleCustomBar_customLifeColor, Color.BLACK)
            tv_life.text = lifeComstring
//            tv_life.textSize = life_Size
            tv_life.setTextSize(TypedValue.COMPLEX_UNIT_PX, life_Size)
            tv_life.setTextColor(life_Color)

            val rightComstring = a.getString(R.styleable.TitleCustomBar_customRightText)
            val right_Size = a.getDimension(R.styleable.TitleCustomBar_customRightSize, 14F)
            val right_Color = a.getColor(R.styleable.TitleCustomBar_customRightColor, Color.BLACK)
            tv_right.text = rightComstring
//            tv_right.textSize = right_Size
            tv_right.setTextSize(TypedValue.COMPLEX_UNIT_PX,right_Size)
            tv_right.setTextColor(right_Color)

            val contentComstring = a.getString(R.styleable.TitleCustomBar_customContentText)
            val content_Size = a.getDimension(R.styleable.TitleCustomBar_customContentSize, 16F)
            val content_Color =
                a.getColor(R.styleable.TitleCustomBar_customContentColor, Color.BLACK)
            val typeface = a.getInt(R.styleable.TitleCustomBar_customtextStyle, 0)
            val gravity = a.getInt(R.styleable.TitleCustomBar_customContentGravity, -1)
            tv_Content.text = contentComstring
//            tv_Content.textSize = content_Size
            tv_Content.setTextSize(TypedValue.COMPLEX_UNIT_PX,content_Size)
            tv_Content.setTextColor(content_Color)
            tv_Content.setTypeface(Typeface.defaultFromStyle(typeface))
            when (gravity) {
                1 -> {
                    tv_Content.gravity = Gravity.CENTER
                }
                2 -> {
                    tv_Content.gravity = Gravity.START
                }
                3 -> {
                    tv_Content.gravity = Gravity.END
                }
                else -> {
                }
            }
            val showLife = a.getBoolean(R.styleable.TitleCustomBar_showLife, true)
            val showRight = a.getBoolean(R.styleable.TitleCustomBar_showRight, true)

            val lifeshow = a.getBoolean(R.styleable.TitleCustomBar_showTvOrIvLife, false)
            if (showLife){
                tv_life.visibility = if (lifeshow) View.VISIBLE else View.GONE
                iv_life.visibility = if (!lifeshow) View.VISIBLE else View.GONE
            }else {
                tv_life.visibility = View.GONE
                iv_life.visibility = View.GONE
            }
            val show = a.getBoolean(R.styleable.TitleCustomBar_showTvOrIvRight, false)
            if (showRight){
                tv_right.visibility = if (show) View.VISIBLE else View.GONE
                iv_right.visibility = if (!show) View.VISIBLE else View.GONE
            }else{
                tv_right.visibility = View.GONE
                iv_right.visibility =  View.GONE
            }
            a.recycle()
            iv_right.setOnClickListener {
                if (::ivRightlistener.isInitialized) {
                    ivRightlistener.invoke()
                }

            }
            tv_right.setOnClickListener {
                if (::tvRightlistener.isInitialized) {
                    tvRightlistener.invoke()
                }

            }
            iv_life.setOnClickListener {
                if (::ivlifelistener.isInitialized) {
                    ivlifelistener.invoke()
                }

            }
            tv_life.setOnClickListener {
                if (::tvlifelistener.isInitialized) {
                    tvlifelistener.invoke()
                }
            }
        }
    }



}