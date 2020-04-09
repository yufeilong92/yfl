/*
package com.backpacker.yflLibrary.java.share;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.backpacker.yflLibrary.java.JavaStringUtil;
import com.backpacker.yflLibrary.kotlin.KotlinStringUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.io.File;
import java.util.List;

*/
/**
 * Author     wildma
 * DATE       2017/07/16
 * Des	      ${友盟分享工具类}
 *//*

public class ShareUtils {

    */
/**
     * 分享链接
     *//*

    public static void shareWeb(final Activity activity, String WebUrl, String title, String description, String imageUrl, int imageID, SHARE_MEDIA platform) {
        UMWeb web = new UMWeb(WebUrl);//连接地址
        web.setTitle(KotlinStringUtil.INSTANCE.isEmpty(title) ? "小乐到家" : title);//标题
//        if (platform==SHARE_MEDIA.SINA){
//            boolean wbInstall = isWBInstall(activity);
//            if (!wbInstall){
//                T.showToast(activity,"请先安装微新浪博软件");
//                return;
//            }
//        }
        web.setDescription(JavaStringUtil.isEmpty(description) ? "小乐到家" : description);//描述
        if (TextUtils.isEmpty(imageUrl)) {
            web.setThumb(new UMImage(activity, imageID));  //本地缩略图
        } else {
            web.setThumb(new UMImage(activity, imageUrl));  //网络缩略图
        }
        new ShareAction(activity)
                .setPlatform(platform)
                .withMedia(web)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onResult(final SHARE_MEDIA share_media) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (share_media.name().equals("WEIXIN_FAVORITE")) {
                                    Toast.makeText(activity, share_media + " 收藏成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(activity, share_media + " 分享成功", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                    @Override
                    public void onError(final SHARE_MEDIA share_media, final Throwable throwable) {
                        if (throwable != null) {
                            Log.d("throw", "throw:" + throwable.getMessage());
                        }
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                Toast.makeText(activity, share_media + " 分享失败", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }

                    @Override
                    public void onCancel(final SHARE_MEDIA share_media) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                Toast.makeText(activity, share_media + " 分享取消", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .share();

        //新浪微博中图文+链接
        */
/*new ShareAction(activity)
                .setPlatform(platform)
                .withText(description + " " + WebUrl)
                .withMedia(new UMImage(activity,imageID))
                .share();*//*

    }

    public static void shareImg(final Activity activity, String title, final String imp, SHARE_MEDIA platform) {
        final File file = new File(imp);
        UMImage umImage = new UMImage(activity, file);
//        if (platform==SHARE_MEDIA.SINA){
//            boolean wbInstall = isWBInstall(activity);
//            if (!wbInstall){
//                T.showToast(activity,"请先安装微新浪博软件");
//                return;
//            }
//        }
        new ShareAction(activity)
                .setPlatform(platform)
                .withText(title)
                .withMedia(umImage)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        Log.e("开始", "==========");
                    }

                    @Override
                    public void onResult(final SHARE_MEDIA share_media) {
                        Log.e("结束", "==========");
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if (share_media.name().equals("WEIXIN_FAVORITE")) {
                                    Toast.makeText(activity, share_media + "分享成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(activity, share_media + " 分享成功", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                    @Override
                    public void onError(final SHARE_MEDIA share_media, final Throwable throwable) {
                        if (!JavaStringUtil.isEmpty(imp)) {
                            File file1 = new File(imp);
                            file1.delete();
                        }
                        if (throwable != null) {
                            Log.d("throw", "throw:" + throwable.getMessage());
                        }

                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                Toast.makeText(activity, share_media + " 分享失败", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }

                    @Override
                    public void onCancel(final SHARE_MEDIA share_media) {
                        if (!JavaStringUtil.isEmpty(imp)) {
                            File file1 = new File(imp);
                            file1.delete();
                        }
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                Toast.makeText(activity, share_media + " 分享取消", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .share();
    }

    */
/**
     * 检测是否安装微信
     *
     * @param context
     * @return
     *//*

    public static boolean isWBInstall(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.sina.weibo")) {
                    return true;
                }
            }
        }
        return false;
    }
}
*/
