package com.backpacker.yflLibrary.view.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.text.TextUtils
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.UtilsLibrary.R

/**
 * @Author : YFL  is Creating a porject in My Application
 * @Package com.liang.myapplication
 * @Email : yufeilong92@163.com
 * @Time :2020/6/9 15:15
 * @Purpose : 自定义可控制速度的跑马灯
 */
class CustomMarqueeView : AppCompatTextView {
    companion object {
        val SPEED_FAST = 9
        val SPEED_MEDIUM = 6
        val SPEED_SLOW = 3
    }

    //View宽度
    private var mViewWidth = 0
    private var mViewHeight = 0
    private var mScrollX = 0F
    private var mMarqueeMode = 3
    private val rect = Rect()

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        includeFontPadding = false
        initAttrs(context, attrs)
    }

    fun setScrollSpeed(speed: Int) {
        if (speed == SPEED_FAST || speed == SPEED_MEDIUM || speed == SPEED_SLOW) {
            mMarqueeMode = speed
        }
    }


    override fun onDraw(canvas: Canvas?) {
        val textContentText = text.toString().trim()
        if (TextUtils.isEmpty(textContentText)) {
            return
        }
        val x = mViewWidth - mScrollX
        val y = mViewHeight / 2F + getTextContentHeight() / 2
        canvas?.drawText(textContentText, x, y, paint)
        mScrollX += mMarqueeMode
        if (mScrollX >= (mViewWidth + getTextContentWdith())) {
            mScrollX = 0F
        }
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mViewWidth = MeasureSpec.getSize(widthMeasureSpec)
        mViewHeight = MeasureSpec.getSize(heightMeasureSpec)
    }

    override fun setTextColor(color: Int) {
        super.setTextColor(color)
        paint.setColor(color)
    }

    private fun initAttrs(context: Context, attrs: AttributeSet?) {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.CustomMarqueeView)
        mMarqueeMode =
            typeArray.getInt(R.styleable.CustomMarqueeView_customScrollSpeed, mMarqueeMode)
        typeArray.recycle()
    }

    /**
     * 测量文字宽度
     * @return 文字宽度
     */
    private fun getTextContentWdith(): Int {
        val textContent = text.toString().trim()
        if (!TextUtils.isEmpty(textContent)) {
            paint.getTextBounds(textContent, 0, textContent.length, rect)
            return rect.width()
        }
        return 0
    }

    /**
     * 测量文字高度
     * @return 文字高度
     */
    private fun getTextContentHeight(): Int {
        val textContent = text.toString().trim()
        if (!TextUtils.isEmpty(textContent)) {
            paint.getTextBounds(textContent, 0, textContent.length, rect)
            return rect.height()
        }
        return 0
    }
}