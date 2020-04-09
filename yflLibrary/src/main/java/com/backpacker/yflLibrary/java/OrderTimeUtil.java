package com.backpacker.yflLibrary.java;

import android.content.Context;
import android.os.Handler;
import android.widget.TextView;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.java
 * @Description: todo
 * @author: L-BackPacker
 * @date: 2019/4/1 0:26
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
public class OrderTimeUtil {
    private Context context;
    private long curTime = 0;
    private static int number = 0;
    private boolean isPause = false;
    private TextView mTimeView;
    private static long MAX_TIME = 0;
    private static OrderTimeUtil timeUitl;

    public OrderTimeUtil(Context context) {
        this.context = context;
    }

    public static OrderTimeUtil getInstance(Context context) {
        if (timeUitl == null)
            timeUitl = new OrderTimeUtil(context);
        return timeUitl;
    }

    /**
     * 开始
     *
     * @param tv
     */
    public void start(TextView tv) {
        this.mTimeView = tv;
        number = 0;
        restart();
    }

    public static long getNubmer() {
        return number * 1000;
    }

    public int getTimeNubmer() {
        return number;
    }

    /**
     * 重新开始
     */
    public void restart() {
        curTime = MAX_TIME;
        number = 0;
        mHandler.removeCallbacks(runnable);
        mHandler.postDelayed(runnable, 1000);
    }


    Handler mHandler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            curTime += 1000;
           number += 1;
            mTimeView.setText(TimeUtil.getCountTimeByLong(curTime));
            if (curTime > 0) {
                mHandler.postDelayed(this, 1000);
            }
        }
    };

    /**
     * 取消
     */
    public void cancel() {
        number = 0;
        mTimeView.setText("00:00:00");
        mHandler.removeCallbacks(runnable);
    }

    /**
     * 暂停
     */
    public void pause() {
        if (!isPause) {
            mHandler.removeCallbacks(runnable);
        }
    }

    /**
     * 继续
     */
    public void resume() {
        mHandler.removeCallbacks(runnable);
        mHandler.postDelayed(runnable, 1000);
    }
}
