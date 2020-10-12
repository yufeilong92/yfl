package com.backpacker.yflLibrary.view.dialog


import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.Nullable
import androidx.recyclerview.widget.GridLayoutManager
import com.backpacker.yflLibrary.adapter.MultipleOptionAdapter
import com.example.UtilsLibrary.R
import kotlinx.android.synthetic.main.dialog_multiple_options.*

/**
 * @Author : YFL  is Creating a porject in Dell
 * @Package : cn.ruiye.worker.view.dialog
 * @Email : yufeilong92@163.com
 * @Time :2020/9/30 17:42
 * @Purpose :通用多选对话框
 */

/***
 * @param mContext  上下文
 * @param mData  通用工具类
 * @param mSelectType  选择类型 Single 单选 multiple 多选
 * @param mIsGravityButtom  视图位置 是否在底部 false 居中
 * @param mIsFilter 是否筛选多选，false 原数据 true返回选中数据
 */
class MultipleOptionsBuildeDialog(
    var mContext: Context
) : AlertDialog(mContext, R.style.my_dialog) {
    private lateinit var mData: MutableList<SelectRlv>
    private lateinit var mSelectType: SelectType
    private var mIsGravityButtom: Boolean = false
    private var mIsFilter: Boolean = false
    private var mIsShow: Boolean = true
    private var mSelectIcon: Int = 0
    private var mSelectColor: Int = 0
    private var mNoSelectIcon: Int = 0
    private var mNoSelectColor: Int = 0
    private var mTvGravity: Int = -1

    private lateinit var onMultipeDataListener: (data: MutableList<SelectRlv>?) -> Unit
    private lateinit var onSingleDataListener: (data: SelectRlv?) -> Unit

    private var metrics: DisplayMetrics = context.resources.displayMetrics

    init {
        window!!.setWindowAnimations(R.style.popup_animation)
    }

    companion object {
        fun builde(mContext: Context): MultipleBuidle {
            return MultipleBuidle(mContext)

        }
    }

    class MultipleBuidle(cm: Context) {
        var mMultipleOptionsDialog = MultipleOptionsBuildeDialog(mContext = cm)

        /***
         * @param data  通用数据
         * @return
         */
        fun setData(@Nullable data: MutableList<SelectRlv>): MultipleBuidle {
            mMultipleOptionsDialog.mData = data
            return this
        }

        /***
         * @param select 选择类型  SINGLE（单选）, MULTIPLE（多选）
         * @return
         */
        fun setSelectType(select: SelectType): MultipleBuidle {
            mMultipleOptionsDialog.mSelectType = select
            return this
        }

        /***
         * @param isGravity 对话框是否居中
         * @return
         */
        fun setGravityButtom(isGravity: Boolean): MultipleBuidle {
            mMultipleOptionsDialog.mIsGravityButtom = isGravity
            return this
        }

        /***
         * @param isFilter 是否过滤选中数据
         * @return
         */
        fun setIsFilter(isFilter: Boolean): MultipleBuidle {
            mMultipleOptionsDialog.mIsFilter = isFilter
            return this
        }

        /***
         * @param show 是否显示选中图标
         * @return
         */
        fun setShowIcon(show: Boolean): MultipleBuidle {
            mMultipleOptionsDialog.mIsShow = show
            return this
        }

        /***
         * @param select 选中图标
         * @param noSelect 未选中图标
         * @return
         */
        fun setSelectIcon(@DrawableRes select: Int, @DrawableRes noSelect: Int): MultipleBuidle {
            mMultipleOptionsDialog.mSelectIcon = select
            mMultipleOptionsDialog.mNoSelectIcon = noSelect
            return this
        }

        /***
         * @param gravity item 字体位置
         * @return
         */
        fun setTvGravity(gravity: Int): MultipleBuidle {
            mMultipleOptionsDialog.mTvGravity = gravity
            return this
        }

        /***
         * @param select 选中颜色
         * @param noSelect 未选中颜色
         * @return
         */
        fun setSelectColor(@ColorInt select: Int, @ColorInt noSelect: Int): MultipleBuidle {
            mMultipleOptionsDialog.mSelectColor = select
            mMultipleOptionsDialog.mNoSelectColor = noSelect
            return this
        }

        /***
         * @param data 单选结果回调
         * @return
         */
        fun setSingleDataListener(data: (data: SelectRlv?) -> Unit): MultipleBuidle {
            mMultipleOptionsDialog.onSingleDataListener = data
            return this
        }

        /***
         * @param data 多选结果回调
         * @return
         */
        fun setMultipleDataListener(data: (data: MutableList<SelectRlv>?) -> Unit): MultipleBuidle {
            mMultipleOptionsDialog.onMultipeDataListener = data
            return this
        }

        /***
         * 显示
         * @return
         */
        fun show() {
            mMultipleOptionsDialog.show()
        }

    }


    //适配器
    private var mAdapter: MultipleOptionAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_multiple_options)
        setSizeMode()
        initView()
        initListener()
    }


    private fun initListener() {
        tv_dialog_multiple_cancle.setOnClickListener {
            dismiss()
        }
        btn_dialog_multiple_life.setOnClickListener {
            dismiss()
        }
        tv_dialog_multiple_sure.setOnClickListener {
            sureItem()
        }
        btn_dialog_multiple_right.setOnClickListener {
            sureItem()
        }
    }

    private fun sureItem() {
        when (mSelectType) {
            SelectType.MULTIPLE -> {
                if (mData.isNullOrEmpty()) {
                    if (::onMultipeDataListener.isInitialized) {
                        onMultipeDataListener.invoke(null)
                    }
                    return
                }
                if (mIsFilter) {
                    val filter = mData?.filter { it.check } as MutableList
                    if (::onMultipeDataListener.isInitialized) {
                        onMultipeDataListener.invoke(filter)
                    }
                    return
                }
                if (::onMultipeDataListener.isInitialized) {
                    onMultipeDataListener.invoke(mData)
                }
            }
            else -> {
                if (mData.isNullOrEmpty()) {
                    if (::onSingleDataListener.isInitialized) {
                        onSingleDataListener.invoke(null)
                    }
                    return
                }
                val list = mData.filter { it.check }
                if (list.isNullOrEmpty()) {
                    if (::onSingleDataListener.isInitialized) {
                        onSingleDataListener.invoke(null)
                    }
                    return
                }
                if (::onSingleDataListener.isInitialized) {
                    onSingleDataListener.invoke(list[0])
                }

            }
        }
    }


    private fun initView() {
        mAdapter = MultipleOptionAdapter(mContext, mData)
        val g = GridLayoutManager(mContext, 1)
        rlv_dialog_multiple_content.layoutManager = g
        rlv_dialog_multiple_content.adapter = mAdapter
        if (mSelectColor != 0 && mNoSelectColor != 0)
            mAdapter?.setSelectColor(mNoSelectColor, mSelectColor)
        if (mNoSelectIcon != 0 && mSelectIcon != 0)
            mAdapter?.setSelectIcom(mNoSelectIcon, mSelectIcon)
        mAdapter?.setShowIcon(mIsShow)
        if (mTvGravity != -1)
            mAdapter?.setTextGravity(mTvGravity)
        mAdapter?.setRecyclerListener(object : MultipleOptionAdapter.RecyclerItemListener {
            override fun itemClickListener(position: Int) {
                when (mSelectType) {
                    SelectType.SINGLE -> {
                        clearSelect()
                        mData[position].check = !mData[position].check
                        mAdapter?.notifyDataSetChanged()
                    }
                    SelectType.MULTIPLE -> {
                        mData[position].check = !mData[position].check
                        mAdapter?.notifyItemChanged(position)
                    }
                    else -> {
                        clearSelect()
                        mData[position].check = !mData[position].check
                        mAdapter?.notifyDataSetChanged()
                    }
                }
            }
        })

        if (!mData.isNullOrEmpty()) {
            var first = 0
            for ((index, child) in mData.withIndex()) {
                if (child.check) {
                    first = index
                    break
                }
            }
            if (first != 0) {
                Handler().postDelayed({
                    rlv_dialog_multiple_content.scrollToPosition(first)
                }, 300)
            }

        }
    }


    private fun clearSelect() {
        if (mData.isNullOrEmpty()) {
            return
        }
        mData.let {
            for (child in it) {
                child.check = false
            }
        }
    }


    private fun setSizeMode() {
        val params = window!!.attributes
        params.width = metrics.widthPixels
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        window!!.attributes = params
        val lp = rootview.layoutParams as FrameLayout.LayoutParams
        if (mIsGravityButtom) {
            lp.setMargins(0, 0, 0, 0)
            rootview.layoutParams = lp
            window!!.setGravity(Gravity.BOTTOM)
            showTopButtom(true, false, true)
        } else {
            lp.setMargins(30, 0, 30, 0)
            rootview.layoutParams = lp
            window?.setGravity(Gravity.CENTER)
            showTopButtom(false, true, false)
        }
        val windowManager = mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val defaultDisplay = windowManager.defaultDisplay
        val height = defaultDisplay.height
        val layoutParams = rlv_dialog_multiple_content.layoutParams
        layoutParams.height = (height * 0.4).toInt()
        rlv_dialog_multiple_content.layoutParams= layoutParams
    }

    private fun showTopButtom(showTop: Boolean, buttom: Boolean, line: Boolean) {
        tv_dialog_multiple_cancle.visibility = if (showTop) View.VISIBLE else View.GONE
        tv_dialog_multiple_sure.visibility = if (showTop) View.VISIBLE else View.GONE
        btn_dialog_multiple_life.visibility = if (buttom) View.VISIBLE else View.GONE
        btn_dialog_multiple_right.visibility = if (buttom) View.VISIBLE else View.GONE
        view_line_one.visibility = if (line) View.VISIBLE else View.GONE

    }


    enum class SelectType {
        SINGLE, MULTIPLE
    }

    data class SelectRlv (
        var name: String = "",
        var id: String = "",
        var check: Boolean
    ) {
        override fun toString(): String {
            return "SelectRlv(name='$name', id='$id', check=$check)"
        }
    }

}