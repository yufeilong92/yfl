package cn.yfl.myapplication

import android.app.Application
import androidx.multidex.MultiDex
import com.backpacker.yflLibrary.kotlin.KotlinAppBarUtil

/**
 * @Author : YFL  is Creating a porject in My Application
 * @Package cn.yfl.myapplication
 * @Email : yufeilong92@163.com
 * @Time :2020/6/4 14:36
 * @Purpose :
 */
class BaseApplcation:Application() {
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        KotlinAppBarUtil.obtainScreenWH(this)
    }
}