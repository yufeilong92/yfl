package cn.yfl.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * @Author : YFL  is Creating a porject in My Application
 * @Package cn.yfl.myapplication
 * @Email : yufeilong92@163.com
 * @Time :2020/8/12 10:41
 * @Purpose :数据
 */
class MainAdapter(var mContext: Context, var list: MutableList<String>) :
    RecyclerView.Adapter<MainAdapter.ViewHolde>() {
    private var mInflater: LayoutInflater? = null

    init {
        mInflater = LayoutInflater.from(mContext)
    }

    class ViewHolde(view: View) : RecyclerView.ViewHolder(view) {
        val tv = view.findViewById<TextView>(R.id.tv_content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolde {
        return ViewHolde(mInflater!!.inflate(R.layout.main_item, null))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolde, position: Int) {
        holder.tv.text = list[position]
    }
}