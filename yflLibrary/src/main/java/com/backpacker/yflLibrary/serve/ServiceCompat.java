package com.backpacker.yflLibrary.serve;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.text.TextUtils;

import androidx.core.app.NotificationCompat;

import com.example.UtilsLibrary.R;

/**
 * @Author : YFL  is Creating a porject in del
 * @Package
 * @Email : yufeilong92@163.com
 * @Time :2020/8/7 16:08
 * @Purpose :优化通知栏展示
 */
public abstract class ServiceCompat extends Service {
    //判断是否显示通知
    private Boolean isShow = false;

    @Override
    public void onCreate() {
        super.onCreate();
        showNotification();
    }

    protected void showNotification() {
        if (isShow) {
            return;
        }
        isShow = true;
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //适配安卓8.0
            String channelId = getChannelId() + "";
            String channelName = getChannelName();
            NotificationChannel channel = new NotificationChannel(channelId, channelName,
                    NotificationManager.IMPORTANCE_MIN);
            manager.createNotificationChannel(channel);
            startForeground(getChannelId(), getNotification());
        } else {
            manager.notify(getChannelId(), getNotification());
        }
    }

    /**
     * Notification channelName
     */
    protected abstract String getChannelName();

    /**
     * Notification channelId,must not be 0
     */
    protected abstract int getChannelId();

    public Class<?> getStartClass() {
        return null;
    }

    ;


    /**
     * Default content for notification , subclasses can be overwritten and returned
     */
    public String getNotificationContent() {
        return "";
    }


    /**
     * Displayed notifications, subclasses can be overwritten and returned
     */
    public Notification getNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return createNormalNotification(getNotificationContent());
        } else {
            return creteNormalNotificationSmall(getNotificationContent());
        }

    }

    protected Notification creteNormalNotificationSmall(String content) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle(getString(R.string.app_name))
                .setContentText(content)
                .setSmallIcon(getSmallIcon())
                .setWhen(System.currentTimeMillis())
                .setLargeIcon(getLargeIcon())
                .setOngoing(true)
                .setContentIntent(startIntentClass())
                .build();

        return builder.build();
    }

    protected Notification createNormalNotification(String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, getChannelId() + "");
        if (TextUtils.isEmpty(content)) {
            return builder.build();
        }

        builder.setContentTitle(getString(R.string.app_name))
                .setContentText(content)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(getSmallIcon())
                .setOngoing(true)
                .setLargeIcon(getLargeIcon())
                .setContentIntent(startIntentClass())
                .build();

        return builder.build();
    }

    protected PendingIntent startIntentClass() {
        // 通知行为（点击后能进入应用界面）
        Intent intent;
        if (null == getStartClass()) {
            intent = this.getPackageManager().getLaunchIntentForPackage("cn.ruiye.worker");
        } else
            intent = new Intent(this, getStartClass());
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }

    protected void clearNotifucation() {
        isShow = false;
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            manager.deleteNotificationChannel(getChannelId() + "");
        } else {
            manager.cancel(getChannelId());
        }
    }

    /**
     * Large icon for notification , subclasses can be overwritten and returned
     */
    public Bitmap getLargeIcon() {
        return BitmapFactory.decodeResource(getResources(), R.mipmap.ic_logo);
    }

    /**
     * Small icon for notification , subclasses can be overwritten and returned
     */
    public int getSmallIcon() {
        return R.mipmap.ic_logo;
    }


    public static void startService(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //适配安卓8.0
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }
}