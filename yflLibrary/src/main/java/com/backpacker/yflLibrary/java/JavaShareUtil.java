package com.backpacker.yflLibrary.java;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
/**
 * @Author : YFL  is Creating a porject in Dell
 * @Package :
 * @Email : yufeilong92@163.com
 * @Time :2020/11/16 13:52
 * @Purpose :分享工具
 */
public class JavaShareUtil {

    private volatile static JavaShareUtil singleton;
//    private IWXAPI mWxapi;

    private JavaShareUtil() {

    }

    public static JavaShareUtil getSingleton() {
        if (singleton == null) {
            synchronized (JavaShareUtil.class) {
                if (singleton == null) {
                    singleton = new JavaShareUtil();
                }
            }
        }
        return singleton;
    }

//    public void initWeiXin(Context context) {
//        if (mWxapi == null) {
//            mWxapi = WXAPIFactory.createWXAPI(context, DataMangerVo.WeixinAPP_ID, true);
//            mWxapi.registerApp(DataMangerVo.WeixinAPP_ID);
//        }
//    }

    public void shareText(Context mContext, String txt) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        // 比如发送文本形式的数据内容
// 指定发送的内容
        sendIntent.putExtra(Intent.EXTRA_TEXT, txt);
// 指定发送内容的类型
        sendIntent.setType("text/plain");
        sendIntent = Intent.createChooser(sendIntent, "分享");
        mContext.startActivity(sendIntent);
    }

    public void shareImage(Context context, Bitmap mbitmap) {
        Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(context.getContentResolver(), mbitmap, null, null));
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");//设置分享内容的类型
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent = Intent.createChooser(intent, "分享");
        context.startActivity(intent);
    }

//    /**
//     *
//     * @param context
//     * @param com
//     * @param firends 是否是朋友圈
//     */
//    public void shareWeiXinText(Context context, String com, boolean firends) {
//        if (mWxapi == null) {
//            throw new NullPointerException("请先调用initWeixin()");
//        }
//        if (StringUtil.isEmpty(com)) {
//            T.showToast(context, "分享内容为空");
//            return;
//        }
//
//        WXTextObject textObject = new WXTextObject();
//        textObject.text = com;
//        WXMediaMessage msg = new WXMediaMessage();
//        msg.mediaObject = textObject;
//        msg.description = com;
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = buildTransaction(com);
//        req.message = msg;
//        req.scene = firends ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
//        mWxapi.sendReq(req);
//    }

    /**
     *
     * @param context
     * @param path
     * @param firends 是否是朋友圈
     */
//    public void shareWeiXinImager(Context context, String path, boolean firends) {
//        if (mWxapi == null) {
//            throw new NullPointerException("请先调用initWeixin()");
//        }
//        if (StringUtil.isEmpty(path)) {
//            T.showToast(context, "图片不存在");
//            return;
//        }
//        Bitmap bitmap = BitmapFactory.decodeFile(path);
//        WXImageObject imgobj = new WXImageObject(bitmap);
//
//        WXMediaMessage msg = new WXMediaMessage();
//        msg.mediaObject = imgobj;
//
//        Bitmap thumbBmp = Bitmap.createScaledBitmap(bitmap, 400, 400, true);
//        bitmap.recycle();
//        msg.thumbData = bmpToByteArrays(thumbBmp,true);
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = buildTransaction("img");
//        req.message = msg;
//        req.scene = firends ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
//        req.userOpenId = DataMangerVo.WeixinAPP_ID;
//        mWxapi.sendReq(req);
//    }

    /**
//     * @param context
//     * @param bitmap
//     * @param firends 是否是朋友圈
     */
//    public void shareWeiXinImager(Context context, Bitmap bitmap, boolean firends) {
//        if (mWxapi == null) {
//            throw new NullPointerException("请先调用initWeixin()");
//        }
//        if (bitmap == null) {
//            T.showToast(context, "图片不存在");
//            return;
//        }
//        WXImageObject imgobj = new WXImageObject(bitmap);
//        WXMediaMessage msg = new WXMediaMessage();
//        msg.mediaObject = imgobj;
//        Bitmap thumbBmp = Bitmap.createScaledBitmap(bitmap, 400, 400, true);
//        bitmap.recycle();
//        msg.thumbData = bmpToByteArrays(thumbBmp,true);
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = buildTransaction("img");
//        req.message = msg;
//        req.scene = firends ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
//        req.userOpenId = DataMangerVo.WeixinAPP_ID;
//        mWxapi.sendReq(req);
//
//    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    //将bitmap转换为byte[]格式
    private byte[] bmpToByteArrays(final Bitmap bitmap, final boolean needRecycle){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
        if(needRecycle){
            bitmap.recycle();
        }
        byte[] result = output.toByteArray();
        try{
            output.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
