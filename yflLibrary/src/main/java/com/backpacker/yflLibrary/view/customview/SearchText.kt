package com.backpacker.yflLibrary.view.customview

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.UtilsLibrary.R

/**
 * @Author : YFL  is Creating a porject in My Application
 * @Package com.liang.myapplication.view
 * @Email : yufeilong92@163.com
 * @Time :2020/6/4 11:05
 * @Purpose :搜索条件
 */
class SearchText : ConstraintLayout {

    lateinit var onEditonActionlistenre: (com: String?) -> Unit

    constructor(context: Context, attributes: AttributeSet) : super(context, attributes) {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_searchtext, this)
        val ctl_search_bg = view.findViewById<ConstraintLayout>(R.id.ctl_search_bg)
        val et_search = view.findViewById<EditText>(R.id.et_search)
        val iv_search_clear = view.findViewById<ImageView>(R.id.iv_search_clear)
        attributes?.let {
            val a = context.obtainStyledAttributes(attributes, R.styleable.SearchText)
            val resourceId = a.getResourceId(R.styleable.SearchText_drawableRes, 0)
            if (resourceId != 0) {
                ctl_search_bg.setBackgroundResource(resourceId)
            }
            val color = a.getColor(R.styleable.SearchText_textSearchColor, Color.BLACK)
            et_search.setTextColor(color)

            val resourceId1 = a.getResourceId(R.styleable.SearchText_clearImgRes, 0)
            iv_search_clear.setImageResource(resourceId1)

            val string = a.getString(R.styleable.SearchText_hineSearchText)

            if (!TextUtils.isEmpty(string)) {
                et_search.hint = string
            }

            a.recycle()

        }
        et_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                showImger(!TextUtils.isEmpty(et_search.text), iv_search_clear);
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        iv_search_clear.setOnClickListener {
            et_search.text = null
            showImger(false, iv_search_clear)
        }
        et_search.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val toString = et_search.text.toString()
                if (::onEditonActionlistenre.isInitialized) {
                    onEditonActionlistenre.invoke(toString)
                }
                true
            } else
                false
        }

    }

    private fun showImger(show: Boolean, img: ImageView) {
        img.visibility = if (show) View.VISIBLE else View.GONE
    }
}
