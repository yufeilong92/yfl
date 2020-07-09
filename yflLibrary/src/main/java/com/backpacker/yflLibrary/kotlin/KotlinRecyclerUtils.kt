package com.backpacker.yflLibrary.kotlin

import android.annotation.SuppressLint
import android.content.Context
import android.widget.BaseAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.backpacker.UtilsLibrary.kotlin
 * @Email : yufeilong92@163.com
 * @Time :2019/7/4 16:46
 * @Purpose :列表工具类
 */
 object KotlinRecyclerUtils {
    /**
     * @METHOD 设置竖向管理器
     * @param rlv
     */

     fun setMangager(mContext:Context,rlv: RecyclerView) {
        var layout = GridLayoutManager(mContext, 1)
        layout.orientation=GridLayoutManager.VERTICAL
        rlv.layoutManager = layout
    }

    /**
     * 设置横向管理器
     */
     fun setHorizontalMangager(mContext:Context,rlv: RecyclerView) {
        var layout = GridLayoutManager(mContext, 1)
        layout.orientation = GridLayoutManager.HORIZONTAL
        rlv.layoutManager = layout
    }

    /**
     * 设置竖向管理器
     */
     fun setMangager(mContext:Context,rlv: RecyclerView, number: Int, orientai: Int) {
        var layout = GridLayoutManager(mContext, number)
        layout.orientation = orientai
        rlv.layoutManager = layout
    }

    fun setGridManage(m: Context, rlv: RecyclerView) {
        val manager = GridLayoutManager(m, 1)
        manager.orientation = GridLayoutManager.VERTICAL
        rlv.layoutManager = manager
    }

    fun setGridManage(m: Context, rlv: RecyclerView, lin: Int) {
        val manager = GridLayoutManager(m, lin)
        manager.orientation = GridLayoutManager.VERTICAL
        rlv.layoutManager = manager
    }

    fun setGridManageH(m: Context, rlv: RecyclerView) {
        val manager = GridLayoutManager(m, 1)
        manager.orientation = GridLayoutManager.HORIZONTAL
        rlv.layoutManager = manager
    }

    fun setGridManage(m: Context, rlv: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        val manager = GridLayoutManager(m, 1)
        manager.orientation = GridLayoutManager.VERTICAL
        rlv.layoutManager = manager
        rlv.adapter = adapter
    }
    /**
     * 设置横向管理器
     */
    fun setHorizontalMangager(mContext:Context,rlv: RecyclerView,postion:Int,adapter: RecyclerView.Adapter<*>) {
        var layout = GridLayoutManager(mContext, postion)
        rlv.layoutManager = layout
        rlv.adapter=adapter
    }


    fun setGridManageTwo(m: Context, rlv: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        val manager = GridLayoutManager(m, 1)
        manager.orientation = GridLayoutManager.VERTICAL
        rlv.layoutManager = manager
        rlv.adapter = adapter
    }

    fun setGridManage(m: Context, rlv: RecyclerView, lin: Int, adapter: RecyclerView.Adapter<*>) {
        val manager = GridLayoutManager(m, lin)
        manager.orientation = GridLayoutManager.VERTICAL
        rlv.layoutManager = manager
        rlv.adapter = adapter
    }
}