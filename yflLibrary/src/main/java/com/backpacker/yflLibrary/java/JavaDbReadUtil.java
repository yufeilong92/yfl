package com.backpacker.yflLibrary.java;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.java
 * @Description: 打开数据库工具类
 * @author: L-BackPacker
 * @date: 2019/4/1 0:17
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
public class JavaDbReadUtil {
    private volatile static JavaDbReadUtil _instance;

    private JavaDbReadUtil() {
    }

    public static JavaDbReadUtil get_Instance() {
        if (_instance == null) {
            synchronized (JavaDbReadUtil.class) {
                if (_instance == null) {
                    _instance = new JavaDbReadUtil();
                }
            }
        }
        return _instance;
    }

    private String db_path;

    public SQLiteDatabase GetDataBasePath(Context context) {
        String DB_NAME = "xuechuan.db";
        String DB_PATH = "xuechuan_question.db";
        String dbPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/xuechuan/databasecopy/" + DB_NAME;
        if (!new File(dbPath).exists()) {
            try {
                boolean flag = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +"/xuechuan/databasecopy/").mkdirs();
                boolean newFile = new File(dbPath).createNewFile();
                try {
                    FileOutputStream out = new FileOutputStream(dbPath);
                    InputStream in = context.getAssets().open(DB_PATH);
                    byte[] buffer = new byte[1024];
                    int readBytes = 0;
                    while ((readBytes = in.read(buffer)) != -1)
                        out.write(buffer, 0, readBytes);
                    in.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return SQLiteDatabase.openOrCreateDatabase(dbPath, null);


      /*  String packageName = context.getPackageName();
        //Log.i("PackName", packageName);
        String DB_PATH = "xuechuan_question.db";
        // packageName);
        db_path = PolyvDevMountInfo.getInstance().getInternalSDCardPath() + File.separator + "xuechuan/databasecopy/xuechuan.db";
        if ((new File(db_path)).exists() == false) {
            try {
                // 如 SQLite 数据库文件不存在，再检查一下 database 目录是否存在
                File f = new File(db_path);
                // 如 database 目录不存在，新建该目录
                if (!f.exists()) {
                    f.mkdir();
                }
                // 得到 assets 目录下我们实现准备好的 SQLite 数据库作为输入流
                InputStream is = context.getAssets().open(DB_PATH);
                // 输出流
                OutputStream os = new FileOutputStream(db_path);
                // 文件写入
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
                // 关闭文件流
                os.flush();
                os.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return SQLiteDatabase.openOrCreateDatabase(db_path, null);*/
    }
}
