package com.backpacker.yflLibrary.java;

import android.database.Cursor;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.java
 * @Description: 数据库操作类
 * @author: L-BackPacker
 * @date: 2019/4/1 0:09
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
public class JavaDbQueryUtil {
    private volatile static JavaDbQueryUtil _instance;

    private JavaDbQueryUtil() {
    }

    public static JavaDbQueryUtil get_Instance() {
   /*     if (_instance == null) {
            synchronized (DbQueryUtil.class) {
                if (_instance == null) {
                    _instance = new DbQueryUtil();
                }
            }
        }*/
        return _instance = new JavaDbQueryUtil();
    }

    private Cursor mQuery;

    public void initCursor(Cursor query) {
        this.mQuery = query;
    }

    public int queryInt(String key) {
        QuesryException();
        int columnIndex = mQuery.getColumnIndex(key);
        return mQuery.getInt(columnIndex);
    }

    private void QuesryException() {
        if (mQuery == null) throw new NullPointerException("mQuery 不能为空，请执行intCursor()");
    }

    public String queryString(String key) {
        QuesryException();
        int columnIndex = mQuery.getColumnIndex(key);
        return mQuery.getString(columnIndex);
    }

    public byte[] queryBLOB(String key) {
        QuesryException();
        int columnIndex = mQuery.getColumnIndex(key);
        return mQuery.getBlob(columnIndex);
    }

    public double querydouble(String key) {
        QuesryException();
        int columnIndex = mQuery.getColumnIndex(key);
        return mQuery.getDouble(columnIndex);
    }

    public Float queryFloat(String key) {
        QuesryException();
        int columnIndex = mQuery.getColumnIndex(key);
        return mQuery.getFloat(columnIndex);
    }

    public Long queryLong(String key) {
        QuesryException();
        int columnIndex = mQuery.getColumnIndex(key);
        return mQuery.getLong(columnIndex);
    }

    public Short queryShort(String key) {
        QuesryException();
        int columnIndex = mQuery.getColumnIndex(key);
        return mQuery.getShort(columnIndex);
    }

}