package com.backpacker.yflLibrary.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.UtilsLibrary.R

/**
 * @Author : YFL  is Creating a porject in workerApp_Android
 * @Package cn.ruiye.worker.adapter.dialog
 * @Email : yufeilong92@163.com
 * @Time :2020/9/30 17:52
 * @Purpose :多选对话框适配器
 */
class MultipleOptionAdapter(var mContext: Context, var mData: MutableList<SelectRlv>) :
    RecyclerView.Adapter<MultipleOptionAdapter.ViewHodle>() {



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
        holder.mIv.setImageResource(if (selectRlv.check) R.mipmap.ic_gm_select_s else R.mipmap.ic_gm_select_n)
        holder.mTv.setText(selectRlv.name)
        holder.mTv.setTextColor((if (selectRlv.check) Color.GREEN else Color.BLACK))
        holder.view.setOnClickListener {
            listener?.itemClickListener(position)
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }
    data  class SelectRlv(
        var name: String = "",
        var id: String = "",
        var check: Boolean
    ) {
        override fun toString(): String {
            return "SelectRlv(name='$name', id='$id', check=$check)"
        }
    }

}