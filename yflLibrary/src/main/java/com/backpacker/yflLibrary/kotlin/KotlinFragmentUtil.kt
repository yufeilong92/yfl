package com.backpacker.yflLibrary.kotlin
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * @Title:  kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.kotlin
 * @Description:    fragment工具累
 * @author: L-BackPacker
 * @date:   2019/3/31 22:40
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明 * @Copyright: 2019
 */
object KotlinFragmentUtil {
    /**
     * @param sfm    fragment管理器
     * @param list   fragment集合
     * @param layout 显示fragment 布局
     * @param id     要显示的集合的fragment 的几个
     * @return
     */
    fun showSelectFragment(sfm: FragmentManager?, list: List<Fragment>, layout: Int, id: Int) {
        if (id + 1 > list.size) {
            throw IndexOutOfBoundsException("超出集合长度")
        }
        if (sfm == null) {
            throw NullPointerException("FragmentManager不能为空")
        }
        val transaction = sfm!!.beginTransaction()
        val fragment = list[id]
        if (!fragment.isVisible) {
            if (!fragment.isAdded) {
                transaction.add(layout, fragment, fragment.javaClass.name)
            } else {
                for (i in 0 until list.size) {
                    sfm!!.beginTransaction().hide(list[i]).commit()
                }
                transaction.show(fragment)
            }
        }
        transaction.commit()
    }

}