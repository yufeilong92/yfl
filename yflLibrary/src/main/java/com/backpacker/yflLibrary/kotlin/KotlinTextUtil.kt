package com.backpacker.yflLibrary.kotlin

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes

/**
 * @Author : YFL  is Creating a porject in basehttps
 * @Package com.backpacker.yflLibrary.kotlin
 * @Email : yufeilong92@163.com
 * @Time :2019/11/23 17:25
 * @Purpose :文字效果工具类
 */
class KotlinTextUtil {
    /**
     * 设置前景色
     * @param color: 前景色值
     */
    @JvmOverloads
    fun TextView.setFgColorSpan(
        string: String,
        @ColorInt color: Int,
        start: Int,
        end: Int,
        flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
    ) = with(SpannableString(string)) {
        setSpan(ForegroundColorSpan(color), start, end, flag)
        this@setFgColorSpan.text = this
        this@setFgColorSpan
    }

    /**
     * 设置背景色
     * @param color: 背景色值
     */
    @JvmOverloads
    fun TextView.setBgColorSpan(
        string: String,
        @ColorInt color: Int,
        start: Int,
        end: Int,
        flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
    ) = with(SpannableString(string)) {
        setSpan(BackgroundColorSpan(color), start, end, flag)
        this@setBgColorSpan.text = this
        this@setBgColorSpan
    }

    /**
     * 设置删除线
     */
    @JvmOverloads
    fun TextView.setStrikethroughSpan(
        content: CharSequence,
        start: Int,
        end: Int,
        flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
    ) = with(SpannableString(content)) {
        setSpan(StrikethroughSpan(), start, end, flag)
        this@setStrikethroughSpan.text = this
        this@setStrikethroughSpan
    }

    /**
     * 设置下划线
     */
    @JvmOverloads
    fun TextView.setUnderlineSpan(
        string: String,
        start: Int,
        end: Int,
        flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
    ) = with(SpannableString(string)) {
        setSpan(UnderlineSpan(), start, end, flag)
        this@setUnderlineSpan.text = this
        this@setUnderlineSpan
    }


