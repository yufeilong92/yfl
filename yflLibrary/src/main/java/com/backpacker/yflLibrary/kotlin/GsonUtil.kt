package com.backpacker.yflLibrary.kotlin

import com.google.gson.Gson
import java.lang.reflect.Type

/**
 * @Title:  kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.kotlin
 * @Description:    $todo$
 * @author: L-BackPacker
 * @date:   2019/3/31 23:05
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
object GsonUtil {
    fun <T> getGsonT(success: String, c: Class<T>): T {
        val gson = Gson()
        return gson.fromJson(success, c)
    }

    fun <T> getGsonT(success: String, type: Type): T {
        val gson = Gson()
        return gson.fromJson(success, type)
    }
}