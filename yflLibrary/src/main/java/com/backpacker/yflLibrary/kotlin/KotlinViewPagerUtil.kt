package com.backpacker.yflLibrary.kotlin


import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

/**
 * @Author : YFL  is Creating a porject in workerApp_Android
 * @Package com.backpacker.yflLibrary.kotlin
 * @Email : yufeilong92@163.com
 * @Time :2020/1/17 10:08
 * @Purpose :viewpager工具
 */
object KotlinViewPagerUtil {
    fun setViewPageAdater(
        viewPage: ViewPager,
        tab: TabLayout,
        offscreenPageLimit: Int,
        adapter: FragmentStatePagerAdapter
    ) {
        viewPage.adapter = adapter
        tab.setupWithViewPager(viewPage)
        viewPage.offscreenPageLimit = offscreenPageLimit
    }
}