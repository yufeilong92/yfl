package com.backpacker.yflLibrary.demo

import android.content.SharedPreferences
import com.backpacker.yflLibrary.kotlin.SerializableUtil
import android.annotation.SuppressLint
import android.content.Context
import java.io.IOException
import java.io.StreamCorruptedException


/**
 * @Title:  kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.demo
 * @Description:    $todo$
 * @author: L-BackPacker
 * @date:   2019/3/31 23:58
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
class SharedTextListUtil {
    // 用户名key
    val KEY_NAME = "3249937100970977953L"
    val KEY_LEVEL = "KEY_LEVEL"
    private var s_SharedTextListUtil: SharedTextListUtil? = null
    private var s_User: List<String>? = null
    private var msp: SharedPreferences?=null
    // 初始化，一般在应用启动之后就要初始化
    @Synchronized
    fun initSharedPreference(context: Context) {
        if (s_SharedTextListUtil == null) {
            s_SharedTextListUtil = SharedTextListUtil(context)
        }
    }

    /**
     * 获取唯一的instance
     *
     * @return
     */
    @Synchronized
    fun getInstance(): SharedTextListUtil? {
        return s_SharedTextListUtil
    }

    @SuppressLint("WrongConstant")
    fun SharedTextListUtil(context: Context):SharedTextListUtil {
        msp = context.getSharedPreferences(
            "SharedPreUtil",
            Context.MODE_PRIVATE or Context.MODE_APPEND
        )
        return this
    }

    fun getSharedPref(): SharedPreferences? {
        return msp
    }

    @Synchronized
    fun putListAdd(user: List<String>) {
        val editor = msp!!.edit()
        var str = ""
        try {
            str = SerializableUtil.list2String(user)
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        editor.putString(KEY_NAME, str)
        editor.commit()
        s_User = user
    }

    @Synchronized
    fun getUser(): List<String> {
        if (s_User == null) {
            s_User = ArrayList()
            //获取序列化的数据
            val str = msp!!.getString(KEY_NAME, "")
            try {
                val list = SerializableUtil.string2List<String>(str!!)
                if (list != null) {
                    s_User = list
                }
            } catch (e: StreamCorruptedException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            } catch (e: IOException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            }

        }
        return s_User!!
    }

    @Synchronized
    fun DeleteUser() {
        val editor = msp!!.edit()
        editor.putString(KEY_NAME, "")
        editor.commit()
        s_User = null
    }
}