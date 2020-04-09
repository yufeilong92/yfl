package com.backpacker.yflLibrary.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.UtilsLibrary.R

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.backpacker.UtilsLibrary.base
 * @Email : yufeilong92@163.com
 * @Time :2019/7/4 16:34
 * @Purpose :跳转类
 */
 open class StartActivityManger(var mContext:Context) {
    companion object{
        val CNT_PARAMETE_TITLE: String = "param_title"
    }

   open fun getBundler(): Bundle {
        return Bundle()
    }
    fun jumpTo(clazz: Class<*>) {
        val intentB = Intent()
        intentB.setClass(mContext, clazz)
        (mContext as Activity).startActivity(intentB)
        (mContext as Activity).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out)
    }

    fun jumpTo(clazz: Class<*>, title: String) {
        val intentB = Intent()
        intentB.setClass(mContext, clazz)
        intentB.putExtra(CNT_PARAMETE_TITLE, title)
        (mContext as Activity).startActivity(intentB)
        (mContext as Activity).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out)
    }

    fun jumpTo(clazz: Class<*>, bundle: Bundle) {
        val intentB = Intent()
        intentB.setClass(mContext, clazz)
        intentB.putExtras(bundle)
        (mContext as Activity).startActivity(intentB)
        (mContext as Activity).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out)
    }

    fun jumpTo(clazz: Class<*>, bundle: Bundle, title: String) {
        val intentB = Intent()
        intentB.setClass(mContext, clazz)
        intentB.putExtras(bundle)
        intentB.putExtra(CNT_PARAMETE_TITLE, title)
        (mContext as Activity).startActivity(intentB)
        (mContext as Activity).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out)
    }

    fun jumpWeatherTo(clazz: Class<*>, bundle: Bundle, title: String) {
        val intentB = Intent()
        intentB.setClass(mContext, clazz)
        intentB.putExtras(bundle)
        intentB.putExtra(CNT_PARAMETE_TITLE, title)
        (mContext as Activity).startActivity(intentB)
        (mContext as Activity).overridePendingTransition(R.anim.weather_up, R.anim.weather_down)
    }

    fun jumpToWeather(clazz: Class<*>, bundle: Bundle, title: String) {
        val intentB = Intent()
        intentB.setClass(mContext, clazz)
        intentB.putExtras(bundle)
        intentB.putExtra(CNT_PARAMETE_TITLE, title)
        (mContext as Activity).startActivity(intentB)
        (mContext as Activity).overridePendingTransition(R.anim.weather_up, R.anim.weather_down)
    }

    fun jumpToFoResult(clazz: Class<*>, resultCode: Int) {
        val intentB = Intent()
        intentB.setClass(mContext, clazz)
        (mContext as Activity).startActivityForResult(intentB, resultCode)
        (mContext as Activity).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out)
    }

    fun jumpToFoResult(clazz: Class<*>, bundle: Bundle, resultCode: Int) {
        val intentB = Intent()
        intentB.setClass(mContext, clazz)
        intentB.putExtras(bundle)
        (mContext as Activity).startActivityForResult(intentB, resultCode)
        (mContext as Activity).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out)
    }

    fun jumpToBU(clazz: Class<*>) {
        val intentB = Intent()
        intentB.setClass((mContext as Activity), clazz)
        (mContext as Activity).startActivity(intentB)
        (mContext as Activity).overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out)
    }

    fun jumpToBU(clazz: Class<*>, bundle: Bundle) {
        val intentB = Intent()
        intentB.setClass(mContext, clazz)
        intentB.putExtras(bundle)
        (mContext as Activity).startActivity(intentB)
        (mContext as Activity).overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out)
    }

    fun jumpToFoResulBU(clazz: Class<*>, resultCode: Int) {
        val intentB = Intent()
        intentB.setClass(mContext, clazz)
        (mContext as Activity).startActivityForResult(intentB, resultCode)
        (mContext as Activity).overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out)
    }

    fun jumpToFoResultBU(clazz: Class<*>, bundle: Bundle, resultCode: Int) {
        val intentB = Intent()
        intentB.setClass(mContext, clazz)
        intentB.putExtras(bundle)
        (mContext as Activity).startActivityForResult(intentB, resultCode)
        (mContext as Activity).overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out)
    }

    fun jumpToFoResulBU(clazz: Class<*>,  title: String, resultCode: Int) {
        val intentB = Intent()
        intentB.setClass(mContext, clazz)
        intentB.putExtra(CNT_PARAMETE_TITLE, title)
        (mContext as Activity).startActivityForResult(intentB, resultCode)
        (mContext as Activity).overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out)
    }
    fun jumpToFoResulBU(clazz: Class<*>, bundle: Bundle, title: String, resultCode: Int) {
        val intentB = Intent()
        intentB.setClass(mContext, clazz)
        intentB.putExtras(bundle)
        intentB.putExtra(CNT_PARAMETE_TITLE, title)
        (mContext as Activity).startActivityForResult(intentB, resultCode)
        (mContext as Activity).overridePendingTransition(R.anim.push_buttom_in, R.anim.push_buttom_out)
    }

    fun finishBase() {
        (mContext as Activity). finish()
        (mContext as Activity).overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out)
    }

    fun onFinishWeather() {
        (mContext as Activity).finish()
        (mContext as Activity).  overridePendingTransition(R.anim.weather_finish_up, R.anim.weather_finish_down)
    }

    fun finishBaseWeather() {
        (mContext as Activity).finish()
        (mContext as Activity).overridePendingTransition(R.anim.weather_up, R.anim.weather_down)
    }

    fun finishBaseBU() {
        (mContext as Activity). finish()
        (mContext as Activity). overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out)
    }

}