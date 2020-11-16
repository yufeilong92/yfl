package com.backpacker.yflLibrary.java;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

/**
 * 文件工具类
 */
public class JavaFileUtil {
    private static volatile JavaFileUtil _singleton;
    private Context mContext;

    private JavaFileUtil(Context context) {
        this.mContext = context;
    }

    public static JavaFileUtil get_Instance(Context context) {
        if (_singleton == null) {
            synchronized (JavaFileUtil.class) {
                if (_singleton == null) {
                    _singleton = new JavaFileUtil(context);
                }
            }
        }
        return _singleton;
    }


    /**
     * 根据Uri返回文件绝对路径
     * 兼容了file:///开头的 和 content://开头的情况
     */
    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_FILE.equalsIgnoreCase(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equalsIgnoreCase(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * 检查文件是否存在
     */
    public static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }

    private static final String TAG = "FileUtil";

    /**
     * 保存bitmap到SD卡
     *
     * @param bitmap
     * @param imagename
     */
    public static String saveBitmapToSDCard(Bitmap bitmap, String imagename) {
        String name = "img-" + imagename + ".jpg";
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String path = absolutePath + File.separator + "myPic" + File.separator;
        File file = new File(path);
        boolean newFile = file.mkdirs();
        Log.i(TAG, "saveBitmapToSDCard: newFile = " + newFile);

        path = path + name;
        FileOutputStream fos = null;
        Log.i(TAG, "saveBitmapToSDCard: path =" + path);
        try {
            File file1 = new File(path);
            fos = new FileOutputStream(path);
            if (fos != null) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
                fos.close();
            }

            return path;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long getFileSize(String path) {
        if (TextUtils.isEmpty(path) || !new File(path).exists()) {
            return 0L;
        }

        return new File(path).length();
    }

    public static String saveImag(Context mContext, Bitmap bitmap) {
        FileOutputStream outputStream;
        File dir = mContext.getExternalCacheDir();
        if (null == dir)
            dir = mContext.getCacheDir();
        String path = dir.getAbsolutePath() + File.separator + "/" + System.currentTimeMillis() + ".png";
        File file = new File(path);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            path = file.toString();
            outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    public static String getFilePath(Context mContext) {
        File dir = mContext.getExternalCacheDir();
        if (null == dir)
            dir = mContext.getCacheDir();
        return dir.getAbsolutePath() + File.separator + "/";
    }

    public enum SizeUnit {
        Byte,
        KB,
        MB,
        GB,
        TB,
        Auto,
    }

    /**
     * 获取文件的大小
     *
     * @param size
     * @param unit
     * @return
     */
    public static String formatFileSize(long size, SizeUnit unit) {
        if (size < 0) {
            return "未知大小";
        }

        final double KB = 1024;
        final double MB = KB * 1024;
        final double GB = MB * 1024;
        final double TB = GB * 1024;
        if (unit == SizeUnit.Auto) {
            if (size < KB) {
                unit = SizeUnit.Byte;
            } else if (size < MB) {
                unit = SizeUnit.KB;
            } else if (size < GB) {
                unit = SizeUnit.MB;
            } else if (size < TB) {
                unit = SizeUnit.GB;
            } else {
                unit = SizeUnit.TB;
            }
        }
        switch (unit) {
            case Byte:
                return size + "B";
            case KB:
                return String.format(Locale.US, "%.2fKB", size / KB);
            case MB:
                return String.format(Locale.US, "%.2fMB", size / MB);
            case GB:
                return String.format(Locale.US, "%.2fGB", size / GB);
            case TB:
                return String.format(Locale.US, "%.2fPB", size / TB);
            default:
                return size + "B";
        }
    }

    /**
     * 复制单个文件
     *
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean 是否copy文件成功
     */
    public static boolean copyFile(String oldPath, String newPath) {
        boolean isok = true;
        try {
            int bytesum = 0;
            int byteread = 0;
            Log.d(TAG, "oldPath:" + oldPath + "---------->newPath:" + newPath);
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1024];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    fs.write(buffer, 0, byteread);
                }
                fs.flush();//刷新流
                fs.close();//关闭
                inStream.close();//关闭输入流
            } else {
                isok = false;
            }
        } catch (Exception e) {
            isok = false;
        }
        return isok;
    }

