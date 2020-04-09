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
 * @date:   2019/3/31 23:56
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
class SaveUUidUtil {
    // 用户名key
    val KEY_NAME = "useruuid"
    private var sharedUserUtils: SaveUUidUtil? = null
    private lateinit var msp: SharedPreferences
    private var s_User: String? = null

    @SuppressLint("WrongConstant")
    fun SaveUUidUtil(context: Context): SaveUUidUtil {
        msp = context.getSharedPreferences(
            "data",
            Context.MODE_PRIVATE or Context.MODE_APPEND
        )
        return this
    }

    @Synchronized
    fun initSharedPreference(context: Context) {
        if (sharedUserUtils == null) {
            sharedUserUtils = SaveUUidUtil(context)
        }
    }

    @Synchronized
    fun getInstance(): SaveUUidUtil? {
        return sharedUserUtils
    }

    fun getSharedPref(): SharedPreferences {
        return msp
    }

    @Synchronized
    fun putUUID(vo: String) {
        val editor = msp.edit()
        var str = ""
        try {
            str = SerializableUtil.obj2Str(vo)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        editor.putString(KEY_NAME, str)
        editor.commit()
        s_User = vo
    }

    @Synchronized
    fun getUserId(): String {
        if (s_User == null) {
            s_User = String()
            //获取序列化的数据
            val str = msp.getString(KEY_NAME, "")
            try {
                val obj = SerializableUtil.str2Obj(str!!)
                if (obj != null) {
                    s_User = obj as String?
                }
            } catch (e: StreamCorruptedException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        return s_User as String
    }

    @Synchronized
    fun delectUUid() {
        val editor = msp.edit()
        editor.putString(KEY_NAME, "")
        editor.commit()
        s_User = null
    }
}