    /**
     * 设置可点击文本
     * @param isUnderlineText: 是否显示下划线
     * @param textColor: 文字颜色
     * @param bgColor: 背景颜色
     * @param highlightColor: 点击后的文字背景色
     * @param listener: 点击回调，参数为点击的文本内容和View
     */
    @JvmOverloads
    fun TextView.setClickableSpan(
        content: String,
        start: Int,
        end: Int,
        isUnderlineText: Boolean = false,
        flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE,
        @ColorInt textColor: Int = -1,
        @ColorInt bgColor: Int = Color.TRANSPARENT,
        @ColorInt highlightColor: Int = Color.TRANSPARENT,
        listener: (clickString: String, widget: View) -> Unit

    ) = this.apply {
        movementMethod = LinkMovementMethod.getInstance()
        text = with(SpannableString(content)) {
            setSpan(object : ClickableSpan() {
                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    if (textColor != -1) {
                        ds.color = textColor
                    }
                    ds.bgColor = bgColor
                    ds.isUnderlineText = isUnderlineText
                    this@setClickableSpan.highlightColor = highlightColor
                }

                override fun onClick(widget: View) {
                    listener.invoke(content.substring(start, end), widget)
                }
            }, start, end, flag)
            this
        }
    }

    /**
     * 设置文字样式
     * @param style:文字样式
     */
    fun TextView.setStyleSpan(
        content: String,
        start: Int,
        end: Int,
        style: Int,
        flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
    ) = this.apply {
        text = with(SpannableString(content)) {
            setSpan(StyleSpan(style), start, end, flag)
            this
        }
    }

    /**
     * 设置粗体字
     */
    fun TextView.setBoldSpan(
        content: String,
        start: Int,
        end: Int,
        flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
    ) = setStyleSpan(content, start, end, Typeface.BOLD, flag)

    /**
     * 设置斜体字
     */
    fun TextView.setItalicSpan(
        content: String,
        start: Int,
        end: Int,
        flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
    ) = setStyleSpan(content, start, end, Typeface.ITALIC, flag)


    /**
     * 设置粗体和斜体字
     */
    fun TextView.setBoldItalicSpan(
        content: String,
        start: Int,
        end: Int,
        flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
    ) = setStyleSpan(content, start, end, Typeface.BOLD_ITALIC, flag)

    /**
     * 设置超链接
     * @param url:超链接地址
     * @param isUnderlineText: 是否显示下划线
     * @param textColor: 文字颜色
     * @param bgColor: 背景颜色
     * @param highlightColor: 点击后的文字背景色
     */
    fun TextView.setUrlSpan(
        content: String,
        url: String,
        start: Int,
        end: Int,
        isUnderlineText: Boolean = false,
        flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE,
        @ColorInt textColor: Int = -1,
        @ColorInt bgColor: Int = Color.TRANSPARENT,
        @ColorInt highlightColor: Int = Color.TRANSPARENT
    ) = this.apply {
        movementMethod = LinkMovementMethod.getInstance()
        text = with(SpannableString(content)) {
            setSpan(object : URLSpan(url) {
                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    if (textColor != -1) {
                        ds.color = textColor
                    }
                    ds.bgColor = bgColor
                    ds.isUnderlineText = isUnderlineText
                    this@setUrlSpan.highlightColor = highlightColor
                }
            }, start, end, flag)
            this
        }
    }

    /**
     * 设置文字相对大小
     * @param proportion:放缩比例
     */
    fun TextView.setRelativeSizeSpan(
        content: String,
        start: Int,
        end: Int,
        proportion: Float,
        flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
    ) = this.apply {
        text = with(SpannableString(content)) {
            setSpan(RelativeSizeSpan(proportion), start, end, flag)
            this
        }
    }

    /**
     * 设置文字上标
     */
    fun TextView.setSuperScriptSpan(
        content: String,
        start: Int,
        end: Int,
        flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
    ) = this.apply {
        text = with(SpannableString(content)) {
            setSpan(SuperscriptSpan(), start, end, flag)
            this
        }
    }

    /**
     * 设置文字下标
     */
    fun TextView.setSubscriptSpan(
        content: String,
        start: Int,
        end: Int,
        flag: Int = Spanned.SPAN_INCLUSIVE_INCLUSIVE
    ) = this.apply {
        text = with(SpannableString(content)) {
            setSpan(SubscriptSpan(), start, end, flag)
            this
        }
    }


    /**
     * 创建一个SpannableStringBuilder实例，用于链式调用API
     */
    @JvmOverloads
    fun TextView.buildSpan(text: CharSequence, init: (SpanHelper.Builder.() -> Unit)? = null) =
        SpanHelper.build(this, text, init)

    class SpanHelper {

        companion object {
            fun build(textView: TextView, text: CharSequence, init: (Builder.() -> Unit)? = null) =
                Builder(textView, text, init)
        }

        /**
         * SpannableString构造类
         */
        class Builder private constructor() {

            private lateinit var spBuilder: SpannableStringBuilder

            /**
             * 需要设置文字特效的TextView
             */
            private lateinit var textView: TextView

            /**
             * 文字特效开始的位置下标
             */
            private var startIndex = 0

            /**
             * 文字特效结束的位置下标
             */
            private var endIndex = 0

            /**
             * 设置标识，一共四种
             * Spanned#SPAN_INCLUSIVE_EXCLUSIVE
             * Spanned#SPAN_INCLUSIVE_INCLUSIVE
             * Spanned#SPAN_EXCLUSIVE_EXCLUSIVE
             * Spanned#SPAN_EXCLUSIVE_INCLUSIVE
             */
            private var flag = Spanned.SPAN_INCLUSIVE_INCLUSIVE

            constructor(textView: TextView, text: CharSequence, init: (Builder.() -> Unit)?) : this() {
                this.textView = textView
                spBuilder = SpannableStringBuilder(text)
                init?.let { it() }
            }

            /**
             * 设置文字特效开始的位置下标
             */
            fun setStart(start: Int) = apply { startIndex = start }

            /**
             * 设置文字特效结束的位置下标
             */
            fun setEnd(end: Int) = apply { endIndex = end }

            /**
             * 同时设置文字特效开始和结束的位置
             */
            fun setStartEnd(start: Int, end: Int) = apply {
                setStart(start)
                setEnd(end)
            }

            /**
             * 设置标识
             */
            fun setFlag(flag: Int) = apply { this.flag = flag }

            /**
             * 设置前景色
             * @param color: 前景色值
             */
            @JvmOverloads
            fun setFgColor(@ColorInt color: Int, start: Int = startIndex, end: Int = endIndex, flag: Int = this.flag) =
                apply { spBuilder.setSpan(ForegroundColorSpan(color), start, end, flag) }

            /**
             * 设置背景色
             * @param color: 背景色值
             */
            @JvmOverloads
            fun setBgColor(@ColorInt color: Int, start: Int = startIndex, end: Int = endIndex, flag: Int = this.flag) =
                apply { spBuilder.setSpan(BackgroundColorSpan(color), start, end, flag) }

            /**
             * 设置删除线
             */
            @JvmOverloads
            fun setStrikethrough(start: Int = startIndex, end: Int = endIndex, flag: Int = this.flag) =
                apply { spBuilder.setSpan(StrikethroughSpan(), start, end, flag) }

            /**
             * 设置下划线
             */
            @JvmOverloads
            fun setUnderline(start: Int = startIndex, end: Int = endIndex, flag: Int = this.flag) =
                apply { spBuilder.setSpan(UnderlineSpan(), start, end, flag) }

            /**
             * 设置可点击文本
             * @param isUnderlineText: 是否显示下划线
             * @param textColor: 文字颜色
             * @param bgColor: 背景颜色
             * @param highlightColor: 点击后的文字背景色
             * @param listener: 点击回调，参数为点击的文本内容和View
             */
            @JvmOverloads
            fun setClickable(
                start: Int = startIndex,
                end: Int = endIndex,
                isUnderlineText: Boolean = false,
                flag: Int = this.flag,
                @ColorInt textColor: Int = -1,
                @ColorInt bgColor: Int = Color.TRANSPARENT,
                @ColorInt highlightColor: Int = Color.TRANSPARENT,
                listener: (clickString: String, widget: View) -> Unit
            ) = apply {
                textView.movementMethod = LinkMovementMethod.getInstance()
                spBuilder.setSpan(object : ClickableSpan() {
                    override fun updateDrawState(ds: TextPaint) {
                        super.updateDrawState(ds)
                        if (textColor != -1) {
                            ds.color = textColor
                        }
                        ds.bgColor = bgColor
                        ds.isUnderlineText = isUnderlineText
                        textView.highlightColor = highlightColor
                    }

                    override fun onClick(widget: View) {

                        listener.invoke(spBuilder.substring(start, end), widget)
                    }

                }, start, end, flag)
            }

            /**
             * 设置超链接
             * @param url:超链接地址
             * @param isUnderlineText: 是否显示下划线
             * @param textColor: 文字颜色
             * @param bgColor: 背景颜色
             * @param highlightColor: 点击后的文字背景色
             */
            fun setUrl(
                url: String,
                start: Int = startIndex,
                end: Int = endIndex,
                isUnderlineText: Boolean = false,
                @ColorInt textColor: Int = -1,
                @ColorInt bgColor: Int = Color.TRANSPARENT,
                @ColorInt highlightColor: Int = Color.TRANSPARENT,
                flag: Int = this.flag
            ) = apply {
                textView.movementMethod = LinkMovementMethod.getInstance()
                spBuilder.setSpan(object : URLSpan(url) {
                    override fun updateDrawState(ds: TextPaint) {
                        super.updateDrawState(ds)
                        if (textColor != -1) {
                            ds.color = textColor
                        }
                        ds.bgColor = bgColor
                        ds.isUnderlineText = isUnderlineText
                        textView.highlightColor = highlightColor
                    }
                }, start, end, flag)
            }

            /**
             * 设置文字样式
             * @param style:文字样式
             */
            @JvmOverloads
            fun setStyle(style: Int, start: Int = startIndex, end: Int = endIndex, flag: Int = this.flag) =
                apply { spBuilder.setSpan(StyleSpan(style), start, end, flag) }

            /**
             * 设置文字为粗体
             */
            @JvmOverloads
            fun setBold(start: Int = startIndex, end: Int = endIndex, flag: Int = this.flag) =
                apply { setStyle(Typeface.BOLD, start, end, flag) }

            /**
             * 设置文字为斜体
             */
            @JvmOverloads
            fun setItalic(start: Int = startIndex, end: Int = endIndex, flag: Int = this.flag) =
                apply { setStyle(Typeface.ITALIC, start, end, flag) }

            /**
             * 设置文字为粗体和斜体
             */
            @JvmOverloads
            fun setBoldItalic(start: Int = startIndex, end: Int = endIndex, flag: Int = this.flag) =
                apply { setStyle(Typeface.BOLD_ITALIC, start, end, flag) }

            /**
             * 设置文字相对大小
             * @param proportion:放缩比例
             */
            fun setRelativeSize(proportion: Float, start: Int = startIndex, end: Int = endIndex, flag: Int = this.flag) =
                apply { spBuilder.setSpan(RelativeSizeSpan(proportion), start, end, flag) }

            /**
             * 设置文字上标
             */
            fun setSuperScript(start: Int = startIndex, end: Int = endIndex, flag: Int = this.flag) =
                apply { spBuilder.setSpan(SuperscriptSpan(), start, end, flag) }

            /**
             * 设置文字下标
             */
            fun setSubscript(start: Int = startIndex, end: Int = endIndex, flag: Int = this.flag) =
                apply { spBuilder.setSpan(SubscriptSpan(), start, end, flag) }

            /**
             * 设置特效完毕
             */
            fun create() {
                textView.text = spBuilder
            }
        }
    }
    /**
     * 根据布局id填充一个布局
     */
    @JvmOverloads
    fun inflate(@LayoutRes layoutId: Int, root: ViewGroup? = null,mContext: Context) = View.inflate(mContext, layoutId, root)

    /**
     * 当前View是否可见
     */
    val View.isVisible
        get() = visibility == View.VISIBLE

    /**
     * 当前View是否不可见
     */
    val View.isInvisible
        get() = visibility == View.INVISIBLE

    /**
     * 当前View是否隐藏
     */
    val View.isGone
        get() = visibility == View.GONE

    /**
     * 将View设置为隐藏
     */
    fun View.setGone() {
        if (visibility != View.GONE) {
            visibility = View.GONE
        }
    }

    /**
     * 将View设置为可见
     */
    fun View.setVisible() {
        if (visibility != View.VISIBLE) {
            visibility = View.VISIBLE
        }
    }

    /**
     * 将View设置为不可见
     */
    fun View.setInvisible() {
        if (visibility != View.INVISIBLE) {
            visibility = View.INVISIBLE
        }
    }

    /**
     * 设置View的宽度
     * @param width: 宽度值，单位为px
     */
    fun View.setWidth(width: Int) {
        layoutParams = layoutParams.apply {
            this.width = width
        }
    }

    /**
     * 设置View的高度
     * @param height: 高度值，单位为px
     */
    fun View.setHeight(height: Int) {
        layoutParams = layoutParams.apply {
            this.height = height
        }
    }

    /**
     * 设置View的宽度和高度
     * @param width: 宽度值，单位为px
     * @param height: 高度值，单位为px
     */
    fun View.setWidthAndHeight(width: Int, height: Int) {
        layoutParams = layoutParams.apply {
            this.width = width
            this.height = height
        }
    }

    /**
     * 将View转换为Bitmap
     * @param  scale: 生成的Bitmap相对于原View的大小比例，范围为0~1.0
     */
    fun View.toBitmap(scale: Float = 1.0F): Bitmap? = viewToBitmap(this, scale)


    /**
     * 设置View的padding
     */
    fun View.setNewPadding(
        left: Int = paddingLeft,
        top: Int = paddingTop,
        right: Int = paddingRight,
        bottom: Int = paddingBottom
    ) {
        setPadding(left, top, right, bottom)
    }

    /**
     * 测量View
     */
    private fun View.measureView() {
        var params = layoutParams
        if (params == null) {
            params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
        val widthSpec = ViewGroup.getChildMeasureSpec(0, 0, params.width)
        val heightSpec = if (params.height > 0) {
            View.MeasureSpec.makeMeasureSpec(params.height, View.MeasureSpec.EXACTLY)
        } else {
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        }
        measure(widthSpec, heightSpec)
    }

    /**
     * 获取View的高度
     * 如果是“math_parent”属性则无法获取，值为0。
     */
    val View.viewHeight: Int
        get() {
            measureView()
            return measuredHeight
        }

    /**
     * 获取View的宽度
     * 如果是“math_parent”属性则无法获取，值为0。
     */
    val View.viewWidth: Int
        get() {
            measureView()
            return measuredWidth
        }


    /**
     * 获取TextView的String内容
     */
    val TextView.textString: String
        get() = text.toString()

    /**
     * 获取TextView的String内容长度
     */
    val TextView.textLength: Int
        get() = text.length

    /**
     * 判断TextView的内容是否为空
     */
    val TextView.isTextEmpty: Boolean
        get() = text.isEmpty()

    /**
     * 判断TextView的内容是否为null或空
     */
    val TextView.isTextNullOrEmpty: Boolean
        get() = text.isNullOrEmpty()

    /**
     * 判断TextView的内容是否为非空
     */
    val TextView.isTextNotEmpty: Boolean
        get() = !isTextEmpty

    /**
     * 判断TextView的内容是否为空白
     */
    val TextView.isTextBlank: Boolean
        get() = text.isBlank()

    /**
     * 判断TextView的内容是否为null或空白
     */
    val TextView.isTextNullOrBlank: Boolean
        get() = text.isNullOrBlank()

    /**
     * 判断TextView的内容是否为非空白
     */
    val TextView.isTextNotBlank: Boolean
        get() = text.isNotBlank()

    /**
     * 安全创建Bitmap，如果产生了OOM，可以主动GC后再尝试。
     * https://github.com/QMUI/QMUI_Android
     * @param width : Bitmap宽度
     * @param height : Bitmap高度度
     * @param retryCount : 重试次数，默认一次
     * @param config : Bitmap.Config，默认为ARGB_8888
     * @return 创建Bitmap成功返回Bitmap，否则返回null。
     */
    fun createBitmapSafely(
        width: Int,
        height: Int,
        retryCount: Int = 1,
        config: Bitmap.Config = Bitmap.Config.ARGB_8888
    ): Bitmap? = try {
        Bitmap.createBitmap(width, height, config)
    } catch (e: OutOfMemoryError) {
        e.printStackTrace()
        when (retryCount > 0) {
            true -> {
                System.gc()
                createBitmapSafely(width, height, retryCount, config)
            }
            false -> null
        }
    }

    /**
     * 将View转换为Bitmap
     * @param  scale: 生成的Bitmap相对于原View的大小比例，范围为0~1.0
     */
    fun viewToBitmap(view: View, scale: Float = 1.0F): Bitmap? = when (view) {
        is ImageView -> {
            val drawable: Drawable = view.drawable
            (drawable as BitmapDrawable).bitmap
        }
        else -> {
            view.clearFocus()
            val bitmap =
                createBitmapSafely((view.width * scale).toInt(), (view.height * scale).toInt())
            if (bitmap != null) {
                val canvas = Canvas()
                synchronized(canvas) {
                    with(canvas) {
                        setBitmap(bitmap)
                        save()
                        drawColor(Color.WHITE)
                        scale(scale, scale)
                        view.draw(canvas)
                        restore()
                        setBitmap(null)
                    }
                }
            }
            bitmap
        }
    }


}