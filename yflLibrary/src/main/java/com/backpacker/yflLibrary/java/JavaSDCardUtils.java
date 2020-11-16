package com.backpacker.yflLibrary.java;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;


import com.backpacker.yflLibrary.view.dialog.addresspicker.ConvertUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Author : YFL  is Creating a porject in Dell
 * @Package :
 * @Email : yufeilong92@163.com
 * @Time :2020/11/16 13:51
 * @Purpose :SD卡相关工具类
 */
public class JavaSDCardUtils {

    private JavaSDCardUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 判断SD卡是否可用
     *
     * @return true : 可用<br>false : 不可用
     */
    public static boolean isSDCardEnable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 获取SD卡Data路径
     *
     * @return SD卡Data路径
     */
    public static String getDataPath() {
        if (!isSDCardEnable()) return "sdcard unable!";
        return Environment.getDataDirectory().getPath() + File.separator;
    }

    /**
     * 获取SD卡路径
     * <p>一般是/storage/emulated/0/</p>
     *
     * @return SD卡路径
     */
    public static String getSDCardPath() {
        if (!isSDCardEnable()) return "sdcard unable!";
        return Environment.getExternalStorageDirectory().getPath() + File.separator;
    }

    /**
     * 获取SD卡路径
     *
     * @return SD卡路径
     */
    public static String getSDCardPathByCmd() {
        if (!isSDCardEnable()) return "sdcard unable!";
        String cmd = "cat /proc/mounts";
        Runtime run = Runtime.getRuntime();
        BufferedReader bufferedReader = null;
        try {
            Process p = run.exec(cmd);
            bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(p.getInputStream())));
            String lineStr;
            while ((lineStr = bufferedReader.readLine()) != null) {
                if (lineStr.contains("sdcard") && lineStr.contains(".android_secure")) {
                    String[] strArray = lineStr.split(" ");
                    if (strArray.length >= 5) {
                        return strArray[1].replace("/.android_secure", "") + File.separator;
                    }
                }
                if (p.waitFor() != 0 && p.exitValue() == 1) {
                    return " 命令执行失败";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return Environment.getExternalStorageDirectory().getPath() + File.separator;
    }




    /**
     * 获取SD卡信息
     *
     * @return SDCardInfo
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static SDCardInfo getSDCardInfo() {
        SDCardInfo sd = new SDCardInfo();
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            sd.isExist = true;
            File sdcardDir = Environment.getExternalStorageDirectory();
            StatFs sf = new StatFs(sdcardDir.getPath());
            sd.totalBlocks = sf.getBlockCountLong();
            sd.blockByteSize = sf.getBlockSizeLong();
            sd.availableBlocks = sf.getAvailableBlocksLong();
            sd.availableBytes = sf.getAvailableBytes();
            sd.freeBlocks = sf.getFreeBlocksLong();
            sd.freeBytes = sf.getFreeBytes();
            sd.totalBytes = sf.getTotalBytes();
        }
        return sd;
    }

    private static class SDCardInfo {
        boolean isExist;
        long totalBlocks;
        long freeBlocks;
        long availableBlocks;

        long blockByteSize;

        long totalBytes;
        long freeBytes;
        long availableBytes;

        @Override
        public String toString() {
            return "SDCardInfo{" +
                    "isExist=" + isExist +
                    ", totalBlocks=" + totalBlocks +
                    ", freeBlocks=" + freeBlocks +
                    ", availableBlocks=" + availableBlocks +
                    ", blockByteSize=" + blockByteSize +
                    ", totalBytes=" + totalBytes +
                    ", freeBytes=" + freeBytes +
                    ", availableBytes=" + availableBytes +
                    '}';
        }
    }

    public static String getRealPathFromURI(Activity a, Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = a.getContentResolver().query(contentUri, proj, null, null, null);
        if (null != cursor && cursor.moveToFirst()) {
            ;
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
            cursor.close();
        }
        return res;
    }

    /**
     *  * 专为Android4.4设计的从Uri获取文件绝对路径，以前的方法已不好使
     *  
     */
    @SuppressLint("NewApi")
    public static String getPath(final Context context, final Uri uri) {


        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;


// DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
//                final String type = split[0];
//                if ("primary".equalsIgnoreCase(type)) {
                return Environment.getExternalStorageDirectory() + "/" + split[1];
//                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {


                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));


                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];


                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }


                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};


                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
// MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
// File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    /**
     *  * Get the value of the data column for this Uri. This is useful for
     *  * MediaStore Uris, and other file-based ContentProviders.
     *  *
     *  * @param context    The context.
     *  * @param uri     The Uri to query.
     *  * @param selection  (Optional) Filter used in the query.
     *  * @param selectionArgs (Optional) Selection arguments used in the query.
     *  * @return The value of the _data column, which is typically a file path.
     *  
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {


        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};


        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     *  * @param uri The Uri to check.
     *  * @return Whether the Uri authority is ExternalStorageProvider.
     *  
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }


    /**
     *  * @param uri The Uri to check.
     *  * @return Whether the Uri authority is DownloadsProvider.
     *  
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }


    /**
     *  * @param uri The Uri to check.
     *  * @return Whether the Uri authority is MediaProvider.
     *  
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static String mReadTxtFile(String strFilePath) {
        String path = String.valueOf(strFilePath);
        String content = ""; //文件内容字符串
        //打开文件
        File file = new File(path);
        //如果path是传递过来的参数，可以做一个非目录的判断
        if (file.isDirectory()) {
        } else {
            try {
                InputStream instream = new FileInputStream(file);
                if (instream != null) {
                    String line=null;
                    BufferedReader buffreader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
                    //分行读取
                    while ((line = buffreader.readLine()) != null) {
                        content += line+";";
                    }
                    instream.close();
                }
            } catch (java.io.FileNotFoundException e) {
                Log.d("TestFile", "The File doesn't not exist.");
            } catch (IOException e) {
                Log.d("TestFile", e.getMessage());
            }
        }
        return content;
    }

/*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
 val rui = data.data
            if ("file".equals(rui!!.scheme)) {
        path = rui.path
    } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
        path = FileUtil.getPath(this, rui)
    } else
    path = FileUtil.getRealPathFromURI(this, rui)*/
}