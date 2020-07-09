package com.backpacker.yflLibrary.kotlin

import com.tencent.wcdb.Cursor


/**
 * @Title:  kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.kotlin
 * @Description:    $todo$
 * @author: L-BackPacker
 * @date:   2019/4/1 0:10
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
class KotlinDbQueryUtil {
    companion object {//被companion object包裹的语句都是private的

        private var singletonInstance: KotlinDbQueryUtil? = null

        @Synchronized
        fun getInstance(): KotlinDbQueryUtil? {
            if (singletonInstance == null) {
                singletonInstance = KotlinDbQueryUtil()
            }
            return singletonInstance
        }
    }
    private var mQuery: Cursor? = null

    fun initCursor(query: Cursor) {
        this.mQuery = query
    }

    fun queryInt(key: String): Int {
        QuesryException()
        val columnIndex = mQuery!!.getColumnIndex(key)
        return mQuery!!.getInt(columnIndex)
    }

    private fun QuesryException() {
        if (mQuery == null) throw NullPointerException("mQuery 不能为空，请执行intCursor()") as Throwable
    }

    fun queryString(key: String): String? {
        QuesryException()
        val columnIndex = mQuery!!.getColumnIndex(key)
        return mQuery!!.getString(columnIndex)
    }

    fun queryBLOB(key: String): ByteArray {
        QuesryException()
        val columnIndex = mQuery!!.getColumnIndex(key)
        return mQuery!!.getBlob(columnIndex)
    }

    fun querydouble(key: String): Double {
        QuesryException()
        val columnIndex = mQuery!!.getColumnIndex(key)
        return mQuery!!.getDouble(columnIndex)
    }

    fun queryFloat(key: String): Float? {
        QuesryException()
        val columnIndex = mQuery!!.getColumnIndex(key)
        return mQuery!!.getFloat(columnIndex)
    }

    fun queryLong(key: String): Long? {
        QuesryException()
        val columnIndex = mQuery!!.getColumnIndex(key)
        return mQuery!!.getLong(columnIndex)
    }

    fun queryShort(key: String): Short? {
        QuesryException()
        val columnIndex = mQuery!!.getColumnIndex(key)
        return mQuery!!.getShort(columnIndex)
    }
}