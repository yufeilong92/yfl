package com.backpacker.yflLibrary.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.backpacker.yflLibrary.view.dialog.MultipleOptionsBuildeDialog
import com.example.UtilsLibrary.R

/**
 * @Author : YFL  is Creating a porject in workerApp_Android
 * @Package cn.ruiye.worker.adapter.dialog
 * @Email : yufeilong92@163.com
 * @Time :2020/9/30 17:52
 * @Purpose :多选对话框适配器
 */
class MultipleOptionAdapter(var mContext: Context, var mData: MutableList<MultipleOptionsBuildeDialog.SelectRlv>) :
    RecyclerView.Adapter<MultipleOptionAdapter.ViewHodle>() {
    //选择图片
    private var mSelectIcon: Int = R.mipmap.ic_gm_select_s

    //没有选中图片
    private var mNoSelectIcon: Int = R.mipmap.ic_gm_select_n

    //是否显示图片
    private var mShowIcon: Boolean = true

    private var mSelectColor: Int = Color.RED

    private var mNoSelectColor: Int = Color.BLACK

    private var mTextGravity: Int = -1

    interface RecyclerItemListener {
        fun itemClickListener(position: Int)
    }

    private var listener: RecyclerItemListener? = null;

    fun setRecyclerListener(listener: RecyclerItemListener) {
        this.listener = listener
    }

    class ViewHodle(var view: View) : RecyclerView.ViewHolder(view) {
        val mIv = view.findViewById<ImageView>(R.id.iv_item_mutliple_option)
        val mTv = view.findViewById<TextView>(R.id.tv_item_mutliple_option)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHodle {
        return ViewHodle(LayoutInflater.from(mContext).inflate(R.layout.item_multiple_option, null))
    }

    override fun onBindViewHolder(holder: ViewHodle, position: Int) {
        val selectRlv = mData[position]
        if (mShowIcon) {
            holder.mIv.setImageResource(if (selectRlv.check) mSelectIcon else mNoSelectIcon)
        } else {
            holder.mIv.visibility = View.GONE
        }
        if (mTextGravity != -1) {
            holder.mTv.gravity = mTextGravity
        }
        holder.mTv.setText(selectRlv.name)
        holder.mTv.setTextColor((if (selectRlv.check) mSelectColor else mNoSelectColor))
        holder.view.setOnClickListener {
            listener?.itemClickListener(position)
        }
    }

    fun setSelectIcom(noSelect: Int, select: Int) {
        mSelectIcon = select
        mNoSelectIcon = noSelect
    }

    fun setShowIcon(show: Boolean) {
        mShowIcon = show
    }

    fun setSelectColor(noSelect: Int, select: Int) {
        mSelectColor = select
        mNoSelectColor = noSelect
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun setTextGravity(gravity: Int) {
        mTextGravity = gravity

    }

}