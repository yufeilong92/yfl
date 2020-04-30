package com.backpacker.yflLibrary.sqlitdb;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: xcedu
 * @Package com.xuechuan.xcedu.db.DbHelp
 * @Description: todo
 * @author: L-BackPacker
 * @date: 2018/6/5 10:21
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class DatabaseContext extends ContextWrapper {

    public DatabaseContext(Context base) {
        super(base);
    }

    /**
     * 获得数据库路径，如果不存在，则自动创建
     */
    @Override
    public File getDatabasePath(String name) {
        //判断是否存在sd卡
        boolean sdExist = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
        if (!sdExist) {//如果不存在,
            Log.e("SD卡管理：", "SD卡不存在，请加载SD卡");
            return super.getDatabasePath(name);
        }
        String dbDir = getStoragePath(this, true);
//        String dbDir = android.os.Environment.getExternalStorageDirectory().toString();
        dbDir += "/xiaole";
        dbDir += "/databases";//数据库所在目录
        String dbPath = dbDir + "/" + name;//数据库路径
        //判断目录是否存在，不存在则创建该目录
        File dirFile = new File(dbDir);
        if (!dirFile.exists())
            dirFile.mkdirs();
        //数据库文件是否创建成功
        //判断文件是否存在，不存在则创建该文件
        File dbFile = new File(dbPath);
        //数据库文件是否创建成功
        boolean isFileCreateSuccess = false;
        if (!dbFile.exists()) {
            try {
                isFileCreateSuccess = dbFile.createNewFile();//创建文件
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            isFileCreateSuccess = true;
        }
        //返回数据库文件对象
        if (isFileCreateSuccess)
            return dbFile;
        else {
            return super.getDatabasePath(name);
        }

    }


    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode,
                                               SQLiteDatabase.CursorFactory factory) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);
        return result;
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory,
                                               DatabaseErrorHandler errorHandler) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);
        return result;
    }

    /**
     * 通过反射调用获取内置存储和外置sd卡根路径(通用)
     *
     * @param mContext    上下文
     * @param is_removale 是否可移除，false返回内部存储路径，true返回外置SD卡路径
     * @return
     */
    private static String getStoragePath(Context mContext, boolean is_removale) {
        String path = "";
        //使用getSystemService(String)检索一个StorageManager用于访问系统存储功能。
        StorageManager mStorageManager = (StorageManager) mContext.getSystemService(Context.STORAGE_SERVICE);
        Class<?> storageVolumeClazz = null;
        try {
            storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");
            Method getPath = storageVolumeClazz.getMethod("getPath");
            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
            Object result = getVolumeList.invoke(mStorageManager);

            for (int i = 0; i < Array.getLength(result); i++) {
                Object storageVolumeElement = Array.get(result, i);
                path = (String) getPath.invoke(storageVolumeElement);
                boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);
                if (is_removale == removable) {
                    return path;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }
}