    /**
     * 计算缓存的大小,
     *
     * @return
     */
    public String getCacheSize() {
        long fileSize = 0;
        String cacheSize = "0KB";
        File filesDir = mContext.getApplicationContext().getFilesDir();// /data/data/package_name/files
        File cacheDir = mContext.getCacheDir();// /data/data/package_name/cache
        fileSize += getDirSize(filesDir);
        fileSize += getDirSize(cacheDir);
        // 2.2版本才有将应用缓存转移到sd卡的功能
        if (isMethodsCompat(android.os.Build.VERSION_CODES.FROYO)) {
            File externalCacheDir = getExternalCacheDir(mContext);// "<sdcard>/Android/data/<package_name>/cache/"
            fileSize += getDirSize(externalCacheDir);
        }
        if (fileSize > 0)
            cacheSize = formatFileSize(fileSize);
        return cacheSize;
    }

    /**
     * 清除app缓存
     */
    public void clearAppCache() {
        // 清除数据缓存
        clearCacheFolder(mContext.getFilesDir(), System.currentTimeMillis());
        clearCacheFolder(mContext.getCacheDir(), System.currentTimeMillis());
        // 2.2版本才有将应用缓存转移到sd卡的功能
        if (isMethodsCompat(android.os.Build.VERSION_CODES.FROYO)) {
            clearCacheFolder(getExternalCacheDir(mContext),
                    System.currentTimeMillis());
        }
    }

    /**
     * 清除缓存目录
     *
     * @param dir     目录
     * @param curTime 当前系统时间
     * @return
     */
    private int clearCacheFolder(File dir, long curTime) {
        int deletedFiles = 0;
        if (dir != null && dir.isDirectory()) {
            try {
                for (File child : dir.listFiles()) {
                    if (child.isDirectory()) {
                        deletedFiles += clearCacheFolder(child, curTime);
                    }
                    if (child.lastModified() < curTime) {
                        if (child.delete()) {
                            deletedFiles++;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deletedFiles;
    }


    /**
     * 获取目录文件大小
     *
     * @param dir
     * @return
     */
    public static long getDirSize(File dir) {
        if (dir == null) {
            return 0;
        }
        if (!dir.isDirectory()) {
            return 0;
        }
        long dirSize = 0;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                dirSize += file.length();
            } else if (file.isDirectory()) {
                dirSize += file.length();
                dirSize += getDirSize(file); // 递归调用继续统计
            }
        }
        return dirSize;
    }

    /**
     * 将二进制长度转换成文件大小
     *
     * @param length
     * @return
     */
    public static String formatFileSize(long length) {
        String result = null;
        int sub_string = 0;
        if (length >= 1073741824) {
            sub_string = String.valueOf((float) length / 1073741824).indexOf(
                    ".");
            result = ((float) length / 1073741824 + "000").substring(0,
                    sub_string + 3) + "GB";
        } else if (length >= 1048576) {
            sub_string = String.valueOf((float) length / 1048576).indexOf(".");
            result = ((float) length / 1048576 + "000").substring(0,
                    sub_string + 3) + "MB";
        } else if (length >= 1024) {
            sub_string = String.valueOf((float) length / 1024).indexOf(".");
            result = ((float) length / 1024 + "000").substring(0,
                    sub_string + 3) + "KB";
        } else if (length < 1024)
            result = Long.toString(length) + "B";
        return result;
    }


    /**
     * 判断当前版本是否兼容目标版本的方法
     *
     * @param VersionCode
     * @return
     */
    public static boolean isMethodsCompat(int VersionCode) {
        int currentVersion = android.os.Build.VERSION.SDK_INT;
        return currentVersion >= VersionCode;
    }

    public static File getExternalCacheDir(Context context) {
        // return context.getExternalCacheDir(); API level 8
        // e.g. "<sdcard>/Android/data/<package_name>/cache/"
        File dir = context.getExternalCacheDir();
        if (null == dir)
            dir = context.getCacheDir();
        return dir;
    }

}
