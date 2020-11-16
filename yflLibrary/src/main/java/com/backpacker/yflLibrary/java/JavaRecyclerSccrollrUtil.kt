package com.backpacker.yflLibrary.java

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * @Author : YFL  is Creating a porject in xiaole
 * @Package cn.ruiye.xiaole.utils
 * @Email : yufeilong92@163.com
 * @Time :2019/11/13 11:34
 * @Purpose :滑动工具
 */
 public  class JavaRecyclerSccrollrUtil {
  companion object {//被companion object包裹的语句都是private的

          private var singletonInstance: JavaRecyclerSccrollrUtil ?= null

          @Synchronized fun getInstance(): JavaRecyclerSccrollrUtil?{
              if (singletonInstance == null){
                  singletonInstance =  JavaRecyclerSccrollrUtil()
              }
              return singletonInstance
          }
      }
    /**
     * 获取滑动距离
     *
     * @param manager
     * @return
     */
     fun getScroolY(rlv:RecyclerView,manager: GridLayoutManager): Int {
        val c = rlv.getChildAt(0) ?: return 0
        val i = manager.findFirstVisibleItemPosition()
        val top = c.top
        /**
         * 声明一下，这里测试得到的top值始终是RecyclerView条目中显示的第一条距离顶部的距离，
         * 而这个在坐标中的表示是一个负数，所以需要对其取一个绝对值
         */
        return i * c.height + Math.abs(top)
    }

    /**
     * 更具相应位子显示相应的透明度
     *
     * @param dis
     * @return
     */
     fun getAlphaFloat(dis: Int): Float {
        val step = 100
        if (dis == 0) {
            return 0.0f
        }
        return if (dis < step) {
            (dis * (1.0 / step)).toFloat()
        } else {
            1.0f
        }
    }
}