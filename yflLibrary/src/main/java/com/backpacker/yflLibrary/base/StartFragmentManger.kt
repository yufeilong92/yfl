package com.backpacker.yflLibrary.base

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.UtilsLibrary.R

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.backpacker.UtilsLibrary.base
 * @Email : yufeilong92@163.com
 * @Time :2019/7/4 16:40
 * @Purpose :fragment跳转Activity
 */
open class StartFragmentManger(var mContext: FragmentActivity) {
    companion object{
        val CNT_PARAMETE_TITLE: String = "param_title"
    }
     open fun getBundler(): Bundle {
        return Bundle()
    }
    fun jumpTo(clazz: Class<*>) {
        val intentB = Intent()
        intentB.setClass(mContext, clazz)
        mContext.startActivity(intentB)
        mContext.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out)
    }

    fun jumpTo(clazz: Class<*>, title: String) {
        val intentB = Intent()
        intentB.setClass(mContext, clazz)
        intentB.putExtra(CNT_PARAMETE_TITLE, title)
        mContext.startActivity(intentB)
        mContext.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out)
    }


    fun jumpTo(clazz: Class<*>, bundle: Bundle) {
        val intentB = Intent()
        intentB.setClass(mContext, clazz)
        intentB.putExtras(bundle)
        mContext.startActivity(intentB)
        mContext.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out)
    }

    fun jumpTo(clazz: Class<*>, bundle: Bundle, title: String) {
        val intentB = Intent()
        intentB.setClass(mContext, clazz)
        intentB.putExtras(bundle)
        intentB.putExtra(CNT_PARAMETE_TITLE, title)
        mContext.startActivity(intentB)
        mContext.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out)
    }

    fun jumpWeatherTo(clazz: Class<*>, bundle: Bundle, title: String) {
        val intentB = Intent()
        intentB.setClass(mContext, clazz)
        intentB.putExtras(bundle)
        intentB.putExtra(CNT_PARAMETE_TITLE, title)
        mContext.startActivity(intentB)
        mContext.overridePendingTransition(R.anim.weather_up, R.anim.weather_down)
    }
    fun jumpToFoResult(clazz: Class<*>, resultCode: Int) {
        val intentB = Intent()
        intentB.setClass(mContext, clazz)
        mContext. startActivityForResult(intentB, resultCode)
        mContext.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out)
    }

    fun jumpToFoResult(clazz: Class<*>, bundle: Bundle, resultCode: Int) {
        val intentB = Intent()
        intentB.setClass(mContext, clazz)
        intentB.putExtras(bundle)
       mContext. startActivityForResult(intentB, resultCode)
        mContext.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out)
    }

    fun jumpToBU(clazz: Class<*>) {
        val intentB = Intent()
        intentB.setClass(mContext, clazz)
        mContext.startActivity(intentB)
        mContext.overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out)
    }

    fun jumpToBU(clazz: Class<*>, bundle: Bundle) {
        val intentB = Intent()
        intentB.setClass(mContext, clazz)
        intentB.putExtras(bundle)
        mContext.startActivity(intentB)
        mContext.overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out)
    }

    fun jumpToFoResulBU(clazz: Class<*>, resultCode: Int) {
        val intentB = Intent()
        intentB.setClass(mContext, clazz)
        mContext.startActivityForResult(intentB, resultCode)
        mContext.overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out)
    }

    fun jumpToFoResulBU(clazz: Class<*>, resultCode: Int, title: String) {
        val intentB = Intent()
        intentB.setClass(mContext, clazz)
        intentB.putExtra(CNT_PARAMETE_TITLE, title)
        mContext. startActivityForResult(intentB, resultCode)
        mContext.overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out)
    }

    fun jumpToFoResultBU(clazz: Class<*>, bundle: Bundle, resultCode: Int) {
        val intentB = Intent()
        intentB.setClass(mContext, clazz)
        intentB.putExtras(bundle)
        mContext. startActivityForResult(intentB, resultCode)
        mContext.overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out)
    }

    fun finishBase() {
        mContext.finish()
        mContext.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out)
    }
}