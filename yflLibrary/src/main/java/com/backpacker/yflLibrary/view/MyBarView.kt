package com.backpacker.yflLibrary.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import com.backpacker.yflLibrary.kotlin.Constant
import com.example.UtilsLibrary.R
import java.lang.ref.Reference


class MyBarView : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        var tda: TypedArray = context!!.obtainStyledAttributes(attrs, R.styleable.MyBarStyle)

        val barbg = tda.getColor(R.styleable.MyBarStyle_barBg, Color.TRANSPARENT)
        val drawable = tda.getResourceId(R.styleable.MyBarStyle_bgDrawable, 0)
        if (drawable == 0) {
            setBackgroundColor(barbg)
        } else {
            setBackgroundResource(drawable)
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(
            Constant.screenWidth,
            Constant.statusHeight
        )
    }

}